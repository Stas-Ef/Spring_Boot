package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.Exception.NameIsBlankException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BasketServiceTest {

    private ProductBasket productBasket;
    private StorageService storageService;
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        productBasket = mock(ProductBasket.class);
        storageService = mock(StorageService.class);
        basketService = new BasketService(productBasket, storageService);
    }

    @Test
    void addProductToBasket_NonExistentProduct_ShouldThrow() {
        UUID fakeId = UUID.randomUUID();
        when(storageService.getProductById(fakeId)).thenReturn(java.util.Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            basketService.addProductToBasket(fakeId);
        });
    }

    @Test
    void addProductToBasket_ExistingProduct_ShouldCallAddProduct() throws NameIsBlankException {
        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct(productId, "Вишня", 50) {};
        when(storageService.getProductById(productId)).thenReturn(java.util.Optional.of(product));

        basketService.addProductToBasket(productId);

        verify(productBasket).addProduct(productId);
    }

    @Test
    void getUserBasket_Empty_ShouldReturnEmptyUserBasket() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getItems().isEmpty(), "Корзина должна быть пустой");
    }

    @Test
    void getUserBasket_WithItems_ShouldReturnCorrectItems() throws NameIsBlankException {
        UUID productId = UUID.randomUUID();
        Map<UUID, Integer> mockProducts = new HashMap<>();
        mockProducts.put(productId, 2);
        when(productBasket.getProducts()).thenReturn(mockProducts);

        Product product = new SimpleProduct(productId, "Черешня",100500) {};
        when(storageService.getProductById(productId)).thenReturn(java.util.Optional.of(product));

        UserBasket basket = basketService.getUserBasket();

        assertEquals(1, basket.getItems().size());
        BasketItem item = basket.getItems().get(0);
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }
}