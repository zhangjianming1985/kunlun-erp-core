package com.kunlun.erp.core.test;

import com.kunlun.erp.core.entity.test.User;
import com.kunlun.erp.core.service.test.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Description 测试DB读写
 * @Author Jm.zhang
 * @Date 2019-11-10 23:33
 * @Version 1.0
 **/
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user/add",method = RequestMethod.GET)
    public @ResponseBody
    User add(HttpServletRequest request){
        User user = new User();
        user.setName("test");
        int r = userService.add(user);
        System.out.println("r="+r);
        return user;
    }
}
