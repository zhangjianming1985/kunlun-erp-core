package com.kunlun.erp.core.service.impl.product;

import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.product.RouteBaseDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteInfo;
import com.kunlun.erp.core.mapper.RouteInfoMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.RouteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName RouteServiceImpl
 * @Description 线路服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/10 17:55
 * @Version 1.0
 **/
@Service(value = "route_service")
public class RouteServiceImpl extends BaseService implements RouteService {
    @Resource
    private RouteInfoMapper route_dao;
    @Override
    public RouteInfo add(String product_code, AbstractResponse<UserInfoRespDto> user_info, RouteBaseDto route_base_info) {
        RouteInfo route_record = new RouteInfo();
        route_record.setProduct_code(product_code);
        route_record.setRoute_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route.getValue()));
        if (StringUtils.isNotBlank(route_base_info.getInternal_code())){
            route_record.setInternal_code(route_base_info.getInternal_code());
        }
        route_record.setIs_shopping(route_base_info.getIs_shopping());
        route_record.setOwner_expense(route_base_info.getOwner_expense());
        route_record.setRoom_type(route_base_info.getRoom_type());
        route_record.setInsurance_type(route_base_info.getInsurance_type());
        route_record.setCreate_time(new Date());
        route_record.setCreator_id(user_info.getBody().getUid());
        route_record.setCreator_name(user_info.getBody().getUserName());
        int route_id = route_dao.insertSelective(route_record);
        return route_record;
    }

    @Override
    public RouteInfo update(String product_code,RouteBaseDto route_base_info) {
        RouteInfo route_record = route_dao.selectByProductCode(product_code);
        RouteInfo new_route = new RouteInfo();
        new_route.setId(route_record.getId());
        new_route.setRoute_code(route_record.getRoute_code());
        if (StringUtils.isNotBlank(route_base_info.getInternal_code())){
            if (StringUtils.isBlank(route_record.getInternal_code()) || !route_record.getInternal_code().equals(route_base_info.getInternal_code())){
                new_route.setInternal_code(route_base_info.getInternal_code());
            }
        }

        if (route_base_info.getIs_shopping() != route_record.getIs_shopping()){
            new_route.setIs_shopping(route_base_info.getIs_shopping());
        }
        if (route_base_info.getOwner_expense()!=route_record.getOwner_expense()){
            new_route.setOwner_expense(route_base_info.getOwner_expense());
        }
        new_route.setRoom_type(route_base_info.getRoom_type());
        new_route.setInsurance_type(route_base_info.getInsurance_type());
        int update_route_result = route_dao.updateByPrimaryKeySelective(new_route);
        return new_route;
    }
}
