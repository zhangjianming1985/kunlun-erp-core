package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.HallProductDetailRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductListRequest;
import com.kunlun.erp.core.dto.routeHall.request.HallProductStateUpdateRequest;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteHall;
import com.kunlun.erp.core.mapper.RouteHallMapper;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName RouteHallValidator
 * @Description 线路大厅校验器
 * @Author Jm.zhang
 * @Date 2019/12/18 21:39
 * @Version 1.0
 **/
@Component(value = "route_hall_validator")
public class RouteHallValidator  extends AbstractValidator {

    public RouteHallValidator() {
        super.name_space= Urls.RouteHall.NAMESPACE;
    }

    @Resource
    private RouteHallMapper hall_dao;



    @Override
    public boolean supports(Class<?> clazz) {
        return HallProductListRequest.class.isAssignableFrom(clazz) || HallProductDetailRequest.class.isAssignableFrom(clazz)
                || HallProductStateUpdateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code= null;
        if (obj instanceof HallProductDetailRequest){
            HallProductDetailRequest  request = (HallProductDetailRequest)obj;
            error_code = this.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof HallProductStateUpdateRequest){
            HallProductStateUpdateRequest request = (HallProductStateUpdateRequest)obj;
            error_code= this.checkGroupCode(request.getBody().getGroup_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                RouteHall record = hall_dao.selectByGroupCode(request.getBody().getGroup_code());
                if (record.getStatus() == request.getBody().getState()){
                    //状态一致
                    error_code = ErrorCodeConstant.HALL_DAILY_STATE_INVALID;
                }
                if (error_code ==null){
                    //是否可执行取消操作
                    if (request.getBody().getState() == SysConstant.HallProductStatus.cancel_trip.getValue()){
                        //原始状态为待出团、行程中、行程结束 才可取消行程
                        if (record.getStatus()!=SysConstant.HallProductStatus.pending_trip.getValue()
                                && record.getStatus()!=SysConstant.HallProductStatus.on_trip.getValue() &&record.getStatus()!=SysConstant.HallProductStatus.end_trip.getValue()){
                            error_code = ErrorCodeConstant.HALL_DAILY_STATE_INVALID;
                        }
                    }
                }
                if (error_code == null){
                    //是否可执行删除操作
                    if (request.getBody().getState() == SysConstant.HallProductStatus.delete_trip.getValue()){

                    }
                }
                if (error_code == null){
                    //是否可执行恢复操作
                    if (request.getBody().getState() == 5){
                        if (record.getStatus()!=SysConstant.HallProductStatus.delete_trip.getValue() && record.getStatus()!=SysConstant.HallProductStatus.cancel_trip.getValue()){
                            //非已删除 已取消状态，不可执行恢复操作
                            error_code = ErrorCodeConstant.HALL_DAILY_STATE_INVALID;
                        }
                    }
                }

            }

        }
        return error_code;
    }

    /**
     * 校验团号
     * @param group_code
     * @return
     */
    public String checkGroupCode(String group_code,String trans_no,String secret_key,String per_key){
        String error_code = null;
        RouteHall hall_record = hall_dao.selectByGroupCode(group_code);
        if (hall_record == null){
            error_code =  ErrorCodeConstant.HALL_DAILY_CODE_INVALID;
        }
        if (error_code == null){
            AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(trans_no,secret_key,per_key);
            if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(trans_no,secret_key, Urls.RouteHall.NAMESPACE);
                if (hall_record.getCreator_id()!=user_info.getBody().getUid()){
                    error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
                }
            }
        }
        return error_code;
    }

    /**
     * 校验团号
     * @param group_code
     * @return
     */
    public String checkGroupCode(String group_code){
       RouteHall hall_record = hall_dao.selectByGroupCode(group_code);
       if (hall_record == null){
            return ErrorCodeConstant.HALL_DAILY_CODE_INVALID;
       }
       return null;
    }
}
