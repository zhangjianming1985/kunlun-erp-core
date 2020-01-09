package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.dto.product.RoutePriceCostDetailDto;
import com.kunlun.erp.core.entity.RoutePricePlanCostDetail;
import com.kunlun.erp.core.mapper.RoutePricePlanCostDetailMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PricePlanCostValidator
 * @Description 价格套餐成本明细校验器
 * @Author Jm.zhang
 * @Date 2019/12/17 12:47
 * @Version 1.0
 **/
@Component(value = "price_plan_cost_validator")
public class PricePlanCostValidator extends  RoutePlanBasePriceValidator{
    @Resource
    private RoutePricePlanCostDetailMapper route_cost_dao;

    public String checkCost(List<RoutePriceCostDetailDto> cost_info){
        String error_code = null;
        if (cost_info!=null && !cost_info.isEmpty()){
            for (RoutePriceCostDetailDto cost_dto :cost_info){
                if (StringUtils.isNotBlank(cost_dto.getCost_code())){
                    error_code = this.checkCostCode(cost_dto.getCost_code());
                    if (error_code !=null){
                        break;
                    }
                }
                error_code = this.checkFeeType(cost_dto.getCost_type());
                if (error_code !=null){
                    error_code=ErrorCodeConstant.ROUTE_COST_TYPE_INVALID;
                    break;
                }
                error_code = this.checkCurrency(cost_dto.getCurrency());
                if (error_code !=null){
                    error_code=ErrorCodeConstant.ROUTE_COST_CURRENCY_INVALID;
                    break;
                }
                error_code = this.checkPrice(cost_dto.getCost_price());
                if (error_code !=null){
                    error_code=ErrorCodeConstant.ROUTE_COST_PRICE_INVALID;
                    break;
                }
                error_code = this.checkQuantity(cost_dto.getQuantity());
                if (error_code !=null){
                    error_code=ErrorCodeConstant.ROUTE_COST_QUANTITY_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(cost_dto.getTotal_price(),cost_dto.getQuantity(),cost_dto.getCost_price());
                if (error_code !=null){
                    error_code=ErrorCodeConstant.ROUTE_COST_TOTAL_PRICE_INVALID;
                    break;
                }
            }
        }
        return error_code;

    }

    /**
     * 校验成本项编号
     * @param cost_code
     * @return
     */
    public String checkCostCode(String cost_code){
        RoutePricePlanCostDetail cost_record = route_cost_dao.selectByCostCode(cost_code);
        if (cost_record == null){
            return ErrorCodeConstant.ROUTE_COST_CODE_INVALID;
        }
        return null;
    }
}
