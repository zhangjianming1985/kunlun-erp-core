package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteOtherDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteOtherAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteOtherListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteOther;
import com.kunlun.erp.core.mapper.RouteOtherMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteOtherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteOtherServiceImpl
 * @Description 线路团其他服务业务实现
 * @Author Jm.zhang
 * @Date 2019-12-23 1:08
 * @Version 1.0
 **/
@Service(value = "route_other_service")
public class RouteOtherServiceImpl extends BaseService implements RouteOtherService {
    @Resource
    private RouteOtherMapper other_dao;

    @Override
    public AbstractResponse<RouteOtherListRespDto> list(RouteOtherListRequest request) {
        AbstractResponse<RouteOtherListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteOtherListRespDto resp_body = new RouteOtherListRespDto();
        List<RouteOtherDto> list = other_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setOther_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteOtherAddRespDto> add(RouteOtherAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteOtherAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteOtherAddRespDto resp_body = new RouteOtherAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getOther_data()== null || request.getBody().getOther_data().isEmpty()){
            //删除数据
            int del_count = other_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteOtherDto dto :request.getBody().getOther_data()){
            if (StringUtils.isBlank(dto.getOther_code())){
                //创建数据
                RouteOther record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getOther_code());
            }else{
                //更新数据
                RouteOther record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getOther_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = other_dao.deleteByGroupCodeAndOtherCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }

    public RouteOther create(String group_code, RouteOtherDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteOther record = new RouteOther();
        record.setOther_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setFee_type(dto.getFee_type());
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setQuantity(dto.getQuantity());
        if (dto.getFee_type()== SysConstant.AmountType.income.getValue()){
            record.setFee_total(new BigDecimal(dto.getFee_total()));
        }else{
            record.setFee_total(new BigDecimal(dto.getFee_total()).negate());
        }
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        other_dao.insertSelective(record);
        return record;
    }

    public RouteOther update(String group_code, RouteOtherDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteOther old_record = other_dao.selectByOtherCode(dto.getOther_code());
        RouteOther record = new RouteOther();
        record.setId(old_record.getId());
        record.setOther_code(old_record.getOther_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setFee_type(dto.getFee_type());
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setQuantity(dto.getQuantity());
        if (dto.getFee_type()== SysConstant.AmountType.income.getValue()){
            record.setFee_total(new BigDecimal(dto.getFee_total()));
        }else{
            record.setFee_total(new BigDecimal(dto.getFee_total()).negate());
        }

        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        other_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
