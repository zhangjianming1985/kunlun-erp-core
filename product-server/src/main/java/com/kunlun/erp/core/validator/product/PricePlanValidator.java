package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.product.RoutePricePlanDetailDto;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.entity.RoutePlan;
import com.kunlun.erp.core.entity.RoutePricePlan;
import com.kunlun.erp.core.mapper.RoutePlanMapper;
import com.kunlun.erp.core.mapper.RoutePricePlanMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PricePlanValidator
 * @Description 价格方案校验器
 * @Author Jm.zhang
 * @Date 2019/12/16 18:40
 * @Version 1.0
 **/
@Component(value = "price_plan_validator")
public class PricePlanValidator extends AbstractValidator {
    public PricePlanValidator(){
        super.name_space= Urls.Product.NAMESPACE;
    }
    @Resource(name = "price_plan_cost_validator")
    private PricePlanCostValidator price_plan_cost_validator;
    @Resource
    private RoutePricePlanMapper price_plan_dao;
    @Resource
    private RoutePlanMapper route_plan_dao;

    @Override
    public boolean supports(Class<?> clazz) {
        return PricePlanAddRequest.class.isAssignableFrom(clazz) || PricePlanDetailRequest.class.isAssignableFrom(clazz)
                || PricePlanDelRequest.class.isAssignableFrom(clazz) || PricePlanCostDetailRequest.class.isAssignableFrom(clazz)
                || PriceDateDelRequest.class.isAssignableFrom(clazz)||PricePlanNameUpdateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code= null;
        if (obj instanceof PricePlanAddRequest){
            PricePlanAddRequest request = (PricePlanAddRequest)obj;
            //校验行程方案编号
            error_code = route_plan_validator.checkPlanCode(request.getBody().getPlan_code());
            if (error_code == null){
                if (StringUtils.isNotBlank(request.getBody().getPrice_plan_code())){
                    //更新价格套餐,校验价格套餐编号
                    error_code = this.checkPricePlanCode(request.getBody().getPrice_plan_code());
                }else{
                    //增加价格套餐， 校验套餐数量是否达到上限
                   int count =  getCountByProductCode(request.getBody().getPlan_code());
                   if (count >6){
                       error_code = ErrorCodeConstant.PRICE_PLAN_MAX_SIZE_LIMIT;
                   }
                   if (error_code == null){
                       //方案名称重复
                        int name_size = this.getPricePlanNameSize(request.getBody().getPlan_code(),request.getBody().getPrice_plan_name());
                        if (name_size>0){
                            error_code = ErrorCodeConstant.PRICE_PLAN_NAME_DUPLICATE;
                        }
                   }

                }
            }
            if (error_code == null){
                error_code = this.checkPlanName(request.getBody().getPrice_plan_name());
            }
            if (error_code ==null){
                error_code = this.checkPriceData(request.getBody().getPrice_info());
            }
            if (error_code == null){
                error_code = this.checkDateArray(request.getBody().getDate_info());
            }
            if (error_code == null){
                error_code = price_plan_cost_validator.checkCost(request.getBody().getCost_info());
            }
        }else if (obj instanceof PricePlanDetailRequest){
            //套餐详情
            PricePlanDetailRequest request = (PricePlanDetailRequest)obj;
            error_code = product_validator.checkProductCode(request.getBody().getProduct_code());
        }else if (obj instanceof PricePlanDelRequest){
            //删除价格套餐
            PricePlanDelRequest request = (PricePlanDelRequest)obj;
            error_code = this.checkPricePlanCode(request.getBody().getPrice_plan_code());
        }else if (obj instanceof  PricePlanCostDetailRequest){
            //成本明细
            PricePlanCostDetailRequest request = (PricePlanCostDetailRequest)obj;
            error_code = this.checkPricePlanCode(request.getBody().getPrice_plan_code());
            if (error_code == null){
                if (RegexUtil.isDate(request.getBody().getDeparture_date())==false){
                    error_code = ErrorCodeConstant.PRICE_DETAIL_DEPARTURE_DATE_INVALID;
                }
            }

        }else if (obj instanceof  PriceDateDelRequest){
            //删除价格日期
            PriceDateDelRequest request = (PriceDateDelRequest)obj;
            error_code = this.checkPricePlanCode(request.getBody().getPrice_plan_code());
            if (error_code == null){
                error_code = this.checkDateArray(request.getBody().getDate_info());
            }
        }else if (obj instanceof PricePlanNameUpdateRequest){
            //修改价格套餐名称
            PricePlanNameUpdateRequest request = (PricePlanNameUpdateRequest)obj;
            error_code = this.checkPricePlanCode(request.getBody().getPrice_plan_code());
            if (error_code == null){
                error_code = this.checkPlanName(request.getBody().getPrice_plan_name());
            }


        }
        return error_code;
    }

