//package com.example.applicationservice.config;
//
//import com.netflix.appinfo.ApplicationInfoManager;
//import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
//import com.netflix.discovery.DefaultEurekaClientConfig;
//import com.netflix.discovery.EurekaClient;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.netflix.eureka.CloudEurekaClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class EurekaClientConfig {
//
//    @Bean
//    public AbstractDiscoveryClientOptionalArgs<?> discoveryClientOptionalArgs() {
//        return new AbstractDiscoveryClientOptionalArgs<Object>() {
//
//        };
//    }
//
////    @Bean
////    public EurekaClient eurekaClient(ApplicationInfoManager applicationInfoManager, EurekaClientConfig eurekaClientConfig) {
////        return new CloudEurekaClient(applicationInfoManager, eurekaClientConfig, new AbstractDiscoveryClientOptionalArgs(), this.context);
////    }
//
////    @Bean
////    public AbstractDiscoveryClientOptionalArgs<?> discoveryClientOptionalArgs() {
////        return new DefaultEurekaClientConfig();
////    }
//}
