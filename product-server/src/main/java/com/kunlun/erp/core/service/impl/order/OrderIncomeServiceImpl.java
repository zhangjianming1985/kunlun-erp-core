package com.kunlun.erp.core.service.impl.order;

import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteOrder;
import com.kunlun.erp.core.entity.RouteOrderIncome;
import com.kunlun.erp.core.mapper.RouteOrderIncomeMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.order.OrderIncomeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderIncomeServiceImpl
 * @Description 订单的团款服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/26 11:38
 * @Version 1.0
 **/
@Service(value = "route_income_service")
public class OrderIncomeServiceImpl extends BaseService implements OrderIncomeService {
    @Resource
    private RouteOrderIncomeMapper income_dao;
    @Override
    public void add(RouteOrder order_record, AbstractResponse<UserInfoRespDto> user_info, List<OrderIncomeDto> income_data) {
        if (income_data== null || income_data.isEmpty()){
            //删除数据
            int del_count = income_dao.deleteByOrderCode(order_record.getOrder_code());
            return;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (OrderIncomeDto dto :income_data){
            if (StringUtils.isBlank(dto.getIncome_code())){
                //创建数据
                RouteOrderIncome record = this.create(order_record.getOrder_code(),order_record.getGroup_code(),order_record.getCompany_code(),dto,user_info);
                exist_code_list.add(record.getIncome_code());
            }else{
                //更新数据
                RouteOrderIncome record = this.update(dto);
                exist_code_list.add(record.getIncome_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = income_dao.deleteByOrderCodeAndIncomeCode(order_record.getOrder_code(),exist_code_list);
        }
    }

    public RouteOrderIncome create(String order_code, String group_code, String company_code, OrderIncomeDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteOrderIncome record = new RouteOrderIncome();
        record.setIncome_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.sales_channel_cost.getValue()));
        record.setOrder_code(order_code);
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


    public RouteOrderIncome update(OrderIncomeDto dto){
        RouteOrderIncome old_record = income_dao.selectByIncomeCode(dto.getIncome_code());
        RouteOrderIncome record = new RouteOrderIncome();
        record.setId(old_record.getId());
        record.setIncome_code(old_record.getIncome_code());
        record.setOrder_code(old_record.getOrder_code());
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
