package com.kunlun.erp.core.controller.rate;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.RateRequest;
import com.kunlun.erp.core.dto.common.RateRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.rate.RateService;
import com.kunlun.erp.core.validator.common.RateValidator;
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
 * @ClassName RateController
 * @Description 汇率转换
 * @Author Jm.zhang
 * @Date 2020/1/2 9:58
 * @Version 1.0
 **/

@Controller
@Api(position = 1,description = "汇率换算",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RateController extends AbstractController {
    @Resource(name = "rate_service")
    private RateService rate_service;

    public RateController(RateValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "汇率转换接口",httpMethod = "POST",notes = "汇率转换接口")
    @SystemLog
    @RequestMapping(value = Urls.Common.RATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RateRespDto> rate(@RequestBody @Valid RateRequest request, BindingResult result){
        AbstractResponse<RateRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = rate_service.rate(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
