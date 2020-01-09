package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteInsuranceDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteInsuranceAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteInsuranceListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteInsurance;
import com.kunlun.erp.core.mapper.RouteInsuranceMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteInsuranceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteInsuranceServiceImpl
 * @Description 线路团保险服务业务实现
 * @Author Jm.zhang
 * @Date 2019-12-23 0:19
 * @Version 1.0
 **/
@Service(value = "route_insurance_service")
public class RouteInsuranceServiceImpl extends BaseService implements RouteInsuranceService {
    @Resource
    private RouteInsuranceMapper insurance_dao;

    @Override
    public AbstractResponse<RouteInsuranceListRespDto> list(RouteInsuranceListRequest request) {
        AbstractResponse<RouteInsuranceListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteInsuranceListRespDto resp_body = new RouteInsuranceListRespDto();
        List<RouteInsuranceDto> list = insurance_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setInsurance_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteInsuranceAddRespDto> add(RouteInsuranceAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteInsuranceAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteInsuranceAddRespDto resp_body = new RouteInsuranceAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getInsurance_data()== null || request.getBody().getInsurance_data().isEmpty()){
            //删除数据
            int del_count = insurance_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteInsuranceDto dto :request.getBody().getInsurance_data()){
            if (StringUtils.isBlank(dto.getInsurance_code())){
                //创建数据
                RouteInsurance record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getInsurance_code());
            }else{
                //更新数据
                RouteInsurance record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getInsurance_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = insurance_dao.deleteByGroupCodeAndInsuranceCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }
    public RouteInsurance create(String group_code, RouteInsuranceDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteInsurance record = new RouteInsurance();
        record.setInsurance_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setInsurance_type(dto.getInsurance_type());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setInsurance_count(dto.getInsurance_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        insurance_dao.insertSelective(record);
        return record;
    }

    public RouteInsurance update(String group_code, RouteInsuranceDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteInsurance old_record = insurance_dao.selectByInsuranceCode(dto.getInsurance_code());
        RouteInsurance record = new RouteInsurance();
        record.setId(old_record.getId());
        record.setInsurance_code(old_record.getInsurance_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setInsurance_type(dto.getInsurance_type());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setInsurance_count(dto.getInsurance_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        insurance_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
