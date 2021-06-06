package com.longder.trace.service;

import com.longder.trace.entity.Commodity;
import com.longder.trace.entity.SysUser;

import java.util.List;

/**
 * 商品管理的服务类
 */
public interface CommodityManageService {

    /**
     * 根据某用户（卖方）查询
     *
     * @param sysUser
     * @return
     */
    List<Commodity> listCommodities(SysUser sysUser);

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Commodity> listAllCommodities();

    /**
     * 保存一个商品（新增OR修改）
     *
     * @param commodity 商品对象
     * @param seller    卖方
     */
    void saveOneCommodity(Commodity commodity, SysUser seller);

    /**
     * 查询获取一个商品信息（编辑前查询用）
     *
     * @param commodityId 商品id
     * @return
     */
    Commodity getOneCommodity(Long commodityId);

    /**
     * 扫描统计数+1
     * @param commodityId
     */
    void scanCountPlus(Long commodityId);


}
