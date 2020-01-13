package com.kunlun.erp.core.component;

import com.kunlun.erp.core.common.configuration.PermissionKeyProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RouteHallCondition;
import com.kunlun.erp.core.dto.finance.request.HallProductEndListRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductListRequest;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.mapper.RouteOrderMapper;
import com.kunlun.erp.core.service.account.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteHallComponent
 * @Description 线路大厅组件
 * @Author Jm.zhang
 * @Date 2019/12/18 17:12
 * @Version 1.0
 **/
@Component(value = "route_hall_component")
public class RouteHallComponent {
    @Resource
    private RouteOrderMapper order_dao;
    @Resource(name = "permission_service")
    private PermissionService permission_service;
    @Resource
    private PermissionKeyProperties per_properties;

    /**
     * 获取散团日期
     * @param start_date
     * @param days
     * @return
     */
    public String getDisbandDate(Date start_date,int days){
        Date d =DateUtil.getDateAfter(start_date,days);
        return DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,d);
    }

    /**
     * 行程状态
     * @param departure_date
     * @return
     */
    public int getProductStatus(Date departure_date,Date disband_date){
        Date now= new Date();
        if (now.getTime() >=disband_date.getTime()){
            //行程结束
            return SysConstant.HallProductStatus.end_trip.getValue();
        }else{
            if (now.getTime() > departure_date.getTime()){
                return SysConstant.HallProductStatus.pending_trip.getValue();
            }else{
                return SysConstant.HallProductStatus.on_trip.getValue();
            }
        }
    }

    /**
     * 发团审核检索条件转换
     * @param request
     * @return
     */
    public RouteHallCondition convert(HallProductEndListRequest request){
        List<String> group_code_list = new ArrayList<>();
        RouteHallCondition  condition = new RouteHallCondition();
        condition.setPage_index(request.getBody().getPage_index());
        condition.setPage_size(request.getBody().getPage_size());
        condition.setProduct_code(request.getBody().getProduct_code());
        condition.setProduct_name(request.getBody().getProduct_name());
        condition.setCreator_id(request.getBody().getCreator_id());
        if (StringUtils.isNotBlank(request.getBody().getGroup_code())){
            condition.setGroup_code(request.getBody().getGroup_code());
        }
        if (StringUtils.isNotBlank(request.getBody().getCompany_name())){
            List<String> group_codes = order_dao.selectGroupCodeByCompanyName(request.getBody().getCompany_name());
            if (group_codes!=null && group_codes.isEmpty()==false){
                group_code_list.addAll(group_codes);
            }
        }
        if (StringUtils.isNotBlank(request.getBody().getLeader_name())){
            List<String> group_codes = order_dao.selectGroupCodeByLeaderName(request.getBody().getLeader_name());
            if (group_codes!=null && group_codes.isEmpty()==false){
                group_code_list.addAll(group_codes);
            }
        }
        if (StringUtils.isNotBlank(request.getBody().getContact_name())){
            List<String> group_codes = order_dao.selectGroupCodeByContactName(request.getBody().getContact_name());
            if (group_codes!=null && group_codes.isEmpty()==false){
                group_code_list.addAll(group_codes);
            }
        }
        condition.setDeparture_start_date(request.getBody().getDeparture_date_start());
        condition.setDeparture_end_date(request.getBody().getDeparture_date_end());
        condition.setClient_name(request.getBody().getClient_name());
        condition.setClient_mobile(request.getBody().getClient_mobile());
        condition.setOrder_create_start_date(request.getBody().getOrder_create_start_date());
        condition.setOrder_create_end_date(request.getBody().getOrder_create_end_date());
        condition.setOrder_state(request.getBody().getOrder_state());
        condition.setOrder_code(request.getBody().getOrder_code());
        condition.setCompany_order_code(request.getBody().getCompany_order_code());
        if (StringUtils.isNotBlank(request.getBody().getGroup_code())){
            group_code_list.add(request.getBody().getGroup_code());
        }
        return condition;

    }


    /**
     * 报名大厅检索条件转换
     * @param request
     * @return
     */
    public RouteHallCondition convert(HallProductListRequest request){
        RouteHallCondition  condition = new RouteHallCondition();
        condition.setPage_index(request.getBody().getPage_index());
        condition.setPage_size(request.getBody().getPage_size());
        condition.setGroup_code(request.getBody().getGroup_code());
        condition.setProduct_code(request.getBody().getProduct_code());
        condition.setProduct_name(request.getBody().getProduct_name());
        condition.setInternal_code(request.getBody().getInternal_code());
        AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
            condition.setUid(permission_dto.getBody().getUid());
        }

        //发团日期区间检索
        if (StringUtils.isNotBlank(request.getBody().getStart_date())){
            condition.setDeparture_start_date(request.getBody().getStart_date());
        }
        if (StringUtils.isNotBlank(request.getBody().getEnd_date())){
            condition.setDeparture_end_date(request.getBody().getEnd_date());
        }
        if (StringUtils.isNotBlank(request.getBody().getCategory_code())){
            condition.setCategory_code(request.getBody().getCategory_code());
        }
        if (request.getBody().getStatus()!=null){
            if (request.getBody().getStatus() == SysConstant.HallProductStatus.pending_trip.getValue()){
                //检索待出行,发团日期大于当前日期
                condition.setDeparture_start_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,new Date()));
                condition.setStatus(null);
                //排除取消和已删除
                List<Integer> not_include_status = new ArrayList<>();
                not_include_status.add(SysConstant.HallProductStatus.cancel_trip.getValue());
                not_include_status.add(SysConstant.HallProductStatus.delete_trip.getValue());
                condition.setNot_include_status(not_include_status);
            }else if (request.getBody().getStatus() == SysConstant.HallProductStatus.on_trip.getValue()){
                //检索行程中，发团日期小于等于当前日期 且 散团日期大于当前日期
                condition.setDeparture_end_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,new Date()));
                condition.setDisband_start_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,new Date()));
                condition.setStatus(null);
                //排除取消和已删除
                List<Integer> not_include_status = new ArrayList<>();
                not_include_status.add(SysConstant.HallProductStatus.cancel_trip.getValue());
                not_include_status.add(SysConstant.HallProductStatus.delete_trip.getValue());
                condition.setNot_include_status(not_include_status);
            }else if (request.getBody().getStatus() == SysConstant.HallProductStatus.end_trip.getValue()){
                //行程结束 散团日期小于当前日期
                condition.setDisband_end_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,new Date()));
                condition.setStatus(null);
                //排除取消和已删除
                List<Integer> not_include_status = new ArrayList<>();
                not_include_status.add(SysConstant.HallProductStatus.cancel_trip.getValue());
                not_include_status.add(SysConstant.HallProductStatus.delete_trip.getValue());
                condition.setNot_include_status(not_include_status);
            }else{
                condition.setStatus(request.getBody().getStatus());
            }
        }
        return condition;

    }
}
