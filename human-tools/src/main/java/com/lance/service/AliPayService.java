package com.lance.service;

import com.alipay.api.AlipayApiException;
import com.lance.domain.AlipayConfig;
import com.lance.domain.vo.TradeVo;

public interface AliPayService {
    AlipayConfig find();

    AlipayConfig config(AlipayConfig alipayConfig);

    String toPayAsPC(AlipayConfig alipayConfig, TradeVo tradeVo) throws AlipayApiException;
}
