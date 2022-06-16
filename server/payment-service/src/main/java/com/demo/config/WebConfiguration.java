package com.demo.config;

import com.demo.interceptor.UserNameAwareInterceptor;
import com.demo.interceptor.AuthInterceptor;
import com.demo.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class WebConfiguration implements WebMvcConfigurer {

        @Bean
        public AuthInterceptor authInterceptor() {
            return new AuthInterceptor();
        }

        @Bean
        public RoleInterceptor roleInterceptor() {
            return new RoleInterceptor();
        }

        @Bean
        public UserNameAwareInterceptor userNameAwareInterceptor() {
            return new UserNameAwareInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/login", "/registration",
                            "/logout", "/css/**", "/js/**", "/checkLoginExist");

            registry.addInterceptor(roleInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/login",
                            "/registration", "/logout", "/css/**", "/js/**", "/checkLoginExist", "/refill", "/transfer",
                            "/account_blocking", "/main");

            registry.addInterceptor(userNameAwareInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/login",
                            "/registration", "/logout", "/css/**", "/js/**", "/checkLoginExist");

        }

    }
