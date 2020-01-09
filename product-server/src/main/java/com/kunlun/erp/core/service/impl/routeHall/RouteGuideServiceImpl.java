package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteGuidesDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteGuideAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteGuideListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteGuides;
import com.kunlun.erp.core.mapper.RouteGuidesMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteGuideService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteGuideServiceImpl
 * @Description 线路导服服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/19 19:11
 * @Version 1.0
 **/
@Service(value = "route_guide_service")
public class RouteGuideServiceImpl extends BaseService implements RouteGuideService {
    @Resource
    private RouteGuidesMapper route_guide_dao;
    @Override
    public AbstractResponse<RouteGuideListRespDto> list(RouteGuideListRequest request) {
        AbstractResponse<RouteGuideListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteGuideListRespDto resp_body = new RouteGuideListRespDto();
        List<RouteGuidesDto> list = route_guide_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setGuides_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteGuideAddRespDto> add(RouteGuideAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteGuideAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteGuideAddRespDto resp_body = new RouteGuideAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getGuide_data()== null || request.getBody().getGuide_data().isEmpty()){
            //删除数据
            int del_count = route_guide_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteGuidesDto dto :request.getBody().getGuide_data()){
            if (StringUtils.isBlank(dto.getGuide_code())){
                //创建数据
                RouteGuides record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getGuide_code());
            }else{
                //更新数据
                RouteGuides record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getGuide_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = route_guide_dao.deleteByGroupCodeAndGuideCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }


    public RouteGuides create(String group_code,RouteGuidesDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteGuides record = new RouteGuides();
        record.setGuide_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setClient_count(dto.getClient_count());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setDays(dto.getDays());
        record.setCurrency(dto.getCurrency());
        record.setFee_daily(new BigDecimal(dto.getFee_daily()));
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_times(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        route_guide_dao.insertSelective(record);
        return record;
    }


    public RouteGuides update(String group_code,RouteGuidesDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteGuides old_record = route_guide_dao.selectByGuideCode(dto.getGuide_code());
        RouteGuides record = new RouteGuides();
        record.setId(old_record.getId());
        record.setGuide_code(old_record.getGuide_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setClient_count(dto.getClient_count());
        record.setStart_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getStart_date()));
        record.setEnd_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getEnd_date()));
        record.setDays(dto.getDays());
        record.setCurrency(dto.getCurrency());
        record.setFee_daily(new BigDecimal(dto.getFee_daily()));
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setUpdate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        route_guide_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
