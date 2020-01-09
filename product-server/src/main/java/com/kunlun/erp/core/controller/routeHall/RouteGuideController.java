package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteGuideAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteGuideListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteGuideService;
import com.kunlun.erp.core.validator.routeHall.RouteGuideValidator;
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
 * @ClassName RouteGuideController
 * @Description 线路团的导服数据管理
 * @Author Jm.zhang
 * @Date 2019/12/19 19:21
 * @Version 1.0
 **/
@Controller
@Api(position = 11,description = "线路团的导服数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteGuideController extends AbstractController {
    @Resource(name = "route_guide_service")
    private RouteGuideService route_guide_service;
    public RouteGuideController(RouteGuideValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的导服列表",httpMethod = "POST",notes = "线路团的导服列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_GUIDES,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteGuideListRespDto> list(@RequestBody @Valid RouteGuideListRequest request, BindingResult result){
        AbstractResponse<RouteGuideListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_guide_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置导服数据",httpMethod = "POST",notes = "为一个线路团设置到导服数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_GUIDE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteGuideAddRespDto> add(@RequestBody @Valid RouteGuideAddRequest request, BindingResult result){
        AbstractResponse<RouteGuideAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_guide_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
