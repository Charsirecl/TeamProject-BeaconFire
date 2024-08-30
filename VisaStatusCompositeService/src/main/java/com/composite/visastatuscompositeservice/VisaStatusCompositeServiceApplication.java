package com.composite.visastatuscompositeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class VisaStatusCompositeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisaStatusCompositeServiceApplication.class, args);
    }

}
