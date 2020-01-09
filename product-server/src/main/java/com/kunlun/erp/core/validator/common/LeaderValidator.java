package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.company.LeaderDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName LeaderValidator
 * @Description 负责人数据校验
 * @Author Jm.zhang
 * @Date 2019/11/19 10:00
 * @Version 1.0
 **/
@Component(value = "leader_validator")
public class LeaderValidator {
    public String check(LeaderDto leader_dto){
        String error_code = null;
        if (leader_dto == null){
               return null;
        }
        // 负责人姓名 选填，姓名，支持中英文及少数民族姓名•或.特殊标点，最多32个字符；
        if (StringUtils.isNotBlank(leader_dto.getLeader_name())){
            if (RegexUtil.commonStrCheck(leader_dto.getLeader_name(),1,32,"[~!@#$%^&*()_+=-]") == false){
                error_code = ErrorCodeConstant.LEADER_NAME_INVALID;
            }
        }
        //负责人手机号码
        if (StringUtils.isNotBlank(leader_dto.getLeader_mobile())){
            if (RegexUtil.isMobile(leader_dto.getLeader_mobile())==false){
                error_code = ErrorCodeConstant.LEADER_MOBILE_INVALID;
            }
        }

        //负责人微信号
        if (StringUtils.isNotBlank(leader_dto.getLeader_wechat())){
            if (RegexUtil.commonStrCheck(leader_dto.getLeader_wechat(),1,16,"[~!@#$%^&*()+=-]") == false){
                error_code = ErrorCodeConstant.LEADER_WECHAT_INVALID;
            }
        }
        //负责人微信号
        if (StringUtils.isNotBlank(leader_dto.getLeader_wechat())){
            if (RegexUtil.commonStrCheck(leader_dto.getLeader_wechat(),1,16,"[~!@#$%^&*()+=-]") == false){
                error_code = ErrorCodeConstant.LEADER_WECHAT_INVALID;
            }
        }
        //负责地址
        if (StringUtils.isNotBlank(leader_dto.getLeader_address())){
            if (RegexUtil.commonStrCheck(leader_dto.getLeader_address(),1,80,true) == false){
                error_code = ErrorCodeConstant.LEADER_ADDRESS_INVALID;
            }
        }
        return error_code;
    }


}
