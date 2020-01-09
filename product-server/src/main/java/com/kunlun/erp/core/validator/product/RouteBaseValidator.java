package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName RouteBaseValidator
 * @Description 线路产品基础信息校验器
 * @Author Jm.zhang
 * @Date 2019/12/9 15:52
 * @Version 1.0
 **/
@Component(value = "route_validator")
public class RouteBaseValidator {


    /**
     * 是否购物
     * @param is_shopping
     * @return
     */
    public String checkIsShopping(Integer is_shopping){
        if (SysConstant.IsShopping.getIsShopping(is_shopping)==null){
            return ErrorCodeConstant.PRODUCT_IS_SHOPPING_INVALID;
        }
        return null;
    }


    /**
     * 是否购物
     * @param expense
     * @return
     */
    public String checkOwnerExpense(Integer expense){
        if (SysConstant.IsOwnerExpense.getIsOwnerExpense(expense)==null){
            return ErrorCodeConstant.PRODUCT_OWNER_EXPENSE_INVALID;
        }
        return null;
    }

    /**
     * 用房信息
     * @param room_type
     * @return
     */
    public String checkRoomType(String room_type){
        if (StringUtils.isNotBlank(room_type)){
            String[] array = room_type.split(",");
            for (String str : array){
                if (SysConstant.RoomType.getRoomType(Integer.valueOf(str))==null){
                    return ErrorCodeConstant.PRODUCT_ROOM_TYPE_INVALID;
                }

            }
        }

        return null;
    }

    /**
     * 保险信息
     * @param insurance_type
     * @return
     */
    public String checkInsuranceType(String insurance_type){
        if (StringUtils.isNotBlank(insurance_type)){
            String[] array = insurance_type.split(",");
            for (String str : array){
                if (SysConstant.InsuranceType.getInsuranceType(Integer.valueOf(str))==null){
                    return ErrorCodeConstant.PRODUCT_INSURANCE_TYPE_INVALID;
                }

            }
        }
        return null;
    }
}
