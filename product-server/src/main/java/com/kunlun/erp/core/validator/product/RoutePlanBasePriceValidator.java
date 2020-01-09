package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.util.ArithmeticUtil;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.product.RoutePlanBasePriceDto;
import com.kunlun.erp.core.entity.RoutePlanBasePrice;
import com.kunlun.erp.core.mapper.RoutePlanBasePriceMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RoutePlanBasePriceValidator
 * @Description 线路行程基础价格校验器
 * @Author Jm.zhang
 * @Date 2019/12/9 16:52
 * @Version 1.0
 **/
@Component(value = "route_plan_price_validator")
public class RoutePlanBasePriceValidator {
    @Resource
    private RoutePlanBasePriceMapper price_dao;

    public String check(List<RoutePlanBasePriceDto> plan_price_info){
        String error_code = null;
        if (plan_price_info!=null && plan_price_info.size()>0){
            for (RoutePlanBasePriceDto price_dto :plan_price_info){
                if (StringUtils.isNotBlank(price_dto.getBase_price_code())){
                    error_code = this.checkPriceCode(price_dto.getBase_price_code());
                    if (error_code !=null){
                        break;
                    }
                }
                error_code = this.checkFeeType(price_dto.getFee_type());
                if (error_code !=null){
                    break;
                }
                error_code = this.checkCurrency(price_dto.getCurrency());
                if (error_code !=null){
                    break;
                }
                error_code = this.checkPrice(price_dto.getPrice());
                if (error_code !=null){
                    break;
                }
                error_code = this.checkQuantity(price_dto.getQuantity());
                if (error_code !=null){
                    break;
                }
                error_code = this.checkTotalPrice(price_dto.getTotal_price(),price_dto.getQuantity(),price_dto.getPrice());
                if (error_code !=null){
                    break;
                }
            }
        }
        return error_code;

    }

    /**
     * 校验价格编号
     * @param price_code
     * @return
     */
    public String checkPriceCode(String price_code){
        RoutePlanBasePrice price_record = price_dao.selectByPriceCode(price_code);
        if (price_code == null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_BASE_PRICE_NOT_EXIST;
        }
        return null;
    }


    /**
     * 费用类型
     * @param fee_type
     * @return
     */
    public String checkFeeType(Integer fee_type){
        if (SysConstant.RouteFeeType.getSRouteFeeType(fee_type)==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_FEE_TYPE_INVALID;
        }
        return null;
    }

    /**
     * 货币
     * @param currency
     * @return
     */
    public String checkCurrency(String currency){
        if (SysConstant.Currency.getCurrency(currency)== null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_CURRENCY_INVALID;
        }
        return null;
    }

    /**
     * 单价
     * @param price
     * @return
     */
    public String checkPrice(String price){
        if (RegexUtil.isDecimal(price)==false || ArithmeticUtil.compare(price,"0")==false){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_PRICE_INVALID;
        }
        return  null;
    }
    /**
     * 数量
     * @param quantity
     * @return
     */
    public String checkQuantity(Integer quantity){
        if (quantity==null || quantity<=0){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_QUANTITY_INVALID;
        }
        return  null;
    }

    /**
     * 总价
     * @param total_price
     * @return
     */
    public String checkTotalPrice(String  total_price,Integer quantity,String price){
        if (RegexUtil.isDecimal(total_price)==false){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_TOTAL_PRICE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_TOTAL_PRICE_INVALID;
        }

        return  null;
    }
}


