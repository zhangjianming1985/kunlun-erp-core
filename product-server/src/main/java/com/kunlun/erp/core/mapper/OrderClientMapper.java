package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.entity.OrderClient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderClientMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByOrderCode(@Param("order_code")String order_code);

    int deleteByProductCode(@Param("product_code")String product_code);

    int deleteByCompanyCode(@Param("company_code")String company_code);

    int deleteByOrderCodeAndClientCode(@Param("order_code")String order_code,@Param("client_codes")List<String>client_codes);

    int deleteByGroupCodeList(@Param("group_code_list")List<String> group_code_list);

    int insertSelective(OrderClient record);

    OrderClient selectByPrimaryKey(Integer id);

    OrderClient selectByClientCode(@Param("client_code") String client_code);

    OrderClient selectByClientCodeAndGroupCode(@Param("client_code") String client_code,@Param("group_code") String group_code);

    OrderClient selectByClientCodeAndOrderCode(@Param("client_code") String client_code,@Param("order_code") String order_code);

    int updateByPrimaryKeySelective(OrderClient record);

    List<OrderClientDto> selectDtoByOrderCode(@Param("order_code") String order_code);

    List<OrderClientDto> selectDtoByGroupCode(@Param("group_code") String group_code);

    int updateTrafficStatus(OrderClientDto client);

}