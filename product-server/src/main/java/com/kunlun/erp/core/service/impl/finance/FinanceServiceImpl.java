package com.kunlun.erp.core.service.impl.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.component.RouteHallComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RouteHallCondition;
import com.kunlun.erp.core.dto.finance.CollectedDto;
import com.kunlun.erp.core.dto.finance.PaymentDto;
import com.kunlun.erp.core.dto.finance.RouteHallEndDto;
import com.kunlun.erp.core.dto.finance.request.*;
import com.kunlun.erp.core.dto.finance.response.*;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteHall;
import com.kunlun.erp.core.mapper.RouteHallMapper;
import com.kunlun.erp.core.mapper.RouteOrderMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.finance.FinanceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FinanceServiceImpl
 * @Description 财务管理服务接口
 * @Author Jm.zhang
 * @Date 2019/12/27 10:33
 * @Version 1.0
 **/
@Service(value = "finance_manage_service")
public class FinanceServiceImpl extends BaseService implements FinanceService {
    @Resource(name = "route_hall_component")
    private RouteHallComponent route_hall_component;
    @Resource
    private RouteHallMapper route_hall_dao;
    @Resource
    private RouteOrderMapper order_dao;
    @Override
    public AbstractResponse<HallProductEndListRespDto> finish_route(HallProductEndListRequest request) {
        AbstractResponse<HallProductEndListRespDto> response = dtoFactory.createResponse(request.getHeader());
        HallProductEndListRespDto resp_body = new HallProductEndListRespDto();
        RouteHallCondition condition  = route_hall_component.convert(request);
        PageHelper.startPage(condition.getPage_index(), condition.getPage_size(), true);
        List<RouteHallEndDto> list = route_hall_dao.selectListDtoEndByCondition(condition);
        PageInfo<RouteHallEndDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<FinanceAuditRespDto> audit(FinanceAuditRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<FinanceAuditRespDto> response = dtoFactory.createResponse(request.getHeader());
        FinanceAuditRespDto body = new FinanceAuditRespDto();
        RouteHall hall_record = route_hall_dao.selectByGroupCode(request.getBody().getGroup_code());
        RouteHall record = new RouteHall();
        record.setId(hall_record.getId());
        record.setApprove_state(request.getBody().getApprove_state());
        record.setApprove_time(new Date());
        record.setApprove_id(user_info.getBody().getUid());
        record.setApprove_name(user_info.getBody().getUserName());
        if (StringUtils.isNotBlank(request.getBody().getApprove_remarks()))
        record.setApprove_remarks(request.getBody().getApprove_remarks());
        route_hall_dao.updateByPrimaryKeySelective(record);
        body.setGroup_code(request.getBody().getGroup_code());
        response.setBody(body);
        return response;
    }

    @Override
    public AbstractResponse<FinanceAuditResultRespDto> audit_result(FinanceAuditResultRequest request) {
        AbstractResponse<FinanceAuditResultRespDto> response = dtoFactory.createResponse(request.getHeader());
        FinanceAuditResultRespDto body = new FinanceAuditResultRespDto();
        RouteHall record = route_hall_dao.selectByGroupCode(request.getBody().getGroup_code());
        body.setApprove_remarks(record.getApprove_remarks());
        body.setAudit_state(record.getApprove_state());
        response.setBody(body);
        return response;
    }

    @Override
    public AbstractResponse<CollectedListRespDto> collect_list(CollectedListRequest request) {
        AbstractResponse<CollectedListRespDto> response = dtoFactory.createResponse(request.getHeader());
        CollectedListRespDto resp_body = new CollectedListRespDto();
        PageHelper.startPage(request.getBody().getPage_index(), request.getBody().getPage_size(), true);
        List<CollectedDto> list = order_dao.selectCollectDtoByCondition(request.getBody());
        PageInfo<CollectedDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<PaymentListRespDto> payment_list(PaymentListRequest request) {
        AbstractResponse<PaymentListRespDto> response = dtoFactory.createResponse(request.getHeader());
        PaymentListRespDto resp_body = new PaymentListRespDto();
        PageHelper.startPage(request.getBody().getPage_index(), request.getBody().getPage_size(), true);
        List<PaymentDto> list = route_hall_dao.selectPaymentDtoByCondition(request.getBody());
        PageInfo<PaymentDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }
}
