package com.kunlun.erp.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseRequestHeader
 * @Description 请求报头数据
 * @Author Jm.zhang
 * @Date 2019/11/14 9:59
 * @Version 1.0
 **/
@ApiModel(description = "请求报头")
public class BaseRequestHeader implements Serializable {
    @ApiModelProperty(value = "请求流水号。由开发者生成，每次接口请求流水号不能重复，可以是随机字符串。",example = "1234567890121")
    private String trans_no;
    @ApiModelProperty(value = "密钥,账户认证成功后 由服务端返回",example = "539e93ae83e01ee0a25ab135a1b21d29")
    private String secret_key;



    public String getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(String trans_no) {
        this.trans_no = trans_no;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public static void main(String[] arg){
        System.out.println(new Date().getTime());
    }

}
