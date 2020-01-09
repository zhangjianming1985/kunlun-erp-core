package com.kunlun.erp.core.component;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.company.request.CompanyListReqDto;
import com.kunlun.erp.core.dto.company.request.CompanyListRequest;
import com.kunlun.erp.core.dto.condition.CompanyCondition;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.mapper.CompanyInfoMapper;
import com.kunlun.erp.core.service.area.*;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName CompanyComponent
 * @Description 企业组件
 * @Author Jm.zhang
 * @Date 2019/11/22 15:32
 * @Version 1.0
 **/
@Component(value = "component_company")
public class CompanyComponent {
    @Resource
    private CompanyInfoMapper company_dao;
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

    /**
     * 重设企业地区数据
     * @param company_record
     * @param area_info
     * @return
     */
    public CompanyInfo BuildArea(CompanyInfo company_record, AreaDto area_info){
        //出发地
        SysCountry country = sys_country_service.getCountryByCountryId(area_info.getCountry_id());
        company_record.setCountry_id(country.getCountry_id());
        company_record.setCountry_name(country.getZh_name());

        SysCity city = sys_city_service.getCityByCityId(area_info.getCity_id());
        company_record.setCity_id(city.getCity_id());
        company_record.setCity_name(city.getZh_name());
        if (area_validator.isChina(area_info.getCountry_id())){
            SysDistrict district = sys_district_service.getDistrictByDistrictId(area_info.getDistrict_id());
            company_record.setDistrict_id(district.getDistrict_id());
            company_record.setDistrict_name(district.getZh_name());
            SysProvince province = sys_province_service.getProvinceByProvinceId(area_info.getProvince_id());
            company_record.setProvince_id(province.getProvince_id());
            company_record.setProvince_name(province.getZh_name());
            SysCounty county = sys_county_service.getCountyByCountyId(area_info.getCounty_id());
            company_record.setCounty_id(county.getCounty_id());
            company_record.setCounty_name(county.getZh_name());
        }else{
            company_record.setDistrict_id(null);
            company_record.setDistrict_name(null);
            company_record.setProvince_id(null);
            company_record.setProvince_name(null);
            company_record.setCounty_id(null);
            company_record.setCounty_name(null);
        }
        return company_record;
    }


    /**
     * 统计企业名称的数量
     * @param company_name
     * @return
     */
    public Integer countByCompanyName(String company_name){
        return company_dao.countByCompanyName(company_name);
    }



    /**
     * 企业查询请求DTO 转为SQL检索条件
     * @param request
     * @return
     */
    public CompanyCondition convert(CompanyListRequest request){
        CompanyListReqDto request_body = request.getBody();
        CompanyCondition condition = new CompanyCondition();
        condition.setPage_index(request_body.getPage_index());
        condition.setPage_size(request_body.getPage_size());
        condition.setCompany_type(request_body.getCompany_type());
        condition.setCompany_name(request_body.getCompany_name());
        condition.setLeader_name(request_body.getLeader_name());
        condition.setLeader_mobile(request_body.getLeader_mobile());
        condition.setCredit_level(request_body.getCredit_level());
        if (request_body.getArea()!=null){
            condition.setCountry_id(request_body.getArea().getCountry_id());
//            condition.setDistrict_id(request_body.getArea().getDistrict_id());
            condition.setProvince_id(request_body.getArea().getProvince_id());
            condition.setCity_id(request_body.getArea().getCity_id());
            condition.setCounty_id(request_body.getArea().getCounty_id());
        }
        condition.setPerson_name(request_body.getPerson_name());
        condition.setPerson_mobile(request_body.getPerson_mobile());
        if (request.getBody().getCompany_type() == SysConstant.CompanyType.sales_channel_online.getValue()){
            if (request.getBody().getBelong_platform()!=null){
                condition.setBelong_platform(request_body.getBelong_platform());
            }
        }

        return condition;
    }

}
