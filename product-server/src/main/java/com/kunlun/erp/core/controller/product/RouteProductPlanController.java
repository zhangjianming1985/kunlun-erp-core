package com.kunlun.erp.core.controller.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.RoutePlanShortRequest;
import com.kunlun.erp.core.dto.product.response.RoutePlanShortRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.product.RoutePlanService;
import com.kunlun.erp.core.validator.product.RoutePlanValidator;
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
 * @ClassName RouteProductPlanController
 * @Description 线路产品行程方案管理
 * @Author Jm.zhang
 * @Date 2019/12/16 17:42
 * @Version 1.0
 **/
@Controller
@Api(position = 8,description = "线路产品行程计划管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteProductPlanController  extends AbstractController {
    @Resource(name = "route_plan_service")
    private RoutePlanService route_plan_service;


    public RouteProductPlanController(RoutePlanValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "列表接口",httpMethod = "POST",notes = "行程计划列表")
    @SystemLog
    @RequestMapping(value = Urls.RoutePlan.LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RoutePlanShortRespDto> list(@RequestBody @Valid RoutePlanShortRequest request, BindingResult result){
        AbstractResponse<RoutePlanShortRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_plan_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}
