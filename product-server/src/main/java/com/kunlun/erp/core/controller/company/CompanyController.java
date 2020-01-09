package com.kunlun.erp.core.controller.company;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.request.*;
import com.kunlun.erp.core.dto.company.response.*;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.company.CompanyService;
import com.kunlun.erp.core.validator.company.CompanyValidator;
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
 * @ClassName CompanyController
 * @Description 供应商处理入口
 * @Author Jm.zhang
 * @Date 2019/11/18 15:53
 * @Version 1.0
 **/
@Controller
@Api(position = 3,description = "企业管理，包含供应商管理、销售渠道管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class CompanyController extends AbstractController {
    @Resource(name = "company_service")
    private CompanyService company_service;
    public CompanyController(CompanyValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "创建接口",httpMethod = "POST",notes = "创建新的企业")
    @SystemLog
    @RequestMapping(value = Urls.Company.ADD,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<CompanyAddRespDto> add(@RequestBody @Valid CompanyAddRequest request, BindingResult result){
        AbstractResponse<CompanyAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = company_service.add(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @ApiOperation(value = "查询接口",httpMethod = "POST",notes = "查询企业列表，分页显示")
    @SystemLog
    @RequestMapping(value = Urls.Company.LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<CompanyListRespDto> list(@RequestBody @Valid CompanyListRequest request, BindingResult result){
        AbstractResponse<CompanyListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = company_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "详情接口",httpMethod = "POST",notes = "获取企业信息详情")
    @SystemLog
    @RequestMapping(value = Urls.Company.DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<CompanyDetailRespDto> detail(@RequestBody @Valid CompanyDetailRequest request, BindingResult result){
        AbstractResponse<CompanyDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = company_service.detail(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "修改接口",httpMethod = "POST",notes = "修改企业信息")
    @SystemLog
    @RequestMapping(value = Urls.Company.UPDATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<CompanyEditRespDto> update(@RequestBody @Valid CompanyEditRequest request, BindingResult result){
        AbstractResponse<CompanyEditRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = company_service.update(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "检索企业名称",httpMethod = "POST",notes = "检索企业名称")
    @SystemLog
    @RequestMapping(value = Urls.Company.LIKE_NAME,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<LikeSearchNameRespDto> likeName(@RequestBody @Valid LikeSearchNameRequest request, BindingResult result){
        AbstractResponse<LikeSearchNameRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = company_service.likeName(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @ApiOperation(value = "删除企业，企业相关的级联数据都将被删除",httpMethod = "POST",notes = "一旦企业被删除，企业相关的级联数据都将被删除")
    @SystemLog
    @RequestMapping(value = Urls.Company.DELETE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<CompanyDeleteRespDto> delete(@RequestBody @Valid CompanyDeleteRequest request, BindingResult result){
        AbstractResponse<CompanyDeleteRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = company_service.delete(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


}
