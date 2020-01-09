package com.kunlun.erp.core.service.impl.product;

import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.component.RoutePlanComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RoutePlanCondition;
import com.kunlun.erp.core.dto.product.RoutePlanDto;
import com.kunlun.erp.core.dto.product.RoutePlanShortDto;
import com.kunlun.erp.core.dto.product.request.RoutePlanShortRequest;
import com.kunlun.erp.core.dto.product.response.RoutePlanShortRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RoutePlan;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.RoutePlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RoutePlanServiceImpl
 * @Description 限流产品的行程计划服务实现
 * @Author Jm.zhang
 * @Date 2019/12/10 17:36
 * @Version 1.0
 **/
@Service(value = "route_plan_service")
public class RoutePlanServiceImpl extends BaseService implements RoutePlanService {

    @Resource(name = "component_roue_plan")
    private RoutePlanComponent component_roue_plan;
    @Resource
    private RoutePlanMapper route_plan_dao;
    @Resource
    private RoutePricePlanMapper price_plan_dao;
    @Resource
    private RoutePricePlanDetailMapper price_plan_detail_dao;
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
    private RouteHallMapper hall_dao;

    @Override
    public RoutePlan add(String product_code, String route_code, AbstractResponse<UserInfoRespDto> user_info, RoutePlanDto plan_dto) {
        RoutePlan plan_record = new RoutePlan();
        plan_record.setPlan_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_plan.getValue()));
        plan_record.setRoute_code(route_code);
        plan_record.setProduct_code(product_code);
        plan_record.setRoute_plan_name(plan_dto.getRoute_plan_name());
        plan_record.setIs_default(plan_dto.getIs_default());
        //出发地
        component_roue_plan.BuildDepartureArea(plan_record,plan_dto.getDeparture_area_info());
        //目的地
        component_roue_plan.BuildDestinationArea(plan_record,plan_dto.getDestination_area_info());
        //成团地点
        component_roue_plan.BuildRendezvousArea(plan_record,plan_dto.getRendezvous_area_info());
        plan_record.setDays(plan_dto.getDays());
        plan_record.setNights(plan_dto.getNights());
        plan_record.setFee_contain(plan_dto.getFee_contain());
        plan_record.setFee_not_contain(plan_dto.getFee_not_contain());
        plan_record.setPre_notice(plan_dto.getPre_notice());
        plan_record.setCreate_time(new Date());
        plan_record.setCreator_id(user_info.getBody().getUid());
        plan_record.setCreator_name(user_info.getBody().getUserName());
        int plan_id = route_plan_dao.insertSelective(plan_record);
        return plan_record;
    }

    @Override
    public RoutePlan update(RoutePlanDto plan_dto) {
        RoutePlan plan_record = route_plan_dao.selectByPlanCode(plan_dto.getRoute_plan_code());
        RoutePlan new_plan_record = new RoutePlan();
        new_plan_record.setId(plan_record.getId());
        new_plan_record.setPlan_code(plan_record.getPlan_code());
        if (!plan_dto.getRoute_plan_name().equals(plan_record.getRoute_plan_name())){
            new_plan_record.setRoute_plan_name(plan_dto.getRoute_plan_name());
        }
        if (plan_dto.getIs_default()!=plan_record.getIs_default()){
            new_plan_record.setIs_default(plan_dto.getIs_default());
        }
        if (plan_dto.getDeparture_area_info()!=null){
            component_roue_plan.BuildDepartureArea(new_plan_record,plan_dto.getDeparture_area_info());
        }
        if (plan_dto.getDestination_area_info()!=null){
            component_roue_plan.BuildDestinationArea(new_plan_record,plan_dto.getDestination_area_info());
        }
        if (plan_dto.getRendezvous_area_info()!=null){
            component_roue_plan.BuildRendezvousArea(new_plan_record,plan_dto.getRendezvous_area_info());
        }
        new_plan_record.setDays(plan_dto.getDays());
        new_plan_record.setNights(plan_dto.getNights());
        int update_plan_result = route_plan_dao.updateByPrimaryKeySelective(new_plan_record);
        return new_plan_record;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String product_code,String route_code,List<String> code_list,List<String> will_be_del) {
        //删除计划
        RoutePlanCondition plan_condition = new RoutePlanCondition();
        plan_condition.setPlan_code_include(false);
        plan_condition.setPlan_codes(code_list);
        List<String> product_codes = new ArrayList<>();
        product_codes.add(product_code);
        plan_condition.setProduct_codes(product_codes);
        plan_condition.setProduct_code_include(true);
        List<String> route_codes = new ArrayList<>();
        route_codes.add(route_code);
        plan_condition.setRoute_codes(route_codes);
        plan_condition.setRoute_code_include(true);
        route_plan_dao.deleteByCondition(plan_condition);
        if (will_be_del==null || will_be_del.isEmpty()){
            return;
        }
        //删除团的订单相关、供应商相关数据
        List<String> group_list = price_plan_detail_dao.selectPriceCodeByRoutePlan(will_be_del);
        if (group_list!=null && group_list.isEmpty()==false){
            //跟团有关的订单确认款
            int income_count = order_income_dao.deleteByGroupCodeList(group_list);
            //删除游客
            int client_count= order_client_dao.deleteByGroupCodeList(group_list);
            //删除订单
            int order_count = order_dao.deleteByGroupCodeList(group_list);
            //删除团的导服数据
            int guides_count = route_guide_dao.deleteByGroupCodeList(group_list);
            //删除交通票务
            int traffic_count = route_traffic_dao.deleteByGroupCodeList(group_list);
            //删除住宿
            int reside_count = route_reside_dao.deleteByGroupCodeList(group_list);
            //删除景点门票
            int ticket_count = route_ticket_dao.deleteByGroupCodeList(group_list);
            //删除餐饮
            int meal_count = route_meal_dao.deleteByGroupCodeList(group_list);
            //删除车队
            int motorcade_count = route_motorcade_dao.deleteByGroupCodeList(group_list);
            //删除保险
            int insurance_count = route_insurance_dao.deleteByGroupCodeList(group_list);
            //删除地接报价
            int travel_income_count = route_travel_income_dao.deleteByGroupCodeList(group_list);
            //删除地接旅行社
            int travel_count = route_travel_dao.deleteByGroupCodeList(group_list);
            //删除其他
            int other_count = route_other_dao.deleteByGroupCodeList(group_list);
            //删除大厅数据
            int hall_count = hall_dao.deleteByGroupCode(group_list);
        }
        //删除价格日历数据
        int price_del = price_plan_detail_dao.deleteByRoutePlan(will_be_del);
        //删除价格套餐数据
        int price_plan_del = price_plan_dao.deleteByRoutePlan(will_be_del);


    }

    @Override
    public AbstractResponse<RoutePlanShortRespDto> list(RoutePlanShortRequest request) {
        AbstractResponse<RoutePlanShortRespDto> response = dtoFactory.createResponse(request.getHeader());
        RoutePlanShortRespDto resp_body = new RoutePlanShortRespDto();
        List<RoutePlanShortDto> list = route_plan_dao.selectShortDtoByProductCode(request.getBody().getProduct_code());
        resp_body.setPlan_data(list);
        response.setBody(resp_body);
        return response;
    }
}
