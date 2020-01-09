package com.kunlun.erp.core.service.company;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.company.request.*;
import com.kunlun.erp.core.dto.company.response.*;

/**
 * @InterfaceName CompanyService
 * @Description 企业服务接口
 * @Author Jm.zhang
 * @Date 2019/11/19 17:09
 * @Version 1.0
 **/
public interface CompanyService {

    AbstractResponse<CompanyAddRespDto> add(CompanyAddRequest request)throws Exception;

    AbstractResponse<CompanyListRespDto> list(CompanyListRequest request)throws Exception;

    AbstractResponse<CompanyDetailRespDto> detail (CompanyDetailRequest request) throws Exception;

    AbstractResponse<CompanyEditRespDto> update (CompanyEditRequest request) throws Exception;

    AbstractResponse<CompanyDeleteRespDto> delete (CompanyDeleteRequest request) throws Exception;

    AbstractResponse<LikeSearchNameRespDto> likeName(LikeSearchNameRequest request) throws Exception;




}