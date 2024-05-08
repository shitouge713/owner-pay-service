package com.owner.pay.controller;


import com.alibaba.fastjson.JSON;
import com.owner.pay.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xub
 * @Description: 商品服务对外提供接口
 * @date 2019/7/12 下午12:43
 */
@RestController
@RequestMapping("/api/v1/produce")
public class ProduceController {

    @Autowired
    private ProduceService produceService;

    /**
     * 根据主键ID获取商品
     */
    @GetMapping("/find")
    public String findById(@RequestParam(value = "produceId") int produceId) {
        return JSON.toJSONString(produceService.findById(produceId));

    }

}
