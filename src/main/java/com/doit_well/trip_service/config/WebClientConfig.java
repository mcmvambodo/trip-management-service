package com.doit_well.trip_service.config;

import com.doit_well.trip_service.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


   @Value("${customer.client.baseUrl:http://customer-service}")
    private String customerClientBaseUrl;
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;
    @Bean
    public WebClient customerWebClient(){
        return WebClient.builder()
                .filter(filterFunction)
                .build();
    }
}
