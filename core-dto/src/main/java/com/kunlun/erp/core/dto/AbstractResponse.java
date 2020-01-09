package com.kunlun.erp.core.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @ClassName AbstractResponse
 * @Description 抽象的数据响应对象
 * @Author Jm.zhang
 * @Date 2019/11/14 9:58
 * @Version 1.0
 **/
@ApiModel(description = "统一响应报文结构，包含 报文头header 和 报文体body")
public class AbstractResponse<T> implements Serializable {
    private BaseResponseHeader header;
    private T body;


    public BaseResponseHeader getHeader() {
        return header;
    }

    public void setHeader(BaseResponseHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
