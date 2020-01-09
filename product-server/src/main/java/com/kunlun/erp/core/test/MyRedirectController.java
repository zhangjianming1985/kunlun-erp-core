package com.kunlun.erp.core.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @ClassName MyRedirectController
 * @Description 测试Redirect跳转
 * @Author Jm.zhang
 * @Date 2019-11-10 23:08
 * @Version 1.0
 **/
@Controller
public class MyRedirectController {
    @RequestMapping(value = "redirect",method = RequestMethod.GET)
    public String toRedirect(){
        return "redirect:http://www.baidu.com";
    }
    @RequestMapping(value = "redirectView",method = RequestMethod.GET)
    public RedirectView toRedirectView(){
        RedirectView rv = new RedirectView();
        rv.setUrl("http://www.baidu.com");
        return rv;
    }
}
