package com.kunlun.erp.core.service.routeHall;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.routeHall.RouteTravelAgencyIncomeDto;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.entity.RouteTravelAgency;

import java.util.List;

/**
 * @InterfaceName RouteTravelAgencyIncomeService
 * @Description 地接团款服务接口
 * @Author Jm.zhang
 * @Date 2019/12/26 16:13
 * @Version 1.0
 **/
public interface RouteTravelAgencyIncomeService {
    void add(RouteTravelAgency record, List<RouteTravelAgencyIncomeDto> income_data, AbstractResponse<UserInfoRespDto> user_info);
}