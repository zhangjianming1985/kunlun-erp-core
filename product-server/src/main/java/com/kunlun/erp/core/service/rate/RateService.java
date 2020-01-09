package com.kunlun.erp.core.service.rate;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.RateRequest;
import com.kunlun.erp.core.dto.common.RateRespDto;

/**
 * @InterfaceName RateService
 * @Description 汇率服务接口
 * @Author Jm.zhang
 * @Date 2020/1/2 10:47
 * @Version 1.0
 **/
public interface RateService {
    AbstractResponse<RateRespDto> rate(RateRequest request)throws Exception;
}