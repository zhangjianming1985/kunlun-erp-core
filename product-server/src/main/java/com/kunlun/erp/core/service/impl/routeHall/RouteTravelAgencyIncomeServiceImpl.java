package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyIncomeDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteTravelAgency;
import com.kunlun.erp.core.entity.RouteTravelAgencyIncome;
import com.kunlun.erp.core.mapper.RouteTravelAgencyIncomeMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteTravelAgencyIncomeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteTravelAgencyIncomeServiceImpl
 * @Description 地接团款服务接口
 * @Author Jm.zhang
 * @Date 2019/12/26 16:13
 * @Version 1.0
 **/
@Service(value = "route_travel_income_service")
public class RouteTravelAgencyIncomeServiceImpl extends BaseService implements RouteTravelAgencyIncomeService {
    @Resource
    private RouteTravelAgencyIncomeMapper income_dao;
    @Override
    public void add(RouteTravelAgency travel_record, List<RouteTravelAgencyIncomeDto> income_data, AbstractResponse<UserInfoRespDto> user_info) {
        if (income_data== null || income_data.isEmpty()){
            //删除数据
            int del_count = income_dao.deleteByGroupCode(travel_record.getGroup_code());
            return;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteTravelAgencyIncomeDto dto :income_data){
            if (StringUtils.isBlank(dto.getIncome_code())){
                //创建数据
                RouteTravelAgencyIncome record = this.create(travel_record.getGroup_code(),travel_record.getCompany_code(),dto,user_info);
                exist_code_list.add(record.getIncome_code());
            }else{
                //更新数据
                RouteTravelAgencyIncome record = this.update(dto);
                exist_code_list.add(record.getIncome_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = income_dao.deleteByGroupCodeAndIncomeCode(travel_record.getGroup_code(),exist_code_list);
        }
    }



    public RouteTravelAgencyIncome create(String group_code, String company_code, RouteTravelAgencyIncomeDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTravelAgencyIncome record = new RouteTravelAgencyIncome();
        record.setIncome_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_price_detail.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(company_code);
        record.setFee_type(dto.getFee_type());
        record.setFee_description(dto.getFee_description());
        record.setCurrency(dto.getCurrency());
        record.setPrice(new BigDecimal(dto.getPrice()));
        record.setQuantity(dto.getQuantity());
        record.setTotal_price(new BigDecimal(dto.getTotal_price()));
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        income_dao.insertSelective(record);
        return record;
    }

    public RouteTravelAgencyIncome update(RouteTravelAgencyIncomeDto dto){
        RouteTravelAgencyIncome old_record = income_dao.selectByIncomeCode(dto.getIncome_code());
        RouteTravelAgencyIncome record = new RouteTravelAgencyIncome();
        record.setId(old_record.getId());
        record.setIncome_code(old_record.getIncome_code());
        record.setGroup_code(old_record.getGroup_code());
        record.setCompany_code(old_record.getCompany_code());
        record.setFee_type(dto.getFee_type());
        record.setFee_description(dto.getFee_description());
        record.setCurrency(dto.getCurrency());
        record.setPrice(new BigDecimal(dto.getPrice()));
        record.setQuantity(dto.getQuantity());
        record.setTotal_price(new BigDecimal(dto.getTotal_price()));
        record.setUpdate_time(new Date());
        income_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
