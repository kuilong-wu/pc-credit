package com.fuanpengcheng.it.pc.credit.service;

import org.springframework.stereotype.Service;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-03
 * @description:
 */
@Service
public class PcOrderInfoManageService {
    public String generateOutTradeNo(){
        return "tradepay" + System.currentTimeMillis()
                + (long) (Math.random() * 10000000L);
    }

    public String selectSeller(){
        return "testSellerId";//todo
    }
}
