package com.kunlun.erp.core.service;

import com.kunlun.erp.core.common.configuration.GlobalProperties;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.DtoMessageUtil;
import com.kunlun.erp.core.dto.factory.CoreDtoFactory;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.service.account.AccountService;
import com.kunlun.erp.core.service.area.*;
import com.kunlun.erp.core.validator.common.BaseInfoValidator;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @ClassName BaseService
 * @Description
 * @Author Jm.zhang
 * @Date 2019/11/19 17:12
 * @Version 1.0
 **/
public class BaseService {
    @Resource(name = "dto_message_util")
    protected DtoMessageUtil dto_message_util;
    @Resource(name = "dtoFactory")
    protected CoreDtoFactory dtoFactory;
    @Resource(name = "base_validator")
    protected BaseInfoValidator base_validator;
    @Resource(name = "account_service")
    protected AccountService account_service;
    @Autowired
    protected GlobalProperties config;
    @Resource
    protected CacheManager cacheManager;

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

    public  AbstractResponse<UserInfoRespDto> getUserInfo(AbstractRequest request,String name_space){
        AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(), name_space);
        return user_info;
    }




}
