package com.longder.trace.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 系统角色枚举
 */
public enum SysRole implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN", "系统管理员"),
    ROLE_SELLER("ROLE_SELLER", "卖家"),
    ROLE_BUYER("ROLE_BUYER", "买家");

    /**
     * 名称
     */
    private String name;
    /**
     * 展示名
     */
    private String displayName;


    SysRole(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
