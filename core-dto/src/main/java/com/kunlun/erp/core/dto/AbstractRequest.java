package com.kunlun.erp.core.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @ClassName AbstractRequest
 * @Description 抽象的数据请求对象
 * @Author Jm.zhang
 * @Date 2019/11/14 9:57
 * @Version 1.0
 **/
@ApiModel(description = "请求报文结构,含报头和主体")
public class AbstractRequest<T> implements Serializable {
    @Valid
    private BaseRequestHeader header;

    @Valid
    private T body;


    public BaseRequestHeader getHeader() {
        return header;
    }

    public void setHeader(BaseRequestHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
