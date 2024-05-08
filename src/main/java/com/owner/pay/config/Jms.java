package com.owner.pay.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * @Description: 连接RocketMQ服务器实体
 *
 * @author sxl
 * @date 2019/7/15 下午11:30
 */
@Data
@Configuration
public class Jms {

    /**
     * 配置中心读取 服务器地址
     */
    @Value("${name_server:127.0.0.1:9876}")
    private String nameServer;

    /**
     * 配置中心读取 主题
     */
    @Value("${order_topic:order_topic}")
    private String orderTopic;

}
