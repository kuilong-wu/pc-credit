package com.fuanpengcheng.it.pc.credit.service;

import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
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
 * @created: 2018-02-22
 * @description:
 */
@Service
public class AlipayTradeQueryService {
    @Autowired
    private PcAlipayClient pcAlipayClient;

    public AlipayTradeQueryResponse alipayTradeQuery(String tradeNo){
        String content = buildBizContent(tradeNo);
        return alipayTradeQueryByBizContent(content);
    }

    private AlipayTradeQueryResponse alipayTradeQueryByBizContent(String content) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent(content);
        return pcAlipayClient.execute(request);
    }

    private String buildBizContent(String tradeNo){
        Map<String, Object> content = new HashMap<>();
        content.put(AlipayConsts.FIELD_NAME_TRADE_NO, tradeNo);
        return JsonUtils.toJsonString(content);
    }
}
