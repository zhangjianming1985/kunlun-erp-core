package com.kunlun.erp.core.controller.finance;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.finance.request.*;
import com.kunlun.erp.core.dto.finance.response.*;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.finance.FinanceService;
import com.kunlun.erp.core.validator.financial.FinancialValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName AuditController
 * @Description 财务审核管理
 * @Author Jm.zhang
 * @Date 2019/12/27 10:28
 * @Version 1.0
 **/
@Controller
@Api(position = 10,description = "财务管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class FinanceManageController extends AbstractController {
    @Resource(name = "finance_manage_service")
    private FinanceService finance_manage_service;
    public FinanceManageController(FinancialValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "发团审核线路产品列表接口",httpMethod = "POST",notes = "发团审核线路产品列表接口")
    @SystemLog
    @RequestMapping(value = Urls.FinanceManage.FINISH_ROUTE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<HallProductEndListRespDto> listFinish(@RequestBody @Valid HallProductEndListRequest request, BindingResult result){
        AbstractResponse<HallProductEndListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = finance_manage_service.finish_route(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "审核财务",httpMethod = "POST",notes = "审核已结束团的财务数据")
    @SystemLog
    @RequestMapping(value = Urls.FinanceManage.AUDIT_ROUTE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<FinanceAuditRespDto> auditRoute(@RequestBody @Valid FinanceAuditRequest request, BindingResult result){
        AbstractResponse<FinanceAuditRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = finance_manage_service.audit(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "获取审核结果",httpMethod = "POST",notes = "获取审核结果")
    @SystemLog
    @RequestMapping(value = Urls.FinanceManage.AUDIT_RESULT,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<FinanceAuditResultRespDto> auditRoute(@RequestBody @Valid FinanceAuditResultRequest request, BindingResult result){
        AbstractResponse<FinanceAuditResultRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = finance_manage_service.audit_result(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "销售渠道应收款列表",httpMethod = "POST",notes = "销售渠道应收款列表")
    @SystemLog
    @RequestMapping(value = Urls.FinanceManage.COLLECT_LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<CollectedListRespDto> collectCash(@RequestBody @Valid CollectedListRequest request, BindingResult result){
        AbstractResponse<CollectedListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = finance_manage_service.collect_list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "供应商应付款列表",httpMethod = "POST",notes = "供应商应付款列表")
    @SystemLog
    @RequestMapping(value = Urls.FinanceManage.PAYMENT_LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PaymentListRespDto> paymentCash(@RequestBody @Valid PaymentListRequest request, BindingResult result){
        AbstractResponse<PaymentListRespDto> response = dtoFactory.createResponse(request.getHeader());

        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = finance_manage_service.payment_list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
