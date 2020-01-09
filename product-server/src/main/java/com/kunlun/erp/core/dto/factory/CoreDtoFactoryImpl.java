package com.kunlun.erp.core.dto.factory;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.*;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName CoreDtoFactoryImpl
 * @Description DTO工厂
 * @Author Jm.zhang
 * @Date 2019/11/14 16:54
 * @Version 1.0
 **/
@Service(value = "dtoFactory")
public class CoreDtoFactoryImpl implements CoreDtoFactory{

    @Override
    public <T> AbstractRequest<T> createRequest(Object... objects) {
        AbstractRequest<T> request = new AbstractRequest<>();
        BaseRequestHeader req_header = new BaseRequestHeader();
        req_header.setTrans_no(String.valueOf(new Date().getTime()));
        request.setHeader(req_header);
        return request;
    }

    @Override
    public <T> AbstractResponse<T> createResponse(Object... objects) {
        AbstractResponse<T> response = new AbstractResponse<>();
        BaseResponseHeader resp_header = new BaseResponseHeader();
        resp_header.setState(SysConstant.RespStatus.resp_status_success.getValue());
        response.setHeader(resp_header);
        return response;
    }

    @Override
    public <T> AbstractResponse<T> createResponse(BaseRequestHeader request_header) {
        AbstractResponse<T> response = new AbstractResponse<>();
        BaseResponseHeader resp_header = new BaseResponseHeader();
        if (request_header != null){
            resp_header.setTrans_no(request_header.getTrans_no()==null?"":request_header.getTrans_no());
        }
        resp_header.setState(SysConstant.RespStatus.resp_status_success.getValue());
        response.setHeader(resp_header);
        return response;
    }

}
