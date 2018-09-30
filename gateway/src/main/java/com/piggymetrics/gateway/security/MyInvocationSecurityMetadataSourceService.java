//package com.piggymetrics.gateway.security;
//
//import com.piggymetrics.gateway.client.UserServiceClient;
//import com.piggymetrics.gateway.domain.SysPermission;
//import com.piggymetrics.gateway.domain.SysUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
///**
// * Created by terry on 2018/9/18.
// */
//@Service
//public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
//
//    private HashMap<String, Collection<ConfigAttribute>> map =null;
//
//    @Autowired
//    private UserServiceClient userServiceClient;
//
//    /**
//     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
//     * @param o
//     * @return
//     * @throws IllegalArgumentException
//     */
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        if(map ==null) loadResourceDefine();
//        //object 中包含用户请求的request 信息
//        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
//        AntPathRequestMatcher matcher;
//        String resUrl;
//        for (String s : map.keySet()) {
//            resUrl = s;
//            matcher = new AntPathRequestMatcher(resUrl);
//            if (matcher.matches(request)) {
//                return map.get(resUrl);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//
//    /**
//     * 加载权限表中所有权限
//     */
//    private void loadResourceDefine(){
//        map = new HashMap<>();
//        Collection<ConfigAttribute> array;
//        ConfigAttribute cfg;
//        List<SysPermission> permissions = userServiceClient.queryPermission(new SysUser());
//        for(SysPermission permission : permissions) {
//            array = new ArrayList<>();
//            cfg = new SecurityConfig(permission.getName());
//            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
//            array.add(cfg);
//            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
//            map.put(permission.getUrl(), array);
//        }
//
//    }
//}
