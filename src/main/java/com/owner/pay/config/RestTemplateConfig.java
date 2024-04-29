package com.owner.pay.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * @author pengdonglan
 * @version V1.0
 * @date 2022-05-31
 */
@Configuration
@Slf4j
public class RestTemplateConfig {

    @Bean("simpleClientHttpRequestFactory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 设置连接超时
        factory.setReadTimeout(6000);
        // 设置读取超时
        factory.setConnectTimeout(5000);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory simpleClientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);//处理401响应
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
        //    restTemplate.setMessageConverters(Collections.singletonList(new FastJsonHttpMessageConverter()));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){//处理401响应
            @Override
            public void handleError(ClientHttpResponse response) throws IOException{
                if(response.getRawStatusCode() != 401){
                    super.handleError(response);
                }
            }
        });
        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                logRequest(request, body);
                ClientHttpResponse response = execution.execute(request, body);
                logResponse(response);
                return response;
            }

            private void logRequest(HttpRequest request, byte[] body) throws IOException {
//                log.info("----------------------------request begin----------------------------");
                log.info("----------URI         : {}", request.getURI());
//                log.info("----------Method      : {}", request.getMethod());
//                log.info("Headers     : {}", request.getHeaders());
                log.info("----------Request body: {}", new String(body, "UTF-8"));
//                log.info("----------------------------request end----------------------------");
            }

            private void logResponse(ClientHttpResponse response) throws IOException {
//                log.info("----------------------------response begin----------------------------");
//                log.info("----------Status code  : {}", response.getStatusCode());
//                log.info("Status text  : {}", response.getStatusText());
//                log.info("Headers      : {}", response.getHeaders());
                String responseStr= StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
                log.info("----------Response body: {}", responseStr);
//                log.info("----------------------------response end----------------------------");
            }
        }));

        return restTemplate;
    }
}
