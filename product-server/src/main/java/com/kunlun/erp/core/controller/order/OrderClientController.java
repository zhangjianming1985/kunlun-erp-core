package com.kunlun.erp.core.controller.order;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.request.ClientAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.ClientListRequest;
import com.kunlun.erp.core.dto.routeOrder.response.ClientAddRespDto;
import com.kunlun.erp.core.dto.routeOrder.response.ClientListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.order.OrderClientService;
import com.kunlun.erp.core.validator.routeOrder.OrderClientValidator;
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
 * @ClassName OrderClientController
 * @Description 线路订单出游人管理
 * @Author Jm.zhang
 * @Date 2019/12/24 14:31
 * @Version 1.0
 **/
@Controller
@Api(description = "线路订单出游人管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class OrderClientController extends AbstractController {
    @Resource(name = "order_client_service")
    private OrderClientService order_client_service;

    public OrderClientController(OrderClientValidator validator) {
        super(validator);
    }


    @ApiOperation(value = "出游人管理",httpMethod = "POST",notes = "创建 更新 删除出游人")
    @SystemLog
    @RequestMapping(value = Urls.RouteOrder.ADD_CLIENT,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ClientAddRespDto> add(@RequestBody @Valid ClientAddRequest request, BindingResult result){
        AbstractResponse<ClientAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = order_client_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "出游人列表",httpMethod = "POST",notes = "出游人列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteOrder.LIST_CLIENT,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ClientListRespDto> list(@RequestBody @Valid ClientListRequest request, BindingResult result){
        AbstractResponse<ClientListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = order_client_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
