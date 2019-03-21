package com.daishuaiqing.farmland.config;

import com.daishuaiqing.farmland.authc.AuthcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new AuthcFilter());
        //拦截规则
        registration.addUrlPatterns("/*");

        //过滤器名称
        registration.setName("PermissionFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(false);//false关闭权限，true开启权限
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }
}
