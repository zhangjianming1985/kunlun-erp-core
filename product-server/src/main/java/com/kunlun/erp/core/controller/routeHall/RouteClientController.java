package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteClientListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteClientListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteClientService;
import com.kunlun.erp.core.validator.routeHall.RouteClientValidator;
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
 * @ClassName RouteClientController
 * @Description 线路团的出游人数据管理
 * @Author Jm.zhang
 * @Date 2019/12/24 20:14
 * @Version 1.0
 **/
@Controller
@Api(position = 14,description = "线路团的出游人数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteClientController extends AbstractController {
    @Resource(name = "route_client_service")
    private RouteClientService route_client_service;

    public RouteClientController(RouteClientValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团的出游人数据列表",httpMethod = "POST",notes = "线路团的出游人数据列表")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST_CLIENT,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteClientListRespDto> list(@RequestBody @Valid RouteClientListRequest request, BindingResult result){
        AbstractResponse<RouteClientListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_client_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
