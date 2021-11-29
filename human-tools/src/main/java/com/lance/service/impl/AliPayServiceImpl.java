package com.lance.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lance.domain.AlipayConfig;
import com.lance.domain.vo.TradeVo;
import com.lance.exception.BadRequestException;
import com.lance.repository.AliPayRepository;
import com.lance.service.AliPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "aliPay")
public class AliPayServiceImpl implements AliPayService {

    private final AliPayRepository aliPayRepository;

    @Override
    public AlipayConfig find() {
        Optional<AlipayConfig> alipayConfig = aliPayRepository.findById(1L);
        return alipayConfig.orElseGet(AlipayConfig::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AlipayConfig config(AlipayConfig alipayConfig) {
        alipayConfig.setId(1L);
        return aliPayRepository.save(alipayConfig);
    }

    @Override
    public String toPayAsPC(AlipayConfig alipay, TradeVo tradeVo) throws AlipayApiException {
        if (null == alipay.getId()) {
            throw new BadRequestException("请先添加相关配置");
        }

        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(),  alipay.getPrivateKey(),
                alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(alipay.getReturnUrl());
        payRequest.setNotifyUrl(alipay.getNotifyUrl());
        payRequest.setBizContent( "{" +
                "    \"out_trade_no\":\""+tradeVo.getOutTradeNo()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+tradeVo.getTotalAmount()+"," +
                "    \"subject\":\""+tradeVo.getSubject()+"\"," +
                "    \"body\":\""+tradeVo.getBody()+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+alipay.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }"
        );
        // 通过GET方式，获取返回url
        return alipayClient.pageExecute(payRequest, "GET").getBody();
    }
}
