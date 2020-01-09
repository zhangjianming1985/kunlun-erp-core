package com.kunlun.erp.core.controller.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.product.response.*;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.product.ProductCategoryService;
import com.kunlun.erp.core.validator.product.ProductCategoryValidator;
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
 * @ClassName ProductCategoryController
 * @Description 产品类别管理
 * @Author Jm.zhang
 * @Date 2019/12/6 10:36
 * @Version 1.0
 **/
@Controller
@Api(position = 6,description = "产品类别管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class ProductCategoryController extends AbstractController {
    @Resource(name = "product_category_service")
    private ProductCategoryService product_category_service;

    public ProductCategoryController(ProductCategoryValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "查询列表接口",httpMethod = "POST",notes = "查询类别列表，分页显示")
    @SystemLog
    @RequestMapping(value = Urls.Product.CATEGORY_LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ProductCategoryListRespDto> list(@RequestBody @Valid ProductCategoryListRequest request, BindingResult result){
        AbstractResponse<ProductCategoryListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_category_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "创建接口",httpMethod = "POST",notes = "创建产品分类")
    @SystemLog
    @RequestMapping(value = Urls.Product.CATEGORY_ADD,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ProductCategoryAddRespDto> add(@RequestBody @Valid ProductCategoryAddRequest request, BindingResult result){
        AbstractResponse<ProductCategoryAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_category_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "详情接口",httpMethod = "POST",notes = "产品分类详情")
    @SystemLog
    @RequestMapping(value = Urls.Product.CATEGORY_DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ProductCategoryDetailRespDto> detail(@RequestBody @Valid ProductCategoryDetailRequest request, BindingResult result){
        AbstractResponse<ProductCategoryDetailRespDto> response = dtoFactory.createResponse(request.getHeader());

        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_category_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    @ApiOperation(value = "修改接口",httpMethod = "POST",notes = "修改产品分类")
    @SystemLog
    @RequestMapping(value = Urls.Product.CATEGORY_UPDATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ProductCategoryUpdateRespDto> update(@RequestBody @Valid ProductCategoryUpdateRequest request, BindingResult result){
        AbstractResponse<ProductCategoryUpdateRespDto> response = dtoFactory.createResponse(request.getHeader());

        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_category_service.update(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "删除接口",httpMethod = "POST",notes = "删除接口")
    @SystemLog
    @RequestMapping(value = Urls.Product.CATEGORY_DELETE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<ProductCategoryDelRespDto> delete(@RequestBody @Valid ProductCategoryDelRequest request, BindingResult result){
        AbstractResponse<ProductCategoryDelRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = product_category_service.delete(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
