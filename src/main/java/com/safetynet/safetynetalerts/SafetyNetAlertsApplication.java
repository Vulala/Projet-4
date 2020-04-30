package com.safetynet.safetynetalerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SafetyNetAlertsApplication {
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SafetyNetAlertsApplication.class, args);
        JSONReader.readJSON();
    }
    
    @Bean
    // Bean used to enable the httptrace endpoint, making it save the requests in memory
    // Since Spring Boot 2.2
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
    
    @Bean
    public JSONReader jsonReaderBean() {
        return new JSONReader();
    }
}
