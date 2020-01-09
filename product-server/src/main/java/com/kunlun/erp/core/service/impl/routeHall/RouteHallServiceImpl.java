package com.kunlun.erp.core.service.impl.routeHall;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.component.RouteHallComponent;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RouteHallCondition;
import com.kunlun.erp.core.dto.product.RoutePlanDto;
import com.kunlun.erp.core.dto.product.request.RouteProductDetailReqDto;
import com.kunlun.erp.core.dto.product.request.RouteProductDetailRequest;
import com.kunlun.erp.core.dto.product.response.RouteProductDetailRespDto;
import com.kunlun.erp.core.dto.routeHall.*;
import com.kunlun.erp.core.dto.routeHall.request.HallProductDetailRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductListRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductStateUpdateRequest;
import com.kunlun.erp.core.dto.routeHall.response.HallProductDetailRespDto;
import com.kunlun.erp.core.dto.routeHall.response.HallProductListRespDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.ProductService;
import com.kunlun.erp.core.service.routeHall.RouteHallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteHallServiceImpl
 * @Description 报名大厅服务实现
 * @Author Jm.zhang
 * @Date 2019/12/18 12:12
 * @Version 1.0
 **/
@Service(value = "route_hall_service")
public class RouteHallServiceImpl extends BaseService implements RouteHallService {
    @Resource
    private RouteHallMapper route_hall_dao;
    @Resource
    private ProductInfoMapper product_dao;
    @Resource
    private RouteInfoMapper route_dao;
    @Resource
    private RoutePricePlanMapper price_plan_dao;
    @Resource
    private RoutePlanMapper route_plan_dao;
    @Resource(name = "route_hall_component")
    private RouteHallComponent route_hall_component;
    @Resource(name = "product_service")
    private ProductService product_service;
    @Resource
    private RouteOrderMapper order_dao;


    /**
     * 价格日历变动引起的修改
     * @param notify_dto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void processByPrice(PriceUpdateNotifyDto notify_dto) {
        if (notify_dto.getNeed_create().isEmpty()==false){
            List<RoutePricePlanDetail> need_create = notify_dto.getNeed_create();
            for (RoutePricePlanDetail price_detail :need_create){
                this.create(price_detail);
            }
        }
        if (notify_dto.getNeed_update().isEmpty()==false){
            List<RoutePricePlanDetail> need_update = notify_dto.getNeed_update();
            for (RoutePricePlanDetail price_detail :need_update){
                this.updateByPrice(price_detail);
            }
        }
    }

    /**
     * 产品变动引起的修改
     * @param notify_dto
     */
    @Override
    public void processByProduct(ProductUpdateNotifyDto notify_dto) {
        //更新产品名称、内部代码
        int update_count = route_hall_dao.updateByProductCode(notify_dto);
    }

    /**
     * 删除价格方案引起的修改
     * @param notify_dto
     */
    @Override
    public void processByPricePlan(PricePlanDeleteNotifyDto notify_dto) {
        int delete_count = route_hall_dao.deleteByPricePlanCode(notify_dto.getPrice_plan_code());

    }

    /**
     * 删除日期引起的修改
     * @param notify_dto
     */
    @Override
    public int processByPriceDate(PriceDateDeleteNotifyDto notify_dto) {
        int hall_count = route_hall_dao.deleteByPricePlanCodeAndDate(notify_dto.getPrice_plan_code(),notify_dto.getDate_info());
        return hall_count;
    }

    @Override
    public void processByPerson(PersonUpdateNotifyDto notify_dto) {
        RouteHall hall_record = route_hall_dao.selectByGroupCode(notify_dto.getGroup_code());
        RouteHall  record = new RouteHall();
        record.setId(hall_record.getId());
        record.setUpdate_time(notify_dto.getUpdate_time());
        if (notify_dto.getConfirm_count()!=null){
            record.setCount_confirm(hall_record.getCount_confirm() + notify_dto.getConfirm_count());
        }
        if (notify_dto.getHold_count()!=null){
            record.setCount_hold(hall_record.getCount_hold()+notify_dto.getHold_count());
        }
        if (notify_dto.getRemain_count()!=null){
            record.setCount_remain(hall_record.getCount_remain()+notify_dto.getRemain_count());
        }
        route_hall_dao.updateByPrimaryKeySelective(record);

    }

