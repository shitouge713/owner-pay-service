package com.owner.pay.controller;

import com.owner.pay.dao.pojo.OCart;
import com.owner.pay.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/car")
public class CarController {
    @Resource
    ICartService cartService;

    @GetMapping("/insert")
    public Boolean save() {
        return cartService.save();
    }

    @GetMapping("/queryByUserId")
    public List<OCart> queryByUserId(@RequestParam("userId")  String userId) {
        return cartService.queryByUserId(userId);
    }

    @GetMapping("/deleteByUserId")
    public int deleteByUserId(@RequestParam("userId")  String userId) {
        return cartService.deleteByUserId(userId);
    }


}
