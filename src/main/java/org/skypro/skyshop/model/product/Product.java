package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.Exception.NameIsBlankException;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String productName;
    private final UUID id;


    public Product(UUID id, String productName) throws NameIsBlankException {

        if (productName == null) {
            throw new IllegalArgumentException("Имя продукта не введено");
        }
        this.productName = productName;

        if (productName.isBlank()) {
            throw new NameIsBlankException("Имя состоит из одних пробелов");
        }
        this.productName = productName;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }


    public abstract double getProductCost();

    public String getproductName() {
        return productName;
    }

    public abstract String toString();

    @JsonIgnore
    public boolean isSpecial() {
        return false;
    }

    public String setproductName() {
        return productName;
    }

    @Override
    public boolean equals(Object obj) {

        Product product = (Product) obj;
        return productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }

    @Override
    public String searchTerm() {
        return productName;
    }

    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }
}



