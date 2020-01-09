package com.kunlun.erp.core.controller.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.product.ProductService;
import com.kunlun.erp.core.validator.product.RouteProductValidator;
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
 * @ClassName RouteProductController
 * @Description 线路产品管理
 * @Author Jm.zhang
 * @Date 2019/12/9 17:24
 * @Version 1.0
 **/
@Controller
@Api(position = 7,description = "线路产品管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteProductController extends AbstractController {
    @Resource(name = "product_service")
    private ProductService product_service;

    public RouteProductController(RouteProductValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "列表接口",httpMethod = "POST",notes = "列表分页接口")
    @SystemLog
    @RequestMapping(value = Urls.Product.LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteProductListRespDto> list(@RequestBody @Valid RouteProductListRequest request, BindingResult result){
        AbstractResponse<RouteProductListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "创建接口",httpMethod = "POST",notes = "创建线路产品")
    @SystemLog
    @RequestMapping(value = Urls.Product.ADD,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteProductAddRespDto> add(@RequestBody @Valid RouteProductAddRequest request, BindingResult result){
        AbstractResponse<RouteProductAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    @ApiOperation(value = "详情接口",httpMethod = "POST",notes = "产品详情")
    @SystemLog
    @RequestMapping(value = Urls.Product.DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteProductDetailRespDto> detail(@RequestBody @Valid RouteProductDetailRequest request, BindingResult result){
        AbstractResponse<RouteProductDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "修改接口",httpMethod = "POST",notes = "修改线路产品")
    @SystemLog
    @RequestMapping(value = Urls.Product.UPDATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteProductEditRespDto> update(@RequestBody @Valid RouteProductEditRequest request, BindingResult result){
        AbstractResponse<RouteProductEditRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_service.update(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    @ApiOperation(value = "线路产品复制接口",httpMethod = "POST",notes = "线路产品复制接口")
    @SystemLog
    @RequestMapping(value = Urls.Product.COPY,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteProductCopyRespDto> copy(@RequestBody @Valid RouteProductCopyRequest request, BindingResult result){
        AbstractResponse<RouteProductCopyRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_service.copy(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @ApiOperation(value = "线路产品删除接口",httpMethod = "POST",notes = "线路产品删除接口")
    @SystemLog
    @RequestMapping(value = Urls.Product.DELETE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteProductDelRespDto> delete(@RequestBody @Valid RouteProductDelRequest request, BindingResult result){
        AbstractResponse<RouteProductDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_service.delete(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
