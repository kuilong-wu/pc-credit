package com.fuanpengcheng.it.pc.credit.service;

import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
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
 * @created: 2018-02-24
 * @description:
 */
@Service
public class AlipayFundTransToaccountTransfer {
    @Autowired
    private PcAlipayClient pcAlipayClient;

    public AlipayFundTransToaccountTransferResponse alipayFundTransToaccount(String outBizNo,
                                                                             String payeeType,
                                                                             String payeeAccount,
                                                                             Double amount,
                                                                             String payerShowName,
                                                                             String remark) {
        String bizContent = buildBizContent(outBizNo, payeeType, payeeAccount, amount, payerShowName, remark);
        return alipayFundTransToaccountByBizContent(bizContent);
    }

    private AlipayFundTransToaccountTransferResponse alipayFundTransToaccountByBizContent(String content) {
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent(content);
        return pcAlipayClient.execute(request);
    }

    private String buildBizContent(String outBizNo,
                                   String payeeType,
                                   String payeeAccount,
                                   Double amount,
                                   String payerShowName,
                                   String remark) {
        Map<String, Object> content = new HashMap<>();
        content.put(AlipayConsts.FIELD_NAME_OUT_BIZ_NO, outBizNo);
        content.put(AlipayConsts.FIELD_NAME_PAYEE_TYPE, payeeType);
        content.put(AlipayConsts.FIELD_NAME_PAYEE_ACCOUNT, payeeAccount);
        content.put(AlipayConsts.FIELD_NAME_AMOUNT, String.valueOf(amount));//todo
        content.put(AlipayConsts.FIELD_NAME_PAYER_SHOW_NAME, payerShowName);
        content.put(AlipayConsts.FIELD_NAME_REMARK, remark);
        return JsonUtils.toJsonString(content);
    }
}
