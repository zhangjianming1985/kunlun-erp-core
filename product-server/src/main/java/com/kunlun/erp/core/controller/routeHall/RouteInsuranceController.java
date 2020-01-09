package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteInsuranceListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteInsuranceAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteInsuranceListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteInsuranceService;
import com.kunlun.erp.core.validator.routeHall.RouteInsuranceValidator;
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
 * @ClassName RouteInsuranceController
 * @Description 线路团的保险数据管理
 * @Author Jm.zhang
 * @Date 2019-12-23 0:12
 * @Version 1.0
 **/
@Controller
@Api(position = 12,description = "线路团的保险数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteInsuranceController extends AbstractController {
    @Resource(name = "route_insurance_service")
    private RouteInsuranceService route_insurance_service;

    public RouteInsuranceController(RouteInsuranceValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的保险数据列表",httpMethod = "POST",notes = "线路团的保险数据列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_INSURANCE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteInsuranceListRespDto> list(@RequestBody @Valid RouteInsuranceListRequest request, BindingResult result){
        AbstractResponse<RouteInsuranceListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_insurance_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置线路团的保险数据",httpMethod = "POST",notes = "设置线路团的保险数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_INSURANCE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteInsuranceAddRespDto> add(@RequestBody @Valid RouteInsuranceAddRequest request, BindingResult result){
        AbstractResponse<RouteInsuranceAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_insurance_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
