package com.kunlun.erp.core.controller.company;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.person.request.*;
import com.kunlun.erp.core.dto.person.response.*;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.person.PersonValidator;
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
 * @ClassName PersonController
 * @Description 人员信息入口
 * @Author Jm.zhang
 * @Date 2019-12-02 22:56
 * @Version 1.0
 **/
@Controller
@Api(position = 5,description = "人员信息管理，包含普通联系人、导游数据(根据原型设计，暂只开放导游数据独立管理)",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class PersonController extends AbstractController {
    @Resource(name = "person_service")
    private PersonService person_service;
    public PersonController(PersonValidator validator) {
        super(validator);
    }


    @ApiOperation(value = "查询接口",httpMethod = "POST",notes = "查询人员列表，分页显示")
    @SystemLog
    @RequestMapping(value = Urls.Person.LIST,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PersonListRespDto> list(@RequestBody @Valid PersonListRequest request, BindingResult result){
        AbstractResponse<PersonListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = person_service.list(request);
            }
        }catch (Exception e){
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "创建接口",httpMethod = "POST",notes = "创建人员信息")
    @SystemLog
    @RequestMapping(value = Urls.Person.ADD,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PersonAddRespDto> add(@RequestBody @Valid PersonAddRequest request, BindingResult result){
        AbstractResponse<PersonAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = person_service.add(request);
            }
        }catch (Exception e){
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "详情接口",httpMethod = "POST",notes = "查看人员信息")
    @SystemLog
    @RequestMapping(value = Urls.Person.DETAIL,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PersonDetailRespDto> detail(@RequestBody @Valid PersonDetailRequest request, BindingResult result){
        AbstractResponse<PersonDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = person_service.detail(request);
            }
        }catch (Exception e){
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "修改接口",httpMethod = "POST",notes = "修改人员信息")
    @SystemLog
    @RequestMapping(value = Urls.Person.UPDATE,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<PersonUpdateRespDto> update(@RequestBody @Valid PersonUpdateRequest request, BindingResult result){
        AbstractResponse<PersonUpdateRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = person_service.update(request);
            }
        }catch (Exception e){
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation(value = "获取人员基础数据，用于其他模块下拉框获取数据",httpMethod = "POST",notes = "用于其他模块下拉框获取数据")
    @SystemLog
    @RequestMapping(value = Urls.Person.LIKE_PERSON,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<LikeSearchPersonRespDto> likeSearch(@RequestBody @Valid LikeSearchPersonRequest request, BindingResult result){
        AbstractResponse<LikeSearchPersonRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = person_service.likeSearch(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
