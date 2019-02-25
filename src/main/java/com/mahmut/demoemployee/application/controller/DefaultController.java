package com.mahmut.demoemployee.application.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {


    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if(request.getUserPrincipal().getName().contains("admin@admin.com")){
            return "redirect:/admin/home";
        }
        else {
            return "redirect:/user/home";

        }
    }
}