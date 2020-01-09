package com.kunlun.erp.core.service.impl.company;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.ArithmeticUtil;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.component.CompanyComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.common.AreaRespDto;
import com.kunlun.erp.core.dto.company.FinanceAccountDto;
import com.kunlun.erp.core.dto.company.LeaderDto;
import com.kunlun.erp.core.dto.company.SalesChannelCostDto;
import com.kunlun.erp.core.dto.company.request.*;
import com.kunlun.erp.core.dto.company.response.*;
import com.kunlun.erp.core.dto.condition.CompanyCondition;
import com.kunlun.erp.core.dto.condition.FinancialAccountCondition;
import com.kunlun.erp.core.dto.condition.PersonCondition;
import com.kunlun.erp.core.dto.condition.SalesChannelCostCondition;
import com.kunlun.erp.core.dto.person.PersonDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.CompanyInfo;
import com.kunlun.erp.core.entity.FinancialAccount;
import com.kunlun.erp.core.entity.PersonInfo;
import com.kunlun.erp.core.entity.SalesChannelCost;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.company.CompanyService;
import com.kunlun.erp.core.service.company.FinancialAccountService;
import com.kunlun.erp.core.service.company.PersonService;
import com.kunlun.erp.core.service.company.SalesChannelCostService;
import com.kunlun.erp.core.service.order.OrderClientService;
import com.kunlun.erp.core.service.order.RouteOrderService;
import com.kunlun.erp.core.validator.common.AreaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CompanyServiceImpl
 * @Description 公司服务接口实现
 * @Author Jm.zhang
 * @Date 2019/11/19 17:09
 * @Version 1.0
 **/
