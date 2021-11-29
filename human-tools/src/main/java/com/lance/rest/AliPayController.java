package com.lance.rest;

import com.alipay.api.AlipayApiException;
import com.lance.domain.AlipayConfig;
import com.lance.domain.vo.TradeVo;
import com.lance.service.AliPayService;
import com.lance.utils.AlipayUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aliPay")
@Api(tags = "工具：支付宝管理")
public class AliPayController {

    private final AliPayService aliPayService;
    private final AlipayUtils alipayUtils;

    @GetMapping
    public ResponseEntity<AlipayConfig> queryConfig() {
        return new ResponseEntity<>(aliPayService.find(), HttpStatus.OK);
    }

    @ApiOperation("配置支付宝")
    @PutMapping
    public ResponseEntity<Object> updateConfig(@Validated @RequestBody AlipayConfig alipayConfig) {
        aliPayService.config(alipayConfig);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("支付宝网页支付")
    @PostMapping("/toPayAsPC")
    public ResponseEntity<String> toPayAsPC(@Validated @RequestBody TradeVo tradeVo) throws AlipayApiException {
        AlipayConfig alipayConfig = aliPayService.find();
        tradeVo.setOutTradeNo(alipayUtils.getOrderCode());
        String payUrl = aliPayService.toPayAsPC(alipayConfig, tradeVo);
        return ResponseEntity.ok(payUrl);
    }
}
