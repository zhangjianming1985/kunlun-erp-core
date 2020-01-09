package com.kunlun.erp.core.dto.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName RouteTravelAgencyDto
 * @Description 线路团的地接旅行社数据
 * @Author Jm.zhang
 * @Date 2019/12/25 11:52
 * @Version 1.0
 **/
@ApiModel(description = "线路团的地接旅行社数据")
public class RouteTravelAgencyDto {

    @ApiModelProperty(required = true,value = "数据唯一编号,新增时 无需传参",example = "123")
    private String travel_code;

    @ApiModelProperty(required = true,value = "供应商编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(value = "供应商名称,新增时 无需传参",example = "兴旺车队公司")
    private String company_name;


    @ApiModelProperty(required = true,value = "联系人编号",example = "13000001409888077")
    @NotBlank(message = ErrorCodeConstant.CONTACT_CODE_INVALID)
    private String person_code;

    @ApiModelProperty(value = "联系人名字,新增时 无需传参",example = "小王")
    private String person_name;

    @ApiModelProperty(value = "联系人手机号码,新增时 无需传参",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(value = "联系人固话,新增时 无需传参",example = "0755-25697885")
    private String person_phone;

    /**
     * 地接团款
     */
    private List<RouteTravelAgencyIncomeDto> income_data;


    public String getTravel_code() {
        return travel_code;
    }

    public void setTravel_code(String travel_code) {
        this.travel_code = travel_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_mobile() {
        return person_mobile;
    }

    public void setPerson_mobile(String person_mobile) {
        this.person_mobile = person_mobile;
    }

    public String getPerson_phone() {
        return person_phone;
    }

    public void setPerson_phone(String person_phone) {
        this.person_phone = person_phone;
    }

    public List<RouteTravelAgencyIncomeDto> getIncome_data() {
        return income_data;
    }

    public void setIncome_data(List<RouteTravelAgencyIncomeDto> income_data) {
        this.income_data = income_data;
    }

}
