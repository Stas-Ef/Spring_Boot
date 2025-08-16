package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.Exception.NameIsBlankException;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 100;

    public FixPriceProduct(UUID id, String productName) throws NameIsBlankException {
        super(id, productName);

    }


    @Override
    public double getProductCost() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return getproductName() + " с фиксированной ценой: Фиксированная цена " + getProductCost();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
