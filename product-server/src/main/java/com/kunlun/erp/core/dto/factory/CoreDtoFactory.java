package com.kunlun.erp.core.dto.factory;

import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.BaseRequestHeader;

/**
 * @InterfaceName CoreDtoFactory
 * @Description DTO 工厂接口
 * @Author Jm.zhang
 * @Date 2019/11/14 16:55
 * @Version 1.0
 **/
public interface CoreDtoFactory {
    <T> AbstractRequest<T> createRequest(Object... objects);
    <T> AbstractResponse<T> createResponse(Object... objects);
    <T> AbstractResponse<T> createResponse(BaseRequestHeader request_header);

}