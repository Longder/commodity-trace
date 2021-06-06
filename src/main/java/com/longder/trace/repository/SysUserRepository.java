package com.longder.trace.repository;

import com.longder.trace.entity.SysRole;
import com.longder.trace.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户表操作对象
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据登录名查询唯一用户
     * @param loginName
     * @return
     */
    @Query("select s from SysUser s where s.loginName = :loginName")
    SysUser getByLoginName(@Param("loginName") String loginName);


    @Query("select distinct s from SysUser s left join s.roles r where r.role = :role ")
    List<SysUser> listByRole(@Param("role") SysRole role);

    /**
     * 查询普通用户（不包括管理员）
     * @return
     */
    @Query("select distinct s from SysUser s left join s.roles r where r.role <> 'ROLE_ADMIN' ")
    List<SysUser> listCommonUser();
}
