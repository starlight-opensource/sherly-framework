package cn.starlightsoftware.sherly.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 谷子毅
 */
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                // 所有controller.admin的url前面加个"/admin-api"
                AntPathMatcher antPathMatcher = new AntPathMatcher(".");
                configurer.addPathPrefix("admin-api", clazz -> (clazz.isAnnotationPresent(RestController.class) || clazz.isAnnotationPresent(Controller.class)) && antPathMatcher.match("**.controller.admin.**", clazz.getPackage().getName()));
                // 所有controller.app的url前面加个"/app-api"
                configurer.addPathPrefix("app-api", clazz -> (clazz.isAnnotationPresent(RestController.class) || clazz.isAnnotationPresent(Controller.class)) && antPathMatcher.match("**.controller.app.**", clazz.getPackage().getName()));
            }
        };
    }
}
