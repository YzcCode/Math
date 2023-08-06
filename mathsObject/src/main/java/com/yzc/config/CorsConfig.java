package com.yzc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * TODO: 前后端分离的跨域配置
 * 小结：为什么需要配置跨域？
 * 前端和后端不同的进程(不同的域名)，因此需要在后端进行跨域配置
 * @author yangzicheng
 * @version 1.0
 * @date 2021/12/29 14:19
 */
@Configuration
@Slf4j
public class CorsConfig {

    /**
     * 配置跨域过滤器
     * @return 跨域过滤器
     */
    @Bean
    public CorsFilter createCorsFilter() {
        log.info("CorsConfig start");
        // 跨域基本配置： 允许所有的域名访问我的后端、允许所有的请求头访问我的后端、允许所有的请求方法访问后端
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        // 配置跨域的URL
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        log.info("Corsconfig end");
        return new CorsFilter(source);
    }
}
