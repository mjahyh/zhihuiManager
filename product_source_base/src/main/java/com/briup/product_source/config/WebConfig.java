package com.briup.product_source.config;

import com.briup.product_source.Interceptor.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Hlmove
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对swagger的请求不进行拦截
        String[] excludePatterns = new String[]{
                "/profile/**",
                "/animal/**",
                "/common/download**",
                "/common/download/resource**",
                "/swagger/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/*/api-docs",
                "/favicon.ico",
                "/doc.html",
                "/error",
                "/static/images/**"
        };
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePatterns)
                .excludePathPatterns("/login");
        //.excludePathPatterns("/animal/findByAnimalId/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //根据HTTP协议规定的预检请求的返回头信息进行设置
        registry.addMapping("/**")// 映射所有路径
                .allowedOrigins("*")
                //不允许携带cookie
                .allowCredentials(false)
                //支持的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                //运行所有请求头字段
                .allowedHeaders("*")
                //允许客户端缓存“预检请求”中获取的信息，3600秒
                .maxAge(3600);
    }

    /**
     * 静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String targetPath  = "file:F:/Code2023/Code/workplace/zhihuiManager/images/";
        registry.addResourceHandler("static/images/**").addResourceLocations(targetPath);
    }

}
