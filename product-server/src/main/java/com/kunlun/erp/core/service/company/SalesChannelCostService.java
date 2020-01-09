package com.kunlun.erp.core.service.company;

import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.dto.condition.SalesChannelCostCondition;
import com.kunlun.erp.core.entity.SalesChannelCost;

import java.util.List;

/**
 * @ClassName SalesChannelCostService
 * @Description 销售渠道费用业务接口
 * @Author Jm.zhang
 * @Date 2019/12/4 17:12
 * @Version 1.0
 **/
public interface SalesChannelCostService {
    int insert(SalesChannelCost record);

    int deleteByCompanyCode(String company_code);

    int updateCostRecord(SalesChannelCost record);

    SalesChannelCost getRecordByCostCode(String cost_code);

    List<SalesChannelCostDto> getDtoByCondition(SalesChannelCostCondition condition);

    void deleteByCondition(SalesChannelCostCondition condition);
}
