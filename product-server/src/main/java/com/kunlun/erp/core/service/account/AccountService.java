package com.kunlun.erp.core.service.account;

import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;

/**
 * @ClassName AccountService
 * @Description 账户服务
 * @Author Jm.zhang
 * @Date 2019/11/19 18:57
 * @Version 1.0
 **/
public interface AccountService {

    AbstractResponse<UserInfoRespDto> getUserInfo(String trans_no,String secret_key,String name_space);
    AbstractResponse<UserInfoRespDto>  getUserInfoFromCache(String secret_key,String name_space);
    void putUserInfoToCache(String secret_key, AbstractResponse<UserInfoRespDto> user_info,String name_space);
    AbstractResponse<UserInfoRespDto> getUserInfoFromApi(String trans_no,String secret_key,String name_space);
}
