package com.kunlun.erp.core.test;

import com.kunlun.erp.core.common.cache.CacheKeyConstants;
import com.kunlun.erp.core.common.constants.SysConstant;
import com.kunlun.erp.core.common.constants.Urls;
import com.kunlun.erp.core.controller.AbstractController;
import com.kunlun.erp.core.dto.AbstractResponse;
import com.kunlun.erp.core.dto.user.UserInfoRespDto;
import com.kunlun.erp.core.service.account.AccountService;
import com.kunlun.erp.core.validator.test.TestValidator;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName CacheController
 * @Description
 * @Author Jm.zhang
 * @Date 2019/11/21 16:38
 * @Version 1.0
 **/

@Controller
@CacheConfig(cacheNames = "cache_secret_key")
public class CacheController extends AbstractController {
    @Resource
    private CacheManager cacheManager;
    @Resource(name = "account_service")
    protected AccountService account_service;
    public CacheController(TestValidator validator) {
        super(validator);
    }

    @RequestMapping(value = "ehCache",method = RequestMethod.GET)
    @ResponseBody
    public String testEhcache(){
//        LogUtil.writeLogInfo(this.getClass().getName(),"hello()","msg",msg.toString(), Urls.SalesChannel.NAMESPACE);
        String zhangjm_cache_val = "secret_key11111111111111111111";
        Cache<String,Object> cache   = cacheManager.getCache(CacheKeyConstants.user_cache,String.class,Object.class);
        cache.put("zhangjm_cache_key",zhangjm_cache_val);
        System.out.println("get from cache : " + cache.get("zhangjm_cache_key"));
        return "hello my friend !"+ SysConstant.LogLevel.info;
    }

    @RequestMapping(value = "/cache/putUser",method = RequestMethod.GET)
    @ResponseBody
    public String putUser(){
        AbstractResponse<UserInfoRespDto> user_info = dtoFactory.createResponse();
        user_info.getHeader().setTrans_no("test_trans_no_1");
        UserInfoRespDto user = new UserInfoRespDto();
        user.setUid(1);
        user.setUserName("test_user_name");
        user.setLoginName("test_login_name");
        user_info.setBody(user);
        account_service.putUserInfoToCache("test_secret_key_001",user_info,Urls.Company.NAMESPACE);
        return "put user to cache ok!";
    }


    @RequestMapping(value = "/cache/getUser",method = RequestMethod.GET)
    @ResponseBody
    public  AbstractResponse<UserInfoRespDto> getUser(){
        AbstractResponse<UserInfoRespDto> user_info = account_service.getUserInfoFromCache("test_secret_key_001", Urls.Company.NAMESPACE);
        return user_info;
    }
}
