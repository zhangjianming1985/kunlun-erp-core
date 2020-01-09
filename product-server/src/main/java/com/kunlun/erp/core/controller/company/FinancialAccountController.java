package com.kunlun.erp.core.controller.company;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.request.FinancialAccountListRequest;
import com.kunlun.erp.core.dto.company.response.FinancialAccountListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.company.FinancialAccountService;
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
 * @ClassName FinancialAccountController
 * @Description 金融账户控制层
 * @Author Jm.zhang
 * @Date 2019/11/22 16:17
 * @Version 1.0
 **/
@Controller
@Api(position = 4,description = "金融账户管理，含 供应商、销售渠道的金融账户",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class FinancialAccountController extends AbstractController {
    @Resource(name = "financial_account_service")
    private FinancialAccountService financial_account_service;

    public FinancialAccountController(FinancialValidator validator) {
        super(validator);
    }


    @ApiOperation(value = "金融账户列表",httpMethod = "POST",notes = "金融账户列表")
    @SystemLog
    @RequestMapping(value = Urls.Financial.ACCOUNT_LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<FinancialAccountListRespDto> accountList(@RequestBody @Valid FinancialAccountListRequest request, BindingResult result){
        AbstractResponse<FinancialAccountListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = financial_account_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
