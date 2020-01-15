package com.kunlun.erp.core.service.impl.account;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.kunlun.erp.core.common.configuration.PermissionKeyProperties;
import com.kunlun.erp.core.common.configuration.PermissionProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.common.util.HttpUtil;
import com.kunlun.erp.core.common.util.LogUtil;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.request.*;
import com.kunlun.erp.core.dto.finance.request.*;
import com.kunlun.erp.core.dto.person.request.PersonAddRequest;
import com.kunlun.erp.core.dto.person.request.PersonDetailRequest;
import com.kunlun.erp.core.dto.person.request.PersonListRequest;
import com.kunlun.erp.core.dto.person.request.PersonUpdateRequest;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.routeHall.request.*;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDeleteRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDetailRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListRequest;
import com.kunlun.erp.core.dto.user.HasPermissionReqDto;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.account.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName PermissionServiceImp
 * @Description 权限业务
 * @Author Jm.zhang
 * @Date 2019/11/22 17:51
 * @Version 1.0
 **/
@Service(value = "permission_service")
public class PermissionServiceImp extends BaseService implements PermissionService {
    @Autowired
    private PermissionProperties permission;
    @Resource
    private PermissionKeyProperties permission_key;
    @Override
    public AbstractResponse initPermission() {
        AbstractRequest request = dtoFactory.createRequest();
        request.getHeader().setTrans_no(String.valueOf(new Date().getTime()));
        request.getHeader().setSecret_key("");
        request.setBody(permission.getData());
        String reqJson = JsonUtil.toStr(request);
        LogUtil.writeLogInfo(this.getClass().getName(),"initPermission()","reqJson "+config.getAdd_permission_api_url() ,reqJson, Urls.USER_NAME_SPACE);
        String respJson = new HttpUtil().sendMessage(reqJson, config.getAdd_permission_api_url());
        LogUtil.writeLogInfo(this.getClass().getName(),"initPermission()","respJson " ,respJson, Urls.USER_NAME_SPACE);
        if (StringUtils.isBlank(respJson)){
            return null;
        }else{
            AbstractResponse user_info = JsonUtil.toBean(respJson, new TypeReference<AbstractResponse>(){});
            return user_info;
        }
    }



    @Override
    public AbstractResponse<HasPermissionRespDto> getUserByPermission(String trans_no, String secret_key, String permission_key) {
        AbstractRequest<HasPermissionReqDto> request = dtoFactory.createRequest();
        request.getHeader().setSecret_key(secret_key);
        request.getHeader().setTrans_no(trans_no);
        HasPermissionReqDto body  = new HasPermissionReqDto();
        body.setPermissionKey(permission_key);
        request.setBody(body);
        String reqJson = JsonUtil.toStr(request);
        LogUtil.writeLogInfo(this.getClass().getName(),"hasPermission()","reqJson "+config.getHas_permission_api_url() ,reqJson, Urls.USER_NAME_SPACE);
        String respJson = new HttpUtil().sendMessage(reqJson, config.getHas_permission_api_url());
        LogUtil.writeLogInfo(this.getClass().getName(),"hasPermission()","respJson " ,respJson, Urls.USER_NAME_SPACE);
        if (StringUtils.isNotBlank(respJson)){
            AbstractResponse<HasPermissionRespDto> response = JsonUtil.toBean(respJson, new TypeReference<AbstractResponse<HasPermissionRespDto>>(){});
            return response;
        }

        return null;
    }

