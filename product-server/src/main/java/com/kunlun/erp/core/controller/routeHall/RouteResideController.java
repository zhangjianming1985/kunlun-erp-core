package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteResideListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteResideAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteResideListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteResideService;
import com.kunlun.erp.core.validator.routeHall.RouteResideValidator;
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
 * @ClassName RouteResideController
 * @Description 线路团的住宿数据管理
 * @Author Jm.zhang
 * @Date 2019/12/20 17:15
 * @Version 1.0
 **/
@Controller
@Api(position = 16,description = "线路团的住宿数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteResideController extends AbstractController {
    @Resource(description = "route_reside_service")
    private RouteResideService route_reside_service;

    public RouteResideController(RouteResideValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的住宿数据列表",httpMethod = "POST",notes = "线路团的住宿数据列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_RESIDE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteResideListRespDto> list(@RequestBody @Valid RouteResideListRequest request, BindingResult result){
        AbstractResponse<RouteResideListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_reside_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置线路团的住宿数据",httpMethod = "POST",notes = "为一个线路团设置住宿数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_RESIDE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteResideAddRespDto> add(@RequestBody @Valid RouteResideAddRequest request, BindingResult result){
        AbstractResponse<RouteResideAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_reside_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
