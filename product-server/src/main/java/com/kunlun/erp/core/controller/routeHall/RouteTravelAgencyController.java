package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteTravelAgencyAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTravelAgencyListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteTravelAgencyService;
import com.kunlun.erp.core.validator.routeHall.RouteTravelAgencyValidator;
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
 * @ClassName RouteTravelAgencyController
 * @Description 线路团的地接旅行社数据管理
 * @Author Jm.zhang
 * @Date 2019/12/25 13:36
 * @Version 1.0
 **/
@Controller
@Api(position = 14,description = "线路团的地接旅行社数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteTravelAgencyController extends AbstractController {
    @Resource(name = "route_travelAgency_service")
    private RouteTravelAgencyService route_travelAgency_service;
    public RouteTravelAgencyController(RouteTravelAgencyValidator validator) {
        super(validator);
    }


    @ApiOperation(value = "线路团的地接旅行社详情",httpMethod = "POST",notes = "线路团的地接旅行社详情")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.DETAIL_TRAVEL_AGENCY,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteTravelAgencyListRespDto> detail(@RequestBody @Valid RouteTravelAgencyListRequest request, BindingResult result){
        AbstractResponse<RouteTravelAgencyListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_travelAgency_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "设置线路团的地接旅行社",httpMethod = "POST",notes = "设置线路团的地接旅行社")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.ADD_TRAVEL_AGENCY,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteTravelAgencyAddRespDto> add(@RequestBody @Valid RouteTravelAgencyAddRequest request, BindingResult result){
        AbstractResponse<RouteTravelAgencyAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_travelAgency_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}
