package com.kunlun.erp.core.validator.routeOrder;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderAddRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDeleteRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderDetailRequest;
import com.kunlun.erp.core.dto.routeOrder.request.RouteOrderListRequest;
import com.kunlun.erp.core.dto.user.HasPermissionRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteHall;
import com.kunlun.erp.core.entity.RouteOrder;
import com.kunlun.erp.core.entity.RouteOrderIncome;
import com.kunlun.erp.core.mapper.RouteHallMapper;
import com.kunlun.erp.core.mapper.RouteOrderIncomeMapper;
import com.kunlun.erp.core.mapper.RouteOrderMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import com.kunlun.erp.core.validator.product.RoutePlanBasePriceValidator;
import com.kunlun.erp.core.validator.routeHall.RouteHallValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RouteOrderValidator
 * @Description 线路订单校验器
 * @Author Jm.zhang
 * @Date 2019/12/23 14:23
 * @Version 1.0
 **/
@Component(value = "route_order_validator")
public class RouteOrderValidator extends AbstractValidator {
    public RouteOrderValidator(){
        super.name_space= Urls.RouteOrder.NAMESPACE;
    }
    @Resource
    private RouteOrderMapper order_dao;
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource(name = "person_service")
    private PersonService person_service;
    @Resource
    private RouteHallMapper hall_dao;
    @Resource
    private RouteOrderIncomeMapper income_dao;
    @Resource(name = "route_plan_price_validator")
    private RoutePlanBasePriceValidator route_plan_price_validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteOrderDetailRequest.class.isAssignableFrom(clazz) || RouteOrderAddRequest.class.isAssignableFrom(clazz)
                || RouteOrderListRequest.class.isAssignableFrom(clazz)||RouteOrderDeleteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code  = null;
        if (obj instanceof  RouteOrderDetailRequest){
            RouteOrderDetailRequest request = (RouteOrderDetailRequest)obj;
            error_code = this.checkOrderCode(request.getBody().getOrder_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getQuery_all_data());
        }else if (obj instanceof RouteOrderAddRequest){
            RouteOrderAddRequest request = (RouteOrderAddRequest)obj;
            if (StringUtils.isNotBlank(request.getBody().getOrder_code())){
                //校验订单号
                error_code = this.checkOrderCode(request.getBody().getOrder_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            }
            if (error_code == null){
                //校验团号
                error_code= route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            }
            if (error_code == null){
                //校验团审核状态
                RouteHall record= hall_dao.selectByGroupCode(request.getBody().getGroup_code());
                if (record.getApprove_state()==SysConstant.FinanceAuditStatus.approved.getValue()){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_NO_EDIT;
                }
            }

            if (error_code == null){
                //校验销售渠道
                error_code = base_validator.checkCompanyCode(request.getBody().getCompany_code());
            }
            if (error_code ==null){
                //校验销售联系人
                PersonInfo record = person_service.getPersonByPersonCode(request.getBody().getContact_code());
                if (record ==null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                }
            }
            if (error_code == null){
                //校验客源地
                error_code =area_validator.check(request.getBody().getClient_area(),true);
            }
            if (error_code == null){
                //用房标准
                if (SysConstant.RoomLevel.getRoomLevel(request.getBody().getHotel_level())==null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_ROOM_LEVEL_INVALID;
                }
            }
            if (error_code ==null){
                //校验状态
                if (SysConstant.RouteOrderState.getRouteOrderState(request.getBody().getState())==null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_STATE_INVALID;
                }
            }
            if (error_code == null){
                //校验团款
                error_code = this.checkIncome(request.getBody().getIncome_data());
            }

            if (error_code == null){
                if (StringUtils.isBlank(request.getBody().getOrder_code())){
                    //校验团的状态 是否可报名
                    int status = hall_dao.selectStatusByGroupCode(request.getBody().getGroup_code());
                    if (status!=SysConstant.HallProductStatus.pending_trip.getValue() && status !=SysConstant.HallProductStatus.on_trip.getValue()){
                        //非待出团和行程中，不可新报名
                        error_code = ErrorCodeConstant.ROUTE_ORDER_NO_CREATE_DUE_TO_STATUS;
                    }
                    if (error_code == null){
                        //报名人数已上限
                        RouteHall hall_record = hall_dao.selectByGroupCode(request.getBody().getGroup_code());
                        int total_person = request.getBody().getAdult_count()+request.getBody().getChildren_count();
                        if (hall_record.getCount_remain()<=0 || hall_record.getCount_remain()< total_person){
                            error_code = ErrorCodeConstant.ROUTE_ORDER_PEOPLE_UPPER_LIMIT;
                        }
                    }

                }else{
                    //人数变动 是否达到上限
                    RouteOrder old_record = order_dao.selectByOrderCode(request.getBody().getOrder_code());
                    int origin_total = old_record.getAdult_count()+old_record.getChildren_count();
                    int current_total = request.getBody().getAdult_count() + request.getBody().getChildren_count();
                    if (request.getBody().getState()!=SysConstant.RouteOrderState.cancel.getValue() && current_total > origin_total){
                        //非取消的订单 增加了人数，需校验人数限制
                        RouteHall hall_record = hall_dao.selectByGroupCode(request.getBody().getGroup_code());
                        if (hall_record.getCount_remain() < (current_total - origin_total)){
                            error_code = ErrorCodeConstant.ROUTE_ORDER_PEOPLE_UPPER_LIMIT;
                        }
                    }
                }
            }


        }else if (obj instanceof  RouteOrderListRequest){


        }else if (obj instanceof RouteOrderDeleteRequest){
            RouteOrderDeleteRequest request = (RouteOrderDeleteRequest)obj;
            error_code = this.checkOrderCode(request.getBody().getOrder_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
        }
        return error_code;
    }


    /**
     * 校验订单号
     * @return
     */
    public String checkOrderCode(String order_code,String trans_no,String secret_key,String per_key){
        String error_code = null;
        RouteOrder order_record = order_dao.selectByOrderCode(order_code);
        if (order_record == null){
            error_code = ErrorCodeConstant.ROUTE_ORDER_CODE_INVALID;
        }
        if (error_code == null){
            AbstractResponse<HasPermissionRespDto> permission_dto = permission_service.getUserByPermission(trans_no,secret_key,per_key);
            if (permission_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_fail.getValue())){
                AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfo(trans_no,secret_key, Urls.RouteOrder.NAMESPACE);
                if (order_record.getCreator_id()!=user_info.getBody().getUid()){
                    error_code = ErrorCodeConstant.REQUEST_ILLEGAL;
                }
            }
        }
        return error_code;
    }

