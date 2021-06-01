/*
 * Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
 *
 * 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package cn.edu.cess.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean authFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CustomFilter customFilter = new CustomFilter();
        registrationBean.setFilter(customFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/api/*");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