    /**
     * 产品的价格方案数量
     * @param route_plan_code
     * @return
     */
    public Integer getCountByProductCode(String route_plan_code){
        RoutePlan plan_record = route_plan_dao.selectByPlanCode(route_plan_code);
        return price_plan_dao.countByProductCode(plan_record.getProduct_code());
    }

    /**
     * 一个线路产品中价格套餐名称的数量
     * @param route_plan_code
     * @return
     */
    public Integer getPricePlanNameSize(String route_plan_code,String price_plan_name){
        RoutePlan plan_record = route_plan_dao.selectByPlanCode(route_plan_code);
        return price_plan_dao.countByProductCodeAndPlanName(plan_record.getProduct_code(),price_plan_name);
    }

    /**
     * 校验价格套餐名称
     * @param price_plan_name
     * @return
     */
    public String checkPlanName(String price_plan_name){
        if (StringUtils.isBlank(price_plan_name) || RegexUtil.commonStrCheck(price_plan_name,2,50,true)==false){
            return ErrorCodeConstant.PRICE_PLAN_NAME_INVALID;
        }
        return null;
    }

    /**
     * 校验价格方案编号
     * @param price_plan_code
     * @return
     */
    public String checkPricePlanCode(String price_plan_code){
        String error_code = null;
        RoutePricePlan price_plan_record = price_plan_dao.selectByPricePlanCode(price_plan_code);
        if (price_plan_record == null){
            error_code = ErrorCodeConstant.PRICE_PLAN_CODE_INVALID;
        }
        return error_code;
    }

    /**
     * 校验价格明细
     * @param price_dto
     * @return
     */
    public String checkPriceData(RoutePricePlanDetailDto price_dto){
        String error_code = null;
/*        if (StringUtils.isBlank(price_dto.getDeparture_date()) || RegexUtil.isDate(price_dto.getDeparture_date())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_DEPARTURE_DATE_INVALID;
        }*/
        if (RegexUtil.isDecimal(price_dto.getAdult_trade_price())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_ADULT_TRADE_PRICE_INVALID;
        }
        if (error_code ==null && RegexUtil.isDecimal(price_dto.getChildren_trade_price())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_CHILDREN_TRADE_PRICE_INVALID;
        }
        if (error_code ==null && RegexUtil.isDecimal(price_dto.getAdult_sales_price())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_ADULT_SALES_PRICE_INVALID;
        }
        if (error_code ==null && RegexUtil.isDecimal(price_dto.getChildren_sales_price())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_CHILDREN_SALES_PRICE_INVALID;
        }
        if (error_code ==null && RegexUtil.isDecimal(price_dto.getRoom_diff_price())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_ROOM_DIFF_PRICE_INVALID;
        }
        if (error_code ==null && price_dto.getInventory()==null || price_dto.getInventory()<0){
            error_code = ErrorCodeConstant.PRICE_DETAIL_INVENTORY_INVALID;
        }
        if (RegexUtil.isDecimal(price_dto.getCost_price())==false){
            error_code = ErrorCodeConstant.PRICE_DETAIL_COST_PRICE_INVALID;
        }

        return  error_code;
    }

    /**
     * 校验日期
     * @param data_arr
     * @return
     */
    public String checkDateArray(List<String> data_arr){
        String error_code = null;
        if (data_arr == null || data_arr.isEmpty()){
            error_code = ErrorCodeConstant.PRICE_DETAIL_DEPARTURE_DATE_INVALID;
        }
        if (error_code == null){
            for (String str :data_arr){
                if (RegexUtil.isDate(str)==false){
                    error_code = ErrorCodeConstant.PRICE_DETAIL_DEPARTURE_DATE_INVALID;
                    break;
                }
            }

        }
        return error_code;
    }
}
