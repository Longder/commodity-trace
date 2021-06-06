package com.longder.trace.controller;

import com.longder.trace.service.CommodityManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * 主控制器，首页，管理员页，登录。
 */
@Controller
public class MainController {

    @Resource
    private CommodityManageService commodityManageService;

    /**
     * 主页 公众端
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("commodityList", commodityManageService.listAllCommodities());
        return "index";
    }

    /**
     * 去登陆页
     */
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";

    }

    /**
     * 后台主页 商家用
     */
    @GetMapping("/admin/index")
    public String adminIndex() {
        System.out.println("后台主页");
        return "adminIndex";
    }

    /**
     * 仪表盘，后台欢迎页面
     */
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 商品详情信息，扫码查看
     */
    @GetMapping("/commodityInfo/{commodityId}")
    public String commodityInfo(@PathVariable Long commodityId, Model model) {
        commodityManageService.scanCountPlus(commodityId);
        model.addAttribute("commodity", commodityManageService.getOneCommodity(commodityId));
        return "detail";
    }
}
