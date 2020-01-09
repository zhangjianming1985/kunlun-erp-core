package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMotorcadeListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteMotorcadeAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteMotorcadeListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteMotorcadeService;
import com.kunlun.erp.core.validator.routeHall.RouteMotorcadeValidator;
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
 * @ClassName RouteMotorcadeController
 * @Description 线路团的车队数据管理
 * @Author Jm.zhang
 * @Date 2019-12-22 22:45
 * @Version 1.0
 **/
@Controller
@Api(position = 14,description = "线路团的车队数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteMotorcadeController extends AbstractController {
    @Resource(name = "route_motorcade_service")
    private RouteMotorcadeService route_motorcade_service;

    public RouteMotorcadeController(RouteMotorcadeValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的车队数据列表",httpMethod = "POST",notes = "线路团的车队数据列表\"")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_MOTORCADE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteMotorcadeListRespDto> list(@RequestBody @Valid RouteMotorcadeListRequest request, BindingResult result){
        AbstractResponse<RouteMotorcadeListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_motorcade_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置线路团的车队数据",httpMethod = "POST",notes = "为一个线路团设置车队数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_MOTORCADE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteMotorcadeAddRespDto> add(@RequestBody @Valid RouteMotorcadeAddRequest request, BindingResult result){
        AbstractResponse<RouteMotorcadeAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_motorcade_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
