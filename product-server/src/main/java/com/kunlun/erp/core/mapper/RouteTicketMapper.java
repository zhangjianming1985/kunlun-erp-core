package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.routeHall.RouteTicketDto;
import com.kunlun.erp.core.entity.RouteTicket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteTicketMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByCompanyCode(@Param("company_code")String company_code);

    int deleteByGroupCodeList(@Param("group_code_list") List<String> group_code_list);


    int insertSelective(RouteTicket record);


    RouteTicket selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(RouteTicket record);

    int deleteByGroupCode(@Param("group_code") String group_code);

    int deleteByGroupCodeAndTicketCode(@Param("group_code") String group_code,@Param("ticket_codes") List<String> ticket_codes);

    RouteTicket selectByTicketCode(@Param("ticket_code") String ticket_code);

    List<RouteTicketDto> selectDtoByGroupCode(@Param("group_code") String group_code);

}