package com.kunlun.erp.core.component;

import com.kunlun.erp.core.service.area.*;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RoutePlanNodeComponent
 * @Description 线路行程节点组件
 * @Author Jm.zhang
 * @Date 2019/12/10 16:57
 * @Version 1.0
 **/
@Component(value = "component_route_node")
public class RoutePlanNodeComponent {
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




}
