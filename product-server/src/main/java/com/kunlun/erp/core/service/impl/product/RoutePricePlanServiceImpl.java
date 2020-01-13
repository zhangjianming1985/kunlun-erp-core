package com.kunlun.erp.core.service.impl.product;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RouteCostCondition;
import com.kunlun.erp.core.dto.product.PricePlanDto;
import com.kunlun.erp.core.dto.product.RoutePriceCostDetailDto;
import com.kunlun.erp.core.dto.product.RoutePricePlanDetailDto;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.dto.routeHall.PriceDateDeleteNotifyDto;
import com.kunlun.erp.core.dto.routeHall.PricePlanDeleteNotifyDto;
import com.kunlun.erp.core.dto.routeHall.PriceUpdateNotifyDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.RoutePricePlanService;
import com.kunlun.erp.core.service.routeHall.RouteHallService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RoutePricePlanServiceImpl
 * @Description 线路产品价格方案服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/16 19:09
 * @Version 1.0
 **/
@Service(value = "route_price_plan_service")
public  class RoutePricePlanServiceImpl extends BaseService implements RoutePricePlanService {
    @Resource
    private RoutePricePlanMapper price_plan_dao;
    @Resource
    private RoutePricePlanDetailMapper price_detail_dao;
    @Resource
    private RoutePlanMapper route_plan_dao;
    @Resource
    private RoutePricePlanCostDetailMapper route_price_cost_dao;
    @Resource(name = "route_hall_service")
    private RouteHallService route_hall_service;
    @Resource
    private RouteOrderMapper order_dao;
    @Resource
    private RouteOrderIncomeMapper order_income_dao;
    @Resource
    private OrderClientMapper order_client_dao;

