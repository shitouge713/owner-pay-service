package com.owner.pay.remote;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author sxl
 * @create 2023/1/11 10:46
 * @Description: 远程调用服务
 * @RefreshScope 对 url = "${owner.server.orderUrl}"不起作用
 */
@Service
@RefreshScope
@FeignClient(name = "owner-order", url = "${owner.server.orderUrl}")
public interface IRemoteOrderService {

    /**
     * 省市区地址编码查询
     *
     * @param
     * @return
     */
    @GetMapping("/car/insert")
    void handleOrder();
}
