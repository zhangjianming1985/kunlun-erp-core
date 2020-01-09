package com.kunlun.erp.core.dto.company.request;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.common.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName CompanyListReqDto
 * @Description 公司列表请求参数
 * @Author Jm.zhang
 * @Date 2019/11/26 12:46
 * @Version 1.0
 **/
@ApiModel(description = "企业列表检索分页请求数据")
public class CompanyListReqDto {
    @ApiModelProperty(value = "当前页码",example = "1")
    private Integer page_index = 1;

    @ApiModelProperty(value = "每页条数",example = "20")
    private Integer page_size = 20;

    @ApiModelProperty(required = true,value = "企业类型：0=线上OT销售渠道、1=线下门店销售渠道、2=其他销售渠道、3=旅行社、4=车队供应商、5=酒店住宿供应商、6=餐饮供应商、7=景区供应商、8=票务供应商、9=保险供应商、10=导服供应商、11=其他供应商",example = "3")
    @NotNull(message = ErrorCodeConstant.COMPANY_TYPE_IS_INVALID)
    private Integer company_type;

    @ApiModelProperty(value = "企业名称",example = "测试")
    private String company_name;

    @ApiModelProperty(value = "负责人姓名",example = "张三")
    private String leader_name;

    @ApiModelProperty(value = "负责人手机 手机号需校验",example = "15915328866")
    private String leader_mobile;

    @ApiModelProperty(value = "联系人姓名",example = "李四")
    private String person_name;

    @ApiModelProperty(value = "手机号码",example = "15915328866")
    private String person_mobile;

    @ApiModelProperty(value = "共有”A级、B级、C级、D级“四个级别，默认D级",example = "C")
    private String credit_level;

    @ApiModelProperty(value = "区域检索")
    private AreaDto area;

    @ApiModelProperty(value = "线上销售渠道 所属平台,0=飞猪、1=携程、2=美团、3=去哪儿、4=马蜂窝、5=同程艺龙、6=途牛、7=驴妈妈、8=蜗友行、9=魔方云仓")
    private Integer belong_platform;




    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }

    public String getLeader_mobile() {
        return leader_mobile;
    }

    public void setLeader_mobile(String leader_mobile) {
        this.leader_mobile = leader_mobile;
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

    public String getCredit_level() {
        return credit_level;
    }

    public void setCredit_level(String credit_level) {
        this.credit_level = credit_level;
    }

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

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

    public Integer getBelong_platform() {
        return belong_platform;
    }

    public void setBelong_platform(Integer belong_platform) {
        this.belong_platform = belong_platform;
    }
}
