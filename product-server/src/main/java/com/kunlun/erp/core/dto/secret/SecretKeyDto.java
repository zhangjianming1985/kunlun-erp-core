package com.kunlun.erp.core.dto.secret;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SecretKeyDto
 * @Description 密钥对象
 * @Author Jm.zhang
 * @Date 2019/11/14 17:04
 * @Version 1.0
 **/
public class SecretKeyDto implements Serializable {
    /**
     * 账号
     */
    private String sender;
    /**
     * 请求流水号
     */
    private String trans_no;
    /**
     * 请求时间
     */
    private Date last_request_time;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getLast_request_time() {
        return last_request_time;
    }

    public void setLast_request_time(Date last_request_time) {
        this.last_request_time = last_request_time;
    }

    public String getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(String trans_no) {
        this.trans_no = trans_no;
    }
}
