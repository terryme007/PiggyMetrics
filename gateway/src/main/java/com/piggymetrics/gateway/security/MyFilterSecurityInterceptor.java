package com.piggymetrics.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.SecurityMetadataSource;
//import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
//import org.springframework.security.access.intercept.InterceptorStatusToken;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by terry on 2018/9/18.
 */
@Service
public class MyFilterSecurityInterceptor implements Filter {

//    @Autowired
//    private FilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    @Autowired
//    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
//        super.setAccessDecisionManager(myAccessDecisionManager);
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String name=servletRequest.getParameter("name");
        System.out.println("=================name:"+name);
        filterChain.doFilter(servletRequest,servletResponse);
//        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
//        invoke(fi);
    }

    @Override
    public void destroy() {

    }

//    @Override
//    public Class<?> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }
//
//    @Override
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return this.securityMetadataSource;
//    }
//
//    private void invoke(FilterInvocation fi) throws IOException, ServletException {
//        //fi里面有一个被拦截的url
//        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
//        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
//        InterceptorStatusToken token = super.beforeInvocation(fi);
//        try {
//            //执行下一个拦截器
//            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//        } finally {
//            super.afterInvocation(token, null);
//        }
//    }
}
