package com.example.shopmanager.service;

import com.example.shopmanager.model.Shop;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ShopService {
    private final List<Shop> shops = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public ShopService() {
        addShop(new Shop("Продукты 24/7", "ул. Ленина, 10", "+7-495-111-22-33",
                "shop1@mail.ru", "http://products24.ru", "продовольственный",
                "Круглосуточный продуктовый магазин"));
        addShop(new Shop("Спортмастер", "пр. Мира, 25", "+7-495-222-33-44",
                "info@sportmaster.ru", "http://sportmaster.ru", "спортивный",
                "Магазин спортивных товаров"));
    }

    public List<Shop> getAllShops() {
        return new ArrayList<>(shops);
    }

    public Shop getShopById(Long id) {
        return shops.stream()
                .filter(shop -> shop.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addShop(Shop shop) {
        shop.setId(counter.incrementAndGet());
        shops.add(shop);
    }
}