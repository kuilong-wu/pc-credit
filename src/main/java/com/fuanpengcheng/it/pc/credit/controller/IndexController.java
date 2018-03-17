package com.fuanpengcheng.it.pc.credit.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-02
 * @description:
 */
@Controller
@EnableAutoConfiguration
public class IndexController {

    @RequestMapping("/")
    public void home(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("alipay/fillOrder");
        } catch (Exception e){
            return;
        }
    }

    @RequestMapping("")
    public void home1(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("alipay/fillOrder");
        } catch (Exception e){
            return;
        }
    }
}
