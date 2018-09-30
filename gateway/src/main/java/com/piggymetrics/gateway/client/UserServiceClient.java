//package com.piggymetrics.gateway.client;
//
//import com.piggymetrics.gateway.domain.SysPermission;
//import com.piggymetrics.gateway.domain.SysUser;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
///**
// * Created by terry on 2018/9/18.
// */
//@FeignClient(name = "user-service", fallback = UserServiceClientFallBack.class)
//public interface UserServiceClient {
//
//    @RequestMapping(method = RequestMethod.POST, value = "/user/query_user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public SysUser queryUser(String username);
//
//    @RequestMapping(method = RequestMethod.POST, value = "/user/query_permission", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<SysPermission> queryPermission(SysUser sysUser);
//}
