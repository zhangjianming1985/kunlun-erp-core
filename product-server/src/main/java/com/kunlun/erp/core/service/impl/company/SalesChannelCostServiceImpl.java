package com.kunlun.erp.core.service.impl.company;

import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.dto.condition.SalesChannelCostCondition;
import com.kunlun.erp.core.entity.SalesChannelCost;
import com.kunlun.erp.core.mapper.SalesChannelCostMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.company.SalesChannelCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SalesChannelCostServiceImpl
 * @Description 销售渠道费用业务实现
 * @Author Jm.zhang
 * @Date 2019/12/4 17:12
 * @Version 1.0
 **/
@Service(value = "sales_channel_cost_service")
public class SalesChannelCostServiceImpl extends BaseService implements SalesChannelCostService {
    @Autowired
    private SalesChannelCostMapper sales_channel_cost_dao;

    @Override
    public int insert(SalesChannelCost record) {
        return sales_channel_cost_dao.insertSelective(record);
    }

    @Override
    public int deleteByCompanyCode(String company_code) {
        return sales_channel_cost_dao.deleteByCompanyCode(company_code);
    }

    @Override
    public int updateCostRecord(SalesChannelCost record) {
        return sales_channel_cost_dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public SalesChannelCost getRecordByCostCode(String cost_code) {
        return sales_channel_cost_dao.selectByCostCode(cost_code);
    }

    @Override
    public List<SalesChannelCostDto> getDtoByCondition(SalesChannelCostCondition condition) {
        return sales_channel_cost_dao.selectDtoByCondition(condition);
    }

    @Override
    public void deleteByCondition(SalesChannelCostCondition condition) {

    }
}
