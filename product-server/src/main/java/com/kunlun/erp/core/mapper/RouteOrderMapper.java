package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.finance.CollectedDto;
import com.kunlun.erp.core.dto.finance.request.CollectedListReqDto;
import com.kunlun.erp.core.dto.routeHall.PeopleCount;
import com.kunlun.erp.core.dto.routeHall.RoomCount;
import com.kunlun.erp.core.dto.routeHall.SalesChannelDto;
import com.kunlun.erp.core.dto.routeOrder.OrderListDto;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListReqDto;
import com.kunlun.erp.core.entity.RouteOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByOrderCode(@Param("order_code")String order_code);

    int deleteByProductCode(@Param("product_code")String product_code);

    int deleteByCompanyCode(@Param("company_code")String company_code);

    int deleteByGroupCodeList(@Param("group_code_list")List<String> group_code_list);

    int insertSelective(RouteOrder record);

    RouteOrder selectByPrimaryKey(Integer id);

    RouteOrder selectByOrderCode(@Param("order_code") String order_code);

    List<RouteOrder> selectByProductCode(@Param("product_code") String product_code);

    int updateByPrimaryKeySelective(RouteOrder record);

    List<OrderListDto> selectListDtoByCondition(RouteOrderListReqDto condition);

    PeopleCount peopleCountByGroupCode(@Param("group_code") String group_code);

    RoomCount roomCountByGroupCode(@Param("group_code") String group_code);

    List<RouteOrder> selectByGroupCode(@Param("group_code") String group_code);

    SalesChannelDto selectSalesChannelByOrderCode(@Param("order_code") String order_code);

    List<String> selectGroupCodeByCompanyName(@Param("company_name")String company_name);

    List<String> selectGroupCodeByLeaderName(@Param("leader_name")String leader_name);

    List<String> selectGroupCodeByContactName(@Param("contact_name")String contact_name);

    List<CollectedDto> selectCollectDtoByCondition(CollectedListReqDto condition);

}