package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.condition.RouteHallCondition;
import com.kunlun.erp.core.dto.finance.PaymentDto;
import com.kunlun.erp.core.dto.finance.request.PaymentListReqDto;
import com.kunlun.erp.core.dto.routeHall.PersonUpdateNotifyDto;
import com.kunlun.erp.core.dto.routeHall.ProductUpdateNotifyDto;
import com.kunlun.erp.core.dto.routeHall.RouteHallDto;
import com.kunlun.erp.core.dto.finance.RouteHallEndDto;
import com.kunlun.erp.core.entity.RouteHall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteHallMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByPricePlanCode(@Param("price_plan_code") String price_plan_code);

    int deleteByPricePlanCodeAndDate(@Param("price_plan_code") String price_plan_code,@Param("departure_date") List<String> departure_date);

    int deleteByGroupCode(@Param("group_code_list") List<String> group_code_list);

    int deleteByProductCode(@Param("product_code") String product_code);

    int insertSelective(RouteHall record);

    RouteHall selectByPrimaryKey(Integer id);

    RouteHall selectByGroupCode(@Param("group_code") String group_code);

    int updateByPrimaryKeySelective(RouteHall record);

    int updateByProductCode(ProductUpdateNotifyDto record);

    int updateByPerson(PersonUpdateNotifyDto notify);

    List<RouteHallDto> selectDtoByCondition(RouteHallCondition condition);

    List<RouteHallEndDto> selectListDtoEndByCondition(RouteHallCondition condition);

    int selectStatusByGroupCode(@Param("group_code") String group_code);

    List<PaymentDto> selectPaymentDtoByCondition(PaymentListReqDto condition);

    List<String> selectGroupCodeByProductCode(@Param("product_code") String product_code);

}