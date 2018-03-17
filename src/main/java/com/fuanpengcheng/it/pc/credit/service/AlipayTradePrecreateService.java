package com.fuanpengcheng.it.pc.credit.service;

import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.fuanpengcheng.it.pc.credit.client.PcAlipayClient;
import com.fuanpengcheng.it.pc.credit.consts.AlipayConsts;
import com.fuanpengcheng.it.pc.credit.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-03
 * @description:
 */
@Service
public class AlipayTradePrecreateService {
    @Autowired
    private PcAlipayClient pcAlipayClient;

    /*
    必填字段
    * out_trade_no String
    * seller_id String: 码主支付宝ID
    * total_amount Price
    * subject String: 订单标题
    * */
    public AlipayTradePrecreateResponse precreateTrade(String outTradeNo, String sellerId, Double totalAmount, String subject) {
        String content = buildBizContent(outTradeNo, sellerId, totalAmount, subject);
        return precreateTradeByBizContent(content);
    }

    private AlipayTradePrecreateResponse precreateTradeByBizContent(String content) {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(content);
        return pcAlipayClient.execute(request);
    }

    private String buildBizContent(String outTradeNo, String sellerId, Double totalAmount, String subject) {
        Map<String, Object> content = new HashMap<>();
        content.put(AlipayConsts.FIELD_NAME_OUT_TRADE_NO, outTradeNo);
        content.put(AlipayConsts.FIELD_NAME_SELLER_ID, sellerId);
        content.put(AlipayConsts.FIELD_NAME_TOTAL_AMOUNT, totalAmount);
        content.put(AlipayConsts.FIELD_NAME_SUBJECT, subject);
        return JsonUtils.toJsonString(content);
    }
}
