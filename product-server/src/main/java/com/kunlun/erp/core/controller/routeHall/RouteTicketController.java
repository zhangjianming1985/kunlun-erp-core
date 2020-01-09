package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTicketAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTicketListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteTicketService;
import com.kunlun.erp.core.validator.routeHall.RouteTicketValidator;
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
 * @ClassName RouteTicketController
 * @Description 线路团的景点门票数据管理
 * @Author Jm.zhang
 * @Date 2019/12/20 18:40
 * @Version 1.0
 **/
@Controller
@Api(position = 17,description = "线路团的景点门票数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteTicketController  extends AbstractController {
    @Resource(name = "route_ticket_service")
    private RouteTicketService route_ticket_service;

    public RouteTicketController(RouteTicketValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的景点门票数据列表",httpMethod = "POST",notes = "线路团的景点门票数据列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_TICKET,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteTicketListRespDto> list(@RequestBody @Valid RouteTicketListRequest request, BindingResult result){
        AbstractResponse<RouteTicketListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_ticket_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置线路团的景点门票数据",httpMethod = "POST",notes = "为一个线路团设置景点门票数据")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_TICKET,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteTicketAddRespDto> add(@RequestBody @Valid RouteTicketAddRequest request, BindingResult result){
        AbstractResponse<RouteTicketAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_ticket_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
