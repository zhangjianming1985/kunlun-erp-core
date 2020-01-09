package com.kunlun.erp.core.service.impl.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.component.ProductComponent;
import com.kunlun.erp.core.component.RoutePlanComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.condition.ProductCondition;
import com.kunlun.erp.core.dto.product.*;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.dto.routeHall.ProductUpdateNotifyDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.*;
import com.kunlun.erp.core.service.routeHall.RouteHallService;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description 产品服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/9 18:29
 * @Version 1.0
 **/
@Service(value = "product_service")
public class ProductServiceImpl extends BaseService implements ProductService {
    @Resource(name = "route_service")
    private RouteService route_service;
    @Resource(name = "route_plan_service")
    private RoutePlanService route_plan_service;
    @Resource(name = "route_plan_node_service")
    private RoutePlanNodeService route_plan_node_service;
    @Resource(name = "route_plan_price_service")
    private RoutePlanPriceService route_plan_price_service;
    @Resource
    private RouteOrderMapper order_dao;
    @Resource(name = "area_validator")
    protected AreaValidator area_validator;
    @Resource(name = "component_product")
    private ProductComponent component_product;
    @Resource(name = "component_roue_plan")
    private RoutePlanComponent component_route_plan;

    @Resource
    private ProductInfoMapper product_dao;
    @Resource
    private RouteInfoMapper route_dao;
    @Resource
    private RoutePlanMapper route_plan_dao;

    @Resource
    private RoutePlanNodeMapper route_plan_node_dao;
    @Resource
    private RoutePlanBasePriceMapper route_plan_price_dao;
    @Resource(name = "route_hall_service")
    private RouteHallService route_hall_service;
    @Resource
    private RouteHallMapper hall_dao;
    @Resource
    private OrderClientMapper client_dao;
    @Resource
    private RouteOrderIncomeMapper order_income_dao;
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
    private RoutePricePlanDetailMapper price_plan_detail_dao;
    @Resource
    private RoutePricePlanMapper price_plan_dao;
    @Resource
    private RoutePricePlanCostDetailMapper price_plan_cost_dao;




