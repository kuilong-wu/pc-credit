package com.fuanpengcheng.it.pc.credit.controller;

import com.fuanpengcheng.it.pc.credit.service.AlipayTradeOrderSettleService;
import com.fuanpengcheng.it.pc.credit.service.AlipayTradeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-01-22
 * @description:
 */
@Controller
@EnableAutoConfiguration
public class TestController {

    @Autowired
    private AlipayTradeOrderSettleService alipayTradeOrderSettleService;

    @Autowired
    private AlipayTradeQueryService alipayTradeQueryService;

}
