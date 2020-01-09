package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.dto.product.RoutePlanNodeDto;
import com.kunlun.erp.core.entity.RoutePlanNode;
import com.kunlun.erp.core.mapper.RoutePlanNodeMapper;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RoutePlanNodeValidator
 * @Description 线路产品计划节点校验器
 * @Author Jm.zhang
 * @Date 2019/12/9 16:22
 * @Version 1.0
 **/
@Component(value = "route_plan_node_validator")
public class RoutePlanNodeValidator {
    @Resource
    private RoutePlanNodeMapper node_dao;
    @Resource(name = "area_validator")
    private AreaValidator area_validator;

    public String check(List<RoutePlanNodeDto> plan_node_info){
        String error_code= null;
        for (RoutePlanNodeDto node_dto : plan_node_info){
            if (StringUtils.isNotBlank(node_dto.getNode_code())){
                error_code = this.checkNodeCode(node_dto.getNode_code());
                if (error_code != null){
                    break;
                }
            }

            error_code = this.checkDay(node_dto.getNode_day());
            if (error_code != null){
                break;
            }
            error_code = this.checkTrafficType(node_dto.getTraffic_type());
            if (error_code != null){
                break;
            }
            error_code = this.checkBreakfast(node_dto.getHas_breakfast());
            if (error_code != null){
                break;
            }
            error_code = this.checkLunch(node_dto.getHas_lunch());
            if (error_code != null){
                break;
            }
            error_code = this.checkDinner(node_dto.getHas_dinner());
            if (error_code != null){
                break;
            }
        }

        return error_code;
    }

    /**
     * 校验节点编号
     * @param node_code
     * @return
     */
    public String checkNodeCode(String node_code){
        RoutePlanNode node_record = node_dao.selectByNodeCode(node_code);
        if (node_record == null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_NOT_EXIST;
        }
        return null;
    }

    /**
     * 节点序号，第 1 天  2  3
     * @param node_day
     * @return
     */
    public String checkDay(Integer node_day){
        if (node_day == null || node_day<=0){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_DAY_INVALID;
        }
        return null;
    }

    /**
     * 校验交通工具
     * @param traffic_type
     * @return
     */
    public String checkTrafficType(Integer traffic_type){
        if (SysConstant.TrafficType.getTrafficType(traffic_type)==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_TRAFFIC_TYPE_INVALID;
        }
        return null;
    }



    /**
     * 早餐
     * @param has_breakfast
     * @return
     */
    public String checkBreakfast(Integer has_breakfast){
        if (SysConstant.IncludeMeal.getIncludeMeal(has_breakfast)==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_HAS_BREAKFAST_INVALID;
        }
        return null;
    }

    /**
     * 中餐
     * @param has_lunch
     * @return
     */
    public String checkLunch(Integer has_lunch){
        if (SysConstant.IncludeMeal.getIncludeMeal(has_lunch)==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_HAS_LUNCH_INVALID;
        }
        return null;
    }
    /**
     * 晚餐
     * @param has_dinner
     * @return
     */
    public String checkDinner(Integer has_dinner){
        if (SysConstant.IncludeMeal.getIncludeMeal(has_dinner)==null){
            return ErrorCodeConstant.PRODUCT_ROUTE_PLAN_NODE_HAS_DINNER_INVALID;
        }
        return null;
    }
}
