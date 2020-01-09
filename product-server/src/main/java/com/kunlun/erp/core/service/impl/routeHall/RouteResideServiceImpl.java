package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteResideDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteResideAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteResideListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteReside;
import com.kunlun.erp.core.mapper.RouteResideMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteResideService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteResideServiceImpl
 * @Description 线路团住宿服务业务实现
 * @Author Jm.zhang
 * @Date 2019/12/20 16:46
 * @Version 1.0
 **/
@Service(value = "route_reside_service")
public class RouteResideServiceImpl extends BaseService implements RouteResideService {
    @Resource
    private RouteResideMapper route_reside_dao;

    @Override
    public AbstractResponse<RouteResideListRespDto> list(RouteResideListRequest request) {
        AbstractResponse<RouteResideListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteResideListRespDto resp_body = new RouteResideListRespDto();
        List<RouteResideDto> list = route_reside_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setReside_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteResideAddRespDto> add(RouteResideAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteResideAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteResideAddRespDto resp_body = new RouteResideAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getReside_data()== null || request.getBody().getReside_data().isEmpty()){
            //删除数据
            int del_count = route_reside_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteResideDto dto :request.getBody().getReside_data()){
            if (StringUtils.isBlank(dto.getReside_code())){
                //创建数据
                RouteReside record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getReside_code());
            }else{
                //更新数据
                RouteReside record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getReside_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = route_reside_dao.deleteByGroupCodeAndResideCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }

    public RouteReside create(String group_code, RouteResideDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteReside record = new RouteReside();
        record.setReside_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setRoom_type(dto.getRoom_type());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setDays(dto.getDays());
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setRoom_count(dto.getRoom_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        route_reside_dao.insertSelective(record);
        return record;
    }


    public RouteReside update(String group_code, RouteResideDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteReside old_record = route_reside_dao.selectByResideCode(dto.getReside_code());
        RouteReside record = new RouteReside();
        record.setId(old_record.getId());
        record.setReside_code(old_record.getReside_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setRoom_type(dto.getRoom_type());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setDays(dto.getDays());
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setRoom_count(dto.getRoom_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setUpdate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        route_reside_dao.insertSelective(record);
        return record;
    }

}
