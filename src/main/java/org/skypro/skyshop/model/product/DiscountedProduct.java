package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.Exception.DiscountPriceException;
import org.skypro.skyshop.model.Exception.NameIsBlankException;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basicCoast;
    private int discount;

    public DiscountedProduct(UUID id, String productName, int basicCoast, int discount) throws DiscountPriceException, IllegalArgumentException, NameIsBlankException {

        super(id, productName);
        if (basicCoast < 1) {
            throw new IllegalArgumentException("Введена неверная цена продукта " + productName + " Введенная цена: " + basicCoast);
        }
        this.basicCoast = basicCoast;
        if (discount < 0 || discount > 100) {
            throw new DiscountPriceException("Введена неверная скидка продукта " + productName + " Введенная скидка " + discount);
        }
        this.discount = discount;
    }


    @Override
    public double getProductCost() {
        return basicCoast * (1 - discount / 100.0);
    }

    @Override
    public String toString() {
        return getproductName() + " со скидкой: " + getProductCost() + " (" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


}
