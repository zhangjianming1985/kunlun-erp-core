package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteTicketDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTicketAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTicketListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteTicket;
import com.kunlun.erp.core.mapper.RouteTicketMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteTicketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteTicketServiceImpl
 * @Description 线路团景点门票服务业务实现
 * @Author Jm.zhang
 * @Date 2019/12/20 18:24
 * @Version 1.0
 **/
@Service(value = "route_ticket_service")
public class RouteTicketServiceImpl extends BaseService implements RouteTicketService {
    @Resource
    private RouteTicketMapper ticket_dao;

    @Override
    public AbstractResponse<RouteTicketListRespDto> list(RouteTicketListRequest request) {
        AbstractResponse<RouteTicketListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteTicketListRespDto resp_body = new RouteTicketListRespDto();
        List<RouteTicketDto> list = ticket_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setTicket_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteTicketAddRespDto> add(RouteTicketAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteTicketAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteTicketAddRespDto resp_body = new RouteTicketAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getTicket_data()== null || request.getBody().getTicket_data().isEmpty()){
            //删除数据
            int del_count = ticket_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteTicketDto dto :request.getBody().getTicket_data()){
            if (StringUtils.isBlank(dto.getTicket_code())){
                //创建数据
                RouteTicket record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getTicket_code());
            }else{
                //更新数据
                RouteTicket record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getTicket_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = ticket_dao.deleteByGroupCodeAndTicketCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }

    public RouteTicket create(String group_code, RouteTicketDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTicket record = new RouteTicket();
        record.setTicket_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setTicket_type(dto.getTicket_type());
        record.setTicket_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getTicket_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setTicket_count(dto.getTicket_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        ticket_dao.insertSelective(record);
        return record;
    }

    public RouteTicket update(String group_code, RouteTicketDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTicket old_record = ticket_dao.selectByTicketCode(dto.getTicket_code());
        RouteTicket record = new RouteTicket();
        record.setId(old_record.getId());
        record.setTicket_code(old_record.getTicket_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setTicket_type(dto.getTicket_type());
        record.setTicket_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getTicket_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setTicket_count(dto.getTicket_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        ticket_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
