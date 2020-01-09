package com.kunlun.erp.core.controller.routeHall;

import com.kunlun.erp.core.common.constants.ErrorCodeConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.request.RouteStatisticsRequest;
import com.kunlun.erp.core.dto.routeHall.response.RouteStatisticsRespDto;
import com.kunlun.erp.core.log.SystemLog;
import com.kunlun.erp.core.service.routeHall.RouteStatisticsService;
import com.kunlun.erp.core.validator.routeHall.RouteStatisticValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName RouteStatisticsController
 * @Description 线路团收支统计管理
 * @Author Jm.zhang
 * @Date 2019/12/26 10:15
 * @Version 1.0
 **/
@Controller
@Api(position = 14,description = "线路团收支统计管理",produces =  Urls.CONTENT_TYPE_JSON,consumes = Urls.CONTENT_TYPE_JSON)
public class RouteStatisticsController extends AbstractController {
    @Resource(name = "route_statistics_service")
    private RouteStatisticsService route_statistics_service;
    public RouteStatisticsController(RouteStatisticValidator validator) {
        super(validator);
    }

    @ApiOperation(value = "线路团收支明细",httpMethod = "POST",notes = "线路团收支明细")
    @SystemLog
    @RequestMapping(value = Urls.RouteHall.STATISICS,method = RequestMethod.POST,produces = Urls.CONTENT_TYPE_JSON, consumes = Urls.CONTENT_TYPE_JSON)
    public @ResponseBody
    AbstractResponse<RouteStatisticsRespDto> statistic(@RequestBody @Valid RouteStatisticsRequest request, BindingResult result){
        AbstractResponse<RouteStatisticsRespDto> response = dtoFactory.createResponse(request.getHeader());
        try{
            if (result.hasErrors()){
                response = dto_message_util.setDtoErrorFromResult(response,result);
            }else{
                response = route_statistics_service.routeStatistic(request);
            }
        }catch (Exception e){
            response = dtoFactory.createResponse(request.getHeader());
            dto_message_util.setDtoErrorFromErrorCode(response, ErrorCodeConstant.INTERNAL_ERROR,e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
