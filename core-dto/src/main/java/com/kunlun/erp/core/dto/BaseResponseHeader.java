package com.kunlun.erp.core.dto;

import com.kunlun.erp.core.common.constants.SysConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseResponseHeader
 * @Description 响应报头数据
 * @Author Jm.zhang
 * @Date 2019/11/14 10:40
 * @Version 1.0
 **/
@ApiModel(description = "系统响应报文头")
public class BaseResponseHeader implements Serializable {

    @ApiModelProperty(value = "请求流水号",example = "123456789021545")
    private String trans_no;

    @ApiModelProperty(value = "响应状态, 成功：success，失败：fail",example = "success")
    private String state;

    @ApiModelProperty(value = "错误代码、错误消息")
    private Map<String, String> error_message = new HashMap<String, String>();

    @ApiModelProperty(value = "错误消息文本")
    private String error_msg;




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, String> getError_message() {
        return error_message;
    }

    public void setError_message(Map<String, String> error_message) {
        this.state= SysConstant.RespStatus.resp_status_fail.getValue();
        this.error_message = error_message;
    }

    public String getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(String trans_no) {
        this.trans_no = trans_no;
    }

    public String getError_msg() {
        StringBuffer sb = new StringBuffer();
        if (this.getError_message()!= null && this.getError_message().size()>0)
        for(Map.Entry<String, String> entry : this.getError_message().entrySet()){
            String mapValue = entry.getValue();
            sb.append(mapValue).append("|");
        }
        String msg = sb.toString();
        if (StringUtils.isNotBlank(msg) && msg.indexOf("|")>-1){
            msg =msg.substring(0,msg.lastIndexOf("|"));
        }
        return msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static void main(String[] art){
        String msg="aaaabbb|ccccc|";
        if (StringUtils.isNotBlank(msg) && msg.indexOf("|")>-1){
            msg =msg.substring(0,msg.lastIndexOf("|"));
        }
        System.out.println(msg);
    }
}
