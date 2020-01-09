package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteOtherListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteOtherAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteOtherListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteOtherService;
import com.kunlun.erp.core.validator.routeHall.RouteOtherValidator;
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
 * @ClassName RouteOtherController
 * @Description 线路团的其他数据管理
 * @Author Jm.zhang
 * @Date 2019-12-23 1:06
 * @Version 1.0
 **/

@Controller
@Api(position = 15,description = "线路团的其他数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteOtherController extends AbstractController {
    @Resource(name = "route_other_service")
    private RouteOtherService route_other_service;
    public RouteOtherController(RouteOtherValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的其他数据列表",httpMethod = "POST",notes = "线路团的其他数据列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_OTHER,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteOtherListRespDto> list(@RequestBody @Valid RouteOtherListRequest request, BindingResult result){
        AbstractResponse<RouteOtherListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_other_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置线路团的其他数据",httpMethod = "POST",notes = "设置线路团的其他数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_OTHER,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteOtherAddRespDto> add(@RequestBody @Valid RouteOtherAddRequest request, BindingResult result){
        AbstractResponse<RouteOtherAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_other_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