@Service(value = "company_service")
public class CompanyServiceImpl extends BaseService implements CompanyService {
    @Resource
    private CompanyInfoMapper companyDao;
    @Resource(name = "financial_account_service")
    private FinancialAccountService financial_account_service;
    @Resource(name = "person_service")
    private PersonService person_service;
    @Resource(name = "component_company")
    private CompanyComponent component_company;
    @Resource(name = "area_validator")
    protected AreaValidator area_validator;
    @Resource(name = "sales_channel_cost_service")
    private SalesChannelCostService sales_channel_cost_service;
    @Resource(name = "route_order_service")
    private RouteOrderService route_order_service;
    @Resource(name = "order_client_service")
    private OrderClientService order_client_service;
    @Resource
    private RouteOrderIncomeMapper route_order_income_dao;
    @Resource
    private RouteGuidesMapper route_guide_dao;
    @Resource
    private RouteTrafficMapper route_traffic_dao;
    @Resource
    private RouteResideMapper route_reside_dao;
    @Resource
    private RouteTicketMapper route_ticket_dao;
    @Resource
    private RouteMealMapper route_meal_dao;
    @Resource
    private RouteMotorcadeMapper route_motorcade_dao;
    @Resource
    private RouteInsuranceMapper route_insurance_dao;
    @Resource
    private RouteTravelAgencyIncomeMapper route_travel_income_dao;
    @Resource
    private RouteTravelAgencyMapper route_travel_dao;
    @Resource
    private RouteOtherMapper route_other_dao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<CompanyAddRespDto> add(CompanyAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request,Urls.Company.NAMESPACE);
        AbstractResponse<CompanyAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        CompanyAddRespDto resp_body= new CompanyAddRespDto();
        CompanyAddReqDto request_body = request.getBody();
        CompanyInfo company_record = new CompanyInfo();
        //基础信息
        company_record.setCompany_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.company_info.getValue()));
        company_record.setCompany_type(request_body.getCompany_type());
        company_record.setCompany_name(request_body.getBase_info().getCompany_name());
        if (base_validator.salesChannel(request_body.getCompany_type())){
            company_record.setBelong_platform(request_body.getBase_info().getBelong_platform());
        }
        if (StringUtils.isNotBlank(request_body.getBase_info().getBusiness_licence_url())){
            company_record.setBusiness_licence_url(request_body.getBase_info().getBusiness_licence_url());
        }
        if (StringUtils.isNotBlank(request_body.getBase_info().getBusiness_certificate())){
            company_record.setBusiness_certificate(request_body.getBase_info().getBusiness_certificate());
        }
        component_company.BuildArea(company_record,request_body.getArea_info());
        if (StringUtils.isNotBlank(request_body.getBase_info().getAddress())){
            company_record.setAddress(request_body.getBase_info().getAddress());
        }
        if (StringUtils.isNotBlank(request_body.getBase_info().getIntroduction())){
            company_record.setIntroduction(request_body.getBase_info().getIntroduction());
        }
        company_record.setCooperation_state(request_body.getBase_info().getCooperation_state());
        company_record.setContact_person(request_body.getBase_info().getContact_person());
        company_record.setContract_document_state(request_body.getBase_info().getContract_document_state());
        if (StringUtils.isNotBlank(request_body.getBase_info().getContract_document_url())){
            company_record.setContract_document_url(request_body.getBase_info().getContract_document_url());
        }
        if (StringUtils.isNotBlank(request_body.getBase_info().getCooperation_start_date())){
            company_record.setCooperation_start_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,request_body.getBase_info().getCooperation_start_date()));
        }
        if (StringUtils.isNotBlank(request_body.getBase_info().getCooperation_end_date())){
            company_record.setCooperation_end_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,request_body.getBase_info().getCooperation_end_date()));
        }
        if (StringUtils.isNotBlank(request_body.getBase_info().getRemarks())){
            company_record.setRemarks(request_body.getBase_info().getRemarks());
        }
        //负责人信息
        if (request_body.getLeader_info()!=null){
            company_record.setLeader_name(request_body.getLeader_info().getLeader_name());
            if (StringUtils.isNotBlank(request_body.getLeader_info().getLeader_mobile())){
                company_record.setLeader_mobile(request_body.getLeader_info().getLeader_mobile());
            }
            if (StringUtils.isNotBlank(request_body.getLeader_info().getLeader_wechat())){
                company_record.setLeader_wechat(request_body.getLeader_info().getLeader_wechat());
            }
            if (StringUtils.isNotBlank(request_body.getLeader_info().getLeader_address())){
                company_record.setLeader_address(request_body.getLeader_info().getLeader_address());
            }
        }
        //财务结算模式
        company_record.setSettlement_mode(request_body.getFinancial_info().getSettlement_mode());
        company_record.setCredit_level(request_body.getFinancial_info().getCredit_level());
        company_record.setIs_common_use(request_body.getFinancial_info().getIs_common_use());
        company_record.setCreate_time(new Date());
        company_record.setCreator_id(user_info.getBody().getUid());
        company_record.setCreator_name(user_info.getBody().getUserName());
        //执行插入
        int company_id = companyDao.insertSelective(company_record);
        resp_body.setCompany_code(company_record.getCompany_code());
        //金融账户
        FinancialAccount account_record;
        List<String> financial_account_codes = new ArrayList<>();
        for (FinanceAccountDto account_dto : request_body.getFinancial_info().getFinance_accounts()){
            account_record = new FinancialAccount();
            account_record.setCompany_code(company_record.getCompany_code());
            account_record.setAccount_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.financial_account.getValue()));
            account_record.setAccount_type(account_dto.getAccount_type());
            account_record.setAccount_name(account_dto.getAccount_name());
            account_record.setAccount_no(account_dto.getAccount_no());
            if (account_dto.getAccount_type()==SysConstant.FinanceAccountType.Bank.getValue()){
                account_record.setBank_name(account_dto.getBank_name());
            }
            account_record.setIs_default(account_dto.getIs_default());
            account_record.setCreate_time(new Date());
            account_record.setCreator_id(user_info.getBody().getUid());
            account_record.setCreator_name(user_info.getBody().getUserName());
            int account_id = financial_account_service.insertAccount(account_record);
            financial_account_codes.add(account_record.getAccount_code());
            account_record =null;
        }
        resp_body.setFinancial_account_codes(financial_account_codes);



        if (request_body.getContact_info() != null && request_body.getContact_info().size() >0){
            //联系人
            PersonInfo person_record;
            List<String> persons = new ArrayList<>();
            for (PersonDto person : request_body.getContact_info()){
                person_record = new PersonInfo();
                person_record.setCompany_code(company_record.getCompany_code());
                person_record.setPerson_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.person_info.getValue()));
                person_record.setPerson_type(person.getPerson_type());
                person_record.setPerson_name(person.getPerson_name());
                person_record.setPerson_mobile(person.getPerson_mobile());
                if (StringUtils.isNotBlank(person.getPerson_wechat())){
                    person_record.setPerson_wechat(person.getPerson_wechat());
                }
                if (StringUtils.isNotBlank(person.getPerson_phone())){
                    person_record.setPerson_phone(person.getPerson_phone());
                }
                if (StringUtils.isNotBlank(person.getPerson_qq())){
                    person_record.setPerson_qq(person.getPerson_qq());
                }
                if (StringUtils.isNotBlank(person.getPosition())){
                    person_record.setPosition(person.getPosition());
                }
                person_record.setCreate_time(new Date());
                person_record.setCreator_id(user_info.getBody().getUid());
                person_record.setCreator_name(user_info.getBody().getUserName());
                int result_id = person_service.insert(person_record);
                persons.add(person_record.getPerson_code());
            }
            resp_body.setPerson_codes(persons);
        }


        if (request.getBody().getSales_channel_cost()!=null && request.getBody().getSales_channel_cost().size()>0){
            SalesChannelCost cost_record;
            List<String> cost_record_code = new ArrayList<>();
            for (SalesChannelCostDto cost_dto :request.getBody().getSales_channel_cost()){
                cost_record = new SalesChannelCost();
                cost_record.setCost_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.sales_channel_cost.getValue()));
                cost_record.setCompany_code(company_record.getCompany_code());
                cost_record.setCost_type_id(cost_dto.getCost_type_id());
                cost_record.setProduct_category_code(cost_dto.getProduct_category_code());
                cost_record.setCost_settlement_mode(cost_dto.getCost_settlement_mode());
                cost_record.setCharge_mode(cost_dto.getCharge_mode());
                cost_record.setFee(new BigDecimal(ArithmeticUtil.roundDown(cost_dto.getFee(),2)));
                cost_record.setRate(new BigDecimal(ArithmeticUtil.roundDown(cost_dto.getRate(),2)));
                cost_record.setCreate_time(new Date());
                cost_record.setCreator_id(user_info.getBody().getUid());
                cost_record.setCreator_name(user_info.getBody().getUserName());
                int result_id = sales_channel_cost_service.insert(cost_record);
                cost_record_code.add(cost_record.getCost_code());
            }
            resp_body.setSales_channel_cost_codes(cost_record_code);
        }

        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<CompanyListRespDto> list(CompanyListRequest request) {
        AbstractResponse<CompanyListRespDto> response = dtoFactory.createResponse(request.getHeader());
        CompanyListRespDto resp_body = new CompanyListRespDto();
        CompanyCondition condition = component_company.convert(request);
        PageHelper.startPage(condition.getPage_index(), condition.getPage_size(), true);
        List<CompanyListDto> list = companyDao.selectByCondition(condition);
        PageInfo<CompanyListDto> page_list = new PageInfo<>(list);
        resp_body.setPage_data(page_list);
        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<CompanyDetailRespDto> detail(CompanyDetailRequest request) {
        AbstractResponse<CompanyDetailRespDto> response = dtoFactory.createResponse(request.getHeader());
        CompanyDetailRespDto response_body = new CompanyDetailRespDto();
        CompanyInfo company_record = companyDao.selectByCompanyCode(request.getBody().getCompany_code());
        //基本信息
        CompanyDetailBaseInfo resp_base_info = new CompanyDetailBaseInfo();
        resp_base_info.setCompany_code(company_record.getCompany_code());
        resp_base_info.setCompany_type(company_record.getCompany_type());
        resp_base_info.setCompany_name(company_record.getCompany_name());
        resp_base_info.setBelong_platform(company_record.getBelong_platform());
        resp_base_info.setBusiness_licence_url(company_record.getBusiness_licence_url());
        resp_base_info.setBusiness_certificate(company_record.getBusiness_certificate());
        resp_base_info.setAddress(company_record.getAddress());
        resp_base_info.setIntroduction(company_record.getIntroduction());
        resp_base_info.setCooperation_state(company_record.getCooperation_state());
        resp_base_info.setContact_person(company_record.getContact_person());
        resp_base_info.setContract_document_state(company_record.getContract_document_state());
        resp_base_info.setContract_document_url(company_record.getContract_document_url());
        if (company_record.getCooperation_start_date()!=null){
            resp_base_info.setCooperation_start_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,company_record.getCooperation_start_date()));
        }
        if (company_record.getCooperation_end_date() != null){
            resp_base_info.setCooperation_end_date(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,company_record.getCooperation_end_date()));
        }

        resp_base_info.setRemarks(company_record.getRemarks());
        response_body.setBase_info(resp_base_info);
        //地区信息
        AreaRespDto area_info = new AreaRespDto();
        area_info.setCountry_id(company_record.getCountry_id());
        area_info.setCountry_name(company_record.getCountry_name());
        area_info.setDistrict_id(company_record.getDistrict_id());
        area_info.setDistrict_name(company_record.getDistrict_name());
        area_info.setProvince_id(company_record.getProvince_id());
        area_info.setProvince_name(company_record.getProvince_name());
        area_info.setCity_id(company_record.getCity_id());
        area_info.setCity_name(company_record.getCity_name());
        area_info.setCounty_id(company_record.getCounty_id());
        area_info.setCounty_name(company_record.getCounty_name());
        response_body.setArea_info(area_info);
        //负责人信息
        LeaderDto leader_info = new LeaderDto();
        leader_info.setLeader_name(company_record.getLeader_name());
        leader_info.setLeader_mobile(company_record.getLeader_mobile());
        leader_info.setLeader_wechat(company_record.getLeader_wechat());
        leader_info.setLeader_address(company_record.getLeader_address());
        response_body.setLeader_info(leader_info);
        //财务信息
        FinanceRespDto financial_info = new FinanceRespDto();
        financial_info.setSettlement_mode(company_record.getSettlement_mode());
        financial_info.setCredit_level(company_record.getCredit_level());
        financial_info.setIs_common_use(company_record.getIs_common_use());
        List<FinanceAccountDto> finance_accounts = new ArrayList<>();
        List<FinancialAccount> finance_account_list = financial_account_service.getListByCompanyCode(request.getBody().getCompany_code());
        for (FinancialAccount account : finance_account_list){
            FinanceAccountDto dto = new FinanceAccountDto();
            dto.setAccount_code(account.getAccount_code());
            dto.setAccount_type(account.getAccount_type());
            dto.setAccount_name(account.getAccount_name());
            dto.setAccount_no(account.getAccount_no());
            dto.setBank_name(account.getBank_name());
            dto.setIs_default(account.getIs_default());
            finance_accounts.add(dto);
        }
        financial_info.setFinance_accounts(finance_accounts);
        response_body.setFinancial_info(financial_info);
        //联系人信息
        PersonCondition person_condition = new PersonCondition();
        person_condition.setCompany_code(request.getBody().getCompany_code());
        List<PersonDto> contact_info =person_service.getDtoByCondition(person_condition);
        response_body.setContact_info(contact_info);

        //销售渠道费用数据
        if (base_validator.salesChannel(company_record.getCompany_type())){
            SalesChannelCostCondition condition = new SalesChannelCostCondition();
            condition.setCompany_code(company_record.getCompany_code());
            List<SalesChannelCostDto> cost_list =  sales_channel_cost_service.getDtoByCondition(condition);
            response_body.setSales_channel_cost_info(cost_list);
        }


        response.setBody(response_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<CompanyEditRespDto> update(CompanyEditRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request,Urls.Company.NAMESPACE);
        boolean is_modify_company_info = false;
        AbstractResponse<CompanyEditRespDto> response = dtoFactory.createResponse(request.getHeader());
        CompanyInfo company_record = companyDao.selectByCompanyCode(request.getBody().getCompany_code());
        CompanyInfo  new_record = new CompanyInfo();
        new_record.setId(company_record.getId());
        new_record.setCompany_code(company_record.getCompany_code());
        if (request.getBody().getBase_info()!= null){
            if (!request.getBody().getBase_info().getCompany_name().equals(company_record.getCompany_name())){
                new_record.setCompany_name(request.getBody().getBase_info().getCompany_name());
                is_modify_company_info = true;
            }
            if (company_record.getBelong_platform()==null){
                if (request.getBody().getBase_info().getBelong_platform()!=null){
                    new_record.setBelong_platform(request.getBody().getBase_info().getBelong_platform());
                    is_modify_company_info = true;
                }
            }else{
                if (request.getBody().getBase_info().getBelong_platform()!= null && request.getBody().getBase_info().getBelong_platform()!=company_record.getBelong_platform()){
                    new_record.setBelong_platform(request.getBody().getBase_info().getBelong_platform());
                    is_modify_company_info = true;
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getBusiness_licence_url())){
                new_record.setBusiness_licence_url(request.getBody().getBase_info().getBusiness_licence_url());
                is_modify_company_info = true;

            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getBusiness_certificate())){
                new_record.setBusiness_certificate(request.getBody().getBase_info().getBusiness_certificate());
                is_modify_company_info = true;
            }

            if (StringUtils.isNotBlank(request.getBody().getBase_info().getAddress())){
                new_record.setAddress(request.getBody().getBase_info().getAddress());
                is_modify_company_info = true;
            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getIntroduction())){
                new_record.setIntroduction(request.getBody().getBase_info().getIntroduction());
                is_modify_company_info = true;
            }
            if (request.getBody().getBase_info().getCooperation_state()!= null){
                if (request.getBody().getBase_info().getCooperation_state()!= company_record.getCooperation_state()){
                    new_record.setCooperation_state(request.getBody().getBase_info().getCooperation_state());
                    is_modify_company_info = true;
                }
            }
            if (request.getBody().getBase_info().getContact_person()!=null){
                if (company_record.getContact_person() == null){
                    new_record.setContact_person(request.getBody().getBase_info().getContact_person());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getBase_info().getContact_person().equals(company_record.getContact_person())){
                        new_record.setCooperation_state(request.getBody().getBase_info().getCooperation_state());
                        is_modify_company_info = true;
                    }
                }
            }
            if (request.getBody().getBase_info().getContract_document_state()!=null){
                if (request.getBody().getBase_info().getContract_document_state() != company_record.getContract_document_state()){
                    new_record.setContract_document_state(request.getBody().getBase_info().getContract_document_state());
                    is_modify_company_info = true;
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getContract_document_url())){
                if (StringUtils.isBlank(company_record.getContract_document_url())){
                    new_record.setContract_document_url(request.getBody().getBase_info().getContract_document_url());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getBase_info().getContract_document_url().equals(company_record.getContract_document_url())){
                        new_record.setContract_document_url(request.getBody().getBase_info().getContract_document_url());
                        is_modify_company_info = true;
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getCooperation_start_date())){
                if (company_record.getCooperation_start_date() == null){
                    new_record.setCooperation_start_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,request.getBody().getBase_info().getCooperation_start_date()));
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getBase_info().getCooperation_start_date().equals(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,company_record.getCooperation_start_date()))){
                        new_record.setCooperation_start_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,request.getBody().getBase_info().getCooperation_start_date()));
                        is_modify_company_info = true;
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getCooperation_end_date())){
                if (company_record.getCooperation_end_date() == null){
                    new_record.setCooperation_end_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,request.getBody().getBase_info().getCooperation_end_date()));
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getBase_info().getCooperation_end_date().equals(DateUtil.dateToStringByFormat(DateUtil.FORMATTER_DATE,company_record.getCooperation_end_date()))){
                        new_record.setCooperation_end_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,request.getBody().getBase_info().getCooperation_end_date()));
                        is_modify_company_info = true;
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getBase_info().getRemarks())){
                if (StringUtils.isBlank(company_record.getRemarks())){
                    new_record.setRemarks(request.getBody().getBase_info().getRemarks());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getBase_info().getRemarks().equals(company_record.getRemarks())){
                        new_record.setRemarks(request.getBody().getBase_info().getRemarks());
                        is_modify_company_info = true;
                    }
                }
            }

        }

        if (request.getBody().getLeader_info()!= null){
            if (StringUtils.isNotBlank(request.getBody().getLeader_info().getLeader_name())){
                if (StringUtils.isBlank(company_record.getLeader_name())){
                    new_record.setLeader_name(request.getBody().getLeader_info().getLeader_name());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getLeader_info().getLeader_name().equals(company_record.getLeader_name())){
                        new_record.setLeader_name(request.getBody().getLeader_info().getLeader_name());
                        is_modify_company_info = true;
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getLeader_info().getLeader_mobile())){
                if (StringUtils.isBlank(company_record.getLeader_mobile())){
                    new_record.setLeader_mobile(request.getBody().getLeader_info().getLeader_mobile());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getLeader_info().getLeader_mobile().equals(company_record.getLeader_mobile())){
                        new_record.setLeader_mobile(request.getBody().getLeader_info().getLeader_mobile());
                        is_modify_company_info = true;
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getLeader_info().getLeader_wechat())){
                if (StringUtils.isBlank(company_record.getLeader_wechat())){
                    new_record.setLeader_wechat(request.getBody().getLeader_info().getLeader_wechat());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getLeader_info().getLeader_wechat().equals(company_record.getLeader_wechat())){
                        new_record.setLeader_wechat(request.getBody().getLeader_info().getLeader_wechat());
                        is_modify_company_info = true;
                    }
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getLeader_info().getLeader_address())){
                if (StringUtils.isBlank(company_record.getLeader_address())){
                    new_record.setLeader_address(request.getBody().getLeader_info().getLeader_address());
                    is_modify_company_info = true;
                }else{
                    if (!request.getBody().getLeader_info().getLeader_address().equals(company_record.getLeader_address())){
                        new_record.setLeader_address(request.getBody().getLeader_info().getLeader_address());
                        is_modify_company_info = true;
                    }
                }
            }
        }
        if (request.getBody().getArea_info()!=null){
            component_company.BuildArea(new_record,request.getBody().getArea_info());
            is_modify_company_info = true;
        }
        if (request.getBody().getFinancial_info()!= null){
            if (request.getBody().getFinancial_info().getSettlement_mode()!=null){
                if (request.getBody().getFinancial_info().getSettlement_mode()!=company_record.getSettlement_mode()){
                    new_record.setSettlement_mode(request.getBody().getFinancial_info().getSettlement_mode());
                    is_modify_company_info = true;
                }
            }
            if (StringUtils.isNotBlank(request.getBody().getFinancial_info().getCredit_level())){
                if (!request.getBody().getFinancial_info().getCredit_level().equals(company_record.getCredit_level())){
                    new_record.setCredit_level(request.getBody().getFinancial_info().getCredit_level());
                    is_modify_company_info = true;
                }
            }
            if (request.getBody().getFinancial_info().getIs_common_use()!=null){
                if (request.getBody().getFinancial_info().getIs_common_use()!=company_record.getIs_common_use()){
                    new_record.setIs_common_use(request.getBody().getFinancial_info().getIs_common_use());
                    is_modify_company_info = true;
                }
            }
        }

        if (is_modify_company_info){
            companyDao.updateByPrimaryKeySelective(new_record);
        }

        if (request.getBody().getFinancial_info()== null || request.getBody().getFinancial_info().getFinance_accounts()== null || request.getBody().getFinancial_info().getFinance_accounts().size()<=0){
            //删除金融账户列表
            int financial_account_count = financial_account_service.deleteByCompanyCode(new_record.getCompany_code());
        }else{
            //金融账户 有更新 或者新增
            List<String> exist_account_code_list = new ArrayList<>();
            for (FinanceAccountDto account_dto : request.getBody().getFinancial_info().getFinance_accounts()){
                if (StringUtils.isNotBlank(account_dto.getAccount_code())){
                    //现有账户更新操作、
                   FinancialAccount fa = financial_account_service.getByAccountCode(account_dto.getAccount_code());
                    FinancialAccount new_fa = new FinancialAccount();
                    new_fa.setId(fa.getId());
                    new_fa.setAccount_type(account_dto.getAccount_type());
                    new_fa.setAccount_name(account_dto.getAccount_name());
                    new_fa.setAccount_no(account_dto.getAccount_no());
                    if (account_dto.getAccount_type()==SysConstant.FinanceAccountType.Bank.getValue()){
                        new_fa.setBank_name(account_dto.getBank_name());
                    }
                    new_fa.setIs_default(account_dto.getIs_default());
                    financial_account_service.updateAccount(new_fa);
                    //存在的code 放入list
                    exist_account_code_list.add(account_dto.getAccount_code());
                }else{
                    //新增操作
                    FinancialAccount account_record = new FinancialAccount();
                    account_record.setCompany_code(company_record.getCompany_code());
                    account_record.setAccount_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.financial_account.getValue()));
                    account_record.setAccount_type(account_dto.getAccount_type());
                    account_record.setAccount_name(account_dto.getAccount_name());
                    account_record.setAccount_no(account_dto.getAccount_no());
                    if (account_dto.getAccount_type()==SysConstant.FinanceAccountType.Bank.getValue()){
                        account_record.setBank_name(account_dto.getBank_name());
                    }
                    account_record.setIs_default(account_dto.getIs_default());
                    account_record.setCreate_time(new Date());
                    account_record.setCreator_id(user_info.getBody().getUid());
                    account_record.setCreator_name(user_info.getBody().getUserName());
                    int account_id = financial_account_service.insertAccount(account_record);
                    //新增的账户 也要放入list
                    exist_account_code_list.add(account_record.getAccount_code());
                    account_record =null;


                }
            }
            //删除其他账户
            FinancialAccountCondition fa_condition = new FinancialAccountCondition();
            fa_condition.setCompany_code(request.getBody().getCompany_code());
            fa_condition.setAccount_code_include(false);
            fa_condition.setAccount_codes(exist_account_code_list);
            financial_account_service.deleteByCondition(fa_condition);
        }

        if (request.getBody().getContact_info()== null || request.getBody().getContact_info().size()<=0){
            //删除人员数据
            int person_count = person_service.deleteByCompanyCode(new_record.getCompany_code());
        }else{
            //人员数据 有更新 或者新增
            List<String> exist_person_code_list = new ArrayList<>();
            for (PersonDto person_dto : request.getBody().getContact_info()){
                if (StringUtils.isNotBlank(person_dto.getPerson_code())){
                    //现有人员更新操作、
                    PersonInfo person_record = person_service.getPersonByPersonCode(person_dto.getPerson_code());
                    PersonInfo new_person = new PersonInfo();
                    new_person.setId(person_record.getId());
                    new_person.setPerson_code(person_record.getPerson_code());
                    new_person.setPerson_name(person_dto.getPerson_name());
                    new_person.setPerson_mobile(person_dto.getPerson_mobile());
                    if (StringUtils.isNotBlank(person_dto.getPerson_wechat())){
                        new_person.setPerson_wechat(person_dto.getPerson_wechat());
                    }
                    if (StringUtils.isNotBlank(person_dto.getPerson_phone())){
                        new_person.setPerson_phone(person_dto.getPerson_phone());
                    }
                    if (StringUtils.isNotBlank(person_dto.getPerson_qq())){
                        new_person.setPerson_qq(person_dto.getPerson_qq());
                    }
                    if (StringUtils.isNotBlank(person_dto.getPosition())){
                        new_person.setPosition(person_dto.getPosition());
                    }
                    new_person.setUpdate_time(new Date());
                    person_service.updatePerson(new_person);
                    //存在的code 放入list
                    exist_person_code_list.add(new_person.getPerson_code());
                }else{
                    //新增操作
                    PersonInfo person_record = new PersonInfo();
                    person_record.setCompany_code(company_record.getCompany_code());
                    person_record.setPerson_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.person_info.getValue()));
                    person_record.setPerson_type(person_dto.getPerson_type());
                    person_record.setPerson_name(person_dto.getPerson_name());
                    person_record.setPerson_mobile(person_dto.getPerson_mobile());
                    if (StringUtils.isNotBlank(person_dto.getPerson_wechat())){
                        person_record.setPerson_wechat(person_dto.getPerson_wechat());
                    }
                    if (StringUtils.isNotBlank(person_dto.getPerson_phone())){
                        person_record.setPerson_phone(person_dto.getPerson_phone());
                    }
                    if (StringUtils.isNotBlank(person_dto.getPerson_qq())){
                        person_record.setPerson_qq(person_dto.getPerson_qq());
                    }
                    if (StringUtils.isNotBlank(person_dto.getPosition())){
                        person_record.setPosition(person_dto.getPosition());
                    }
                    person_record.setCreate_time(new Date());
                    person_record.setCreator_id(user_info.getBody().getUid());
                    person_record.setCreator_name(user_info.getBody().getUserName());
                    int result_id = person_service.insert(person_record);
                    exist_person_code_list.add(person_record.getPerson_code());
                }
            }
            //删除其他人员数据
            PersonCondition person_condition = new PersonCondition();
            person_condition.setCompany_code(request.getBody().getCompany_code());
            person_condition.setPerson_code_include(false);
            person_condition.setPerson_codes(exist_person_code_list);
            person_service.deleteByCondition(person_condition);
        }

        if (base_validator.salesChannel(company_record.getCompany_type())){
            if (request.getBody().getSales_channel_cost_info()== null || request.getBody().getSales_channel_cost_info().size()<=0){
                //删除销售渠道费用数据
                int cost_count = sales_channel_cost_service.deleteByCompanyCode(new_record.getCompany_code());
            }else{
                //销售渠道费用数据 有更新 或者新增
                List<String> exist_cost_code_list = new ArrayList<>();
                for (SalesChannelCostDto cost_dto : request.getBody().getSales_channel_cost_info()){
                    if (StringUtils.isNotBlank(cost_dto.getCost_code())){
                        //现有费用数据更新操作、
                        SalesChannelCost cost_record = sales_channel_cost_service.getRecordByCostCode(cost_dto.getCost_code());
                        SalesChannelCost new_cost_record = new SalesChannelCost();
                        new_cost_record.setId(cost_record.getId());
                        new_cost_record.setCost_type_id(cost_dto.getCost_type_id());
                        new_cost_record.setProduct_category_code(cost_dto.getProduct_category_code());
                        new_cost_record.setCost_settlement_mode(cost_dto.getCost_settlement_mode());
                        new_cost_record.setCharge_mode(cost_dto.getCharge_mode());
                        new_cost_record.setFee(new BigDecimal(ArithmeticUtil.roundDown(cost_dto.getFee(),2)));
                        new_cost_record.setRate(new BigDecimal(ArithmeticUtil.roundDown(cost_dto.getRate(),2)));
                        sales_channel_cost_service.updateCostRecord(new_cost_record);
                        //存在的code 放入list
                        exist_cost_code_list.add(new_cost_record.getCost_code());
                    }else{
                        //新增操作
                        SalesChannelCost cost_record = new SalesChannelCost();
                        cost_record.setCost_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.sales_channel_cost.getValue()));
                        cost_record.setCompany_code(company_record.getCompany_code());
                        cost_record.setCost_type_id(cost_dto.getCost_type_id());
                        cost_record.setProduct_category_code(cost_dto.getProduct_category_code());
                        cost_record.setCost_settlement_mode(cost_dto.getCost_settlement_mode());
                        cost_record.setCharge_mode(cost_dto.getCharge_mode());
                        cost_record.setFee(new BigDecimal(ArithmeticUtil.roundDown(cost_dto.getFee(),2)));
                        cost_record.setRate(new BigDecimal(ArithmeticUtil.roundDown(cost_dto.getRate(),2)));
                        cost_record.setCreate_time(new Date());
                        cost_record.setCreator_id(user_info.getBody().getUid());
                        cost_record.setCreator_name(user_info.getBody().getUserName());
                        int result_id = sales_channel_cost_service.insert(cost_record);
                        exist_cost_code_list.add(cost_record.getCost_code());
                    }
                }
                //删除其他费用数据
                SalesChannelCostCondition cost_condition = new SalesChannelCostCondition();
                cost_condition.setCompany_code(request.getBody().getCompany_code());
                cost_condition.setCost_code_include(false);
                cost_condition.setCost_codes(exist_cost_code_list);
                sales_channel_cost_service.deleteByCondition(cost_condition);
            }
        }


        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<CompanyDeleteRespDto> delete(CompanyDeleteRequest request) {
        AbstractResponse<CompanyDeleteRespDto> response = dtoFactory.createResponse(request.getHeader());
        CompanyDeleteRespDto body = new CompanyDeleteRespDto();
        //删除人员信息
        int person_count = person_service.deleteByCompanyCode(request.getBody().getCompany_code());
        body.setPerson_count(person_count);
        //删除金融账户
        int financial_count = financial_account_service.deleteByCompanyCode(request.getBody().getCompany_code());
        body.setFinancial_count(financial_count);
        //如果是销售渠道
        if (base_validator.salesChannel(request.getBody().getCompany_type())){
            //删除销售渠道费用数据
            int cost_count = sales_channel_cost_service.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setCost_count(cost_count);
            //删除出游人
            int client_count = order_client_service.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setClient_count(client_count);
            //删除确认团款
            int income_count = route_order_income_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setIncome_count(income_count);
            //删除订单
            int order_count = route_order_service.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setOrder_count(order_count);
        }else if (request.getBody().getCompany_type()==SysConstant.CompanyType.supplier_guides.getValue()){
            //删除团的导服数据
            int guides_count = route_guide_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setGuides_count(guides_count);
        }else if (request.getBody().getCompany_type()==SysConstant.CompanyType.supplier_traffic.getValue()){
            //删除交通票务
            int traffic_count = route_traffic_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setTraffic_count(traffic_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_hotel.getValue()){
            //删除住宿
            int reside_count = route_reside_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setReside_count(reside_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_ticket.getValue()){
            //删除景点门票
            int ticket_count = route_ticket_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setTicket_count(ticket_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_meal.getValue()){
            //删除餐饮
            int meal_count = route_meal_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setMeal_count(meal_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_car.getValue()){
            //删除车队
            int motorcade_count = route_motorcade_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setMotorcade_count(motorcade_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_insurance.getValue()){
            //删除保险
            int insurance_count = route_insurance_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setInsurance_count(insurance_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_travel_agency.getValue()){
            //删除地接报价
            int travel_income_count = route_travel_income_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setTravel_income_count(travel_income_count);
            //删除地接旅行社
            int travel_count = route_travel_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setTravel_count(travel_count);
        }else if (request.getBody().getCompany_type() ==SysConstant.CompanyType.supplier_other.getValue()){
            //删除其他
            int other_count = route_other_dao.deleteByCompanyCode(request.getBody().getCompany_code());
            body.setOther_count(other_count);
        }
        int company_count = companyDao.deleteByCompanyCode(request.getBody().getCompany_code());
        body.setCompany_count(company_count);
        response.setBody(body);
        return response;
    }

    @Override
    public AbstractResponse<LikeSearchNameRespDto> likeName(LikeSearchNameRequest request) {
        AbstractResponse<LikeSearchNameRespDto> response = dtoFactory.createResponse(request.getHeader());
        LikeSearchNameRespDto resp_body = new LikeSearchNameRespDto();
        CompanyCondition condition  = new CompanyCondition();
        condition.setCompany_name(request.getBody().getCompany_name());
        List<Integer> company_type_list = new ArrayList<>();
        if (request.getBody().getCompany_type()==SysConstant.CompanyType.all_supplier.getValue()){
            //供应商
            company_type_list.add(SysConstant.CompanyType.supplier_car.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_hotel.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_meal.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_ticket.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_traffic.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_insurance.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_guides.getValue());
            company_type_list.add(SysConstant.CompanyType.supplier_other.getValue());
        }else if (request.getBody().getCompany_type()==SysConstant.CompanyType.all_sales_channel.getValue()){
            //销售渠道
            company_type_list.add(SysConstant.CompanyType.sales_channel_online.getValue());
            company_type_list.add(SysConstant.CompanyType.sales_channel_offline.getValue());
            company_type_list.add(SysConstant.CompanyType.sales_channel_other.getValue());
        }else if(request.getBody().getCompany_type()==SysConstant.CompanyType.all.getValue()){

        }else{
            company_type_list.add(request.getBody().getCompany_type());
        }
        condition.setCompany_type_list(company_type_list);
        List<LikeNameDto> list = companyDao.selectLikeName(condition);
        resp_body.setCompany_data(list);
        response.setBody(resp_body);
        return response;
    }
}
