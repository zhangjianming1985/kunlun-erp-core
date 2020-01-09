package com.kunlun.erp.core.service.impl.rate;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.ArithmeticUtil;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.component.RateComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.RateRequest;
import com.kunlun.erp.core.dto.common.RateRespDto;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.rate.RateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName RateServiceImpl
 * @Description 汇率服务实现
 * @Author Jm.zhang
 * @Date 2020/1/2 10:48
 * @Version 1.0
 **/
@Service(value = "rate_service")
public class RateServiceImpl extends BaseService implements RateService {
    @Resource(name = "component_rate")
    private RateComponent component_rate;

    @Override
    public AbstractResponse<RateRespDto> rate(RateRequest request)throws Exception {
        AbstractResponse<RateRespDto> response = dtoFactory.createResponse(request.getHeader());
        RateRespDto body = new RateRespDto();
        String date = DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,new Date());
        Float rate =component_rate.getRate(date,SysConstant.Currency.getCurrency(request.getBody().getSource_currency()).getName());
        body.setRate(ArithmeticUtil.roundDown(new BigDecimal(rate/100).toString(),4));
        response.setBody(body);
        return response;
    }
}
