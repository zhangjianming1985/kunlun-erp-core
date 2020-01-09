package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.service.area.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName AreaValidator
 * @Description 地区校验
 * @Author Jm.zhang
 * @Date 2019-11-18 22:38
 * @Version 1.0
 **/
@Component(value = "area_validator")
public class AreaValidator  {
    @Resource(name = "sys_country_service")
    private SysCountryService sys_country_service;
    @Resource(name = "sys_province_service")
    private SysProvinceService sys_province_service;
    @Resource(name = "sys_city_service")
    private SysCityService sys_city_service;
    @Resource(name = "sys_county_service")
    private SysCountyService sys_county_service;
    @Resource(name = "sys_district_service")
    private SysDistrictService sys_district_service;

    /**
     * 是否中国
     * @param country_id
     * @return
     */
    public boolean isChina(Integer country_id){
        return country_id.toString().equals("240");
    }


    /**
     *
     * @param area_dto
     * @param is_create
     * @return
     */
    public String check(AreaDto area_dto,boolean is_create){
        String error_code = null;
        if (!is_create && area_dto == null){
            //更新时，没有传参
            return null;
        }
        if (is_create && area_dto==null){
            //创建时，没有传参
            error_code = ErrorCodeConstant.AREA_DATA_NULL;
        }
        if (error_code == null){
            //国家必填
            if (area_dto.getCountry_id() ==null || sys_country_service.isExist(area_dto.getCountry_id())==false){
                error_code =ErrorCodeConstant.COUNTRY_ID_INVALID;
            }
        }
        if (error_code == null){
            if (isChina(area_dto.getCountry_id())){
                if (area_dto.getDistrict_id()==null || sys_district_service.isExist(area_dto.getDistrict_id())==false){
                    error_code =ErrorCodeConstant.DISTRICT_ID_INVALID;
                }
                if (error_code ==null){
                    //境内、 省 必填
                    if (area_dto.getProvince_id()==null || sys_province_service.isExist(area_dto.getProvince_id())==false){
                        error_code =ErrorCodeConstant.PROVINCE_ID_INVALID;
                    }
                }
                if (error_code == null){
                    //城市必填
                    if (area_dto.getCity_id() ==null || sys_city_service.isExist(area_dto.getCity_id(),area_dto.getCountry_id(),area_dto.getProvince_id())==false){
                        error_code =ErrorCodeConstant.CITY_ID_INVALID;
                    }
                }
                //境内 区县必填
                if (error_code == null){
                    if (area_dto.getCounty_id() ==null || sys_county_service.isExist(area_dto.getCounty_id(),area_dto.getCity_id())==false){
                        error_code =ErrorCodeConstant.COUNTY_ID_INVALID;
                    }
                }

            }else{
                //城市必填
                if (area_dto.getCity_id() ==null || sys_city_service.isExist(area_dto.getCity_id(),area_dto.getCountry_id(),null)==false){
                    error_code =ErrorCodeConstant.CITY_ID_INVALID;
                }
            }
        }

        return error_code;
    }



}
