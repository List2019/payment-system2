package com.payment_system;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
