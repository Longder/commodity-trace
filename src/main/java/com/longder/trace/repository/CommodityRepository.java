package com.longder.trace.repository;

import com.longder.trace.entity.Commodity;
import com.longder.trace.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 商品表数据操作
 */
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    @Query("select c from Commodity c where c.seller = :seller")
    List<Commodity> listByUser(@Param("seller") SysUser seller);
}
