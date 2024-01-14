package com.owner.pay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
public class HealthController {

    @Value("${name:12}")
    private Integer name;


    @GetMapping("/health")
    public String health() {
        log.info(String.valueOf(name));
        return "success-0412";
    }
}
