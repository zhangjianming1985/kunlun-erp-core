package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteTicketDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTicketListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteTicket;
import com.kunlun.erp.core.mapper.RouteTicketMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteTicketValidator
 * @Description 线路景点门票校验器
 * @Author Jm.zhang
 * @Date 2019/12/20 18:44
 * @Version 1.0
 **/
@Component(value = "route_ticket_validator")
public class RouteTicketValidator  extends AbstractValidator {
    public RouteTicketValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource
    private RouteTicketMapper ticket_dao;
    @Resource(name = "person_service")
    private PersonService person_service;

    @Override
    public boolean supports(Class<?> clazz) {
        return RouteTicketListRequest.class.isAssignableFrom(clazz) || RouteTicketAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteTicketListRequest){
            RouteTicketListRequest request = (RouteTicketListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteTicketAddRequest){
            RouteTicketAddRequest request = (RouteTicketAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
            if (error_code == null){
                error_code = this.checkTicket(request.getBody().getTicket_data());
            }
        }
        return error_code;
    }

    public String checkTicket(List<RouteTicketDto> ticket_data){
        String error_code = null;
        if (ticket_data!=null && ticket_data.isEmpty()==false){
            for (RouteTicketDto dto : ticket_data){
                if (StringUtils.isNotBlank(dto.getTicket_code())){
                    RouteTicket record = ticket_dao.selectByTicketCode(dto.getTicket_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.TICKET_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_ticket.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (SysConstant.TicketType.getTicketType(dto.getTicket_type())==null){
                    error_code = ErrorCodeConstant.TICKET_TYPE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getTicket_date())==false){
                    error_code = ErrorCodeConstant.TICKET_DATE_INVALID;
                    break;
                }

                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.TICKET_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee())==false){
                    error_code = ErrorCodeConstant.TICKET_PRICE_INVALID;
                    break;
                }
                if (dto.getTicket_count() <=0){
                    error_code = ErrorCodeConstant.TICKET_COUNT_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getTicket_count(),dto.getFee());
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
    public String checkTotalPrice(String  total_price,Integer quantity,String price){
        if (RegexUtil.isDecimal(total_price)==false){
            return ErrorCodeConstant.TICKET_TOTAL_PRICE_INVALID;
        }
        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.TICKET_TOTAL_PRICE_INVALID;
        }
        return  null;
    }
}
