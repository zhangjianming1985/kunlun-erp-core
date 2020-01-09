package com.kunlun.erp.core.validator.common;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.entity.SalesChannelCost;
import com.kunlun.erp.core.service.company.SalesChannelCostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SalesChannelCostValidator
 * @Description 销售渠道费用校验
 * @Author Jm.zhang
 * @Date 2019/12/4 17:36
 * @Version 1.0
 **/
@Component(value = "sales_channel_cost_validator")
public class SalesChannelCostValidator {
    @Resource(name = "sales_channel_cost_service")
    private SalesChannelCostService sales_channel_cost_service;

    public String check(List<SalesChannelCostDto> data_list,boolean is_create){
        String error_code = null;
        if (data_list == null || data_list.isEmpty())return null;
        for (SalesChannelCostDto cost_dto : data_list){
            if (!is_create && StringUtils.isNotBlank(cost_dto.getCost_code())){
                SalesChannelCost cost_record = sales_channel_cost_service.getRecordByCostCode(cost_dto.getCost_code());
                if (cost_record == null){
                    error_code= ErrorCodeConstant.SALES_COST_CODE_INVALID;
                    break;
                }
            }
            //渠道费用类型
            if (SysConstant.SalesFeeType.getSalesFeeType(cost_dto.getCost_type_id()).getValue()==null){
                error_code = ErrorCodeConstant.SALES_COST_TYPE_INVALID;
                break;
            }
            //产品类别编号
            if (StringUtils.isBlank(cost_dto.getProduct_category_code())){
                error_code = ErrorCodeConstant.PRODUCT_CATEGORY_INVALID;
                break;
            }
            //结算模式
            if (SysConstant.SalesCostSettlementMode.getSalesCostSettlementMode(cost_dto.getCost_settlement_mode())==null){
                error_code = ErrorCodeConstant.SALES_COST_SETTLEMENT_MODEL_INVALID;
                break;
            }
            //收费模式
            if (SysConstant.ChargingModel.getChargingModel(cost_dto.getCharge_mode())==null){
                error_code = ErrorCodeConstant.SALES_CHARGE_MODEL_INVALID;
                break;
            }
            //费用
            if (RegexUtil.isDecimal(cost_dto.getFee())==false){
                error_code = ErrorCodeConstant.SALES_COST_FEE_INVALID;
                break;
            }
            //费率
            if (RegexUtil.isDecimal(cost_dto.getRate())==false){
                error_code = ErrorCodeConstant.SALES_COST_RATE_INVALID;
                break;
            }

        }


        return error_code;


    }
}
