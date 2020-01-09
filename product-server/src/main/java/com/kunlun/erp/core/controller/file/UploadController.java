package com.kunlun.erp.core.controller.file;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.common.util.LogUtil;
import com.kunlun.erp.core.component.UploadFileComponent;
import com.kunlun.erp.core.dto.file.UploadResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UploadController
 * @Description 文件上传
 * @Author Jm.zhang
 * @Date 2019/12/7 9:21
 * @Version 1.0
 **/

@Api(position = 2,description = "文件上传",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
@Controller
public class UploadController {
    @Resource(name = "upload_component")
    private UploadFileComponent upload_component;

    @ApiOperation(value = "文件上传",httpMethod = "POST",notes = "文件上传")
    @PostMapping("upload")
    @RequestMapping(value = Urls.FileHandler.upload,method = RequestMethod.POST)
    public @ResponseBody UploadResultDto fileUpload(HttpServletRequest request) {
        UploadResultDto result = upload_component.upload(request);
        System.out.println("upload result : " + JsonUtil.toStr(result));
        LogUtil.writeLogInfo(UploadController.class.getName(),"fileUpload","upload result",JsonUtil.toStr(result),Urls.FileHandler.NAMESPACE);
        return result;
    }
}
