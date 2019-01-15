package com.bdth.bootDemo.controller;

import com.bdth.bootDemo.util.ReponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author weiming.zhu
 * @date 2019/1/7 13:40
 */
@Controller
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/hello")
    public void hello(HttpServletResponse response) {
        ReponseUtil.response(response, "200", "运行成功", "success");
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("hello","哈喽");
        return "index";
    }
}
