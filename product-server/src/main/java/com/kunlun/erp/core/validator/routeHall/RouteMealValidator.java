package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteMealDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteMeal;
import com.kunlun.erp.core.mapper.RouteMealMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteMealValidator
 * @Description 线路用餐校验器
 * @Author Jm.zhang
 * @Date 2019-12-22 18:44
 * @Version 1.0
 **/
@Component(value = "route_meal_validator")
public class RouteMealValidator  extends AbstractValidator {
    public RouteMealValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }

    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource
    private RouteMealMapper meal_dao;
    @Resource(name = "person_service")
    private PersonService person_service;

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteMealListRequest.class.isAssignableFrom(clazz) || RouteMealAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteMealListRequest){
            RouteMealListRequest request = (RouteMealListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteMealAddRequest){
            RouteMealAddRequest request = (RouteMealAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code == null){
                error_code = this.checkMeal(request.getBody().getMeal_data());
            }
        }
        return error_code;
    }

    public String checkMeal(List<RouteMealDto> meal_data){
        String error_code = null;
        if (meal_data!=null && meal_data.isEmpty()==false){
            for (RouteMealDto dto : meal_data){
                if (StringUtils.isNotBlank(dto.getMeal_code())){
                    RouteMeal record = meal_dao.selectByMealCode(dto.getMeal_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.MEAL_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_meal.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (SysConstant.MealType.getMealType(dto.getMeal_type())==null){
                    error_code = ErrorCodeConstant.MEAL_TYPE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getMeal_date())==false){
                    error_code = ErrorCodeConstant.MEAL_DATE_INVALID;
                    break;
                }

                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.MEAL_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.MEAL_PRICE_INVALID;
                    break;
                }
                if (dto.getDiners_count() <=0){
                    error_code = ErrorCodeConstant.MEAL_DINNERS_COUNT_INVALID;
                    break;
                }
                if (dto.getFree_count() < 0 || dto.getFree_count() >= dto.getDiners_count()){
                    error_code = ErrorCodeConstant.MEAL_FREE_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getDiners_count(),dto.getFree_count(),dto.getFee());
                if (error_code!=null)break;
            }
        }
        return error_code;
    }

    /**
     * 总价
     * @param total_price
     * @return
     */
    public String checkTotalPrice(String  total_price,Integer quantity, Integer free_quantity,String price){
        if (RegexUtil.isDecimal(total_price)==false){
            return ErrorCodeConstant.MEAL_TOTAL_PRICE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*(quantity-free_quantity))){
            return ErrorCodeConstant.MEAL_TOTAL_PRICE_INVALID;
        }
        return  null;
    }
}
