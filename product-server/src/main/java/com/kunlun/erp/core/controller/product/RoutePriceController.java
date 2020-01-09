package com.kunlun.erp.core.controller.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.product.RoutePricePlanService;
import com.kunlun.erp.core.validator.product.PricePlanValidator;
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
 * @ClassName RoutePriceController
 * @Description 线路产品价格管理
 * @Author Jm.zhang
 * @Date 2019/12/16 19:23
 * @Version 1.0
 **/
@Controller
@Api(position = 9,description = "线路产品价格管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RoutePriceController extends AbstractController {
    @Resource(name = "route_price_plan_service")
    private RoutePricePlanService route_price_plan_service;

    public RoutePriceController(PricePlanValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "设置价格套餐",httpMethod = "POST",notes = "设置价格套餐")
    @SystemLog
    @RequestMapping(value = Urls.RoutePrice.ADD,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PricePlanAddRespDto> add(@RequestBody @Valid PricePlanAddRequest request, BindingResult result){
        AbstractResponse<PricePlanAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_price_plan_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;


    }

    @ApiOperation(value = "修改价格套餐名称",httpMethod = "POST",notes = "修改价格套餐名称")
    @SystemLog
    @RequestMapping(value = Urls.RoutePrice.UPDATE_PRICE_PLAN_NAME,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PricePlanNameUpdateRespDto> updatePricePlanName(@RequestBody @Valid PricePlanNameUpdateRequest request, BindingResult result){
        AbstractResponse<PricePlanNameUpdateRespDto> response = dtoFactory.createResponse(request.getHeader());

        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_price_plan_service.updatePricePlanName(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @ApiOperation(value = "价格套餐详情",httpMethod = "POST",notes = "价格套餐详情")
    @SystemLog
    @RequestMapping(value = Urls.RoutePrice.DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PricePlanDetailRespDto> detail(@RequestBody @Valid PricePlanDetailRequest request, BindingResult result){
        AbstractResponse<PricePlanDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_price_plan_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "删除价格套餐",httpMethod = "POST",notes = "删除价格套餐")
    @SystemLog
    @RequestMapping(value = Urls.RoutePrice.DELETE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PricePlanDelRespDto> deletePricePlan(@RequestBody @Valid PricePlanDelRequest request, BindingResult result){
        AbstractResponse<PricePlanDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_price_plan_service.deletePricePlan(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    @ApiOperation(value = "删除日期价格",httpMethod = "POST",notes = "删除日期价格数据")
    @SystemLog
    @RequestMapping(value = Urls.RoutePrice.DELETE_DATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PriceDateDelRespDto> deleteDate(@RequestBody @Valid PriceDateDelRequest request, BindingResult result){
        AbstractResponse<PriceDateDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_price_plan_service.deletePriceDate(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @ApiOperation(value = "成本明细",httpMethod = "POST",notes = "成本明细")
    @SystemLog
    @RequestMapping(value = Urls.RoutePrice.COST_DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PricePlanCostDetailRespDto> costDetail(@RequestBody @Valid PricePlanCostDetailRequest request, BindingResult result){
        AbstractResponse<PricePlanCostDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_price_plan_service.costDetail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
