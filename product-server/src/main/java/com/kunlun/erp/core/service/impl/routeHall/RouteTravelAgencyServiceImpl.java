package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.common.util.UniqueCodeUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyDto;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyIncomeDto;
import com.kunlun.erp.core.dto.routeHall.request.HallProductDetailReqDto;
import com.kunlun.erp.core.dto.routeHall.request.HallProductDetailRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyAddRequest;
import com.kunlun.erp.core.dto.routeHall.request.RouteTravelAgencyListRequest;
import com.kunlun.erp.core.dto.routeHall.response.HallProductDetailRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTravelAgencyAddRespDto;
import com.kunlun.erp.core.dto.routeHall.response.RouteTravelAgencyListRespDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteTravelAgency;
import com.kunlun.erp.core.mapper.RouteTravelAgencyIncomeMapper;
import com.kunlun.erp.core.mapper.RouteTravelAgencyMapper;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteHallService;
import com.kunlun.erp.core.service.routeHall.RouteTravelAgencyIncomeService;
import com.kunlun.erp.core.service.routeHall.RouteTravelAgencyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RouteTravelAgencyServiceImpl
 * @Description 线路团地接旅行社服务实现
 * @Author Jm.zhang
 * @Date 2019/12/25 13:29
 * @Version 1.0
 **/
@Service(value = "route_travelAgency_service")
public class RouteTravelAgencyServiceImpl extends BaseService implements RouteTravelAgencyService {
    @Resource
    private RouteTravelAgencyMapper travel_dao;
    @Resource(name = "route_hall_service")
    private RouteHallService route_hall_service;
    @Resource(name = "route_travel_income_service")
    private RouteTravelAgencyIncomeService route_travel_income_service;
    @Resource
    private RouteTravelAgencyIncomeMapper income_dao;
    @Override
    public AbstractResponse<RouteTravelAgencyListRespDto> detail(RouteTravelAgencyListRequest request) {
        AbstractResponse<RouteTravelAgencyListRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteTravelAgencyListRespDto resp_body = new RouteTravelAgencyListRespDto();
        //地接旅行社数据
        List<RouteTravelAgencyDto> list = travel_dao.selectDtoByGroupCode(request.getBody().getGroup_code());

        for (RouteTravelAgencyDto dto : list){
            List<RouteTravelAgencyIncomeDto> income_data = income_dao.selectDtoByCompanyCode(dto.getCompany_code());
            dto.setIncome_data(income_data);
        }
        resp_body.setTravel_agency_data(list);

        //封装请求，发送到产品服务接口 获取产品详情
        HallProductDetailReqDto hall_detail_request_body =  new HallProductDetailReqDto();
        hall_detail_request_body.setGroup_code(request.getBody().getGroup_code());

        HallProductDetailRequest hall_request = new HallProductDetailRequest();
        hall_request.setHeader(request.getHeader());
        hall_request.setBody(hall_detail_request_body);

        AbstractResponse<HallProductDetailRespDto> product_detail_response = route_hall_service.detail(hall_request);

        resp_body.setHall_data(product_detail_response.getBody());

        response.setBody(resp_body);
        return response;
    }

    @Override
    public AbstractResponse<RouteTravelAgencyAddRespDto> add(RouteTravelAgencyAddRequest request) {
        AbstractResponse<UserInfoRespDto> user_info = super.getUserInfo(request, Urls.RouteHall.NAMESPACE);
        AbstractResponse<RouteTravelAgencyAddRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteTravelAgencyAddRespDto resp_body = new RouteTravelAgencyAddRespDto();
        resp_body.setGroup_code(request.getBody().getGroup_code());
        if (request.getBody().getTravel_agency_data()== null || request.getBody().getTravel_agency_data().isEmpty()){
            //删除数据
            int del_count = travel_dao.deleteByGroupCode(request.getBody().getGroup_code());
            return response;
        }
        List<String> exist_code_list = new ArrayList<>();
        for (RouteTravelAgencyDto dto :request.getBody().getTravel_agency_data()){
            if (StringUtils.isBlank(dto.getTravel_code())){
                //创建数据
                RouteTravelAgency record = this.create(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getTravel_code());
                route_travel_income_service.add(record,dto.getIncome_data(),user_info);
            }else{
                //更新数据
                RouteTravelAgency record = this.update(request.getBody().getGroup_code(),dto,user_info);
                exist_code_list.add(record.getTravel_code());
                route_travel_income_service.add(record,dto.getIncome_data(),user_info);
            }
        }
        if (exist_code_list.isEmpty()==false){
            //删除废弃的数据
            int del_count = travel_dao.deleteByGroupCodeAndTravelAgencyCode(request.getBody().getGroup_code(),exist_code_list);
        }
        response.setBody(resp_body);
        return response;
    }

    public RouteTravelAgency create(String group_code, RouteTravelAgencyDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTravelAgency record = new RouteTravelAgency();
        record.setTravel_code(UniqueCodeUtil.generateUniqueCode(UniqueCodeUtil.UniquePrefix.group_supplier.getValue()));
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setCreate_time(new Date());
        record.setCreator_id(user_info.getBody().getUid());
        record.setCreator_name(user_info.getBody().getUserName());
        travel_dao.insertSelective(record);
        return record;
    }
    public RouteTravelAgency update(String group_code, RouteTravelAgencyDto dto, AbstractResponse<UserInfoRespDto> user_info){
        RouteTravelAgency old_record = travel_dao.selectByTravelAgencyCode(dto.getTravel_code());
        RouteTravelAgency record = new RouteTravelAgency();
        record.setId(old_record.getId());
        record.setTravel_code(old_record.getTravel_code());
        record.setGroup_code(group_code);
        record.setCompany_code(dto.getCompany_code());
        record.setPerson_code(dto.getPerson_code());
        record.setUpdate_time(new Date());
        travel_dao.updateByPrimaryKeySelective(record);
        return record;
    }
}
