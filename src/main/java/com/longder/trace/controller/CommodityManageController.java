package com.longder.trace.controller;

import com.longder.trace.entity.Commodity;
import com.longder.trace.entity.SysUser;
import com.longder.trace.security.SecurityUtil;
import com.longder.trace.service.CommodityManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 商品管理控制器
 */
@Controller
@RequestMapping("/admin/commodity")
public class CommodityManageController {
    @Resource
    private CommodityManageService commodityManageService;

    /**
     * 商品列表，卖家只能查到自己的商品
     */
    @GetMapping("/list")
    public String list(Model model) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        model.addAttribute("commodityList", commodityManageService.listCommodities(currentUser));
        return "commodity/list";
    }

    /**
     * 去添加页面
     *
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "commodity/add-commodity-modal";
    }

    /**
     * 添加商品
     */
    @PostMapping("/add")
    public String add(Commodity commodity) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        commodityManageService.saveOneCommodity(commodity, currentUser);
        return "redirect:list";
    }

    /**
     * 去修改商品
     */
    @GetMapping("/toUpdate")
    public String toUpdate(Long id, Model model) {
        model.addAttribute("commodity", commodityManageService.getOneCommodity(id));
        return "commodity/update-commodity-modal";
    }

    /**
     * 修改商品
     */
    @PostMapping("/update")
    public String update(Commodity commodity) {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        commodityManageService.saveOneCommodity(commodity, currentUser);
        return "redirect:list";
    }

    /**
     * 商品详情
     * @return
     */
    @GetMapping("/detail")
    public String detail(Long id, Model model){
        model.addAttribute("commodity", commodityManageService.getOneCommodity(id));
        return "commodity/detail-modal";
    }
}
