package com.kunlun.erp.core.dto.company.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.company.BaseInfoDto;
import com.kunlun.erp.core.dto.company.FinanceDto;
import com.kunlun.erp.core.dto.company.LeaderDto;
import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.dto.person.PersonDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName CompanyAddReqDto
 * @Description 供应商数据模型
 * @Author Jm.zhang
 * @Date 2019/11/18 12:32
 * @Version 1.0
 **/
@ApiModel(description = "请求数据")
public class CompanyAddReqDto {
    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    @NotNull(message = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID)
    private Integer company_type;

    /**
     * 基础信息
     */
    @NotNull(message = ErrorCodeConstant.COMPANY_BASE_INFO_NULL)
    @Valid
    private BaseInfoDto base_info;

    /**
     * 区域信息
     */
    @NotNull(message = ErrorCodeConstant.AREA_DATA_NULL)
    @Valid
    private AreaDto area_info;

    /**
     * 负责人信息
     */
//    @NotNull(message = ErrorCodeConstant.LEADER_DATA_NULL)
    @Valid
    private LeaderDto leader_info;

    /**
     * 财务信息
     */
    @NotNull(message = ErrorCodeConstant.FINANCE_DATA_NULL)
    @Valid
    private FinanceDto financial_info;

    /**
     * 联系人信息
     */
//    @NotNull(message = ErrorCodeConstant.CONTACT_PERSON_DATA_NULL)
    @Valid
    private List<PersonDto> contact_info;

    /**
     * 销售渠道费用数据
     */
    @Valid
    private List<SalesChannelCostDto> sales_channel_cost;

    public BaseInfoDto getBase_info() {
        return base_info;
    }

    public void setBase_info(BaseInfoDto base_info) {
        this.base_info = base_info;
    }

    public AreaDto getArea_info() {
        return area_info;
    }

    public void setArea_info(AreaDto area_info) {
        this.area_info = area_info;
    }

    public LeaderDto getLeader_info() {
        return leader_info;
    }

    public void setLeader_info(LeaderDto leader_info) {
        this.leader_info = leader_info;
    }

    public FinanceDto getFinancial_info() {
        return financial_info;
    }

    public void setFinancial_info(FinanceDto financial_info) {
        this.financial_info = financial_info;
    }

    public List<PersonDto> getContact_info() {
        return contact_info;
    }

    public void setContact_info(List<PersonDto> contact_info) {
        this.contact_info = contact_info;
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public List<SalesChannelCostDto> getSales_channel_cost() {
        return sales_channel_cost;
    }

    public void setSales_channel_cost(List<SalesChannelCostDto> sales_channel_cost) {
        this.sales_channel_cost = sales_channel_cost;
    }
}
