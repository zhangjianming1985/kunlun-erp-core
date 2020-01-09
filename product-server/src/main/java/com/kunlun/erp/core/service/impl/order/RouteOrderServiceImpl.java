package com.kunlun.erp.core.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.component.RouteOrderComponent;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.product.RoutePlanDto;
import com.kunlun.erp.core.dto.product.RouteProductDto;
import com.kunlun.erp.core.dto.product.request.RouteProductDetailReqDto;
import com.kunlun.erp.core.dto.product.request.RouteProductDetailRequest;
import com.kunlun.erp.core.dto.product.response.RouteProductDetailRespDto;
import com.kunlun.erp.core.dto.routeHall.PersonUpdateNotifyDto;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.dto.routeOrder.OrderListDto;
import com.kunlun.erp.core.dto.routeOrder.RouteOrderBaseInfoDto;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDeleteRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDetailRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListRequest;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderAddRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderDeleteRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderDetailRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.order.OrderIncomeService;
import com.kunlun.erp.core.service.order.RouteOrderService;
import com.kunlun.erp.core.service.product.ProductService;
import com.kunlun.erp.core.service.routeHall.RouteHallService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteOrderServiceImpl
 * @Description 线路订单服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/23 12:10
 * @Version 1.0
 **/
@Service(value = "route_order_service")
public class RouteOrderServiceImpl extends BaseService implements RouteOrderService {
    @Resource
    private RouteOrderMapper order_dao;
    @Resource
    private OrderClientMapper order_client_dao;
    @Resource
    private RouteHallMapper hall_dao;
    @Resource
    private CompanyInfoMapper company_dao;
    @Resource
    private PersonInfoMapper person_dao;
    @Resource
    private RoutePricePlanMapper price_plan_dao;
    @Resource(name = "product_service")
    private ProductService product_service;
    @Resource(name = "component_route_order")
    private RouteOrderComponent component_route_order;
    @Resource(name = "route_hall_service")
    private RouteHallService route_hall_service;
    @Resource(name = "route_income_service")
    private OrderIncomeService route_income_service;
    @Resource
    private RouteOrderIncomeMapper income_dao;


    @Override
    public Integer deleteByCompanyCode(String company_code) {
        return order_dao.deleteByCompanyCode(company_code);
    }

