package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException(" Продукт с id: " + id + " Не найден!");
        }
        productBasket.addProduct(id);
    }


    public UserBasket getUserBasket() {
        Map<UUID, Integer> productMap = productBasket.getProducts();
        List<BasketItem> items = productMap.entrySet().stream()
                .map(entry -> {
                    UUID id = entry.getKey();
                    Integer quantity = entry.getValue();
                    Product product = storageService.getProductById(id)
                            .orElseThrow(() -> new IllegalStateException("Product not found for id " + id));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());
        return new UserBasket(items);
    }
}
