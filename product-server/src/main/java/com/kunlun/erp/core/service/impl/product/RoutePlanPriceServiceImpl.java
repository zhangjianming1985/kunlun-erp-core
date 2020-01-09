package com.kunlun.erp.core.service.impl.product;

import com.kunlun.erp.core.common.util.ArithmeticUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RoutePlanPriceCondition;
import com.kunlun.erp.core.dto.product.RoutePlanBasePriceDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RoutePlanBasePrice;
import com.kunlun.erp.core.mapper.RoutePlanBasePriceMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.RoutePlanPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RoutePlanPriceServiceImpl
 * @Description 线路计划价格服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/10 18:27
 * @Version 1.0
 **/
@Service(value = "route_plan_price_service")
public class RoutePlanPriceServiceImpl extends BaseService implements RoutePlanPriceService {
    @Resource
    private RoutePlanBasePriceMapper route_plan_price_dao;
    @Override
    public RoutePlanBasePrice add(String product_code, String route_code, String plan_code, AbstractResponse<UserInfoRespDto> user_info, RoutePlanBasePriceDto price_dto) {
        RoutePlanBasePrice price_record = new RoutePlanBasePrice();
        price_record.setBase_price_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_plan_price.getValue()));
        price_record.setProduct_code(product_code);
        price_record.setRoute_code(route_code);
        price_record.setRoute_plan_code(plan_code);
        price_record.setFee_type(price_dto.getFee_type());
        price_record.setFee_description(price_dto.getFee_description());
        price_record.setCurrency(price_dto.getCurrency());
        price_record.setPrice(new BigDecimal(ArithmeticUtil.roundDown(price_dto.getPrice(),2)));
        price_record.setQuantity(price_dto.getQuantity());
        price_record.setTotal_price(new BigDecimal(ArithmeticUtil.roundDown(price_dto.getTotal_price(),2)));
        price_record.setCreate_time(new Date());
        price_record.setCreator_id(user_info.getBody().getUid());
        price_record.setCreator_name(user_info.getBody().getUserName());
        int price_id = route_plan_price_dao.insertSelective(price_record);
        return price_record;
    }

    @Override
    public RoutePlanBasePrice update(RoutePlanBasePriceDto price_dto) {
        RoutePlanBasePrice price_record = route_plan_price_dao.selectByPriceCode(price_dto.getBase_price_code());
        RoutePlanBasePrice new_record = new RoutePlanBasePrice();
        new_record.setId(price_record.getId());
        new_record.setFee_type(price_dto.getFee_type());
        new_record.setFee_description(price_dto.getFee_description());
        new_record.setCurrency(price_dto.getCurrency());
        new_record.setPrice(new BigDecimal(price_dto.getPrice()));
        new_record.setQuantity(price_dto.getQuantity());
        new_record.setTotal_price(new BigDecimal(price_dto.getTotal_price()));
        new_record.setUpdate_time(new Date());
        int update_result = route_plan_price_dao.updateByPrimaryKeySelective(new_record);
        return new_record;
    }

    @Override
    public void delete(String plan_code, List<String> price_list) {
        RoutePlanPriceCondition price_condition = new RoutePlanPriceCondition();
        price_condition.setPrice_codes(price_list);
        price_condition.setPrice_code_include(false);

        List<String> plan_codes = new ArrayList<>();
        plan_codes.add(plan_code);
        price_condition.setPlan_codes(plan_codes);
        price_condition.setPlan_code_include(true);

        route_plan_price_dao.deleteByCondition(price_condition);



    }
}
