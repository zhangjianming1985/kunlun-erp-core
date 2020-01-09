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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName CompanyEditReqDto
 * @Description 编辑企业信息
 * @Author Jm.zhang
 * @Date 2019/11/28 12:36
 * @Version 1.0
 **/
@ApiModel(description = "修改企业请求数据")
public class CompanyEditReqDto {

    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    @NotNull(message = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID)
    private Integer company_type;

    @ApiModelProperty(required = true,value = "企业编号",example = "1000001885054422")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;
    /**
     * 基础信息
     */
    private BaseInfoDto base_info;

    /**
     * 区域信息
     */
    private AreaDto area_info;

    /**
     * 负责人信息
     */
    private LeaderDto leader_info;

    /**
     * 财务信息
     */
    private FinanceDto financial_info;

    /**
     * 联系人信息
     */
    private List<PersonDto> contact_info;

    /**
     * 销售渠道费用数据
     */
    private List<SalesChannelCostDto> sales_channel_cost_info;

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

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

    public List<SalesChannelCostDto> getSales_channel_cost_info() {
        return sales_channel_cost_info;
    }

    public void setSales_channel_cost_info(List<SalesChannelCostDto> sales_channel_cost_info) {
        this.sales_channel_cost_info = sales_channel_cost_info;
    }
}
