package com.kunlun.erp.core.component;

import com.kunlun.erp.core.common.configuration.FileProperties;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.dto.file.UploadResultDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * @ClassName UploadFileComponent
 * @Description 文件上传组件
 * @Author Jm.zhang
 * @Date 2019/12/7 10:01
 * @Version 1.0
 **/
@Component(value = "upload_component")
public class UploadFileComponent {
    @Resource
    private FileProperties file_properties;

    public UploadResultDto upload(HttpServletRequest request) {
        UploadResultDto resultDto = new UploadResultDto();
        resultDto.setStatus(SysConstant.FileUploadStatus.resp_status_success.getValue());
        CommonsMultipartResolver multi_resolver = new CommonsMultipartResolver(request.getServletContext());
        if (!multi_resolver.isMultipart(request)) {
//            resultDto.setError_message(messageUtil.getErrorsMessage(ConstantsErrorCode.ERR_UPLOAD_NO_FILE));
            resultDto.setStatus(SysConstant.FileUploadStatus.resp_status_fail.getValue());
            return resultDto;
        }
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
        Iterator<String> fileNameIter = mreq.getFileNames();
        while (fileNameIter.hasNext()) {
            MultipartFile file = mreq.getFile(fileNameIter.next());
            if (file == null) {
//                resultDto.setError_message(messageUtil.getErrorsMessage(ConstantsErrorCode.ERR_UPLOAD_NO_FILE));
                resultDto.setStatus(SysConstant.FileUploadStatus.resp_status_fail.getValue());
                return resultDto;            }
            String myFileName = file.getName();
            if (StringUtils.isBlank(myFileName)) {
//                resultDto.setError_message(messageUtil.getErrorsMessage(ConstantsErrorCode.ERR_FILE_NAME_IS_EMPTY));
                resultDto.setStatus(SysConstant.FileUploadStatus.resp_status_fail.getValue());
                return resultDto;
            }
            //完整文件名
            String original_full_file_name = file.getOriginalFilename();
            resultDto.setOrigin_name(original_full_file_name);
            //文件名 不含后缀
//			String original_file_name = original_full_file_name.substring(0, original_full_file_name.lastIndexOf("."));
            //后缀
            String fileSuffix = original_full_file_name.substring(original_full_file_name.lastIndexOf(".") + 1).toLowerCase();
            String newFileName = DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE_TIME_STR, new Date()) + new Random().nextInt(1000) + "." + fileSuffix;
            resultDto.setName(newFileName);
            String filePath = getFolderPath(fileSuffix) + newFileName;
            File localFile = new File(filePath);
            try {
                if (!localFile.exists()) {
                    localFile.mkdirs();
                }
                file.transferTo(localFile);
            } catch (Exception e) {
                e.printStackTrace();
                resultDto.setStatus(SysConstant.FileUploadStatus.resp_status_fail.getValue());
                return resultDto;
            }
        }
        resultDto.setUrl(this.getFileUrl(resultDto.getName()));
        resultDto.setThumbUrl(this.getFileUrl(resultDto.getName()));
        return resultDto;
    }

    /**
     * 获取响应的文件存放路径
     * @param fileSuffix
     * @return
     */
    public String getFolderPath(String fileSuffix){
        if (fileSuffix.endsWith("jpg") || fileSuffix.endsWith("png")){
            return file_properties.getPic_folder_path();
        }else{
            return file_properties.getDoc_folder_path();
        }
    }

    /**
     * 获取文件的访问地址
     * @param file_name
     * @return
     */
    public String getFileUrl(String file_name){
        if (file_name.endsWith("jpg") || file_name.endsWith("png")){
            return file_properties.getSys_domain()+ file_properties.getPic_access_url().replace("**","")+file_name;
        }else{
            return file_properties.getSys_domain()+ file_properties.getDoc_access_url().replace("**","")+file_name;
        }
    }
}
