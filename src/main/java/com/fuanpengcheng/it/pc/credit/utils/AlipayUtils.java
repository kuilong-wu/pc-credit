package com.fuanpengcheng.it.pc.credit.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fuanpengcheng.it.pc.credit.consts.AlipayConsts;

import java.util.Map;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-02-14
 * @description:
 */
public class AlipayUtils {
    public static String generateOutTradeNo(){
        return "tradepay" + System.currentTimeMillis()
                + (long) (Math.random() * 10000000L);
    }

    public static String generateAlipaySubject(){
        return "testSubject";
    }

    public Boolean checkSign(Map<String, String> params){
        try {
            return AlipaySignature.rsaCheckV1(params, AlipayConsts.ALIPAY_PUBLIC_KEY, AlipayConsts.CHARSET);
        } catch (AlipayApiException e) {
            return false;
        }
    }
}
