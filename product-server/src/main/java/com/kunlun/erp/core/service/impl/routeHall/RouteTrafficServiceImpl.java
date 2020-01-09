package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteTrafficDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTrafficAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTrafficListRespDto;
import com.kunlun.erp.core.dto.routeOrder.OrderClientDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteTraffic;
import com.kunlun.erp.core.mapper.OrderClientMapper;
import com.kunlun.erp.core.mapper.RouteTrafficMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.order.OrderClientService;
import com.kunlun.erp.core.service.routeHall.RouteTrafficService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteTrafficServiceImpl
 * @Description 线路团交通票务服务实现
 * @Author Jm.zhang
 * @Date 2019-12-24 23:54
 * @Version 1.0
 **/
@Service(value = "route_traffic_service")
public class RouteTrafficServiceImpl extends BaseService implements RouteTrafficService {
    @Resource
    private RouteTrafficMapper traffic_dao;
    @Resource
    private OrderClientMapper client_dao;
    @Resource(name = "order_client_service")
    private OrderClientService order_client_service;
    @Override
    public AbstractResponse<RouteTrafficListRespDto> list(RouteTrafficListRequest request) {
        AbstractResponse<RouteTrafficListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteTrafficListRespDto resp_body = new RouteTrafficListRespDto();
        List<RouteTrafficDto> traffic_list = traffic_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setTraffic_data(traffic_list);
        List<OrderClientDto>  client_list = client_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setClient_data(client_list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteTrafficAddRespDto> add(RouteTrafficAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteTrafficAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteTrafficAddRespDto resp_body = new RouteTrafficAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getTraffic_data()== null || request.getBody().getTraffic_data().isEmpty()){
            //删除数据
            int del_count = traffic_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteTrafficDto dto :request.getBody().getTraffic_data()){
            if (StringUtils.isBlank(dto.getTraffic_code())){
                //创建数据
                RouteTraffic record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getTraffic_code());
            }else{
                //更新数据
                RouteTraffic record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getTraffic_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = traffic_dao.deleteByGroupCodeAndTrafficCode(request.getBody().getGroup_code(),exist_code_list);
        }

        //更新出游人票务状态
        order_client_service.updateTrafficState(request.getBody().getClient_data());
        response.setBody(resp_body);
        return response;
    }

    public RouteTraffic create(String group_code, RouteTrafficDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTraffic record = new RouteTraffic();
        record.setTraffic_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setTraffic_type(dto.getTraffic_type());
        record.setDeparture_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getDeparture_date()));
        record.setDeparture(dto.getDeparture());
        record.setDestination(dto.getDestination());
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setTraffic_count(dto.getTraffic_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        traffic_dao.insertSelective(record);
        return record;
    }

    public RouteTraffic update(String group_code, RouteTrafficDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTraffic old_record = traffic_dao.selectByTrafficCode(dto.getTraffic_code());
        RouteTraffic record = new RouteTraffic();
        record.setId(old_record.getId());
        record.setTraffic_code(old_record.getTraffic_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setTraffic_type(dto.getTraffic_type());
        record.setDeparture_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getDeparture_date()));
        record.setDeparture(dto.getDeparture());
        record.setDestination(dto.getDestination());
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setTraffic_count(dto.getTraffic_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setUpdate_time(new Date());
        traffic_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
