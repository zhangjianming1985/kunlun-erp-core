package company;

import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.BaseRequestHeader;
import com.kunlun.erp.core.dto.common.AreaDto;
import com.kunlun.erp.core.dto.company.*;
import com.kunlun.erp.core.dto.company.request.CompanyAddReqDto;
import com.kunlun.erp.core.dto.person.PersonDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Test
 * @Description
 * @Author Jm.zhang
 * @Date 2019/11/22 10:33
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] arg){
        Test.createReq();
    }
    public static void createReq(){
        AbstractRequest<CompanyAddReqDto> request = new AbstractRequest<>();
        BaseRequestHeader header = new BaseRequestHeader();
        header.setTrans_no("001"+new Date().getTime());
        header.setSecret_key("5f84e7fb8abb880352703e3873364ded");
        request.setHeader(header);

        CompanyAddReqDto body  = new CompanyAddReqDto();
        body.setCompany_type(SysConstant.CompanyType.supplier_travel_agency.getValue());
        BaseInfoDto base_info = new BaseInfoDto();
        base_info.setCompany_name("特发旅行社");
        base_info.setBusiness_licence_url("http://tefa.com");
        base_info.setBusiness_certificate("http://tefa.com");
        base_info.setAddress("特发地址");
        base_info.setIntroduction("特发简介");
        base_info.setCooperation_state(SysConstant.CooperationState.no_cooperation.getValue());
        base_info.setContact_person(1);
        base_info.setContract_document_state(SysConstant.ContractDocumentState.no_contract.getValue());
        base_info.setContract_document_url("http://tefa.com");
        base_info.setCooperation_start_date("2019-11-22");
        base_info.setCooperation_end_date("2019-12-30");
        base_info.setRemarks("特发备注");
        body.setBase_info(base_info);


        AreaDto area_info = new AreaDto();
        area_info.setCountry_id(240);
        area_info.setProvince_id(620000);
        area_info.setCity_id(620400);
        area_info.setCounty_id(237);
        body.setArea_info(area_info);


        FinanceDto finance_info = new FinanceDto();
        finance_info.setSettlement_mode(SysConstant.FinanceSettlementMode.real_time.getValue());
        finance_info.setCredit_level(SysConstant.CreditLevel.D.getValue());
        finance_info.setIs_common_use(SysConstant.FinanceCommonUsed.common_used.getValue());
        List<FinanceAccountDto> financeAccount = new ArrayList<>();
        FinanceAccountDto account = new FinanceAccountDto();
        account.setAccount_type(SysConstant.FinanceAccountType.Bank.getValue());
        account.setAccount_name("特发银行卡");
        account.setAccount_no("3333333333333");
        account.setBank_name("南山支行");
        account.setIs_default(0);
        financeAccount.add(account);
        finance_info.setFinance_accounts(financeAccount);
        body.setFinancial_info(finance_info);


        List<PersonDto> contact_info = new ArrayList<>();
        PersonDto person_dto =new PersonDto();
        person_dto.setPerson_type(SysConstant.PersonType.contact.getValue());
        person_dto.setPerson_name("特发联系人");
        person_dto.setPerson_mobile("15915328866");
        person_dto.setPerson_wechat("we2324234");
        person_dto.setPerson_qq("78098701");
        person_dto.setPerson_phone("0755-8588888");
        person_dto.setPosition("洪兴帮");
        contact_info.add(person_dto);
        body.setContact_info(contact_info);


        LeaderDto leader_info = new LeaderDto();
        leader_info.setLeader_name("特发负责人");
        leader_info.setLeader_mobile("15915328877");
        leader_info.setLeader_wechat("ewrwrw3434");
        leader_info.setLeader_address("负责人地址");
        body.setLeader_info(leader_info);
        request.setBody(body);
        System.out.println(JsonUtil.toStr(request));
    }
}
