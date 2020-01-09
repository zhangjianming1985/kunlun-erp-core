package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteMotorcadeDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteMotorcadeAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteMotorcadeListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteMotorcade;
import com.kunlun.erp.core.mapper.RouteMotorcadeMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteMotorcadeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteMotorcadeServiceImpl
 * @Description 线路团车队服务业务实现
 * @Author Jm.zhang
 * @Date 2019-12-22 22:35
 * @Version 1.0
 **/
@Service(value = "route_motorcade_service")
public class RouteMotorcadeServiceImpl extends BaseService implements RouteMotorcadeService {
    @Resource
    private RouteMotorcadeMapper motorcade_dao;

    @Override
    public AbstractResponse<RouteMotorcadeListRespDto> list(RouteMotorcadeListRequest request) {
        AbstractResponse<RouteMotorcadeListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteMotorcadeListRespDto resp_body = new RouteMotorcadeListRespDto();
        List<RouteMotorcadeDto> list = motorcade_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setMotorcade_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteMotorcadeAddRespDto> add(RouteMotorcadeAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteMotorcadeAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteMotorcadeAddRespDto resp_body = new RouteMotorcadeAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getMotorcade_data()== null || request.getBody().getMotorcade_data().isEmpty()){
            //删除数据
            int del_count = motorcade_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteMotorcadeDto dto :request.getBody().getMotorcade_data()){
            if (StringUtils.isBlank(dto.getMotorcade_code())){
                //创建数据
                RouteMotorcade record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getMotorcade_code());
            }else{
                //更新数据
                RouteMotorcade record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getMotorcade_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = motorcade_dao.deleteByGroupCodeAndMotorcadeCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }

    public RouteMotorcade create(String group_code, RouteMotorcadeDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteMotorcade record = new RouteMotorcade();
        record.setMotorcade_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setCar_count(dto.getCar_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        motorcade_dao.insertSelective(record);
        return record;
    }
    public RouteMotorcade update(String group_code, RouteMotorcadeDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteMotorcade old_record = motorcade_dao.selectByMotorcadeCode(dto.getMotorcade_code());
        RouteMotorcade record = new RouteMotorcade();
        record.setId(old_record.getId());
        record.setMotorcade_code(old_record.getMotorcade_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setCar_count(dto.getCar_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setUpdate_time(new Date());
        motorcade_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
