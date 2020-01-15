package com.kunlun.erp.core.component;

import com.kunlun.erp.core.common.configuration.PermissionKeyProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.condition.RouteOrderCondition;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListRequest;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.service.account.AccountService;
import com.kunlun.erp.core.service.account.PermissionService;
import com.kunlun.erp.core.service.area.*;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RouteOrderComponent
 * @Description 线路订单组件
 * @Author Jm.zhang
 * @Date 2019/12/23 17:08
 * @Version 1.0
 **/
@Component(value = "component_route_order")
public class RouteOrderComponent {
    @Resource(name = "sys_country_service")
    protected SysCountryService sys_country_service;
    @Resource(name = "sys_province_service")
    protected SysProvinceService sys_province_service;
    @Resource(name = "sys_city_service")
    protected SysCityService sys_city_service;
    @Resource(name = "sys_county_service")
    protected SysCountyService sys_county_service;
    @Resource(name = "sys_district_service")
    protected SysDistrictService sys_district_service;
    @Resource(name = "area_validator")
    protected AreaValidator area_validator;
    @Resource(name = "permission_service")
    private PermissionService permission_service;
    @Resource
    private PermissionKeyProperties per_properties;
    @Resource(name = "account_service")
    private AccountService account_service;

    public RouteOrderCondition convert(RouteOrderListRequest request){
        RouteOrderCondition condition = new RouteOrderCondition();
        condition.setPage_index(request.getBody().getPage_index());
        condition.setPage_size(request.getBody().getPage_size());
        condition.setProduct_code(request.getBody().getProduct_code());
        condition.setProduct_name(request.getBody().getProduct_name());
        condition.setCreator_id(request.getBody().getCreator_id());
        condition.setCompany_name(request.getBody().getCompany_name());
        condition.setLeader_name(request.getBody().getLeader_name());
        condition.setContact_name(request.getBody().getContact_name());
        condition.setDeparture_date_start(request.getBody().getDeparture_date_start());
        condition.setDeparture_date_end(request.getBody().getDeparture_date_end());
        condition.setClient_name(request.getBody().getClient_name());
        condition.setClient_mobile(request.getBody().getClient_mobile());
        condition.setCreate_date_start(request.getBody().getCreate_date_start());
        condition.setCreate_date_end(request.getBody().getCreate_date_end());
        condition.setState(request.getBody().getState());
        condition.setOrder_code(request.getBody().getOrder_code());
        condition.setCompany_order_code(request.getBody().getCompany_order_code());
        condition.setGroup_code(request.getBody().getGroup_code());
        AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
            AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(), Urls.RouteOrder.NAMESPACE);
            condition.setUid(user_info.getBody().getUid());
        }
        return  condition;
    }


    /**
     * 重设客源地
     * @param new_record
     * @param client_area
     * @return
     */
    public RouteOrder BuildClientArea(RouteOrder new_record, AreaDto client_area){
        //出发地
        SysCountry country = sys_country_service.getCountryByCountryId(client_area.getCountry_id());
        new_record.setClient_country_id(country.getCountry_id());
        new_record.setClient_country_name(country.getZh_name());

        SysCity city = sys_city_service.getCityByCityId(client_area.getCity_id());
        new_record.setClient_city_id(city.getCity_id());
        new_record.setClient_city_name(city.getZh_name());
        if (area_validator.isChina(client_area.getCountry_id())){
            SysDistrict district = sys_district_service.getDistrictByDistrictId(client_area.getDistrict_id());
            new_record.setClient_district_id(district.getDistrict_id());
            new_record.setClient_district_name(district.getZh_name());
            SysProvince province = sys_province_service.getProvinceByProvinceId(client_area.getProvince_id());
            new_record.setClient_province_id(province.getProvince_id());
            new_record.setClient_province_name(province.getZh_name());
            SysCounty county = sys_county_service.getCountyByCountyId(client_area.getCounty_id());
            new_record.setClient_county_id(county.getCounty_id());
            new_record.setClient_county_name(county.getZh_name());
        }else{
            new_record.setClient_district_id(null);
            new_record.setClient_district_name(null);
            new_record.setClient_province_id(null);
            new_record.setClient_province_name(null);
            new_record.setClient_county_id(null);
            new_record.setClient_county_name(null);
        }
        return new_record;
    }

    public String clientAreaToStr(RouteOrder order_record){
        StringBuffer sb = new StringBuffer(order_record.getClient_country_name());

        if (StringUtils.isNotBlank(order_record.getClient_district_name())){
            sb.append("/"+order_record.getClient_district_name());
        }
        if (StringUtils.isNotBlank(order_record.getClient_province_name())){
            sb.append("/"+order_record.getClient_province_name());
        }
        if (StringUtils.isNotBlank(order_record.getClient_city_name())){
            sb.append("/"+order_record.getClient_city_name());
        }
        if (StringUtils.isNotBlank(order_record.getClient_county_name())){
            sb.append("/"+order_record.getClient_county_name());
        }
        return sb.toString();
    }
}
