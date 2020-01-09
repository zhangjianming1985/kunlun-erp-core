package com.kunlun.erp.core.controller.area;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.area.request.AreaRequest;
import com.kunlun.erp.core.dto.area.response.AreaListRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.area.AreaService;
import com.kunlun.erp.core.validator.area.AreaReqValidator;
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
 * @ClassName AreaController
 * @Description 区域数据控制层
 * @Author Jm.zhang
 * @Date 2019/11/25 18:16
 * @Version 1.0
 **/
@Controller
@Api(position = 1,description = "区域数据，含 境外国家、境外城市、境内大区、境内省份、境内城市、境内区县",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class AreaController extends AbstractController {
    @Resource(name = "area_service")
    private AreaService area_service;

    public AreaController(AreaReqValidator validator) {
        super(validator);
    }


    @ApiOperation(value = "查询接口",httpMethod = "POST",notes = "根据参数获取响应的区域数据")
    @SystemLog
    @RequestMapping(value = Urls.Area.QUERY,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<AreaListRespDto> list(@RequestBody @Valid AreaRequest request, BindingResult result){
        AbstractResponse<AreaListRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = area_service.list(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
