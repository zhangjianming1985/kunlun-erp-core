package com.kunlun.erp.core.dto.company.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName CompanyAddRespDto
 * @Description 增加供应商响应
 * @Author Jm.zhang
 * @Date 2019/11/18 15:56
 * @Version 1.0
 **/
@ApiModel(description = "创建企业响应报体")
public class CompanyAddRespDto {
    @ApiModelProperty(value = "企业唯一编号，创建成功后由服务端生成",example = "1000000123963044")
    private String company_code;
    @ApiModelProperty(value = "金融账户唯一编号集合",example = "[\"1111\",\"222222\"]")
    private List<String> financial_account_codes;
    @ApiModelProperty(value = "人员数据唯一编号集合",example = "[\"66666666\",\"8888888888\"]")
    private List<String> person_codes;
    @ApiModelProperty(value = "销售渠道费用数据唯一编号集合",example = "[\"66666666\",\"8888888888\"]")
    private List<String> sales_channel_cost_codes;

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public List<String> getFinancial_account_codes() {
        return financial_account_codes;
    }

    public void setFinancial_account_codes(List<String> financial_account_codes) {
        this.financial_account_codes = financial_account_codes;
    }

    public List<String> getPerson_codes() {
        return person_codes;
    }

    public void setPerson_codes(List<String> person_codes) {
        this.person_codes = person_codes;
    }

    public List<String> getSales_channel_cost_codes() {
        return sales_channel_cost_codes;
    }

    public void setSales_channel_cost_codes(List<String> sales_channel_cost_codes) {
        this.sales_channel_cost_codes = sales_channel_cost_codes;
    }
}
