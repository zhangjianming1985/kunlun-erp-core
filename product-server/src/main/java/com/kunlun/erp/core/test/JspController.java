package com.kunlun.erp.core.test;

import com.kunlun.erp.core.dto.test.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JspController
 * @Description 测试JSP controller
 * @Author Jm.zhang
 * @Date 2019-11-10 23:07
 * @Version 1.0
 **/
@Controller
public class JspController {
    @RequestMapping(value = "/jsp/showUser",method = RequestMethod.GET)
    public String showUser(Model model){
        System.out.println("进入 JspController ");
        List<UserDto> user_list = new ArrayList<>();
        user_list.add(new UserDto(1,"张三"));
        user_list.add(new UserDto(2,"李四"));
//        ModelAndView model = new ModelAndView();
//        model.addObject("list",user_list);
//        model.setViewName("user_list");
        model.addAttribute("list",user_list);
        return "user_list";
    }
    @RequestMapping(value = "/jsp/index",method = RequestMethod.GET)
    public String testJsp(){
        return "index";
    }
}