    /**
     * 校验订单号
     * @param order_code
     * @return
     */
    public String checkOrderCode(String order_code){
        RouteOrder order_record = order_dao.selectByOrderCode(order_code);
        if (order_record == null)return ErrorCodeConstant.ROUTE_ORDER_CODE_INVALID;
        return null;
    }

    /**
     * 校验团款编号
     * @param income_code
     * @return
     */
    public String checkIncomeCode(String income_code){
        RouteOrderIncome record = income_dao.selectByIncomeCode(income_code);
        if (record == null)return ErrorCodeConstant.ROUTE_ORDER_INCOME_CODE_INVALID;
        return null;
    }

    public String checkIncome(List<OrderIncomeDto> income_data){
        String error_code = null;
        if (income_data!=null && income_data.size()>0){
            for (OrderIncomeDto dto :income_data){
                if (StringUtils.isNotBlank(dto.getIncome_code())){
                    error_code = this.checkIncomeCode(dto.getIncome_code());
                    if (error_code !=null){
                        break;
                    }
                }
                error_code = route_plan_price_validator.checkFeeType(dto.getFee_type());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_INCOME_FEE_TYPE_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkCurrency(dto.getCurrency());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_INCOME_CURRENCY_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkPrice(dto.getPrice());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_INCOME_PRICE_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkQuantity(dto.getQuantity());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_INCOME_SIZE_INVALID;
                    break;
                }
                error_code = route_plan_price_validator.checkTotalPrice(dto.getTotal_price(),dto.getQuantity(),dto.getPrice());
                if (error_code !=null){
                    error_code = ErrorCodeConstant.ROUTE_ORDER_INCOME_TOTAL_PRICE_INVALID;
                    break;
                }
            }
        }
        return error_code;

    }

}
