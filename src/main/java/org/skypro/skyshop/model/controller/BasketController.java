package org.skypro.skyshop.model.controller;


import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }


    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }


    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}

