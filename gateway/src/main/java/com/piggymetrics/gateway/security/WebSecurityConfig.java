//package com.piggymetrics.gateway.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
///**
// * Created by terry on 2018/9/18.
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
//
//    @Autowired
//    private UserDetailsService customUserService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService); //user Details Service验证
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/login.html","/register.html","/**/*.css","/**/*.js").permitAll()
////                .antMatchers("/**/token","/**/register").permitAll()
////                .anyRequest().authenticated() //任何请求,登录后可以访问
////                .and()
////                .formLogin()
////                .loginPage("/login.html")
////                .failureUrl("/login.html?error")
////                .permitAll() //登录页面用户任意访问
////                .and()
////                .logout().permitAll(); //注销行为任意访问
//        http.authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login.html?error")
//                .permitAll() //登录页面用户任意访问
//                .and()
//                .logout().permitAll(); //注销行为任意访问
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//    }
//
//}
