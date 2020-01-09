package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.BlankBody;
import com.kunlun.erp.core.dto.routeHall.request.HallProductDetailRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductListRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductStateUpdateRequest;
import com.kunlun.erp.core.dto.routeHall.response.HallProductDetailRespDto;
import com.kunlun.erp.core.dto.routeHall.response.HallProductListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteHallService;
import com.kunlun.erp.core.validator.routeHall.RouteHallValidator;
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
 * @ClassName RouteHallController
 * @Description 线路大厅
 * @Author Jm.zhang
 * @Date 2019/12/18 20:22
 * @Version 1.0
 **/
@Controller
@Api(position = 10,description = "报名大厅数据管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteHallController extends AbstractController {
    @Resource(name = "route_hall_service")
    private RouteHallService route_hall_service;

    public RouteHallController(RouteHallValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "大厅线路列表接口",httpMethod = "POST",notes = "列表分页接口")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<HallProductListRespDto> list(@RequestBody @Valid HallProductListRequest request, BindingResult result){
        AbstractResponse<HallProductListRespDto> response = dtoFactory.createResponse(request.getHeader());

        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_hall_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "大厅线路详情",httpMethod = "POST",notes = "大厅线路详情")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<HallProductDetailRespDto> detail(@RequestBody @Valid HallProductDetailRequest request, BindingResult result){
        AbstractResponse<HallProductDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_hall_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "大厅线路状态修改",httpMethod = "POST",notes = "大厅线路状态修改")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.UPDATE_STATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse updateState(@RequestBody @Valid HallProductStateUpdateRequest request, BindingResult result){
        AbstractResponse<BlankBody> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_hall_service.updateState(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


}
