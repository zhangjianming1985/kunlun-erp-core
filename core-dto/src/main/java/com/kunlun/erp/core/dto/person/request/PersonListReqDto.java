package com.kunlun.erp.core.dto.person.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName PersonListReqDto
 * @Description 人员列表查询
 * @Author Jm.zhang
 * @Date 2019-12-02 22:00
 * @Version 1.0
 **/
@ApiModel(description = "人员列表检索分页请求数据")
public class PersonListReqDto {
    @ApiModelProperty(value = "当前页码，默认1",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数，默认20",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    @NotNull(message = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID)
    private Integer company_type;

    @ApiModelProperty(required = true,value = "企业编号",example = "1000001885054422")
    @NotBlank(message = ErrorCodeConstant.COMPANY_CODE_INVALID)
    private String company_code;

    @ApiModelProperty(required = true,value = "人员类型：0=普通联系人、1=导游",example = "0")
    @NotNull(message = ErrorCodeConstant.CONTACT_TYPE_INVALID)
    private Integer person_type;

    @ApiModelProperty(value = "检索条件：姓名",example = "张三")
    private String person_name;

    @ApiModelProperty(value = "检索条件：手机号码",example = "15915328866")
    private String person_mobile;

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

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

    public Integer getPerson_type() {
        return person_type;
    }

    public void setPerson_type(Integer person_type) {
        this.person_type = person_type;
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
}
