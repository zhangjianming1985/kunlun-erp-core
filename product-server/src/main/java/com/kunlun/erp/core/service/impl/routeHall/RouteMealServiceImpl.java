package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.DateUtil;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteMealDto;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteMealListRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteMealAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteMealListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteMeal;
import com.kunlun.erp.core.mapper.RouteMealMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteMealService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteMealServiceImpl
 * @Description 线路团用餐服务业务实现
 * @Author Jm.zhang
 * @Date 2019-12-22 19:02
 * @Version 1.0
 **/
@Service(value = "route_meal_service")
public class RouteMealServiceImpl extends BaseService implements RouteMealService {
    @Resource
    private RouteMealMapper meal_dao;
    @Override
    public AbstractResponse<RouteMealListRespDto> list(RouteMealListRequest request) {
        AbstractResponse<RouteMealListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteMealListRespDto resp_body = new RouteMealListRespDto();
        List<RouteMealDto> list = meal_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        resp_body.setMeal_data(list);
        response.setBody(resp_body);
        return response;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AbstractResponse<RouteMealAddRespDto> add(RouteMealAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteMealAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteMealAddRespDto resp_body = new RouteMealAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getMeal_data()== null || request.getBody().getMeal_data().isEmpty()){
            //删除数据
            int del_count = meal_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteMealDto dto :request.getBody().getMeal_data()){
            if (StringUtils.isBlank(dto.getMeal_code())){
                //创建数据
                RouteMeal record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getMeal_code());
            }else{
                //更新数据
                RouteMeal record = this.update(request.getBody().getGroup_code(),dto);
                exist_code_list.add(record.getMeal_code());
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = meal_dao.deleteByGroupCodeAndMealCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }


    public RouteMeal create(String group_code, RouteMealDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteMeal record = new RouteMeal();
        record.setMeal_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setMeal_type(dto.getMeal_type());
        record.setMeal_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getMeal_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setDiners_count(dto.getDiners_count());
        record.setFree_count(dto.getFree_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        meal_dao.insertSelective(record);
        return record;
    }

    public RouteMeal update(String group_code, RouteMealDto dto){
        RouteMeal old_record = meal_dao.selectByMealCode(dto.getMeal_code());
        RouteMeal record = new RouteMeal();
        record.setId(old_record.getId());
        record.setMeal_code(old_record.getMeal_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setMeal_type(dto.getMeal_type());
        record.setMeal_date(DateUtil.strToDateByFormat(DateUtil.FORMATTER_DATE,dto.getMeal_date()));
        record.setCurrency(dto.getCurrency());
        record.setFee(new BigDecimal(dto.getFee()));
        record.setDiners_count(dto.getDiners_count());
        record.setFree_count(dto.getFree_count());
        record.setFee_total(new BigDecimal(dto.getFee_total()));
        if (StringUtils.isNotBlank(dto.getRemarks())){
            record.setRemarks(dto.getRemarks());
        }
        record.setUpdate_time(new Date());
        meal_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
