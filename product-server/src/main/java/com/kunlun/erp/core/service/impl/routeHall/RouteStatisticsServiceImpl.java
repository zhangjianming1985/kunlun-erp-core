package com.kunlun.erp.core.service.impl.routeHall;

import com.kunlun.erp.core.common.util.ArithmeticUtil;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.*;
import com.kunlun.erp.core.dto.routeHall.request.RouteStatisticsRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteStatisticsRespDto;
import com.kunlun.erp.core.dto.routeOrder.OrderIncomeDto;
import com.kunlun.erp.core.mapper.*;
import com.kunlun.erp.core.service.BaseService;
import com.kunlun.erp.core.service.routeHall.RouteStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RouteStatisticsServiceImpl
 * @Description 线路团收支汇总实现
 * @Author Jm.zhang
 * @Date 2019/12/26 9:55
 * @Version 1.0
 **/
@Service(value = "route_statistics_service")
public class RouteStatisticsServiceImpl extends BaseService implements RouteStatisticsService {
    @Resource
    private RouteGuidesMapper route_guide_dao;
    @Resource
    private RouteTrafficMapper traffic_dao;
    @Resource
    private RouteResideMapper route_reside_dao;
    @Resource
    private RouteTicketMapper ticket_dao;
    @Resource
    private RouteMealMapper meal_dao;
    @Resource
    private RouteMotorcadeMapper motorcade_dao;
    @Resource
    private RouteInsuranceMapper insurance_dao;
    @Resource
    private RouteOtherMapper other_dao;
    @Resource
    private RouteOrderIncomeMapper income_dao;
    @Resource
    private RouteTravelAgencyIncomeMapper travel_income_dao;


    @Override
    public AbstractResponse<RouteStatisticsRespDto> routeStatistic(RouteStatisticsRequest request) {
        AbstractResponse<RouteStatisticsRespDto> response = dtoFactory.createResponse(request.getHeader());
        RouteStatisticsRespDto body = new RouteStatisticsRespDto();
        //导服
        List<RouteGuidesDto> guide_list = route_guide_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setGuides_data(guide_list);
        double guide_amount = guide_list.stream().mapToDouble(RouteGuidesDto::getFee_total_double).sum();
        //交通票务
        List<RouteTrafficDto> traffic_list = traffic_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setTraffic_data(traffic_list);
        double traffic_amount = traffic_list.stream().mapToDouble(RouteTrafficDto::getFee_total_double).sum();
        //住宿
        List<RouteResideDto> reside_list = route_reside_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setReside_data(reside_list);
        double reside_amount = reside_list.stream().mapToDouble(RouteResideDto::getFee_total_double).sum();
        //景点门票
        List<RouteTicketDto> ticket_list = ticket_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setTicket_data(ticket_list);
        double ticket_amount = ticket_list.stream().mapToDouble(RouteTicketDto::getFee_total_double).sum();
        //用餐
        List<RouteMealDto> meal_list = meal_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setMeal_data(meal_list);
        double meal_amount = meal_list.stream().mapToDouble(RouteMealDto::getFee_total_double).sum();
        //车队
        List<RouteMotorcadeDto> motorcade_list = motorcade_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setMotorcade_data(motorcade_list);
        double motorcade_amount = motorcade_list.stream().mapToDouble(RouteMotorcadeDto::getFee_total_double).sum();
        //保险
        List<RouteInsuranceDto> insurance_list = insurance_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setInsurance_data(insurance_list);
        double insurance_amount = insurance_list.stream().mapToDouble(RouteInsuranceDto::getFee_total_double).sum();
        //其他
        List<RouteOtherDto> other_list = other_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setOther_data(other_list);
        double other_amount = other_list.stream().mapToDouble(RouteOtherDto::getFee_total_double).sum();
        //销售渠道
        List<OrderIncomeDto> income_list = income_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setIncome_data(income_list);
        //地接
        List<RouteTravelAgencyIncomeDto> travel_income_list = travel_income_dao.selectDtoByGroupCode(request.getBody().getGroup_code());
        body.setTravel_income_data(travel_income_list);

        double travel_amount = travel_income_list.stream().mapToDouble(RouteTravelAgencyIncomeDto::getTotal_price_double).sum();
        //总支出
        double total_cost = guide_amount+traffic_amount+reside_amount+ticket_amount+meal_amount+motorcade_amount+insurance_amount+travel_amount;
        //总收入
        double total_income=income_list.stream().mapToDouble(OrderIncomeDto::getTotal_price_double).sum();
        if (other_amount < 0){
            //其他费用为支出  负负得正
            total_cost= total_cost - other_amount;
        }else {
            total_income +=  other_amount;

        }
        body.setTotal_cost(ArithmeticUtil.roundDown( new BigDecimal(total_cost).toString(),2));

        body.setTotal_income(ArithmeticUtil.roundDown(new BigDecimal(total_income).toString(),2));

        response.setBody(body);
        return response;
    }
}
