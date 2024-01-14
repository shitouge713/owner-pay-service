package com.owner.pay.mybatisplus;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.owner.pay.mybatisplus.EasySqlInjector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置分页插件
 */
@Configuration
public class MybatisPlusConfig {

    @Value("${order.mybatisLimit:5000}")
    private Long limit;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setLimit(limit);
        return interceptor;
    }

    @Bean
    public EasySqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }
}
