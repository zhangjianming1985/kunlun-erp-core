package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.product.RoutePlanDto;
import com.kunlun.erp.core.dto.product.request.RoutePlanShortRequest;
import com.kunlun.erp.core.entity.RoutePlan;
import com.kunlun.erp.core.mapper.RoutePlanMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RoutePlanValidator
 * @Description 线路产品行程计划校验器
 * @Author Jm.zhang
 * @Date 2019/12/9 15:54
 * @Version 1.0
 **/
@Component(value = "route_plan_validator")
public class RoutePlanValidator extends AbstractValidator {
    public RoutePlanValidator(){
        super.name_space= Urls.Product.NAMESPACE;
    }

    @Resource
    private RoutePlanMapper route_plan_dao;


    @Override
    public String myValidate(Object obj) {
        String error_code= null;
        if (obj instanceof RoutePlanShortRequest){
            RoutePlanShortRequest request = (RoutePlanShortRequest)obj;
            error_code = product_validator.checkProductCode(request.getBody().getProduct_code());

        }
        return error_code;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RoutePlanShortRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String validatePermission(Object target) {
        if (target instanceof RoutePlanShortRequest){
            return null;
        }
        return super.validatePermission(target);
    }

    public String check(List<RoutePlanDto> route_plan_info){
        String error_code = null;
        for (RoutePlanDto plan_dto : route_plan_info){
            if (StringUtils.isNotBlank(plan_dto.getRoute_plan_code())){
                error_code = this.checkPlanCode(plan_dto.getRoute_plan_code());
                if (error_code != null){
                    break;
                }
            }
            error_code = this.checkPlanName(plan_dto.getRoute_plan_name());
            if (error_code != null){
                break;
            }
            error_code = this.checkDefault(plan_dto.getIs_default());
            if (error_code != null){
                break;
            }
            error_code = this.checkDepartureArea(plan_dto.getDeparture_area_info());
            if (error_code != null){
                break;
            }
            error_code = this.checkDestinationArea(plan_dto.getDestination_area_info());
            if (error_code != null){
                break;
            }
            error_code = this.checkRendezvousArea(plan_dto.getRendezvous_area_info());
            if (error_code != null){
                break;
            }
            error_code = this.checkDays(plan_dto.getDays());
            if (error_code != null){
                break;
            }
            error_code = this.checkNights(plan_dto.getNights());
            if (error_code != null){
                break;
            }
            error_code = route_plan_node_validator.check(plan_dto.getPlan_node_info());
            if (error_code != null){
                break;
            }
            error_code = route_plan_price_validator.check(plan_dto.getPlan_price_info());
            if (error_code != null){
                break;
            }

        }
        return error_code;
    }

    /**
     * 校验行程计划编号
     * @param plan_code
     * @return
     */
    public String checkPlanCode(String plan_code){
        RoutePlan plan_record = route_plan_dao.selectByPlanCode(plan_code);
        if (plan_record==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NOT_EXIST;
        }
        return null;
    }

    /**
     * 校验计划名称
     * @param route_plan_name
     * @return
     */
    public String checkPlanName(String route_plan_name){
        if (StringUtils.isBlank(route_plan_name) || RegexUtil.commonStrCheck(route_plan_name,2,50,true)==false){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NAME_INVALID;
        }
        return null;
    }

    /**
     * 默认行程校验
     * @param is_default
     * @return
     */
    public String checkDefault(Integer is_default){
        if (SysConstant.IsDefaultType.getIsDefaultType(is_default)==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_DEFAULT_INVALID;
        }
        return null;
    }

    /**
     * 校验出发地
     * @param departure_area_info
     * @return
     */
    public String checkDepartureArea(AreaDto departure_area_info){
        return area_validator.check(departure_area_info,true);
    }

    /**
     * 校验目的地
     * @param destination_area_info
     * @return
     */
    public String checkDestinationArea(AreaDto destination_area_info){
        return area_validator.check(destination_area_info,true);
    }

    /**
     * 校验成团地点
     * @param rendezvous_area_info
     * @return
     */
    public String checkRendezvousArea(AreaDto rendezvous_area_info){
        return area_validator.check(rendezvous_area_info,true);
    }

    /**
     * 行程天数
     * @param days
     * @return
     */
    public String checkDays(Integer days){
        if (days == null || days<=0){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_DAYS_INVALID;
        }
        return null;
    }

    /**
     * 行程天数(晚)
     * @param nights
     * @return
     */
    public String checkNights(Integer nights){
        if (nights == null || nights<0){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NIGHTS_INVALID;
        }
        return null;
    }


}
