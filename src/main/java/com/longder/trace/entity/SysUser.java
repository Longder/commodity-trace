package com.longder.trace.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseIdEntity implements UserDetails {

    /**
     * 姓名
     */
    @Column(name = "name_")
    private String name;

    /**
     * 登录名
     */
    @Column(name = "login_name_")
    private String loginName;

    /**
     * 登陆密码
     */
    @Column(name = "password_")
    private String password;

    /**
     * 手机号
     */
    @Column(name = "phone_")
    private String phone;
    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private LocalDate createDate;
    /**
     * 是否是会员
     */
    @Column(name = "member_")
    private Boolean member;


    /**
     * 用户角色（多个）
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sysUser", cascade = CascadeType.ALL)
    private List<SysUserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysUser sysUser = (SysUser) o;

        return id != null && id.equals(sysUser.id);
    }

    @Override
    public int hashCode() {
        return 421708296;
    }
}
