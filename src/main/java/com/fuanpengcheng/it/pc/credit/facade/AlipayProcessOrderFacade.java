package com.fuanpengcheng.it.pc.credit.facade;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.fuanpengcheng.it.pc.credit.service.AlipayTradePrecreateService;
import com.fuanpengcheng.it.pc.credit.service.PcOrderInfoManageService;
import com.fuanpengcheng.it.pc.credit.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-03
 * @description:
 */
@Component
public class AlipayProcessOrderFacade {
    @Autowired
    private AlipayTradePrecreateService alipayTradePrecreateService;

    @Autowired
    private PcOrderInfoManageService pcOrderInfoManageService;

    public String processOrder(Double amount){
        String outTradeNo = pcOrderInfoManageService.generateOutTradeNo();
        String sellerId = pcOrderInfoManageService.selectSeller();
        String subject = AlipayUtils.generateAlipaySubject();
        AlipayTradePrecreateResponse response = alipayTradePrecreateService.precreateTrade(outTradeNo, sellerId, amount, subject);
        return response.getQrCode();
    }
}