    @Override
    public AbstractResponse<HallProductListRespDto> list(HallProductListRequest request) {
        AbstractResponse<HallProductListRespDto> response = dtoFactory.createResponse(request.getHeader());
        HallProductListRespDto resp_body = new HallProductListRespDto();
        RouteHallCondition condition  = route_hall_component.convert(request);
        PageHelper.startPage(condition.getPage_index(), condition.getPage_size(), true);
        List<RouteHallDto> list = route_hall_dao.selectDtoByCondition(condition);
        PageInfo<RouteHallDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }


    @Override
    public AbstractResponse<HallProductDetailRespDto> detail(HallProductDetailRequest request) {
        AbstractResponse<HallProductDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        HallProductDetailRespDto resp_body = new HallProductDetailRespDto();
        //大厅团数据
        RouteHall hall_record = route_hall_dao.selectByGroupCode(request.getBody().getGroup_code());
        resp_body.setGroup_code(hall_record.getGroup_code());
        resp_body.setDeparture_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,hall_record.getDeparture_date()));
        resp_body.setDisband_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,hall_record.getDisband_date()));
        //人数统计
        PeopleCount people_count = order_dao.peopleCountByGroupCode(hall_record.getGroup_code());
        resp_body.setAdult_count(people_count.getAdult_count());
        resp_body.setChildren_count(people_count.getChildren_count());
        //用房统计
        RoomCount room_count = order_dao.roomCountByGroupCode(hall_record.getGroup_code());
        resp_body.setRoom_standard_count(room_count.getRoom_standard_count());
        resp_body.setRoom_big_count(room_count.getRoom_big_count());
        resp_body.setRoom_three_count(room_count.getRoom_three_count());
        resp_body.setAccompany_room_count(room_count.getAccompany_room_count());
        resp_body.setNo_room_count(room_count.getNo_room_count());
        resp_body.setHotel_level(room_count.getHotel_level());

        //价格套餐
        RoutePricePlan price_plan_record = price_plan_dao.selectByPricePlanCode(hall_record.getPrice_plan_code());
        resp_body.setPrice_plan_name(price_plan_record.getPrice_plan_name());
        //封装请求，发送到产品服务接口 获取产品详情
        AbstractRequest<RouteProductDetailReqDto> abs_product_detail_request = dtoFactory.createRequest();
        abs_product_detail_request.setHeader(request.getHeader());
        RouteProductDetailReqDto product_detail_request_body =  new RouteProductDetailReqDto();
        product_detail_request_body.setProduct_code(hall_record.getProduct_code());
        abs_product_detail_request.setBody(product_detail_request_body);
        RouteProductDetailRequest product_detail_request = new RouteProductDetailRequest();
        product_detail_request.setHeader(abs_product_detail_request.getHeader());
        product_detail_request.setBody(abs_product_detail_request.getBody());
        AbstractResponse<RouteProductDetailRespDto> product_detail_response = product_service.detail(product_detail_request);
        //封装产品详情
        resp_body.setProduct_code(product_detail_response.getBody().getProduct_code());
        resp_body.setProduct_category_code(product_detail_response.getBody().getProduct_category_code());
        resp_body.setProduct_name(product_detail_response.getBody().getProduct_name());
        resp_body.setProduct_description(product_detail_response.getBody().getProduct_description());
        resp_body.setArea_info(product_detail_response.getBody().getArea_info());
        resp_body.setArea_str(product_detail_response.getBody().getArea_str());
        resp_body.setRoute_base_info(product_detail_response.getBody().getRoute_base_info());
        resp_body.setRoute_plan_info(product_detail_response.getBody().getRoute_plan_info());
        //移除无用行程方案
        List<RoutePlanDto> route_plan_info = new ArrayList<>();
        for (RoutePlanDto route_plan_dto : product_detail_response.getBody().getRoute_plan_info()){
            if (route_plan_dto.getRoute_plan_code().equals(hall_record.getRoute_plan_code())){
                route_plan_info.add(route_plan_dto);
            }
        }
        resp_body.setRoute_plan_info(route_plan_info);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse updateState(HallProductStateUpdateRequest request) {
        AbstractResponse response = dtoFactory.createResponse(request.getHeader());
        RouteHall hall_record = route_hall_dao.selectByGroupCode(request.getBody().getGroup_code());
        RouteHall  record = new RouteHall();
        record.setId(hall_record.getId());
        if (request.getBody().getState()==5){
            record.setStatus(route_hall_component.getProductStatus(hall_record.getDeparture_date(),hall_record.getDisband_date()));

        }else{
            record.setStatus(request.getBody().getState());
        }

        record.setUpdate_time(new Date());
        route_hall_dao.updateByPrimaryKeySelective(record);
        return response;
    }



    /**
     * 日历数据变动  创建新的大厅数据
     * @param price_detail
     */
    private void create(RoutePricePlanDetail price_detail){
        //线路信息表
        RouteInfo route_record = route_dao.selectByProductCode(price_detail.getProduct_code());
        //行程计划
        RoutePlan route_plan_record = route_plan_dao.selectByPlanCode(price_detail.getRoute_plan_code());
        //产品
        ProductInfo product_record = product_dao.selectByProductCode(price_detail.getProduct_code());
        RouteHall hall_record = new RouteHall();
        hall_record.setProduct_code(price_detail.getProduct_code());
        hall_record.setInternal_code(route_record.getInternal_code());
        hall_record.setRoute_plan_code(price_detail.getRoute_plan_code());
        hall_record.setPrice_plan_code(price_detail.getPrice_plan_code());
        hall_record.setGroup_code(price_detail.getPrice_code());
        hall_record.setDeparture_date(price_detail.getDeparture_date());
        String disband_date = route_hall_component.getDisbandDate(price_detail.getDeparture_date(),route_plan_record.getDays());
        hall_record.setDisband_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,disband_date));
        hall_record.setProduct_name(product_record.getProduct_name());
        hall_record.setCount_plan(price_detail.getInventory());
        hall_record.setCount_confirm(0);
        hall_record.setCount_hold(0);
        hall_record.setCount_remain(price_detail.getInventory());
        hall_record.setStatus(route_hall_component.getProductStatus(price_detail.getDeparture_date(),hall_record.getDisband_date()));
        hall_record.setCreate_time(new Date());
        hall_record.setCreator_id(price_detail.getCreator_id());
        hall_record.setCreator_name(price_detail.getCreator_name());
       int count_insert= route_hall_dao.insertSelective(hall_record);
    }

    /**
     * 日历价格数据变动，影响的报名大厅数据
     * @param price_detail
     */
    private void updateByPrice(RoutePricePlanDetail price_detail){
        RouteHall hall_record = route_hall_dao.selectByGroupCode(price_detail.getPrice_code());
        RouteHall update_hall_record = new RouteHall();
        update_hall_record.setId(hall_record.getId());
        update_hall_record.setUpdate_time(new Date());
        if (price_detail.getInventory() != hall_record.getCount_plan()){
            //计划人数变化
            update_hall_record.setCount_plan(price_detail.getInventory());
            //余位变化
            int remain_count = price_detail.getInventory() - (hall_record.getCount_confirm() + hall_record.getCount_hold());
            if (remain_count < 0) remain_count=0;
            update_hall_record.setCount_remain(remain_count);
        }
        if (!price_detail.getRoute_plan_code().equalsIgnoreCase(hall_record.getRoute_plan_code())){
            update_hall_record.setRoute_plan_code(price_detail.getRoute_plan_code());
        }
        route_hall_dao.updateByPrimaryKeySelective(update_hall_record);
    }
}
