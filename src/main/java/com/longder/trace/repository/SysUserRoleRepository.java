package com.longder.trace.repository;

import com.longder.trace.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户角色表操作对象
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long> {

    /**
     * 根据用户id查询用户关联的所有角色
     *
     * @param userId
     * @return
     */
    @Query("select ur from SysUserRole ur where ur.sysUser.id = :userId")
    List<SysUserRole> listRolesByUserId(@Param("userId") Long userId);
}
