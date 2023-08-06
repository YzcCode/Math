package com.yzc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author yangzicheng
 * @Date Created in 9:45 2021/12/27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApiInfo()).select().
                apis(RequestHandlerSelectors.basePackage("com.yzc.controller")).build();
    }

    private ApiInfo createApiInfo() {

        return  new ApiInfoBuilder().title("数学练习系统").description("后台管理系统由4个模块组成").version("1.0").build();
    }

}
