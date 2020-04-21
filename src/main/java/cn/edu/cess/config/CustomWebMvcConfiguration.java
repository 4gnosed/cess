package cn.edu.cess.config;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Gnosed Lu
 * @Date 2020/1/13
 */
@Configuration
public class CustomWebMvcConfiguration implements WebMvcConfigurer {
    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)     //允许跨域使用 cookie
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*");
    }

    /**
     * 文件路径与本地关联
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Constant.PART_PATH+"**")
                .addResourceLocations("file:" + Constant.FILE_FOLDER);
    }

//    /**
//     * 登录拦截，除了登录登出注册等
//     * 删除该方案，采用shiro过滤器方案
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/index.html")
//                .excludePathPatterns("/api/register")
//                .excludePathPatterns("/api/login")
//                .excludePathPatterns("/api/logout");
//    }
//    @Bean
//    public CustomInterceptor getInterceptor() {
//        return new CustomInterceptor();
//    }
}
