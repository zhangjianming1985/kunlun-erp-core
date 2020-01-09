package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyIncomeDto;
import com.kunlun.erp.core.entity.RouteTravelAgencyIncome;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteTravelAgencyIncomeMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RouteTravelAgencyIncome record);

    RouteTravelAgencyIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RouteTravelAgencyIncome record);

    List<RouteTravelAgencyIncomeDto> selectDtoByGroupCode(@Param("group_code") String group_code);

    List<RouteTravelAgencyIncomeDto>  selectDtoByCompanyCode(@Param("company_code") String company_code);

    RouteTravelAgencyIncome selectByIncomeCode(@Param("income_code")String income_code);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);

    int deleteByCompanyCode(@Param("company_code")String company_code);

    int deleteByGroupCodeAndIncomeCode(@Param("group_code") String group_code,@Param("income_codes")List<String>income_codes);


}