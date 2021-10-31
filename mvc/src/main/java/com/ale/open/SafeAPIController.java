package com.ale.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SafeAPIController {
    @Autowired
    private SignatureService signatureService;

    /**
     * 解决请求重放的问题
     * 方法1：请求接口的参数中添加唯一的随机字符串 nonce
     * 方法2：请求接口的参数中添加时间戳 timestamp
     * 方法3：请求接口的参数中添加签名 signature
     * nonce指唯一的随机字符串，用来标识每个被签名的请求。然而，对服务器来说永久存储所有接收到的nonce的代价是非常大的。
     * 可以使用timestamp来优化nonce的存储。
     * 假设允许客户端和服务端最多能存在15分钟的时间差，同时追踪记录在服务端的nonce集合。
     * 当有新的请求进入时，首先检查携带的timestamp是否在15分钟内，如超出时间范围，则拒绝，
     * 然后查询携带的nonce，如存在已有集合，则拒绝。否则，记录该nonce，并删除集合内时间戳大于15分钟的nonce
     *
     * @author alewu
     * @since 2020/5/15 11:23
     */
    @GetMapping()
    public ResponseEntity safe(@RequestParam("t1") String timestamp, String nonce, String signature) {
        StopWatch sw = new StopWatch();
        signatureService.checkSignature(timestamp, nonce, signature);
        return ResponseEntity.ok().build();
    }
}
