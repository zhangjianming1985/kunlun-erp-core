package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTrafficListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTrafficAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTrafficListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteTrafficService;
import com.kunlun.erp.core.validator.routeHall.RouteTrafficValidator;
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
 * @ClassName RouteTrafficController
 * @Description 线路团的交通票务数据管理
 * @Author Jm.zhang
 * @Date 2019-12-25 0:08
 * @Version 1.0
 **/
@Controller
@Api(position = 14,description = "线路团的交通票务数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteTrafficController  extends AbstractController {
    @Resource(name = "route_traffic_service")
    private RouteTrafficService route_traffic_service;
    public RouteTrafficController(RouteTrafficValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的交通票务数据列表",httpMethod = "POST",notes = "线路团的交通票务数据列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_TRAFFIC,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteTrafficListRespDto> list(@RequestBody @Valid RouteTrafficListRequest request, BindingResult result){
        AbstractResponse<RouteTrafficListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_traffic_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "设置线路团的交通票务数据",httpMethod = "POST",notes = "设置线路团的交通票务数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_TRAFFIC,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteTrafficAddRespDto> add(@RequestBody @Valid RouteTrafficAddRequest request, BindingResult result){
        AbstractResponse<RouteTrafficAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_traffic_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
