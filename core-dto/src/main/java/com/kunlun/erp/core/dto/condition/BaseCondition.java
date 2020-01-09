package com.kunlun.erp.core.dto.condition;

/**
 * @ClassName BaseCondition
 * @Description 基础的条件信息
 * @Author Jm.zhang
 * @Date 2019/11/26 14:50
 * @Version 1.0
 **/
public class BaseCondition {
    /**
     * 页码
     */
    private Integer page_index;
    /**
     * 每页数据条数
     */
    private Integer page_size;


    /**
     * 国家ID
     */
    private Integer country_id;

    /**
     * 中国区域ID
     */
    private Integer district_id;

    /**
     * 省ID
     */
    private Integer province_id;


    /**
     * 城市ID
     */
    private Integer city_id;


    /**
     * 区县ID
     */
    private Integer county_id;

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getCounty_id() {
        return county_id;
    }

    public void setCounty_id(Integer county_id) {
        this.county_id = county_id;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }
}