    @Override
    public String getPermissionKey(Object target) {
        String per_key = null;
        if(target instanceof ProductCategoryListRequest || target instanceof  ProductCategoryAddRequest
                || target instanceof ProductCategoryDetailRequest || target instanceof ProductCategoryUpdateRequest || target instanceof  ProductCategoryDelRequest){
            //产品类别
            if (target instanceof ProductCategoryListRequest){
                ProductCategoryListRequest request= (ProductCategoryListRequest)target;
                if (request.getBody().getPage_size()!=10000){
                    per_key = permission_key.getProduct_setting();
                }
            }else{
                per_key = permission_key.getProduct_setting();
            }

        }else if(target instanceof RouteProductListRequest){
            //线路列表
            per_key=permission_key.getRoute_product_list();
        }else if (target instanceof RouteProductAddRequest || target instanceof PricePlanAddRequest || target instanceof RouteProductCopyRequest){
            //创建线路产品
            per_key=permission_key.getCreate_route();
        }else if (target instanceof  RouteProductDelRequest){
            //删除线路产品
            per_key=permission_key.getDelete_route();
        }else if(target instanceof RouteProductDetailRequest || target instanceof RouteProductEditRequest
        ||target instanceof PricePlanDetailRequest || target instanceof PricePlanDelRequest || target instanceof  PricePlanCostDetailRequest || target instanceof PriceDateDelRequest
                || target instanceof PricePlanNameUpdateRequest ){
            //产品详情  产品修改  价格详情  价格套餐删除 成本明细  价格日期删除
            per_key=permission_key.getEdit_route();

        }else if (target instanceof HallProductListRequest){
            //线路大厅列表
            per_key=permission_key.getRoute_daily_list();
        }else if (target instanceof HallProductDetailRequest || target instanceof RouteGuideListRequest || target instanceof RouteGuideAddRequest
        || target instanceof RouteResideListRequest || target instanceof RouteResideAddRequest || target instanceof RouteTicketListRequest
        || target instanceof  RouteTicketAddRequest ||target instanceof RouteMealListRequest || target instanceof RouteMealAddRequest||target instanceof RouteMotorcadeListRequest
        || target instanceof RouteMotorcadeAddRequest ||target instanceof RouteInsuranceListRequest ||target instanceof RouteInsuranceAddRequest
        || target instanceof  RouteOtherListRequest || target instanceof RouteOtherAddRequest || target instanceof  RouteClientListRequest
        || target instanceof RouteTrafficListRequest || target instanceof RouteTrafficAddRequest || target instanceof RouteTravelAgencyListRequest
        || target instanceof RouteTravelAgencyAddRequest || target instanceof HallProductStateUpdateRequest || target instanceof  RouteStatisticsRequest){
            //编辑大厅产品
            per_key = permission_key.getRoute_daily_edit();
        }else if(target instanceof RouteOrderDetailRequest ){
            //订单详情 编辑订单
            per_key = permission_key.getRoute_order_edit();
        }else if (target instanceof RouteOrderDeleteRequest){
            //删除订单
            per_key=permission_key.getRoute_order_delete();
        }else if (target instanceof RouteOrderAddRequest){
            //报名、创建订单
            per_key=permission_key.getRoute_order_create();
        }else if (target instanceof RouteOrderListRequest){
            //线路订单列表
            per_key=permission_key.getRoute_order_list();
        }else if (target instanceof HallProductEndListRequest){
            //发团审核 结束的团列表
            per_key=permission_key.getFinance_FaTuan_Audit();
        }else if (target instanceof FinanceAuditRequest || target instanceof FinanceAuditResultRequest){
            //审核
            per_key=permission_key.getFinance_Audit();
        }else if (target instanceof CollectedListRequest){
            //应收款
            per_key=permission_key.getFinance_Receivables_List();
        }else if (target instanceof PaymentListRequest){
            //应付款
            per_key=permission_key.getFinance_AccountsPayable_List();
        }else if (target instanceof CompanyAddRequest){
            //新增公司
            CompanyAddRequest request = (CompanyAddRequest)target;
            CompanyAddReqDto body = request.getBody();
            if (body.getCompany_type()== SysConstant.CompanyType.sales_channel_online.getValue()){
                //线上销售
                per_key = permission_key.getCreate_online_ot();
            }else if (body.getCompany_type()== SysConstant.CompanyType.sales_channel_offline.getValue()){
                //线下门店
                per_key = permission_key.getCreate_offline_store();
            }else if (body.getCompany_type()== SysConstant.CompanyType.sales_channel_other.getValue()){
                //其他销售渠道
                per_key = permission_key.getCreate_other_sales_channel();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_travel_agency.getValue()){
                //旅行社
                per_key = permission_key.getCreate_travel_agency();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_car.getValue()){
                //车队
                per_key = permission_key.getCreate_car_team();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_hotel.getValue()){
                //酒店供应商
                per_key = permission_key.getCreate_reside();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_meal.getValue()){
                //餐饮供应商
                per_key = permission_key.getCreate_meal();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_ticket.getValue()){
                //景区景点
                per_key = permission_key.getCreate_ticket();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_traffic.getValue()){
                //交通票务
                per_key = permission_key.getCreate_traffic();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_insurance.getValue()){
                //保险
                per_key = permission_key.getCreate_insurance();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_guides.getValue()){
                //导服
                per_key = permission_key.getCreate_guides();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_other.getValue()){
                //其他供应商
                per_key = permission_key.getCreate_other_supplier();
            }
        }else if (target instanceof CompanyListRequest){
            //查询列表
            CompanyListRequest request = (CompanyListRequest)target;
            CompanyListReqDto body = request.getBody();
            if (body.getCompany_type()== SysConstant.CompanyType.sales_channel_online.getValue()){
                //线上销售
                per_key = permission_key.getOnline_ot();
            }else if (body.getCompany_type()== SysConstant.CompanyType.sales_channel_offline.getValue()){
                //线下门店
                per_key = permission_key.getOffline_store();
            }else if (body.getCompany_type()== SysConstant.CompanyType.sales_channel_other.getValue()){
                //其他销售渠道
                per_key = permission_key.getOther_sales_channel();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_travel_agency.getValue()){
                //旅行社
                per_key = permission_key.getTravel_agency_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_car.getValue()){
                //车队
                per_key = permission_key.getCar_team_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_hotel.getValue()){
                //酒店住宿
                per_key = permission_key.getReside_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_meal.getValue()){
                //餐饮
                per_key = permission_key.getMeal_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_ticket.getValue()){
                //景区景点
                per_key = permission_key.getTicket_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_traffic.getValue()){
                //交通票务
                per_key = permission_key.getTraffic_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_insurance.getValue()){
                //保险
                per_key = permission_key.getInsurance_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_guides.getValue()){
                //导服
                per_key = permission_key.getGuides_list();
            }else if (body.getCompany_type()== SysConstant.CompanyType.supplier_other.getValue()){
                //其他供应商
                per_key = permission_key.getOther_supplier_list();
            }

        }else if (target instanceof CompanyDetailRequest || target instanceof CompanyEditRequest || target instanceof  CompanyDeleteRequest){
            JSONObject json_obj = JsonUtil.toJsonObj(target);
            Integer company_type =  json_obj.getJSONObject("body").getInteger("company_type");
            if (company_type== SysConstant.CompanyType.sales_channel_online.getValue()){
                //线上销售
                per_key = permission_key.getEdit_online_ot();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_online_ot();
                }
            }else if (company_type== SysConstant.CompanyType.sales_channel_offline.getValue()){
                //线下门店
                per_key = permission_key.getEdit_offline_store();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_offline_store();
                }
            }else if (company_type== SysConstant.CompanyType.sales_channel_other.getValue()){
                //其他销售渠道
                per_key = permission_key.getEdit_other_sales_channel();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_other_sales_channel();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_travel_agency.getValue()){
                //旅行社
                per_key = permission_key.getEdit_travel_agency();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_travel_agency();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_car.getValue()){
                //车队
                per_key = permission_key.getEdit_car_team();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_car_team();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_hotel.getValue()){
                //酒店住宿
                per_key = permission_key.getEdit_reside();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_reside();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_meal.getValue()){
                //餐饮
                per_key = permission_key.getEdit_meal();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_meal();
                }
            }else if (company_type == SysConstant.CompanyType.supplier_ticket.getValue()){
                //景区景点
                per_key = permission_key.getEdit_ticket();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_ticket();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_traffic.getValue()){
                //交通票务
                per_key = permission_key.getEdit_traffic();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_traffic();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_insurance.getValue()){
                //保险
                per_key = permission_key.getEdit_insurance();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_insurance();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_guides.getValue()){
                //导服
                per_key = permission_key.getEdit_guides();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_guides();
                }
            }else if (company_type== SysConstant.CompanyType.supplier_other.getValue()){
                //其他供应商
                per_key = permission_key.getEdit_other_supplier();
                if (target instanceof  CompanyDeleteRequest){
                    per_key = permission_key.getDelete_other_supplier();
                }
            }

        }else if (target instanceof PersonListRequest || target instanceof PersonAddRequest || target instanceof PersonDetailRequest
        || target instanceof PersonUpdateRequest){
            per_key = permission_key.getEdit_guides();
        }
        return per_key;
    }
}
