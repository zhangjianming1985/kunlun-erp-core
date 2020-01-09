package com.kunlun.erp.core.mapper;

import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.dto.condition.SalesChannelCostCondition;
import com.kunlun.erp.core.entity.SalesChannelCost;

import java.util.List;

public interface SalesChannelCostMapper {

    int insertSelective(SalesChannelCost record);

    int deleteByCompanyCode(String company_code);

    void deleteByCondition(SalesChannelCostCondition condition);

    SalesChannelCost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalesChannelCost record);

    SalesChannelCost selectByCostCode(String cost_code);

    List<SalesChannelCostDto> selectDtoByCondition(SalesChannelCostCondition condition);


}