package com.kunlun.erp.core.service.finance;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.finance.request.*;
import com.kunlun.erp.core.dto.finance.response.*;

/**
 * @InterfaceName FinanceService
 * @Description 财务管理服务接口
 * @Author Jm.zhang
 * @Date 2019/12/27 10:33
 * @Version 1.0
 **/
public interface FinanceService {
    AbstractResponse<HallProductEndListRespDto> finish_route (HallProductEndListRequest request);

    AbstractResponse<FinanceAuditRespDto> audit (FinanceAuditRequest request);

    AbstractResponse<FinanceAuditResultRespDto> audit_result (FinanceAuditResultRequest request);

    AbstractResponse<CollectedListRespDto> collect_list (CollectedListRequest request);

    AbstractResponse<PaymentListRespDto> payment_list (PaymentListRequest request);

}