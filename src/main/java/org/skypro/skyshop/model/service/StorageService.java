package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(articles.values());
        result.addAll(products.values());
        return result;
    }

    private void initializeTestData() {


        try {
            SimpleProduct Apple = new SimpleProduct(UUID.randomUUID(), "Яблоки", 1);
            SimpleProduct Banana = new SimpleProduct(UUID.randomUUID(), "Бананы", 10);
            FixPriceProduct Onion = new FixPriceProduct(UUID.randomUUID(), "Лук");
            DiscountedProduct Pineapple = new DiscountedProduct(UUID.randomUUID(), "Ананас", 100000, 15);
            SimpleProduct Watermelon = new SimpleProduct(UUID.randomUUID(), "Арбуз", 1000);
            SimpleProduct Potato = new SimpleProduct(UUID.randomUUID(), "Картофель", 10000);

            products.put(UUID.randomUUID(), Apple);
            products.put(UUID.randomUUID(), Banana);
            products.put(UUID.randomUUID(), Onion);
            products.put(UUID.randomUUID(), Pineapple);
            products.put(UUID.randomUUID(), Watermelon);
            products.put(UUID.randomUUID(), Potato);

            Article miniBananas = new Article(UUID.randomUUID(), "Маленькие Бананы", "Почти как бананы, только в несколько раз меньше. Не Яблоки.");
            Article tomato = new Article(UUID.randomUUID(), "Помидоры", "Красные, но Не Яблоки.");
            Article strawberry = new Article(UUID.randomUUID(), "Ягоды", "совсем не Яблоки.");
            Article gala = new Article(UUID.randomUUID(), "Яблоки \"Гала\"", "Еще одна из многих разновидностей яблок");
            Article antonovka = new Article(UUID.randomUUID(), "Яблоки \"Антоновка\"", "Одна из многих разновидностей яблок");

            articles.put(UUID.randomUUID(), miniBananas);
            articles.put(UUID.randomUUID(), tomato);
            articles.put(UUID.randomUUID(), strawberry);
            articles.put(UUID.randomUUID(), gala);
            articles.put(UUID.randomUUID(), antonovka);


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Проверка выполнена");
        }
    }

}
