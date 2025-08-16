package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.Exception.NameIsBlankException;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int productCoast;

    public SimpleProduct(UUID id, String productName, int productCoast) throws IllegalArgumentException, NameIsBlankException {
        super(id, productName);
        if (productCoast < 1) {
            throw new IllegalArgumentException("Введена неверная цена продукта " + productName + " Введенная цена: " + productCoast);
        }
        this.productCoast = productCoast;
    }



    @Override
    public double getProductCost() {
        return productCoast;
    }

    @Override
    public String toString() {
        return getproductName() + ": " + getProductCost();
    }


}
