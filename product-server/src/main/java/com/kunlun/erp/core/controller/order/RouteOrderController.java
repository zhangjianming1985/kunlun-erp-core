package com.kunlun.erp.core.controller.order;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDeleteRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDetailRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListRequest;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderAddRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderDeleteRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderDetailRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.RouteOrderListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.order.RouteOrderService;
import com.kunlun.erp.core.validator.routeOrder.RouteOrderValidator;
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
 * @ClassName RouteOrderController
 * @Description 线路订单管理
 * @Author Jm.zhang
 * @Date 2019/12/23 12:54
 * @Version 1.0
 **/
@Controller
@Api(position = 18,description = "线路订单管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteOrderController extends AbstractController {
    @Resource(name = "route_order_service")
    private RouteOrderService route_order_service;

    public RouteOrderController(RouteOrderValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路订单详情",httpMethod = "POST",notes = "线路订单详情")
    @SystemLog
    @RequestMapping(value = Urls.RouteOrder.DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteOrderDetailRespDto> detail(@RequestBody @Valid RouteOrderDetailRequest request, BindingResult result){
        AbstractResponse<RouteOrderDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_order_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @ApiOperation(value = "线路报名",httpMethod = "POST",notes = "线路报名 创建更新订单")
    @SystemLog
    @RequestMapping(value = Urls.RouteOrder.ADD,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteOrderAddRespDto> add(@RequestBody @Valid RouteOrderAddRequest request, BindingResult result){
        AbstractResponse<RouteOrderAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_order_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "线路订单列表",httpMethod = "POST",notes = "线路订单列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteOrder.LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteOrderListRespDto> list(@RequestBody @Valid RouteOrderListRequest request, BindingResult result){
        AbstractResponse<RouteOrderListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_order_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "删除订单",httpMethod = "POST",notes = "删除订单")
    @SystemLog
    @RequestMapping(value = Urls.RouteOrder.DELETE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteOrderDeleteRespDto> delete(@RequestBody @Valid RouteOrderDeleteRequest request, BindingResult result){
        AbstractResponse<RouteOrderDeleteRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_order_service.delete(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
