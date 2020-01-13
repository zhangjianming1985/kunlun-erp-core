package com.kunlun.erp.core.component;

import com.kunlun.erp.core.common.configuration.PermissionKeyProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.condition.ProductCondition;
import com.kunlun.erp.core.dto.product.request.RouteProductListReqDto;
import com.kunlun.erp.core.dto.product.request.RouteProductListRequest;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.entity.*;
import com.kunlun.erp.core.service.account.PermissionService;
import com.kunlun.erp.core.service.area.*;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName ProductComponent
 * @Description 产品组件
 * @Author Jm.zhang
 * @Date 2019/12/9 18:11
 * @Version 1.0
 **/
@Component(value = "component_product")
public class ProductComponent {
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
    /**
     * 重设产品地区数据
     * @param new_product
     * @param area_info
     * @return
     */
    public ProductInfo BuildArea(ProductInfo new_product, AreaDto area_info){
        //出发地
        SysCountry country = sys_country_service.getCountryByCountryId(area_info.getCountry_id());
        new_product.setCountry_id(country.getCountry_id());
        new_product.setCountry_name(country.getZh_name());

        SysCity city = sys_city_service.getCityByCityId(area_info.getCity_id());
        new_product.setCity_id(city.getCity_id());
        new_product.setCity_name(city.getZh_name());
        if (area_validator.isChina(area_info.getCountry_id())){
            SysDistrict district = sys_district_service.getDistrictByDistrictId(area_info.getDistrict_id());
            new_product.setDistrict_id(district.getDistrict_id());
            new_product.setDistrict_name(district.getZh_name());
            SysProvince province = sys_province_service.getProvinceByProvinceId(area_info.getProvince_id());
            new_product.setProvince_id(province.getProvince_id());
            new_product.setProvince_name(province.getZh_name());
            SysCounty county = sys_county_service.getCountyByCountyId(area_info.getCounty_id());
            new_product.setCounty_id(county.getCounty_id());
            new_product.setCounty_name(county.getZh_name());
        }else{
            new_product.setDistrict_id(null);
            new_product.setDistrict_name(null);
            new_product.setProvince_id(null);
            new_product.setProvince_name(null);
            new_product.setCounty_id(null);
            new_product.setCounty_name(null);
        }
        return new_product;
    }

    /**
     * 查询请求DTO 转为SQL检索条件
     * @param request
     * @return
     */
    public ProductCondition convert(RouteProductListRequest request){
        RouteProductListReqDto request_body = request.getBody();
        ProductCondition condition = new ProductCondition();
        condition.setPage_index(request_body.getPage_index());
        condition.setPage_size(request_body.getPage_size());
        condition.setProduct_code(request_body.getProduct_code());
        condition.setProduct_name(request_body.getProduct_name());
        condition.setCreator_name(request_body.getCreator_name());
        condition.setProduct_category_code(request_body.getProduct_category_code());
        AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
            condition.setUid(permission_dto.getBody().getUid());
        }
        if (request_body.getArea()!=null){
            condition.setCountry_id(request_body.getArea().getCountry_id());
//            condition.setDistrict_id(request_body.getArea().getDistrict_id());
            condition.setProvince_id(request_body.getArea().getProvince_id());
            condition.setCity_id(request_body.getArea().getCity_id());
            condition.setCounty_id(request_body.getArea().getCounty_id());
        }
        return condition;
    }

    public String areaToStr( ProductInfo product_record){
        StringBuffer sb = new StringBuffer(product_record.getCountry_name());

        if (StringUtils.isNotBlank(product_record.getDistrict_name())){
            sb.append("/"+product_record.getDistrict_name());
        }
        if (StringUtils.isNotBlank(product_record.getProvince_name())){
            sb.append("/"+product_record.getProvince_name());
        }
        if (StringUtils.isNotBlank(product_record.getCity_name())){
            sb.append("/"+product_record.getCity_name());
        }
        if (StringUtils.isNotBlank(product_record.getCounty_name())){
            sb.append("/"+product_record.getCounty_name());
        }
        return sb.toString();
    }
}
