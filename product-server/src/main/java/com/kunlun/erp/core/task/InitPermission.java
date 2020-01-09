package com.kunlun.erp.core.task;

import com.kunlun.erp.core.service.account.PermissionService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName InitPermission
 * @Description 初始化权限
 * @Author Jm.zhang
 * @Date 2019/11/22 17:39
 * @Version 1.0
 **/
@Component
public class InitPermission implements ApplicationRunner {
    @Resource(name = "permission_service")
    private PermissionService permission_service;
    @Override
    public void run(ApplicationArguments args) {
        permission_service.initPermission();

    }
}
