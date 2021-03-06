package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.RouteProductDto;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.ProductInfo;
import com.kunlun.erp.core.service.product.ProductService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RouteProductValidator
 * @Description 线路产品请求校验器
 * @Author Jm.zhang
 * @Date 2019/12/9 11:56
 * @Version 1.0
 **/
@Component(value = "product_validator")
public class RouteProductValidator extends AbstractValidator {
    public RouteProductValidator(){
        super.name_space= Urls.Product.NAMESPACE;
    }
    @Resource(name = "product_service")
    private ProductService product_service;

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteProductAddRequest.class.isAssignableFrom(clazz) || RouteProductListRequest.class.isAssignableFrom(clazz)
                || RouteProductDetailRequest.class.isAssignableFrom(clazz) || RouteProductEditRequest.class.isAssignableFrom(clazz)
                ||RouteProductCopyRequest.class.isAssignableFrom(clazz) || RouteProductDelRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code = null;
        if (obj instanceof RouteProductAddRequest){
            RouteProductAddRequest request = (RouteProductAddRequest)obj;
            RouteProductDto req_body = request.getBody();
            error_code = product_category_validator.checkCategoryCode(req_body.getProduct_category_code());
            if (error_code ==null){
                error_code = area_validator.check(req_body.getArea_info(),true);
            }
            if (error_code == null){
                error_code = this.checkProductName(req_body.getProduct_name(),true);
            }
            if (error_code == null){
                error_code = route_validator.checkIsShopping(req_body.getRoute_base_info().getIs_shopping());
            }
            if (error_code == null){
                error_code = route_validator.checkOwnerExpense(req_body.getRoute_base_info().getOwner_expense());
            }
            if (error_code == null){
                error_code = route_validator.checkRoomType(req_body.getRoute_base_info().getRoom_type());
            }
            if (error_code == null){
                error_code = route_validator.checkInsuranceType(req_body.getRoute_base_info().getInsurance_type());
            }

            if (error_code == null){
                error_code = route_plan_validator.check(req_body.getRoute_plan_info());
            }
        }else if (obj instanceof RouteProductListRequest){
            RouteProductListRequest request = (RouteProductListRequest)obj;

        }else if (obj instanceof  RouteProductDetailRequest){
            RouteProductDetailRequest request = (RouteProductDetailRequest)obj;
            error_code = this.checkProductCode(request.getBody().getProduct_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        }else if (obj instanceof RouteProductEditRequest){
            RouteProductEditRequest request = (RouteProductEditRequest)obj;
            RouteProductDto req_body = request.getBody();
            error_code = this.checkProductCode(request.getBody().getProduct_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                error_code = product_category_validator.checkCategoryCode(req_body.getProduct_category_code());
            }
            if (error_code ==null){
                error_code = area_validator.check(req_body.getArea_info(),false);
            }
            if (error_code == null){
                error_code = this.checkProductName(req_body.getProduct_name(),false);
            }
            if (error_code == null){
                error_code = route_validator.checkIsShopping(req_body.getRoute_base_info().getIs_shopping());
            }
            if (error_code == null){
                error_code = route_validator.checkOwnerExpense(req_body.getRoute_base_info().getOwner_expense());
            }
            if (error_code == null){
                error_code = route_validator.checkRoomType(req_body.getRoute_base_info().getRoom_type());
            }
            if (error_code == null){
                error_code = route_validator.checkInsuranceType(req_body.getRoute_base_info().getInsurance_type());
            }

            if (error_code == null){
                error_code = route_plan_validator.check(req_body.getRoute_plan_info());
            }

        }else if (obj instanceof  RouteProductCopyRequest){
            RouteProductCopyRequest request = (RouteProductCopyRequest)obj;


        }else if (obj instanceof  RouteProductDelRequest){
            RouteProductDelRequest request = (RouteProductDelRequest)obj;
            error_code = this.checkProductCode(request.getBody().getProduct_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
        }

        return error_code;
    }


    /**
     * 校验产品名称
     * @param product_name
     * @return
     */
    public String checkProductName(String product_name,boolean check_duplicate){
        String error_code = null;
        if (RegexUtil.commonStrCheck(product_name,2,50,true)==false){
            error_code = ErrorCodeConstant.PRODUCT_NAME_INVALID;
        }
        if (error_code == null){
            if (check_duplicate){
                if (product_service.isExist(product_name)){
                    error_code = ErrorCodeConstant.PRODUCT_NAME_IS_EXIST;
                }
            }

        }
        return error_code;
    }

    /**
     * 校验产品是否存在
     * @param product_code
     * @return
     */
    public String checkProductCode(String product_code,String trans_no,String secret_key,String per_key){
        String error_code=null;
        ProductInfo product_record = product_service.selectByProductCode(product_code);
        if (product_record==null){
            error_code= ErrorCodeConstant.PRODUCT_IS_NOT_EXIST;
        }
        if (error_code == null){
            AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(trans_no,secret_key,per_key);
            if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(trans_no,secret_key, Urls.Product.NAMESPACE);
                if (product_record.getCreator_id()!=user_info.getBody().getUid()){
                    error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
                }
            }
        }
        return error_code;
    }

    /**
     * 校验产品是否存在
     * @param product_code
     * @return
     */
    public String checkProductCode(String product_code){
        ProductInfo product_record = product_service.selectByProductCode(product_code);
        if (product_record==null){
            return ErrorCodeConstant.PRODUCT_IS_NOT_EXIST;
        }
        return null;
    }

}