    @Override
    public AbstractResponse<RouteOrderDetailRespDto> detail(RouteOrderDetailRequest request) {
        AbstractResponse<RouteOrderDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteOrderDetailRespDto resp_body = new RouteOrderDetailRespDto();
        RouteOrderBaseInfoDto order_data = new RouteOrderBaseInfoDto();
        RouteProductDto product_data = new RouteProductDto();
        //订单记录
        RouteOrder order_record = order_dao.selectByOrderCode(request.getBody().getOrder_code());
        //大厅产品记录
        RouteHall hall_record = hall_dao.selectByGroupCode(order_record.getGroup_code());
        //销售渠道
        CompanyInfo company_record =company_dao.selectByCompanyCode(order_record.getCompany_code());
        //联系人
        PersonInfo person_record = person_dao.selectByPersonCode(order_record.getContact_code());
        order_data.setOrder_code(order_record.getOrder_code());
        order_data.setGroup_code(order_record.getGroup_code());
        order_data.setDeparture_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,hall_record.getDeparture_date()));
        order_data.setDisband_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,hall_record.getDisband_date()));
        order_data.setCompany_code(company_record.getCompany_code());
        order_data.setCompany_name(company_record.getCompany_name());
        order_data.setContact_code(person_record.getPerson_code());
        order_data.setContact_name(person_record.getPerson_name());
        order_data.setContact_mobile(person_record.getPerson_mobile());
        order_data.setContact_phone(person_record.getPerson_phone());
        order_data.setCompany_order_code(order_record.getCompany_order_code());
        //客源地
        AreaDto area_info = new AreaDto();
        area_info.setCountry_id(order_record.getClient_country_id());
        area_info.setDistrict_id(order_record.getClient_district_id());
        area_info.setProvince_id(order_record.getClient_province_id());
        area_info.setCity_id(order_record.getClient_city_id());
        area_info.setCounty_id(order_record.getClient_county_id());
        order_data.setClient_area(area_info);
        order_data.setClient_area_str(component_route_order.clientAreaToStr(order_record));
        order_data.setHotel_level(order_record.getHotel_level());
        order_data.setRoom_standard_count(order_record.getRoom_standard_count());
        order_data.setRoom_big_count(order_record.getRoom_big_count());
        order_data.setRoom_three_count(order_record.getRoom_three_count());
        order_data.setAccompany_room_count(order_record.getAccompany_room_count());
        order_data.setNo_room_count(order_record.getNo_room_count());
        order_data.setAdult_count(order_record.getAdult_count());
        order_data.setChildren_count(order_record.getChildren_count());
        order_data.setState(order_record.getState());
        order_data.setRemarks(order_record.getRemarks());
        order_data.setAudit_state(hall_record.getApprove_state());
        //价格套餐
        RoutePricePlan price_plan_record = price_plan_dao.selectByPricePlanCode(hall_record.getPrice_plan_code());
        order_data.setPrice_plan_name(price_plan_record.getPrice_plan_name());

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
        product_data.setProduct_code(product_detail_response.getBody().getProduct_code());
        product_data.setProduct_category_code(product_detail_response.getBody().getProduct_category_code());
        product_data.setProduct_name(product_detail_response.getBody().getProduct_name());
        product_data.setProduct_description(product_detail_response.getBody().getProduct_description());
        product_data.setArea_info(product_detail_response.getBody().getArea_info());
        product_data.setArea_str(product_detail_response.getBody().getArea_str());
        product_data.setRoute_base_info(product_detail_response.getBody().getRoute_base_info());
        product_data.setRoute_plan_info(product_detail_response.getBody().getRoute_plan_info());
        //移除无用行程方案
        for (RoutePlanDto route_plan_dto : product_data.getRoute_plan_info()){
            if (!route_plan_dto.getRoute_plan_code().equals(hall_record.getRoute_plan_code())){
                product_data.getRoute_plan_info().remove(route_plan_dto);
            }
        }
        //团款
        List<OrderIncomeDto> income_data = income_dao.selectDtoByOrderCode(request.getBody().getOrder_code());
        resp_body.setIncome_data(income_data);
        resp_body.setOrder_data(order_data);
        resp_body.setProduct_data(product_data);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteOrderAddRespDto> add(RouteOrderAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteOrder.NAMESPACE);
        AbstractResponse<RouteOrderAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteOrderAddRespDto resp_body = new RouteOrderAddRespDto();
        RouteHall hall_record = hall_dao.selectByGroupCode(request.getBody().getGroup_code());

        if (StringUtils.isBlank(request.getBody().getOrder_code())){
            //创建
            RouteOrder order_record = new RouteOrder();
            order_record.setOrder_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_order.getValue()));
            order_record.setGroup_code(hall_record.getGroup_code());
            order_record.setProduct_code(hall_record.getProduct_code());
            order_record.setRoute_plan_code(hall_record.getRoute_plan_code());
            order_record.setPrice_plan_code(hall_record.getPrice_plan_code());
            order_record.setCompany_code(request.getBody().getCompany_code());
            order_record.setContact_code(request.getBody().getContact_code());
            order_record.setCompany_order_code(request.getBody().getCompany_order_code());
            component_route_order.BuildClientArea(order_record,request.getBody().getClient_area());
            order_record.setHotel_level(request.getBody().getHotel_level());
            order_record.setRoom_standard_count(request.getBody().getRoom_standard_count());
            order_record.setRoom_big_count(request.getBody().getRoom_big_count());
            order_record.setRoom_three_count(request.getBody().getRoom_three_count());
            order_record.setAccompany_room_count(request.getBody().getAccompany_room_count());
            order_record.setNo_room_count(request.getBody().getNo_room_count());
            order_record.setAdult_count(request.getBody().getAdult_count());
            order_record.setChildren_count(request.getBody().getChildren_count());
            order_record.setState(request.getBody().getState());
            order_record.setRemarks(request.getBody().getRemarks());
            order_record.setCreate_time(new Date());
            order_record.setCreator_id(user_info.getBody().getUid());
            order_record.setCreator_name(user_info.getBody().getUserName());
            int insert_count = order_dao.insertSelective(order_record);
            resp_body.setOrder_code(order_record.getOrder_code());
            //报名大厅人数变动
            if (order_record.getState()!=SysConstant.RouteOrderState.cancel.getValue()){
                //非取消状态
                int total_person = order_record.getAdult_count()+order_record.getChildren_count();
                PersonUpdateNotifyDto notify_dto = new PersonUpdateNotifyDto();
                notify_dto.setGroup_code(order_record.getGroup_code());
                notify_dto.setUpdate_time(new Date());
                notify_dto.setRemain_count(-total_person);
                if (order_record.getState() == SysConstant.RouteOrderState.confirm.getValue()){
                    notify_dto.setConfirm_count(total_person);
                }else if (order_record.getState() == SysConstant.RouteOrderState.hold.getValue()){
                    notify_dto.setHold_count(total_person);
                }
                route_hall_service.processByPerson(notify_dto);
            }
            //处理团款
            route_income_service.add(order_record, user_info,request.getBody().getIncome_data());

        }else{
            //更新
            RouteOrder old_record = order_dao.selectByOrderCode(request.getBody().getOrder_code());
            RouteOrder order_record = new RouteOrder();
            order_record.setId(old_record.getId());
            order_record.setOrder_code(old_record.getOrder_code());
            order_record.setGroup_code(old_record.getGroup_code());
            order_record.setCompany_code(old_record.getCompany_code());
            if (!request.getBody().getCompany_code().equals(old_record.getCompany_code())){
                order_record.setCompany_code(request.getBody().getCompany_code());
            }
            if (!request.getBody().getContact_code().equals(old_record.getContact_code())){
                order_record.setContact_code(request.getBody().getContact_code());
            }
            if (StringUtils.isNotBlank(request.getBody().getCompany_order_code())){
                if (StringUtils.isBlank(old_record.getCompany_order_code()) || !request.getBody().getCompany_order_code().equals(old_record.getCompany_order_code())){
                    order_record.setCompany_order_code(request.getBody().getCompany_order_code());
                }
            }
            component_route_order.BuildClientArea(order_record,request.getBody().getClient_area());
            if (request.getBody().getHotel_level()!=old_record.getHotel_level()){
                order_record.setHotel_level(request.getBody().getHotel_level());
            }
            if (request.getBody().getRoom_standard_count() != old_record.getRoom_standard_count()){
                order_record.setRoom_standard_count(request.getBody().getRoom_standard_count());
            }
            if (request.getBody().getRoom_big_count()!=old_record.getRoom_big_count()){
                order_record.setRoom_big_count(request.getBody().getRoom_big_count());
            }
            if (request.getBody().getRoom_three_count() != old_record.getRoom_three_count()){
                order_record.setRoom_three_count(request.getBody().getRoom_three_count());
            }
            if (request.getBody().getAccompany_room_count() != old_record.getAccompany_room_count()){
                order_record.setAccompany_room_count(request.getBody().getAccompany_room_count());
            }
            if (request.getBody().getNo_room_count()!= old_record.getNo_room_count()){
                order_record.setNo_room_count(request.getBody().getNo_room_count());
            }
            if (request.getBody().getAdult_count() != old_record.getAdult_count()){
                order_record.setAdult_count(request.getBody().getAdult_count());
            }
            if (request.getBody().getChildren_count()!= old_record.getChildren_count()){
                order_record.setChildren_count(request.getBody().getChildren_count());
            }
            if (request.getBody().getState()!=old_record.getState()){
                order_record.setState(request.getBody().getState());
            }
            if (StringUtils.isNotBlank(request.getBody().getRemarks())){
                if (StringUtils.isBlank(old_record.getRemarks()) || !request.getBody().getRemarks().equals(old_record.getRemarks())){
                    order_record.setRemarks(request.getBody().getRemarks());
                }
            }
            order_record.setUpdate_time(new Date());
            int update_count = order_dao.updateByPrimaryKeySelective(order_record);
            resp_body.setOrder_code(old_record.getOrder_code());

            //原先总人数
            int origin_total= old_record.getAdult_count()+old_record.getChildren_count();
            //当前总人数
            int current_total = request.getBody().getAdult_count()+request.getBody().getChildren_count();
            if (origin_total != current_total || old_record.getState()!=request.getBody().getState()){
                //人数变化 或者 状态发生变化 都将引起大厅数据变化
                //清除之前的数据
                if (old_record.getState()==SysConstant.RouteOrderState.confirm.getValue()){
                    PersonUpdateNotifyDto notify_dto = new PersonUpdateNotifyDto();
                    notify_dto.setGroup_code(request.getBody().getGroup_code());
                    notify_dto.setUpdate_time(new Date());
                    //减掉确认位
                    notify_dto.setConfirm_count(-origin_total);
                    //增加余位
                    notify_dto.setRemain_count(origin_total);
                    route_hall_service.processByPerson(notify_dto);
                }else if (old_record.getState()==SysConstant.RouteOrderState.hold.getValue()){
                    PersonUpdateNotifyDto notify_dto = new PersonUpdateNotifyDto();
                    notify_dto.setGroup_code(request.getBody().getGroup_code());
                    notify_dto.setUpdate_time(new Date());
                    //减掉占位
                    notify_dto.setHold_count(-origin_total);
                    //增加余位
                    notify_dto.setRemain_count(origin_total);
                    route_hall_service.processByPerson(notify_dto);
                }
                //重新分配数据
                if (request.getBody().getState()!=SysConstant.RouteOrderState.cancel.getValue()){
                    //非取消
                    PersonUpdateNotifyDto _notify_dto = new PersonUpdateNotifyDto();
                    _notify_dto.setGroup_code(order_record.getGroup_code());
                    _notify_dto.setUpdate_time(new Date());
                    if (order_record.getState() == SysConstant.RouteOrderState.confirm.getValue()){
                        _notify_dto.setConfirm_count(current_total);
                        _notify_dto.setRemain_count(-current_total);
                    }else if (order_record.getState() == SysConstant.RouteOrderState.hold.getValue()){
                        _notify_dto.setHold_count(current_total);
                        _notify_dto.setRemain_count(-current_total);
                    }
                    route_hall_service.processByPerson(_notify_dto);
                }

            }
            //处理团款
            route_income_service.add(order_record, user_info,request.getBody().getIncome_data());

        }
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<RouteOrderListRespDto> list(RouteOrderListRequest request) {
        AbstractResponse<RouteOrderListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteOrderListRespDto resp_body = new RouteOrderListRespDto();
        PageHelper.startPage(request.getBody().getPage_index(), request.getBody().getPage_size(), true);
        List<OrderListDto> list = order_dao.selectListDtoByCondition(request.getBody());
        PageInfo<OrderListDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteOrderDeleteRespDto> delete(RouteOrderDeleteRequest request) {
        AbstractResponse<RouteOrderDeleteRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteOrderDeleteRespDto body = new RouteOrderDeleteRespDto();
        RouteOrder order_record = order_dao.selectByOrderCode(request.getBody().getOrder_code());
        //原先总人数
        int origin_total= order_record.getAdult_count()+order_record.getChildren_count();
        //人数变化引起大厅数据变化
        //清除之前的数据
        if (order_record.getState()==SysConstant.RouteOrderState.confirm.getValue()){
            PersonUpdateNotifyDto notify_dto = new PersonUpdateNotifyDto();
            notify_dto.setGroup_code(order_record.getGroup_code());
            notify_dto.setUpdate_time(new Date());
            //减掉确认位
            notify_dto.setConfirm_count(-origin_total);
            //增加余位
            notify_dto.setRemain_count(origin_total);
            route_hall_service.processByPerson(notify_dto);
        }else if (order_record.getState()==SysConstant.RouteOrderState.hold.getValue()){
            PersonUpdateNotifyDto notify_dto = new PersonUpdateNotifyDto();
            notify_dto.setGroup_code(order_record.getGroup_code());
            notify_dto.setUpdate_time(new Date());
            //减掉占位
            notify_dto.setHold_count(-origin_total);
            //增加余位
            notify_dto.setRemain_count(origin_total);
            route_hall_service.processByPerson(notify_dto);
        }

        //删除订单的出游人数据
        int client_count = order_client_dao.deleteByOrderCode(order_record.getOrder_code());
        body.setClient_count(client_count);
        //删除团款
        int income_count = income_dao.deleteByOrderCode(order_record.getOrder_code());
        body.setIncome_count(income_count);
        //删除订单
        int order_count = order_dao.deleteByOrderCode(order_record.getOrder_code());
        body.setOrder_count(order_count);
        response.setBody(body);
        return response;
    }
}
