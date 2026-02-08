package com.example.shopmanager.controller;

import com.example.shopmanager.model.Shop;
import com.example.shopmanager.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/")
    public String listShops(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "shop-list";
    }

    @GetMapping("/shops/{id}")
    public String viewShop(@PathVariable Long id, Model model) {
        Shop shop = shopService.getShopById(id);
        if (shop == null) {
            return "redirect:/";
        }
        model.addAttribute("shop", shop);
        return "shop-details";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("shop", new Shop());
        return "shop-form";
    }

    @PostMapping("/add")
    public String addShop(@ModelAttribute Shop shop) {
        shopService.addShop(shop);
        return "redirect:/";
    }
}