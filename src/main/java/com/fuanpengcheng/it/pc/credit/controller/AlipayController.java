package com.fuanpengcheng.it.pc.credit.controller;

import com.fuanpengcheng.it.pc.credit.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-02-28
 * @description:
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/alipay")
public class AlipayController {

    private static final Logger LOG = LoggerFactory.getLogger(AlipayController.class);

    @RequestMapping("/fillOrder")
    public String fillOrder(@RequestParam(value="name", required=false, defaultValue="APeng") String name, Model model) {
        model.addAttribute("name", name);
        return "fillOrder";
    }

    @RequestMapping("/notify_url")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        String data = "";
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data);
        } catch (IOException e) {
            LOG.error("error while writing response where data={}", data, e);
        }
    }

    @RequestMapping(value = "/processOrder", method = RequestMethod.POST)
    public void processOrder(HttpServletRequest request, HttpServletResponse response) {
        String amountStr = request.getParameter("amount");
        if (StringUtils.isEmpty(amountStr)) {
            return;
        }
        String data = "";
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data);
        } catch (IOException e) {
            LOG.error("error while writing response where data={}", data, e);
        }
    }
}
