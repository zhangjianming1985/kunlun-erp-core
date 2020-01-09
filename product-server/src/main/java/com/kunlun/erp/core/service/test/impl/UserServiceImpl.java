package com.kunlun.erp.core.service.test.impl;

import com.kunlun.erp.core.entity.test.User;
import com.kunlun.erp.core.mapper.test.UserMapper;

import com.kunlun.erp.core.service.test.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserServiceImpl
 * @Description 测试业务接口实现
 * @Author Jm.zhang
 * @Date 2019-11-10 23:31
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int add(User user) {
        return userMapper.insertSelective(user);
    }
}
