package com.fuanpengcheng.it.pc.credit.client;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.fuanpengcheng.it.pc.credit.consts.AlipayConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-03
 * @description:
 */
@Service
public class PcAlipayClient {
    private static final Logger LOG = LoggerFactory.getLogger(PcAlipayClient.class);

    private AlipayClient alipayClient;

    @PostConstruct
    public void init(){
        alipayClient = new DefaultAlipayClient(AlipayConsts.GATEWAY_ROOT, AlipayConsts.APP_ID, AlipayConsts.APP_PRIVATE_KEY, AlipayConsts.FORMAT, AlipayConsts.CHARSET, AlipayConsts.ALIPAY_PUBLIC_KEY, AlipayConsts.SIGH_TYPE);
    }

    public <T extends com.alipay.api.AlipayResponse> T execute(com.alipay.api.AlipayRequest<T> alipayRequest) {
        try {
            return alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            LOG.info("Call alipay client error. request={}, e={}", alipayRequest.toString(), e);
            return null;
        }
    }

}
