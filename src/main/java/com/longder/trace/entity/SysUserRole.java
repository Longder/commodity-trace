package com.longder.trace.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * 用户角色关联
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "SYS_USER_ROLE")
public class SysUserRole extends BaseIdEntity implements GrantedAuthority {

    /**
     * 用户id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "sys_user_id_", referencedColumnName = "id_")
    private SysUser sysUser;

    /**
     * 角色
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role_")
    private SysRole role;

    @Override
    public String getAuthority() {
        return this.role.getName();
    }

    @Override
    public String toString() {
        return "SysUserRole{" + "role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysUserRole that = (SysUserRole) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 960976917;
    }
}
