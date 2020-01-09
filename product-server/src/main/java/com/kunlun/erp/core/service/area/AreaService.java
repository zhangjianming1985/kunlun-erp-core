package com.kunlun.erp.core.service.area;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.area.request.AreaRequest;
import com.kunlun.erp.core.dto.area.response.AreaListRespDto;

/**
 * @InterfaceName AreaService
 * @Description 地区 区域服务接口
 * @Author Jm.zhang
 * @Date 2019/12/5 15:04
 * @Version 1.0
 **/
public interface AreaService {
    AbstractResponse<AreaListRespDto> list(AreaRequest request);
}