    @Override
    public AbstractResponse<RouteProductListRespDto> list(RouteProductListRequest request) {
        AbstractResponse<RouteProductListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteProductListRespDto resp_body = new RouteProductListRespDto();
        ProductCondition condition = component_product.convert(request);
        PageHelper.startPage(condition.getPage_index(), condition.getPage_size(), true);
        List<RouteProductListDto> list = product_dao.selectByCondition(condition);
        PageInfo<RouteProductListDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteProductAddRespDto> add(RouteProductAddRequest request) {
        AbstractResponse<RouteProductAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteProductAddRespDto resp_body = new RouteProductAddRespDto();
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.Product.NAMESPACE);
        RouteProductDto req_body = request.getBody();
        //产品主表
        ProductInfo product_record = new ProductInfo();
        product_record.setProduct_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.product.getValue()));
        product_record.setProduct_name(req_body.getProduct_name());
        product_record.setProduct_description(req_body.getProduct_description());
        product_record.setProduct_category_code(req_body.getProduct_category_code());
        component_product.BuildArea(product_record,req_body.getArea_info());
        product_record.setCreate_time(new Date());
        product_record.setCreator_id(user_info.getBody().getUid());
        product_record.setCreator_name(user_info.getBody().getUserName());
        int product_id = product_dao.insertSelective(product_record);
        resp_body.setProduct_code(product_record.getProduct_code());
        //线路表
        RouteInfo route_record = route_service.add(product_record.getProduct_code(),user_info,req_body.getRoute_base_info());
        List<RoutePlanShortDto> plan_dto_short_list = new ArrayList<>();
        //线路计划
        for (RoutePlanDto plan_dto : req_body.getRoute_plan_info()){
            RoutePlan plan_record  = route_plan_service.add(product_record.getProduct_code(),route_record.getRoute_code(),user_info,plan_dto);
            RoutePlanShortDto route_plan_shot_dto = new RoutePlanShortDto();
            route_plan_shot_dto.setRoute_plan_code(plan_record.getPlan_code());
            route_plan_shot_dto.setRoute_plan_name(plan_record.getRoute_plan_name());
            plan_dto_short_list.add(route_plan_shot_dto);
            //线路计划里的节点
            for (RoutePlanNodeDto node_dto : plan_dto.getPlan_node_info()){
                RoutePlanNode node_record = route_plan_node_service.add(plan_record.getPlan_code(),user_info,node_dto);
            }

            //线路计划里的价格
            for (RoutePlanBasePriceDto price_dto : plan_dto.getPlan_price_info()){
                RoutePlanBasePrice price_record = route_plan_price_service.add(product_record.getProduct_code(),route_record.getRoute_code(),plan_record.getPlan_code(),user_info,price_dto);
            }
        }
        resp_body.setRoute_plan_list(plan_dto_short_list);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<RouteProductDetailRespDto> detail(RouteProductDetailRequest request) {
        AbstractResponse<RouteProductDetailRespDto> response= dtoFactory.createResponse(request.getHeader());
        RouteProductDetailRespDto resp_body = new RouteProductDetailRespDto();
        ProductInfo product_record = product_dao.selectByProductCode(request.getBody().getProduct_code());
        resp_body.setProduct_code(product_record.getProduct_code());
        resp_body.setProduct_category_code(product_record.getProduct_category_code());
        resp_body.setProduct_name(product_record.getProduct_name());
        resp_body.setProduct_description(product_record.getProduct_description());
        //产品区域
        AreaDto area_info = new AreaDto();
        area_info.setCountry_id(product_record.getCountry_id());
        area_info.setDistrict_id(product_record.getDistrict_id());
        area_info.setProvince_id(product_record.getProvince_id());
        area_info.setCity_id(product_record.getCity_id());
        area_info.setCounty_id(product_record.getCounty_id());
        resp_body.setArea_info(area_info);
        resp_body.setArea_str(component_product.areaToStr(product_record));
        //线路基础信息
        RouteInfo route_record = route_dao.selectByProductCode(product_record.getProduct_code());
        RouteBaseDto route_base_info = new RouteBaseDto();
        route_base_info.setInternal_code(route_record.getInternal_code()==null?"":route_record.getInternal_code());
        route_base_info.setIs_shopping(route_record.getIs_shopping());
        route_base_info.setOwner_expense(route_record.getOwner_expense());
        route_base_info.setRoom_type(route_record.getRoom_type());
        route_base_info.setInsurance_type(route_record.getInsurance_type());
        resp_body.setRoute_base_info(route_base_info);
        //线路行程
        List<RoutePlanDto> route_plan_info = new ArrayList<>();
        List<RoutePlan> plan_list_record = route_plan_dao.selectByRouteCode(route_record.getRoute_code());
        for (RoutePlan plan_record : plan_list_record){
            RoutePlanDto plan_dto = new RoutePlanDto();
            plan_dto.setRoute_plan_code(plan_record.getPlan_code());
            plan_dto.setRoute_plan_name(plan_record.getRoute_plan_name());
            plan_dto.setIs_default(plan_record.getIs_default());
            //出发地
            AreaDto departure_area_info = new AreaDto();
            departure_area_info.setCountry_id(plan_record.getDeparture_country_id());
            departure_area_info.setDistrict_id(plan_record.getDeparture_district_id());
            departure_area_info.setProvince_id(plan_record.getDeparture_province_id());
            departure_area_info.setCity_id(plan_record.getDeparture_city_id());
            departure_area_info.setCounty_id(plan_record.getDeparture_county_id());
            plan_dto.setDeparture_area_info(departure_area_info);
            plan_dto.setDeparture_area_str(component_route_plan.areaDepartureToStr(plan_record));
            //目的地
            AreaDto destination_area_info = new AreaDto();
            destination_area_info.setCountry_id(plan_record.getDestination_country_id());
            destination_area_info.setDistrict_id(plan_record.getDestination_district_id());
            destination_area_info.setProvince_id(plan_record.getDestination_province_id());
            destination_area_info.setCity_id(plan_record.getDestination_city_id());
            destination_area_info.setCounty_id(plan_record.getDestination_county_id());
            plan_dto.setDestination_area_info(destination_area_info);
            plan_dto.setDestination_area_str(component_route_plan.areaDestinationToStr(plan_record));
            //成团地点
            AreaDto rendezvous_area_info = new AreaDto();
            rendezvous_area_info.setCountry_id(plan_record.getRendezvous_country_id());
            rendezvous_area_info.setDistrict_id(plan_record.getRendezvous_district_id());
            rendezvous_area_info.setProvince_id(plan_record.getRendezvous_province_id());
            rendezvous_area_info.setCity_id(plan_record.getRendezvous_city_id());
            rendezvous_area_info.setCounty_id(plan_record.getRendezvous_county_id());
            plan_dto.setRendezvous_area_info(rendezvous_area_info);
            plan_dto.setRendezvous_area_str(component_route_plan.areaRendezvousToStr(plan_record));
            //几天
            plan_dto.setDays(plan_record.getDays());
            plan_dto.setNights(plan_record.getNights());
            //行程节点
            List<RoutePlanNodeDto> plan_node_info = new ArrayList<>();
            List<RoutePlanNode> node_list = route_plan_node_dao.selectByRoutePlanCode(plan_record.getPlan_code());
            for (RoutePlanNode node_record : node_list){
                RoutePlanNodeDto node_dto = new RoutePlanNodeDto();
                node_dto.setNode_code(node_record.getNode_code());
                node_dto.setNode_day(node_record.getNode_day());
                node_dto.setTraffic_type(node_record.getTraffic_type());
                node_dto.setLocale_area(node_record.getLocale_area());
                node_dto.setDescription(node_record.getDescription());
                node_dto.setHas_breakfast(node_record.getHas_breakfast());
                node_dto.setHas_lunch(node_record.getHas_lunch());
                node_dto.setHas_dinner(node_record.getHas_dinner());
                node_dto.setHotel_description(node_record.getHotel_description());
                plan_node_info.add(node_dto);

            }
            plan_dto.setPlan_node_info(plan_node_info);

            //价格明细
            List<RoutePlanBasePriceDto> plan_price_info = new ArrayList<>();
            List<RoutePlanBasePrice> plan_price_list_record = route_plan_price_dao.selectByRoutePlanCode(plan_record.getPlan_code());
            for (RoutePlanBasePrice price_record :plan_price_list_record){
                RoutePlanBasePriceDto price_dto = new RoutePlanBasePriceDto();
                price_dto.setBase_price_code(price_record.getBase_price_code());
                price_dto.setFee_type(price_record.getFee_type());
                price_dto.setFee_description(price_record.getFee_description());
                price_dto.setCurrency(price_record.getCurrency());
                price_dto.setPrice(price_record.getPrice().toString());
                price_dto.setQuantity(price_record.getQuantity());
                price_dto.setTotal_price(price_record.getTotal_price().toString());
                plan_price_info.add(price_dto);
            }
            plan_dto.setPlan_price_info(plan_price_info);
            plan_dto.setFee_contain(plan_record.getFee_contain());
            plan_dto.setFee_not_contain(plan_record.getFee_not_contain());
            plan_dto.setPre_notice(plan_record.getPre_notice());
            route_plan_info.add(plan_dto);
        }
        resp_body.setRoute_plan_info(route_plan_info);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteProductEditRespDto> update(RouteProductEditRequest request) {
        AbstractResponse<RouteProductEditRespDto> response = dtoFactory.createResponse(request.getHeader());
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request,Urls.Product.NAMESPACE);
        ProductInfo product_record = product_dao.selectByProductCode(request.getBody().getProduct_code());
        //产品表
        ProductInfo new_product = new ProductInfo();
        new_product.setId(product_record.getId());
        if (StringUtils.isNotBlank(request.getBody().getProduct_category_code()) && !request.getBody().getProduct_category_code().equals(product_record.getProduct_category_code())){
            new_product.setProduct_category_code(request.getBody().getProduct_category_code());
        }
        if (StringUtils.isNotBlank(request.getBody().getProduct_name()) && !request.getBody().getProduct_name().equals(product_record.getProduct_name())){
            new_product.setProduct_name(request.getBody().getProduct_name());
        }
        new_product.setProduct_description(request.getBody().getProduct_description());

        if (request.getBody().getArea_info()!=null){
            component_product.BuildArea(new_product,request.getBody().getArea_info());
        }
        new_product.setUpdate_time(new Date());
        int update_product_result = product_dao.updateByPrimaryKeySelective(new_product);

        //线路主表
        RouteInfo route_record = route_service.update(product_record.getProduct_code(), request.getBody().getRoute_base_info());

        //当前产品计划编号
        List<String> current_plan_code=route_plan_dao.selectPlanCodeByProductCode(product_record.getProduct_code());
        //线路行程计划
        List<String> exist_plan_code_list = new ArrayList<>();
        List<String> exist_node_code_list = new ArrayList<>();
        List<String> exist_price_code_list = new ArrayList<>();
        for (RoutePlanDto plan_dto : request.getBody().getRoute_plan_info()){
            if (StringUtils.isNotBlank(plan_dto.getRoute_plan_code())){
                //从原数据里移除掉，计算出被删除的计划方案
                current_plan_code.remove(plan_dto.getRoute_plan_code());
                //计划更新、
                RoutePlan plan_record = route_plan_service.update(plan_dto);
                //存在的code放入list
                exist_plan_code_list.add(plan_record.getPlan_code());
                //处理节点
                for (RoutePlanNodeDto node_dto : plan_dto.getPlan_node_info()){
                    if (StringUtils.isNotBlank(node_dto.getNode_code())){
                        //节点更新
                        RoutePlanNode node_record = route_plan_node_service.update(node_dto);
                        exist_node_code_list.add(node_record.getNode_code());
                    }else{
                        //节点创建
                        RoutePlanNode node_record =  route_plan_node_service.add(plan_record.getPlan_code(),user_info,node_dto);
                        exist_node_code_list.add(node_record.getNode_code());
                    }
                }
                //处理价格
                for (RoutePlanBasePriceDto price_dto : plan_dto.getPlan_price_info()){
                    if (StringUtils.isNotBlank(price_dto.getBase_price_code())){
                        //价格更新
                        RoutePlanBasePrice price_record = route_plan_price_service.update(price_dto);
                        exist_price_code_list.add(price_record.getBase_price_code());
                    }else{
                        //价格创建
                        RoutePlanBasePrice price_record =  route_plan_price_service.add(product_record.getProduct_code(),route_record.getRoute_code(),plan_record.getPlan_code(),user_info,price_dto);
                        exist_price_code_list.add(price_record.getBase_price_code());
                    }
                }

            }else{
                //计划创建
                RoutePlan plan_record  = route_plan_service.add(product_record.getProduct_code(),route_record.getRoute_code(),user_info,plan_dto);
                exist_plan_code_list.add(plan_record.getPlan_code());
                //处理节点
                for (RoutePlanNodeDto node_dto : plan_dto.getPlan_node_info()){
                    //节点创建
                    RoutePlanNode node_record = route_plan_node_service.add(plan_record.getPlan_code(),user_info,node_dto);
                    exist_node_code_list.add(node_record.getNode_code());
                }
                //处理价格
                for (RoutePlanBasePriceDto price_dto : plan_dto.getPlan_price_info()){
                    //价格创建
                    RoutePlanBasePrice price_record =  route_plan_price_service.add(product_record.getProduct_code(),route_record.getRoute_code(),plan_record.getPlan_code(),user_info,price_dto);
                    exist_price_code_list.add(price_record.getBase_price_code());
                }
            }
            //当一个计划执行完毕后，删除该计划下的废弃节点数据
            if (exist_node_code_list.size()>0){
                route_plan_node_service.delete(plan_dto.getRoute_plan_code(),exist_node_code_list);
            }
            //当一个计划执行完毕后，删除该计划下的废弃价格数据
            if (exist_price_code_list.size()>0){
                route_plan_price_service.delete(plan_dto.getRoute_plan_code(),exist_price_code_list);
            }

        }
        //删除废弃的行程计划
        if (exist_plan_code_list.size()>0){
            route_plan_service.delete(product_record.getProduct_code(),route_record.getRoute_code(),exist_plan_code_list,current_plan_code);
        }

        boolean is_update_product_name = false;
        if (StringUtils.isNotBlank(new_product.getProduct_name())){
            //大厅修改产品名称
            is_update_product_name= true;
        }
        boolean is_update_internal_code = false;
        if (StringUtils.isNotBlank(request.getBody().getRoute_base_info().getInternal_code()) && request.getBody().getRoute_base_info().getInternal_code().equals(route_record.getInternal_code())){
            is_update_internal_code = true;
        }
        if (is_update_product_name || is_update_internal_code){
            ProductUpdateNotifyDto notify_dto = new ProductUpdateNotifyDto();
            notify_dto.setProduct_code(product_record.getProduct_code());
            if (is_update_product_name){
                notify_dto.setProduct_name(new_product.getProduct_name());
            }
            if (is_update_internal_code){
                notify_dto.setInternal_code(route_record.getInternal_code());
            }
            notify_dto.setUpdate_time(new Date());
            route_hall_service.processByProduct(notify_dto);
        }
        return response;
    }

    @Override
    public AbstractResponse<RouteProductCopyRespDto> copy(RouteProductCopyRequest request) {
        AbstractResponse<RouteProductCopyRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteProductCopyRespDto body = new RouteProductCopyRespDto();
        List<String> new_product_code = new ArrayList<>();
        List<String> new_route_code = new ArrayList<>();
        List<String> new_route_plan_code = new ArrayList<>();
        List<String> new_node_code = new ArrayList<>();
        List<String> new_base_price_code = new ArrayList<>();

        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.Product.NAMESPACE);
        for (String product_code :  request.getBody().getProduct_code()){
            ProductInfo product_record = product_dao.selectByProductCode(product_code);
            if (product_record ==null){
                continue;
            }
            //源产品编号
            String origin_product_code = product_record.getProduct_code();
            //产品主表
            product_record.setProduct_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.product.getValue()));
            product_record.setCreate_time(new Date());
            product_record.setCreator_id(user_info.getBody().getUid());
            product_record.setCreator_name(user_info.getBody().getUserName());
            product_dao.insertSelective(product_record);
            new_product_code.add(product_record.getProduct_code());

            //线路表
            RouteInfo route_record=route_dao.selectByProductCode(origin_product_code);
            //源线路编号
            String origin_route_code = route_record.getRoute_code();
            route_record.setProduct_code(product_record.getProduct_code());
            route_record.setRoute_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route.getValue()));
            route_record.setCreate_time(new Date());
            route_record.setCreator_id(user_info.getBody().getUid());
            route_record.setCreator_name(user_info.getBody().getUserName());
            route_dao.insertSelective(route_record);
            new_route_code.add(route_record.getRoute_code());
            //线路计划表
            List<RoutePlan> route_plan_list = route_plan_dao.selectByRouteCode(origin_route_code);
            for (RoutePlan route_plan_record : route_plan_list){
                String origin_route_plan_code = route_plan_record.getPlan_code();
                route_plan_record.setPlan_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_plan.getValue()));
                route_plan_record.setRoute_code(route_record.getRoute_code());
                route_plan_record.setProduct_code(product_record.getProduct_code());
                route_plan_record.setCreate_time(new Date());
                route_plan_record.setCreator_id(user_info.getBody().getUid());
                route_plan_record.setCreator_name(user_info.getBody().getUserName());
                route_plan_dao.insertSelective(route_plan_record);
                new_route_plan_code.add(route_plan_record.getPlan_code());

                //线路计划里的节点
                List<RoutePlanNode> node_list = route_plan_node_dao.selectByRoutePlanCode(origin_route_plan_code);
                if (node_list!=null && node_list.isEmpty()==false){
                    for (RoutePlanNode node_record : node_list){
                        node_record.setNode_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_plan_node.getValue()));
                        node_record.setRoute_plan_code(route_plan_record.getPlan_code());
                        node_record.setCreate_time(new Date());
                        node_record.setCreator_id(user_info.getBody().getUid());
                        node_record.setCreator_name(user_info.getBody().getUserName());
                        route_plan_node_dao.insertSelective(node_record);
                        new_node_code.add(node_record.getNode_code());
                    }
                }
                //线路计划里的价格
                List<RoutePlanBasePrice> base_price_list = route_plan_price_dao.selectByRoutePlanCode(origin_route_plan_code);
                if (base_price_list != null && base_price_list.isEmpty()==false){
                    for (RoutePlanBasePrice price_record : base_price_list){
                        price_record.setBase_price_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_plan_price.getValue()));
                        price_record.setProduct_code(product_record.getProduct_code());
                        price_record.setRoute_code(route_record.getRoute_code());
                        price_record.setRoute_plan_code(route_plan_record.getPlan_code());
                        price_record.setCreate_time(new Date());
                        price_record.setCreator_id(user_info.getBody().getUid());
                        price_record.setCreator_name(user_info.getBody().getUserName());
                        route_plan_price_dao.insertSelective(price_record);
                        new_base_price_code.add(price_record.getBase_price_code());
                    }
                }
            }
        }
        body.setNew_product_code(new_product_code);
        body.setNew_route_code(new_route_code);
        body.setNew_route_plan_code(new_route_plan_code);
        body.setNew_node_code(new_node_code);
        body.setNew_base_price_code(new_base_price_code);
        response.setBody(body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteProductDelRespDto> delete(RouteProductDelRequest request) {
        AbstractResponse<RouteProductDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteProductDelRespDto body = new RouteProductDelRespDto();
        //删除出游人数据
        int client_count = client_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setClient_count(client_count);
        //删除订单确认团款
        int income_count = order_income_dao.deleteByGroupCode(request.getBody().getProduct_code());
        body.setIncome_count(income_count);
        //删除订单数据
        int order_count = order_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setOrder_count(order_count);
        //获取所有团号
        List<String> group_list = hall_dao.selectGroupCodeByProductCode(request.getBody().getProduct_code());
        if (group_list!=null && group_list.isEmpty()==false){
            //删除团的导服数据
            int guides_count = route_guide_dao.deleteByGroupCodeList(group_list);
            body.setGuides_count(guides_count);
            //删除交通票务
            int traffic_count = route_traffic_dao.deleteByGroupCodeList(group_list);
            body.setTraffic_count(traffic_count);
            //删除住宿
            int reside_count = route_reside_dao.deleteByGroupCodeList(group_list);
            body.setReside_count(reside_count);
            //删除景点门票
            int ticket_count = route_ticket_dao.deleteByGroupCodeList(group_list);
            body.setTicket_count(ticket_count);
            //删除餐饮
            int meal_count = route_meal_dao.deleteByGroupCodeList(group_list);
            body.setMeal_count(meal_count);
            //删除车队
            int motorcade_count = route_motorcade_dao.deleteByGroupCodeList(group_list);
            body.setMotorcade_count(motorcade_count);
            //删除保险
            int insurance_count = route_insurance_dao.deleteByGroupCodeList(group_list);
            body.setInsurance_count(insurance_count);
            //删除地接报价
            int travel_income_count = route_travel_income_dao.deleteByGroupCodeList(group_list);
            body.setTravel_income_count(travel_income_count);
            //删除地接旅行社
            int travel_count = route_travel_dao.deleteByGroupCodeList(group_list);
            body.setTravel_count(travel_count);
            //删除其他
            int other_count = route_other_dao.deleteByGroupCodeList(group_list);
            body.setOther_count(other_count);
        }
        //删除大厅数据
        int hall_count = hall_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setHall_count(hall_count);
        //价格数据
        int calendar_count = price_plan_detail_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setCalendar_count(calendar_count);
        //删除价格套餐
        int price_plan_count = price_plan_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setPrice_plan_count(price_plan_count);
        //价格套餐成本明细
        int price_plan_cost_count =price_plan_cost_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setPrice_plan_cost_count(price_plan_cost_count);
        //线路方案基础价格
        int route_plan_base_price_count = route_plan_price_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setRoute_plan_base_price_count(route_plan_base_price_count);
        //删除行程节点
        int node_count =route_plan_node_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setNode_count(node_count);
        //线路行程方案
        int route_plan_count = route_plan_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setRoute_plan_count(route_plan_count);
        //线路表
        int route_count = route_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setRoute_count(route_count);
        int product_count =  product_dao.deleteByProductCode(request.getBody().getProduct_code());
        body.setProduct_count(product_count);
        response.setBody(body);
        return response;
    }

    @Override
    public boolean isExist(String product_name) {
        return product_dao.countByProductName(product_name)>0;
    }

    @Override
    public ProductInfo selectByProductCode(String product_code) {
        return product_dao.selectByProductCode(product_code);
    }
}
