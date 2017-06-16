package com.miaolegemitong.demo.springboot;

import com.miaolegemitong.demo.springboot.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by miaolegemitong on 2017/6/16.
 */
@Configuration
public class Filters {
    @Bean
    public FilterRegistrationBean delegatingFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setName("login");
        filter.setFilter(new LoginFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }
}
