package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteInsuranceDto;
import com.kunlun.erp.core.entity.RouteInsurance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteInsuranceMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code") String company_code);

    int deleteByGroupCodeAndInsuranceCode(@Param("group_code") String group_code,@Param("insurance_codes") List<String> insurance_codes);

    int insertSelective(RouteInsurance record);

    RouteInsurance selectByPrimaryKey(Integer id);

    RouteInsurance selectByInsuranceCode(@Param("insurance_code") String insurance_code);

    int updateByPrimaryKeySelective(RouteInsurance record);

    List<RouteInsuranceDto> selectDtoByGroupCode(@Param("group_code") String group_code);

}