    @Resource
    private RouteGuidesMapper route_guide_dao;
    @Resource
    private RouteTrafficMapper route_traffic_dao;
    @Resource
    private RouteResideMapper route_reside_dao;
    @Resource
    private RouteTicketMapper route_ticket_dao;
    @Resource
    private RouteMealMapper route_meal_dao;
    @Resource
    private RouteMotorcadeMapper route_motorcade_dao;
    @Resource
    private RouteInsuranceMapper route_insurance_dao;
    @Resource
    private RouteTravelAgencyIncomeMapper route_travel_income_dao;
    @Resource
    private RouteTravelAgencyMapper route_travel_dao;
    @Resource
    private RouteOtherMapper route_other_dao;
    @Resource
    private GroupCodeMapper group_code_dao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<PricePlanAddRespDto> add(PricePlanAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.Product.NAMESPACE);
        AbstractResponse<PricePlanAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        PricePlanAddRespDto resp_body = new PricePlanAddRespDto();
        PricePlanAddReqDto req_body = request.getBody();
        RoutePlan route_plan_record = route_plan_dao.selectByPlanCode(req_body.getPlan_code());
        String price_plan_code=null;
        if (StringUtils.isNotBlank(req_body.getPrice_plan_code())){
            //更新价格方案
            RoutePricePlan price_plan_record = price_plan_dao.selectByPricePlanCode(req_body.getPrice_plan_code());
            RoutePricePlan update_record = new RoutePricePlan();
            update_record.setId(price_plan_record.getId());
            update_record.setRoute_plan_code(route_plan_record.getPlan_code());
            update_record.setPrice_plan_code(price_plan_record.getPrice_plan_code());
            update_record.setPrice_plan_name(req_body.getPrice_plan_name());
            update_record.setUpdate_time(new Date());
            price_plan_dao.updateByPrimaryKeySelective(update_record);
            price_plan_code = update_record.getPrice_plan_code();
            resp_body.setPrice_plan_name(update_record.getPrice_plan_name());
        }else{
            //创建价格方案
            RoutePricePlan new_record = new RoutePricePlan();
            new_record.setPrice_plan_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_price_plan.getValue()));
            new_record.setProduct_code(route_plan_record.getProduct_code());
            new_record.setRoute_plan_code(route_plan_record.getPlan_code());
            new_record.setPrice_plan_name(req_body.getPrice_plan_name());
            new_record.setCreate_time(new Date());
            new_record.setCreator_id(user_info.getBody().getUid());
            new_record.setCreator_name(user_info.getBody().getUserName());
            price_plan_dao.insertSelective(new_record);
            price_plan_code = new_record.getPrice_plan_code();
            resp_body.setPrice_plan_name(new_record.getPrice_plan_name());
        }
        resp_body.setPrice_plan_code(price_plan_code);
        resp_body.setRoute_plan_code(route_plan_record.getPlan_code());

        RoutePricePlanDetailDto price_dto = req_body.getPrice_info();


        //删除这些日期的成本明细
        RouteCostCondition cost_condition = new RouteCostCondition();
        cost_condition.setPrice_plan_code(price_plan_code);
        cost_condition.setDeparture_date(req_body.getDate_info());
        route_price_cost_dao.deleteByCondition(cost_condition);

        //准备通知线路大厅的数据
        List<RoutePricePlanDetail> need_create = new ArrayList<>();
        List<RoutePricePlanDetail> need_update = new ArrayList<>();

        for (String  date_str : req_body.getDate_info()){
            RoutePricePlanDetail price_record = price_detail_dao.selectByPricePlanCodeAndDate(price_plan_code,date_str);
            if (price_record!= null){
                //更新价格
                RoutePricePlanDetail update_record = new RoutePricePlanDetail();
                update_record.setId(price_record.getId());
                update_record.setPrice_code(price_record.getPrice_code());
                update_record.setProduct_code(price_record.getProduct_code());
                update_record.setRoute_code(price_record.getRoute_code());
                update_record.setRoute_plan_code(price_record.getRoute_plan_code());
                update_record.setPrice_plan_code(price_record.getPrice_plan_code());
                update_record.setDeparture_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,date_str));
                update_record.setAdult_trade_price(new BigDecimal(price_dto.getAdult_trade_price()));
                update_record.setChildren_trade_price(new BigDecimal(price_dto.getChildren_trade_price()));
                update_record.setAdult_sales_price(new BigDecimal(price_dto.getAdult_sales_price()));
                update_record.setChildren_sales_price(new BigDecimal(price_dto.getChildren_sales_price()));
                update_record.setRoom_diff_price(new BigDecimal(price_dto.getRoom_diff_price()));
                update_record.setInventory(price_dto.getInventory());
                update_record.setCost_price(new BigDecimal(price_dto.getCost_price()));
                update_record.setUpdate_time(new Date());
                price_detail_dao.updateByPrimaryKeySelective(update_record);
                need_update.add(update_record);
            }else{
                //创建价格
                price_record = new RoutePricePlanDetail();

                GroupCode group_code_record = new GroupCode();
                group_code_record.setProduct_code(route_plan_record.getProduct_code());
                group_code_record.setRoute_code(route_plan_record.getRoute_code());
                group_code_record.setRoute_plan_code(route_plan_record.getPlan_code());
                group_code_record.setPrice_plan_code(price_plan_code);
                group_code_record.setDepartrue_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,date_str));
                group_code_record.setCreate_time(new Date());
                group_code_record.setCreator_id(user_info.getBody().getUid());
                group_code_record.setCreator_name(user_info.getBody().getUserName());
                group_code_dao.insertSelective(group_code_record);
                price_record.setPrice_code(String.valueOf(group_code_record.getId()));
                price_record.setProduct_code(route_plan_record.getProduct_code());
                price_record.setRoute_code(route_plan_record.getRoute_code());
                price_record.setRoute_plan_code(route_plan_record.getPlan_code());
                price_record.setPrice_plan_code(price_plan_code);
                price_record.setDeparture_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,date_str));
                price_record.setAdult_trade_price(new BigDecimal(price_dto.getAdult_trade_price()));
                price_record.setChildren_trade_price(new BigDecimal(price_dto.getChildren_trade_price()));
                price_record.setAdult_sales_price(new BigDecimal(price_dto.getAdult_sales_price()));
                price_record.setChildren_sales_price(new BigDecimal(price_dto.getChildren_sales_price()));
                price_record.setRoom_diff_price(new BigDecimal(price_dto.getRoom_diff_price()));
                price_record.setInventory(price_dto.getInventory());
                price_record.setCost_price(new BigDecimal(price_dto.getCost_price()));
                price_record.setCreate_time(new Date());
                price_record.setCreator_id(user_info.getBody().getUid());
                price_record.setCreator_name(user_info.getBody().getUserName());
                price_detail_dao.insertSelective(price_record);
                need_create.add(price_record);
            }
            //添加成本明细
            if (req_body.getCost_info()!= null && !req_body.getCost_info().isEmpty()){
                for (RoutePriceCostDetailDto cost_dto : req_body.getCost_info()){
                    RoutePricePlanCostDetail new_cost_record = new RoutePricePlanCostDetail();
                    new_cost_record.setCost_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_cost_detail.getValue()));
                    new_cost_record.setProduct_code(route_plan_record.getProduct_code());
                    new_cost_record.setRoute_code(route_plan_record.getRoute_code());
                    new_cost_record.setRoute_plan_code(route_plan_record.getPlan_code());
                    new_cost_record.setPrice_code(price_record.getPrice_code());
                    new_cost_record.setPrice_plan_code(price_plan_code);
                    new_cost_record.setDeparture_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,date_str));
                    new_cost_record.setCost_type(cost_dto.getCost_type());
                    new_cost_record.setCost_description(cost_dto.getCost_description());
                    new_cost_record.setCurrency(cost_dto.getCurrency());
                    new_cost_record.setCost_price(new BigDecimal(cost_dto.getCost_price()));
                    new_cost_record.setQuantity(cost_dto.getQuantity());
                    new_cost_record.setTotal_price(new BigDecimal(cost_dto.getTotal_price()));
                    new_cost_record.setCreate_time(new Date());
                    new_cost_record.setCreator_id(user_info.getBody().getUid());
                    new_cost_record.setCreator_name(user_info.getBody().getUserName());
                    route_price_cost_dao.insertSelective(new_cost_record);
                }
            }

        }
        //通知线路大厅处理数据
        PriceUpdateNotifyDto notify_dto = new PriceUpdateNotifyDto();
        notify_dto.setNeed_create(need_create);
        notify_dto.setNeed_update(need_update);
        route_hall_service.processByPrice(notify_dto);
        response.setBody(resp_body);

        return response;
    }

    @Override
    public AbstractResponse<PricePlanNameUpdateRespDto> updatePricePlanName(PricePlanNameUpdateRequest request) {
        AbstractResponse<PricePlanNameUpdateRespDto> response = dtoFactory.createResponse(request.getHeader());
        RoutePricePlan price_plan_record = price_plan_dao.selectByPricePlanCode(request.getBody().getPrice_plan_code());
        RoutePricePlan update_record = new RoutePricePlan();
        update_record.setId(price_plan_record.getId());
        update_record.setPrice_plan_name(request.getBody().getPrice_plan_name());
        update_record.setUpdate_time(new Date());
        price_plan_dao.updateByPrimaryKeySelective(update_record);
        return response;
    }

    @Override
    public AbstractResponse<PricePlanDetailRespDto> detail(PricePlanDetailRequest request) {
        AbstractResponse<PricePlanDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        PricePlanDetailRespDto resp_body = new PricePlanDetailRespDto();
        List<PricePlanDto> price_plan_list = price_plan_dao.selectDtoByProductCode(request.getBody().getProduct_code());
        for (PricePlanDto price_plan_dto : price_plan_list){
            List<RoutePricePlanDetailDto> price_detail_list = price_detail_dao.selectDtoByPricePlanCode(price_plan_dto.getPrice_plan_code());
            price_plan_dto.setPrice_data(price_detail_list);
        }
        resp_body.setPrice_plan_data(price_plan_list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<PricePlanDelRespDto> deletePricePlan(PricePlanDelRequest request) {
        AbstractResponse<PricePlanDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        PricePlanDelRespDto resp_body = new PricePlanDelRespDto();
        List<String> group_list = price_detail_dao.selectByPricePlanCode(request.getBody().getPrice_plan_code());
        if (group_list!=null && group_list.isEmpty()==false){
            //跟团有关的订单确认款
            int income_count = order_income_dao.deleteByGroupCodeList(group_list);
            resp_body.setOrder_income_count(income_count);
            //删除游客
            int client_count= order_client_dao.deleteByGroupCodeList(group_list);
            resp_body.setOrder_client_count(client_count);
            //删除订单
            int order_count = order_dao.deleteByGroupCodeList(group_list);
            resp_body.setOrder_count(order_count);
            //删除团的导服数据
            int guides_count = route_guide_dao.deleteByGroupCodeList(group_list);
            resp_body.setGuides_count(guides_count);
            //删除交通票务
            int traffic_count = route_traffic_dao.deleteByGroupCodeList(group_list);
            resp_body.setTraffic_count(traffic_count);
            //删除住宿
            int reside_count = route_reside_dao.deleteByGroupCodeList(group_list);
            resp_body.setReside_count(reside_count);
            //删除景点门票
            int ticket_count = route_ticket_dao.deleteByGroupCodeList(group_list);
            resp_body.setTicket_count(ticket_count);
            //删除餐饮
            int meal_count = route_meal_dao.deleteByGroupCodeList(group_list);
            resp_body.setMeal_count(meal_count);
            //删除车队
            int motorcade_count = route_motorcade_dao.deleteByGroupCodeList(group_list);
            resp_body.setMotorcade_count(motorcade_count);
            //删除保险
            int insurance_count = route_insurance_dao.deleteByGroupCodeList(group_list);
            resp_body.setInsurance_count(insurance_count);
            //删除地接报价
            int travel_income_count = route_travel_income_dao.deleteByGroupCodeList(group_list);
            resp_body.setTravel_income_count(travel_income_count);
            //删除地接旅行社
            int travel_count = route_travel_dao.deleteByGroupCodeList(group_list);
            resp_body.setTravel_count(travel_count);
            //删除其他
            int other_count = route_other_dao.deleteByGroupCodeList(group_list);
            resp_body.setOther_count(other_count);
        }

        //删除成本明细
        RouteCostCondition cost_condition = new RouteCostCondition();
        cost_condition.setPrice_plan_code(request.getBody().getPrice_plan_code());
        int del_cost_count = route_price_cost_dao.deleteByCondition(cost_condition);
        resp_body.setDel_cost_count(del_cost_count);
        //删除价格数据
        int del_price_count = price_detail_dao.deleteByPricePlanCode(request.getBody().getPrice_plan_code());
        resp_body.setDel_price_count(del_price_count);
        //删除套餐
        int del_price_plan_count = price_plan_dao.deleteByPricePlanCode(request.getBody().getPrice_plan_code());
        resp_body.setDel_price_plan_count(del_price_plan_count);
        response.setBody(resp_body);

        //准备通知线路大厅的数据
        PricePlanDeleteNotifyDto notify = new PricePlanDeleteNotifyDto();
        notify.setPrice_plan_code(request.getBody().getPrice_plan_code());
        route_hall_service.processByPricePlan(notify);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<PriceDateDelRespDto> deletePriceDate(PriceDateDelRequest request) {
        AbstractResponse<PriceDateDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        PriceDateDelRespDto resp_body = new PriceDateDelRespDto();

        List<String> group_list = price_detail_dao.selectPriceCodeByPricePlanCodeAndDate(request.getBody().getPrice_plan_code(),request.getBody().getDate_info());
        if (group_list!=null && group_list.isEmpty()==false){
            //跟团有关的订单确认款
            int income_count = order_income_dao.deleteByGroupCodeList(group_list);
            resp_body.setOrder_income_count(income_count);

            //删除游客
            int client_count= order_client_dao.deleteByGroupCodeList(group_list);
            resp_body.setOrder_client_count(client_count);

            //删除订单
            int order_count = order_dao.deleteByGroupCodeList(group_list);
            resp_body.setOrder_count(order_count);

            //删除团的导服数据
            int guides_count = route_guide_dao.deleteByGroupCodeList(group_list);
            resp_body.setGuides_count(guides_count);

            //删除交通票务
            int traffic_count = route_traffic_dao.deleteByGroupCodeList(group_list);
            resp_body.setTraffic_count(traffic_count);
            //删除住宿
            int reside_count = route_reside_dao.deleteByGroupCodeList(group_list);
            resp_body.setReside_count(reside_count);
            //删除景点门票
            int ticket_count = route_ticket_dao.deleteByGroupCodeList(group_list);
            resp_body.setTicket_count(ticket_count);
            //删除餐饮
            int meal_count = route_meal_dao.deleteByGroupCodeList(group_list);
            resp_body.setMeal_count(meal_count);
            //删除车队
            int motorcade_count = route_motorcade_dao.deleteByGroupCodeList(group_list);
            resp_body.setMotorcade_count(motorcade_count);
            //删除保险
            int insurance_count = route_insurance_dao.deleteByGroupCodeList(group_list);
            resp_body.setInsurance_count(insurance_count);
            //删除地接报价
            int travel_income_count = route_travel_income_dao.deleteByGroupCodeList(group_list);
            resp_body.setTravel_income_count(travel_income_count);
            //删除地接旅行社
            int travel_count = route_travel_dao.deleteByGroupCodeList(group_list);
            resp_body.setTravel_count(travel_count);
            //删除其他
            int other_count = route_other_dao.deleteByGroupCodeList(group_list);
            resp_body.setOther_count(other_count);
        }

        //删除成本明细
        RouteCostCondition cost_condition = new RouteCostCondition();
        cost_condition.setPrice_plan_code(request.getBody().getPrice_plan_code());
        cost_condition.setDeparture_date(request.getBody().getDate_info());
        int del_cost_count = route_price_cost_dao.deleteByCondition(cost_condition);
        resp_body.setDel_cost_count(del_cost_count);

        //准备通知线路大厅的数据
        PriceDateDeleteNotifyDto notify = new PriceDateDeleteNotifyDto();
        notify.setPrice_plan_code(request.getBody().getPrice_plan_code());
        notify.setDate_info(request.getBody().getDate_info());
        int hall_count = route_hall_service.processByPriceDate(notify);
        resp_body.setHall_count(hall_count);

        //删除价格明细
        int  del_price_count =price_detail_dao.deleteByPricePlanCodeAndDate(request.getBody().getPrice_plan_code(),request.getBody().getDate_info());
        resp_body.setDel_price_count(del_price_count);


        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<PricePlanCostDetailRespDto> costDetail(PricePlanCostDetailRequest request) {
        AbstractResponse<PricePlanCostDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        PricePlanCostDetailRespDto resp_body = new PricePlanCostDetailRespDto();
        List<RoutePriceCostDetailDto> cost_detail =route_price_cost_dao.selectDtoByPricePlanCodeAndDate(request.getBody().getPrice_plan_code(),request.getBody().getDeparture_date());
        resp_body.setCost_detail(cost_detail);
        response.setBody(resp_body);
        return response;
    }
}
