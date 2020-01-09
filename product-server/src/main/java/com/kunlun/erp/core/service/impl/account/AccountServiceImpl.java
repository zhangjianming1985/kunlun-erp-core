package com.kunlun.erp.core.service.impl.account;

import com.alibaba.fastjson.TypeReference;
import com.kunlun.erp.core.common.cache.CacheKeyConstants;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.json.JsonUtil;
import com.kunlun.erp.core.common.util.HttpUtil;
import com.kunlun.erp.core.common.util.LogUtil;
import com.kunlun.erp.core.dto.AbstractRequest;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.service.account.AccountService;
import com.kunlun.erp.core.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.springframework.stereotype.Service;

/**
 * @ClassName AccountServiceImpl
 * @Description 账户服务接口
 * @Author Jm.zhang
 * @Date 2019/11/19 18:57
 * @Version 1.0
 **/
@Service(value = "account_service")
public class AccountServiceImpl extends BaseService implements AccountService {
    @Override
    public AbstractResponse<UserInfoRespDto> getUserInfo(String trans_no,String secret_key,String name_space) {
        AbstractResponse<UserInfoRespDto> response_dto = this.getUserInfoFromCache(secret_key,name_space);
        if (response_dto ==null){
            response_dto = this.getUserInfoFromApi(trans_no,secret_key,name_space);
            if (response_dto !=null){
                if (response_dto.getHeader().getState().equals(SysConstant.RespStatus.resp_status_success.getValue())){
                    this.putUserInfoToCache(secret_key,response_dto,name_space);
                }

            }
        }
        return response_dto;
    }

    /**
     * 从缓存中获取用户信息
     * @param secret_key
     * @return
     */
    @Override
    public AbstractResponse<UserInfoRespDto>  getUserInfoFromCache(String secret_key,String name_space){
        AbstractResponse<UserInfoRespDto> user_info = null;
        Cache<String,Object> cache   = cacheManager.getCache(CacheKeyConstants.user_cache,String.class,Object.class);
        Object user_obj = cache.get(secret_key);
        if (user_obj == null){
            LogUtil.writeLogInfo(this.getClass().getName(),"getUserInfoFromCache()","from_cache key : "+secret_key,"null", name_space);
            return null;
        }else{
            user_info =(AbstractResponse<UserInfoRespDto>)user_obj;
            LogUtil.writeLogInfo(this.getClass().getName(),"getUserInfoFromCache()","from_cache key : "+secret_key,JsonUtil.toStr(user_info), name_space);
            return user_info;
        }
    }

    /**
     * 用户信息放入缓存
     * @param secret_key
     * @param user_info
     */
    @Override
    public void putUserInfoToCache(String secret_key, AbstractResponse<UserInfoRespDto> user_info,String name_space){
        Cache<String,Object> cache   = cacheManager.getCache(CacheKeyConstants.user_cache,String.class,Object.class);
        cache.put(secret_key,user_info);
        LogUtil.writeLogInfo(this.getClass().getName(),"putUserInfoToCache()","put_cache key : "+secret_key,JsonUtil.toStr(user_info), name_space);
    }



    /**
     * 从API 获取用户信息
     * @param trans_no
     * @param secret_key
     * @param name_space
     * @return
     */
    @Override
    public  AbstractResponse<UserInfoRespDto> getUserInfoFromApi(String trans_no,String secret_key,String name_space){
        AbstractRequest request = dtoFactory.createRequest();
        request.getHeader().setTrans_no(trans_no);
        request.getHeader().setSecret_key(secret_key);
        String reqJson = JsonUtil.toStr(request);
        LogUtil.writeLogInfo(this.getClass().getName(),"getUserInfoFromApi()","reqJson "+config.getAccount_api_url() ,reqJson, name_space);
        String respJson = new HttpUtil().sendMessage(reqJson, config.getAccount_api_url());
        LogUtil.writeLogInfo(this.getClass().getName(),"getUserInfoFromApi()","respJson " ,respJson, name_space);
        if (StringUtils.isBlank(respJson)){
            return null;
        }else{
            AbstractResponse<UserInfoRespDto> user_info = JsonUtil.toBean(respJson, new TypeReference<AbstractResponse<UserInfoRespDto>>(){});
            return user_info;
        }
    }
}
