package com.piggymetrics.user.domain;

/**
 * Created by terry on 2018/9/18.
 */
public class SysPermissionRole {

    private Integer id;
    private Integer role_id;
    private Integer permission_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission_id) {
        this.permission_id = permission_id;
    }
}
