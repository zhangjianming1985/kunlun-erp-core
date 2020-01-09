package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteMealAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteMealListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteMealService;
import com.kunlun.erp.core.validator.routeHall.RouteMealValidator;
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
 * @ClassName RouteMealController
 * @Description 线路团的用餐数据管理
 * @Author Jm.zhang
 * @Date 2019-12-22 19:09
 * @Version 1.0
 **/
@Controller
@Api(position = 13,description = "线路团的用餐数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteMealController  extends AbstractController {
    @Resource(name = "route_meal_service")
    private RouteMealService route_meal_service;

    public RouteMealController(RouteMealValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的用餐数据列表",httpMethod = "POST",notes = "线路团的用餐数据列表\"")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_MEAL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteMealListRespDto> list(@RequestBody @Valid RouteMealListRequest request, BindingResult result){
        AbstractResponse<RouteMealListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_meal_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "设置线路团的用餐数据",httpMethod = "POST",notes = "为一个线路团设置用餐数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_MEAL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteMealAddRespDto> add(@RequestBody @Valid RouteMealAddRequest request, BindingResult result){
        AbstractResponse<RouteMealAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_meal_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
