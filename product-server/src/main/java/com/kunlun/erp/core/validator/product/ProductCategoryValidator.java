package com.kunlun.erp.core.validator.product;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.request.*;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.ProductCategory;
import com.kunlun.erp.core.mapper.ProductInfoMapper;
import com.kunlun.erp.core.service.product.ProductCategoryService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName ProductCategoryValidator
 * @Description 产品类别请求校验
 * @Author Jm.zhang
 * @Date 2019/12/6 10:36
 * @Version 1.0
 **/
@Component(value = "product_category_validator")
public class ProductCategoryValidator extends AbstractValidator {

    public ProductCategoryValidator(){
        super.name_space= Urls.Product.NAMESPACE;
    }
    @Resource(name = "product_category_service")
    private ProductCategoryService product_category_service;
    @Resource
    private ProductInfoMapper product_dao;
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCategoryListRequest.class.isAssignableFrom(clazz) || ProductCategoryAddRequest.class.isAssignableFrom(clazz)
                || ProductCategoryDetailRequest.class.isAssignableFrom(clazz) || ProductCategoryUpdateRequest.class.isAssignableFrom(clazz)
                || ProductCategoryDelRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code = null;
        if (obj instanceof ProductCategoryDetailRequest){
            ProductCategoryDetailRequest request = (ProductCategoryDetailRequest)obj;
                error_code= this.checkCategoryCode(request.getBody().getCategory_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        }else if (obj instanceof ProductCategoryUpdateRequest){
            ProductCategoryUpdateRequest  request = (ProductCategoryUpdateRequest)obj;
            error_code = this.checkCategoryCode(request.getBody().getCategory_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                if (request.getBody().getCategory_state()!= null && SysConstant.ProductCategoryState.getProductCategoryState(request.getBody().getCategory_state())==null){
                    error_code = ErrorCodeConstant.PRODUCT_CATEGORY_STATE_INVALID;
                }
            }

        }else if (obj instanceof  ProductCategoryDelRequest){
            ProductCategoryDelRequest request = (ProductCategoryDelRequest)obj;
            error_code = this.checkCategoryCode(request.getBody().getCategory_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                //被使用的类别，不能被删除
                if(product_dao.countByProductCategoryCode(request.getBody().getCategory_code())>0){
                    error_code = ErrorCodeConstant.PRODUCT_CATEGORY_DELETE_FAIL_DUE_TO_USING;
                }
                

            }
        }

        return error_code;
    }
    public String checkCategoryCode(String category_code,String trans_no,String secret_key,String per_key){
        String error_code=null;
        ProductCategory record = product_category_service.getRecordByCategoryCode(category_code);
        if (record== null){
            error_code =  ErrorCodeConstant.PRODUCT_CATEGORY_CODE_INVALID;
        }
        if (error_code == null){
            AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(trans_no,secret_key,per_key);
            if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(trans_no,secret_key, Urls.Product.NAMESPACE);
                if (record.getCreator_id()!=user_info.getBody().getUid()){
                    error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
                }
            }
        }
        return error_code;
    }

    public String checkCategoryCode(String category_code){
        ProductCategory record = product_category_service.getRecordByCategoryCode(category_code);
        if (record== null){
            return ErrorCodeConstant.PRODUCT_CATEGORY_CODE_INVALID;
        }
        return null;
    }
}
