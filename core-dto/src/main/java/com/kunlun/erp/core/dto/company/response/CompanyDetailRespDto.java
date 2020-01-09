package com.kunlun.erp.core.dto.company.response;

import com.kunlun.erp.core.dto.common.AreaRespDto;
import com.kunlun.erp.core.dto.company.LeaderDto;
import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.dto.person.PersonDto;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName CompanyDetailRespDto
 * @Description 响应企业信息
 * @Author Jm.zhang
 * @Date 2019/11/28 12:41
 * @Version 1.0
 **/
@ApiModel(description = "响应数据")
public class CompanyDetailRespDto {
    private CompanyDetailBaseInfo base_info;

    private AreaRespDto area_info;

    private LeaderDto leader_info;

    private FinanceRespDto financial_info;

    private List<PersonDto> contact_info;

    private List<SalesChannelCostDto> sales_channel_cost_info;


    public CompanyDetailBaseInfo getBase_info() {
        return base_info;
    }

    public void setBase_info(CompanyDetailBaseInfo base_info) {
        this.base_info = base_info;
    }

    public AreaRespDto getArea_info() {
        return area_info;
    }

    public void setArea_info(AreaRespDto area_info) {
        this.area_info = area_info;
    }

    public LeaderDto getLeader_info() {
        return leader_info;
    }

    public void setLeader_info(LeaderDto leader_info) {
        this.leader_info = leader_info;
    }

    public FinanceRespDto getFinancial_info() {
        return financial_info;
    }

    public void setFinancial_info(FinanceRespDto financial_info) {
        this.financial_info = financial_info;
    }

    public List<PersonDto> getContact_info() {
        return contact_info;
    }

    public void setContact_info(List<PersonDto> contact_info) {
        this.contact_info = contact_info;
    }

    public List<SalesChannelCostDto> getSales_channel_cost_info() {
        return sales_channel_cost_info;
    }

    public void setSales_channel_cost_info(List<SalesChannelCostDto> sales_channel_cost_info) {
        this.sales_channel_cost_info = sales_channel_cost_info;
    }
}
