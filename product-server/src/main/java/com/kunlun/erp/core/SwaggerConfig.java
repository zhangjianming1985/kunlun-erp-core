package com.kunlun.erp.core;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description Swagger配置
 * @Author Jm.zhang
 * @Date 2019/11/29 15:24
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.kunlun.erp.core.controller.company,com.kunlun.erp.core.controller.area"))
//                .apis(basePackage("com.kunlun.erp.core.controller.company,com.kunlun.erp.core.controller.area,com.kunlun.erp.core.controller.product"))
                .apis(basePackage("com.kunlun.erp.core.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("昆仑ERP-产品、供应商、渠道、订单 RESTful 接口文档")
                .description("昆仑ERP-产品、供应商、渠道、订单 RESTful 接口文档")
                .termsOfServiceUrl("http://localhost:9898/")
                .contact("张建明")
                .version("1.0")
                .build();
    }


    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage){
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(",")) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }



}
