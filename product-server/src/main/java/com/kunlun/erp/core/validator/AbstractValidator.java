package com.kunlun.erp.core.validator;

import com.kunlun.erp.core.common.configuration.PermissionKeyProperties;
import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.BaseRequestHeader;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.service.account.AccountService;
import com.kunlun.erp.core.service.account.PermissionService;
import com.kunlun.erp.core.validator.common.*;
import com.kunlun.erp.core.validator.product.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

/**
 * @ClassName AbstractValidator
 * @Description 基础校验器
 * @Author Jm.zhang
 * @Date 2019/11/15 9:14
 * @Version 1.0
 **/
@Component(value = "absValidator")
public abstract class AbstractValidator implements Validator {
    protected String name_space;
    @Resource(name = "base_validator")
    protected BaseInfoValidator base_validator;
    @Resource(name = "area_validator")
    protected AreaValidator area_validator;
    @Resource(name = "leader_validator")
    protected LeaderValidator leader_validator;
    @Resource(name = "finance_validator")
    protected FinanceValidator finance_validator;
    @Resource(name = "contact_validator")
    protected ContactValidator contact_validator;
    @Resource(name = "account_service")
    protected AccountService account_service;
    @Resource(name = "permission_service")
    protected PermissionService permission_service;
    @Resource(name = "sales_channel_cost_validator")
    protected  SalesChannelCostValidator sales_channel_cost_validator;
    @Resource(name = "product_validator")
    protected RouteProductValidator product_validator;
    @Resource(name = "route_plan_validator")
    protected RoutePlanValidator route_plan_validator;
    @Resource(name = "route_plan_node_validator")
    protected RoutePlanNodeValidator route_plan_node_validator;
    @Resource(name = "route_plan_price_validator")
    protected RoutePlanBasePriceValidator route_plan_price_validator;
    @Resource(name = "product_category_validator")
    protected ProductCategoryValidator product_category_validator;
    @Resource(name = "route_validator")
    protected RouteBaseValidator route_validator;

    @Resource
    protected PermissionKeyProperties per_properties;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    public AbstractValidator(){
    }


    @Override
    public void validate(Object target, Errors errors) {
        AbstractRequest request = (AbstractRequest) target;
        Object request_body = request.getBody();
        BaseRequestHeader request_header = request.getHeader();
        String error_code = validateHeader(request_header);
        if (StringUtils.isBlank(error_code)){
            error_code = validateBody(request_body);
        }
        if (error_code == null){
            error_code = this.validateSecretKey(request_header);
        }

        if (error_code== null){
            error_code = this.myValidate(target);
        }
        if (error_code == null){
            error_code= this.validatePermission(target);
        }
        if (StringUtils.isNotBlank(error_code)) {
            errors.rejectValue("body", error_code, error_code);
        }

    }

    /**
     * 功能描述:校验报文头
     *
     * @param header  报头
     * @return 校验结果
     */
    private String validateHeader(BaseRequestHeader header) {
        String error_code = null;
        if (header == null) {
            error_code = ErrorCodeConstant.REQUEST_HEADER_IS_INVALID;
        }
        if (error_code == null) {
            if (StringUtils.isBlank(header.getTrans_no())){
                error_code = ErrorCodeConstant.REQUEST_TRANS_CODE_IS_INVALID;
            }
        }
        if (error_code == null) {
            if (StringUtils.isBlank(header.getSecret_key())){
                error_code = ErrorCodeConstant.REQUEST_SECRET_KEY_IS_INVALID;
            }
        }


        return error_code;
    }

    /**
     * 功能描述:校验报文体
     *
     * @param body 报文
     * @return 校验结果
     */
    private String validateBody(Object body) {
        String error_code = null;
        if (body == null) {
            error_code = ErrorCodeConstant.REQUEST_BODY_IS_INVALID;
        }
        return error_code;
    }

    /**
     * 校验secret key合法
     * @param request_header
     * @return
     */
    private  String validateSecretKey(BaseRequestHeader request_header){
        String error_code = null;
        try{
            AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(request_header.getTrans_no(),request_header.getSecret_key(), this.name_space);
            if (user_info ==null){
                error_code=ErrorCodeConstant.NO_RESPONSE_CHECKING_SECRET_KEY;
            }
            if (error_code == null){
                if (user_info.getHeader().getState().equalsIgnoreCase(SysConstant.RespStatus.resp_status_fail.getValue())){
                    error_code = JsonUtil.toStr(user_info);
                }
            }
        }catch (Exception e){
            error_code = ErrorCodeConstant.REQUEST_SECRET_KEY_IS_INVALID;
            e.printStackTrace();
        }
        return error_code;
    }

    /**
     * 校验权限
     * @param target
     * @return
     */
    public  String validatePermission(Object target){
        String error_code = null;
        BaseRequestHeader request_header = ((AbstractRequest)target).getHeader();
        String per_key= permission_service.getPermissionKey(target);
        AbstractResponse<HasPermissionRespDto> permission_resp = permission_service.getUserByPermission(request_header.getTrans_no(),request_header.getSecret_key(),per_key);
        if (permission_resp == null){
            error_code = ErrorCodeConstant.NO_RESPONSE_VALIDATE_PERMISSIONS;
        }else{
            if (permission_resp.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                error_code = JsonUtil.toStr(permission_resp);
            }
        }
        return error_code;

    }



    /**
     * 功能描述:业务数据校验，由各个模块自己的validate 进行验证
     * @param obj
     * @return
     */
    public abstract String myValidate(Object obj);









}
