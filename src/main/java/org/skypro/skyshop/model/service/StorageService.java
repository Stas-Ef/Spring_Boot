package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.Exception.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initializeTestData();
    }

    public Collection<Product> getAllProducts() {
        System.out.println("products = " + products.values() + "Id" + products.keySet());
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(articles.values());
        result.addAll(products.values());
        System.out.println("result = " + result);
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        if (products.get(id) == null) {
            throw new NoSuchProductException("Product not found");
        }
        return Optional.ofNullable(products.get(id));

    }

    private void initializeTestData() {


        try {
            SimpleProduct Apple = new SimpleProduct(UUID.randomUUID(), "Яблоки", 1);
            SimpleProduct Banana = new SimpleProduct(UUID.randomUUID(), "Бананы", 10);
            FixPriceProduct Onion = new FixPriceProduct(UUID.randomUUID(), "Лук");
            DiscountedProduct Pineapple = new DiscountedProduct(UUID.randomUUID(), "Ананас", 100000, 15);
            SimpleProduct Watermelon = new SimpleProduct(UUID.randomUUID(), "Арбуз", 1000);
            SimpleProduct Potato = new SimpleProduct(UUID.randomUUID(), "Картофель", 10000);

            products.put(Apple.getId(), Apple);
            products.put(Banana.getId(), Banana);
            products.put(Onion.getId(), Onion);
            products.put(Pineapple.getId(), Pineapple);
            products.put(Watermelon.getId(), Watermelon);
            products.put(Potato.getId(), Potato);

            Article miniBananas = new Article(UUID.randomUUID(), "Маленькие Бананы", "Почти как бананы, только в несколько раз меньше. Не Яблоки.");
            Article tomato = new Article(UUID.randomUUID(), "Помидоры", "Красные, но Не Яблоки.");
            Article strawberry = new Article(UUID.randomUUID(), "Ягоды", "совсем не Яблоки.");
            Article gala = new Article(UUID.randomUUID(), "Яблоки \"Гала\"", "Еще одна из многих разновидностей яблок");
            Article antonovka = new Article(UUID.randomUUID(), "Яблоки \"Антоновка\"", "Одна из многих разновидностей яблок");

            articles.put(miniBananas.getId(), miniBananas);
            articles.put(tomato.getId(), tomato);
            articles.put(strawberry.getId(), strawberry);
            articles.put(gala.getId(), gala);
            articles.put(antonovka.getId(), antonovka);


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Проверка выполнена");
        }
    }

}
