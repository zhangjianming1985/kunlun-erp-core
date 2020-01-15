package com.kunlun.erp.core.validator.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.RegexUtil;
import com.kunlun.erp.core.dto.routeHall.RouteGuidesDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteGuideListRequest;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.RouteGuides;
import com.kunlun.erp.core.mapper.RouteGuidesMapper;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName RouteGuideValidator
 * @Description 线路导服校验器
 * @Author Jm.zhang
 * @Date 2019-12-20 0:08
 * @Version 1.0
 **/
@Component(value = "route_guide_validator")
public class RouteGuideValidator extends AbstractValidator {
    public RouteGuideValidator(){
        super.name_space= Urls.RouteHall.NAMESPACE;
    }
    @Resource(name = "route_hall_validator")
    private RouteHallValidator route_hall_validator;
    @Resource
    private RouteGuidesMapper guide_dao;
    @Resource(name = "person_service")
    private PersonService person_service;
    @Override
    public boolean supports(Class<?> clazz) {
        return RouteGuideListRequest.class.isAssignableFrom(clazz) || RouteGuideAddRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public String myValidate(Object obj) {
        String error_code=null;
        if (obj instanceof RouteGuideListRequest){
            RouteGuideListRequest request = (RouteGuideListRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code());
        }else if (obj instanceof RouteGuideAddRequest){
            RouteGuideAddRequest request = (RouteGuideAddRequest)obj;
            error_code=route_hall_validator.checkGroupCode(request.getBody().getGroup_code(),request.getHeader().getTrans_no(),request.getHeader().getSecret_key(),per_properties.getEdit_all_data());
            if (error_code == null){
                error_code = this.checkGuide(request.getBody().getGuide_data());
            }
        }
        return error_code;
    }

    public String checkGuide(List<RouteGuidesDto> guide_data){
        String error_code = null;
        if (guide_data!=null && guide_data.isEmpty()==false){
            for (RouteGuidesDto dto : guide_data){
                if (StringUtils.isNotBlank(dto.getGuide_code())){
                    RouteGuides record = guide_dao.selectByGuideCode(dto.getGuide_code());
                    if (record == null){
                        error_code = ErrorCodeConstant.GUIDE_CODE_INVALID;
                        break;
                    }
                }
                error_code = base_validator.checkCompanyCode(dto.getCompany_code(), SysConstant.CompanyType.supplier_guides.getValue());
                if (error_code!=null)break;
                PersonInfo record = person_service.getPersonByPersonCode(dto.getPerson_code());
                if (record == null){
                    error_code = ErrorCodeConstant.CONTACT_CODE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getStart_date())==false){
                    error_code = ErrorCodeConstant.GUIDE_START_DATE_INVALID;
                    break;
                }
                if (RegexUtil.isDate(dto.getEnd_date())==false){
                    error_code = ErrorCodeConstant.GUIDE_END_DATE_INVALID;
                    break;
                }
                if (SysConstant.Currency.getCurrency(dto.getCurrency())==null){
                    error_code = ErrorCodeConstant.GUIDE_CURRENCY_INVALID;
                    break;
                }
                if (RegexUtil.isDecimal(dto.getFee_daily())==false){
                    error_code = ErrorCodeConstant.GUIDE_TOTAL_FEE_INVALID;
                    break;
                }
                error_code = this.checkTotalPrice(dto.getFee_total(),dto.getDays(),dto.getFee_daily());
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
            return ErrorCodeConstant.GUIDE_TOTAL_FEE_INVALID;
        }
/*        if (new BigDecimal(total_price).doubleValue()!=(Double.valueOf(price)*quantity)){
            return ErrorCodeConstant.GUIDE_TOTAL_FEE_INVALID;
        }*/

        return  null;
    }
}
