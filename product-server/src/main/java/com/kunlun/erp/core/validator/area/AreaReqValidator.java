package com.kunlun.erp.core.validator.area;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.area.request.AreaRequest;
import com.kunlun.erp.core.entity.SysCity;
import com.kunlun.erp.core.entity.SysCountry;
import com.kunlun.erp.core.entity.SysDistrict;
import com.kunlun.erp.core.entity.SysProvince;
import com.kunlun.erp.core.service.area.SysCityService;
import com.kunlun.erp.core.service.area.SysCountryService;
import com.kunlun.erp.core.service.area.SysDistrictService;
import com.kunlun.erp.core.service.area.SysProvinceService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName AreaReqValidator
 * @Description 区域请求校验
 * @Author Jm.zhang
 * @Date 2019/12/5 16:21
 * @Version 1.0
 **/
@Component(value = "area_req_validator")
public class AreaReqValidator extends AbstractValidator {
    @Resource(name = "sys_district_service")
    private SysDistrictService sys_district_service;
    @Resource(name = "sys_country_service")
    private SysCountryService sys_country_service;
    @Resource(name = "sys_province_service")
    private SysProvinceService sys_province_service;
    @Resource(name = "sys_city_service")
    private SysCityService sys_city_service;
    @Override
    public boolean supports(Class<?> clazz) {
        return AreaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        //不需要权限验证
        return null;
    }

    @Override
    public String myValidate(Object obj) {
        boolean hasParam =false;
        String error_code = null;
        if (obj instanceof AreaRequest){
            AreaRequest  request = (AreaRequest)obj;
                if (request.getBody().getArea_type()!=null){
                    hasParam =true;
                    if (SysConstant.AreaType.getAreaType(request.getBody().getArea_type())==null){
                        error_code = ErrorCodeConstant.AREA_TYPE_INVALID;

                    }
                }
                if (error_code == null){
                    if (request.getBody().getDistrict_id()!= null){
                        hasParam =true;
                        SysDistrict record = sys_district_service.getDistrictByDistrictId(request.getBody().getDistrict_id());
                        if (record == null){
                            error_code = ErrorCodeConstant.AREA_DISTRICT_ID_INVALID;
                        }
                    }
                }

                if (error_code == null){
                    if (request.getBody().getCountry_id()!=null){
                        hasParam =true;
                        SysCountry record =sys_country_service.getCountryByCountryId(request.getBody().getCountry_id());
                        if (record == null){
                            error_code = ErrorCodeConstant.AREA_COUNTRY_ID_INVALID;
                        }
                    }
                }

            if (error_code == null){
                if (request.getBody().getProvince_id()!=null){
                    hasParam =true;
                    SysProvince record =sys_province_service.getProvinceByProvinceId(request.getBody().getProvince_id());
                    if (record == null){
                        error_code = ErrorCodeConstant.AREA_PROVINCE_ID_INVALID;
                    }
                }
            }
            if (error_code == null){
                if (request.getBody().getCity_id()!=null){
                    hasParam =true;
                    SysCity record =sys_city_service.getCityByCityId(request.getBody().getProvince_id());
                    if (record == null){
                        error_code = ErrorCodeConstant.AREA_CITY_ID_INVALID;
                    }
                }
            }

            if (error_code == null){
                if (hasParam ==false){
                    error_code =ErrorCodeConstant.REQUEST_ILLEGAL;
                }
            }
        }

        return error_code;
    }
}
