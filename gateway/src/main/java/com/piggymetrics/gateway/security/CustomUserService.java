//package com.piggymetrics.gateway.security;
//
//import com.piggymetrics.gateway.client.UserServiceClient;
//import com.piggymetrics.gateway.domain.SysPermission;
//import com.piggymetrics.gateway.domain.SysUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by terry on 2018/9/18.
// * 自定义UserDetailsService 接口
// */
//@Service
//public class CustomUserService implements UserDetailsService {
//
//    @Autowired
//    private UserServiceClient userServiceClient;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser user = userServiceClient.queryUser(username);
//        if (user != null) {
//            List<SysPermission> permissions = userServiceClient.queryPermission(user);
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            for (SysPermission permission : permissions) {
//                if (permission != null && permission.getName()!=null) {
//
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
//                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
//                    grantedAuthorities.add(grantedAuthority);
//                }
//            }
//            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//        } else {
//            throw new UsernameNotFoundException("用户:" + username + "不存在");
//        }
//    }
//}
