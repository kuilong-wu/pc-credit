package com.fuanpengcheng.it.pc.credit.service;

import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.fuanpengcheng.it.pc.credit.client.PcAlipayClient;
import com.fuanpengcheng.it.pc.credit.consts.AlipayConsts;
import com.fuanpengcheng.it.pc.credit.utils.JsonUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-02-14
 * @description:
 */
@Service
public class AlipayTradeOrderSettleService {
    @Autowired
    private PcAlipayClient pcAlipayClient;

    /*
    必填字段
    * out_request_no String 自己生成, 结算流水, 保存记录
    * trade_no String: 支付宝订单号
    * royalty_parameters OpenApiRoyaltyDetailInfoPojo[]	 必填，但是数组内容均为可填
    *  trans_out 支付方支付宝ID
    *  trans_in
    *  amount
    *  amount_percentage
    *  desc
    *
    * */
    public AlipayTradeOrderSettleResponse orderSettle(String outRequestNo,
                                                      String tradeNo,
                                                      String transOut,
                                                      String transIn,
                                                      Double amount,
                                                      Integer amountPercentage,
                                                      String desc) {
        String bizContent = buildBizContent(outRequestNo, tradeNo, transOut, transIn, amount, amountPercentage, desc);
        return orderSettleByBizContent(bizContent);
    }

    private AlipayTradeOrderSettleResponse orderSettleByBizContent(String content) {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizContent(content);
        return pcAlipayClient.execute(request);
    }

    private String buildBizContent(String outRequestNo,
                                   String tradeNo,
                                   String transOut,
                                   String transIn,
                                   Double amount,
                                   Integer amountPercentage,
                                   String desc) {
        Map<String, Object> content = new HashMap<>();
        List<Map<String, Object>> royaltyParameters = Lists.newArrayList();
        Map<String, Object> royaltyParameter = new HashMap<>();
        royaltyParameter.put(AlipayConsts.FIELD_NAME_TRANS_OUT, transOut);
        royaltyParameter.put(AlipayConsts.FIELD_NAME_TRANS_IN, transIn);
        royaltyParameter.put(AlipayConsts.FIELD_NAME_AMOUNT, amount);
        royaltyParameter.put(AlipayConsts.FIELD_NAME_AMOUNT_PERCENTAGE, amountPercentage);
        royaltyParameter.put(AlipayConsts.FIELD_NAME_DESC, desc);
        royaltyParameters.add(royaltyParameter);

        content.put(AlipayConsts.FIELD_NAME_OUT_REQUEST_NO, outRequestNo);
        content.put(AlipayConsts.FIELD_NAME_TRADE_NO, tradeNo);
        content.put(AlipayConsts.FIELD_NAME_ROYALTY_PARAMETERS, royaltyParameters);
        return JsonUtils.toJsonString(content);
    }
}
