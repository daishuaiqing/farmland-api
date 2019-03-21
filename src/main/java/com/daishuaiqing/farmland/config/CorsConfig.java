package com.daishuaiqing.farmland.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Value("${web.cors.allowed-origins}")
    private String allowedOrigin;
    @Value("${web.cors.allowed-methods}")
    private String allowedMethods;
    @Value("${web.cors.allowed-headers}")
    private String allowedHeaders;
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);//允许跨域携带cookies
        corsConfiguration.addAllowedOrigin(allowedOrigin); // 允许所有域名跨域访问
        corsConfiguration.addAllowedHeader(allowedHeaders); // 允许所有请求头
        corsConfiguration.addAllowedMethod(allowedMethods); // 允许所有请求方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}