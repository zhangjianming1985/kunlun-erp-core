package com.kunlun.erp.core.service.impl.product;

import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.component.RoutePlanNodeComponent;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.condition.RoutePlanNodeCondition;
import com.kunlun.erp.core.dto.product.RoutePlanNodeDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RoutePlanNode;
import com.kunlun.erp.core.mapper.RoutePlanNodeMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.product.RoutePlanNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RoutePlanNodeServiceimpl
 * @Description 线路计划节点服务接口实现
 * @Author Jm.zhang
 * @Date 2019/12/10 18:01
 * @Version 1.0
 **/
@Service(value = "route_plan_node_service")
public class RoutePlanNodeServiceimpl extends BaseService implements RoutePlanNodeService {
    @Resource(name = "component_route_node")
    private RoutePlanNodeComponent component_route_node;
    @Resource
    private RoutePlanNodeMapper route_plan_node_dao;
    @Override
    public RoutePlanNode add(String plan_code, AbstractResponse<UserInfoRespDto> user_info, RoutePlanNodeDto node_dto) {
        RoutePlanNode node_record = new RoutePlanNode();
        node_record.setNode_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.route_plan_node.getValue()));
        node_record.setRoute_plan_code(plan_code);
        node_record.setNode_day(node_dto.getNode_day());
        node_record.setTraffic_type(node_dto.getTraffic_type());
        //途径地点
        node_record.setLocale_area(node_dto.getLocale_area());
        node_record.setDescription(node_dto.getDescription());
        node_record.setHas_breakfast(node_dto.getHas_breakfast());
        node_record.setHas_lunch(node_dto.getHas_lunch());
        node_record.setHas_dinner(node_dto.getHas_dinner());
        node_record.setHotel_description(node_dto.getHotel_description());
        node_record.setCreate_time(new Date());
        node_record.setCreator_id(user_info.getBody().getUid());
        node_record.setCreator_name(user_info.getBody().getUserName());
        int node_id = route_plan_node_dao.insertSelective(node_record);
        return node_record;
    }

    @Override
    public RoutePlanNode update(RoutePlanNodeDto node_dto) {
        RoutePlanNode node_record = route_plan_node_dao.selectByNodeCode(node_dto.getNode_code());
        RoutePlanNode new_node = new RoutePlanNode();
        new_node.setId(node_record.getId());
        new_node.setNode_code(node_record.getNode_code());
        new_node.setNode_day(node_dto.getNode_day());
        new_node.setTraffic_type(node_dto.getTraffic_type());

        new_node.setLocale_area(node_dto.getLocale_area());
        new_node.setDescription(node_dto.getDescription());
        new_node.setHas_breakfast(node_dto.getHas_breakfast());
        new_node.setHas_lunch(node_dto.getHas_lunch());
        new_node.setHas_dinner(node_dto.getHas_dinner());
        new_node.setHotel_description(node_dto.getHotel_description());
        new_node.setUpdate_time(new Date());
        int update_node_result= route_plan_node_dao.updateByPrimaryKeySelective(new_node);
        return new_node;
    }

    @Override
    public void delete(String plan_code, List<String> node_list) {
        RoutePlanNodeCondition node_condition = new RoutePlanNodeCondition();
        node_condition.setNode_codes(node_list);
        node_condition.setNode_code_include(false);
        List<String> plan_codes = new ArrayList<>();
        plan_codes.add(plan_code);
        node_condition.setPlan_codes(plan_codes);
        node_condition.setPlan_code_include(true);
        route_plan_node_dao.deleteByCondition(node_condition);
    }
}
