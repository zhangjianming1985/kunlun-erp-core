package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.entity.RouteOrderIncome;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteOrderIncomeMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByGroupCode(@Param("group_code")String group_code);

    int deleteByProductCode(@Param("product_code")String product_code);

    int deleteByGroupCodeList(@Param("group_code_list")List<String> group_code_list);

    int deleteByOrderCode(@Param("order_code")String order_code);

    int deleteByOrderCodeAndIncomeCode(@Param("order_code")String order_code, @Param("income_codes")List<String>income_codes);

    int deleteByCompanyCode(@Param("company_code")String company_code);

    int insertSelective(RouteOrderIncome record);

    RouteOrderIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RouteOrderIncome record);

    RouteOrderIncome selectByIncomeCode(@Param("income_code")String income_code);

    List<OrderIncomeDto> selectDtoByOrderCode(@Param("order_code")String order_code);

    List<OrderIncomeDto> selectDtoByGroupCode(@Param("group_code")String group_code);


}