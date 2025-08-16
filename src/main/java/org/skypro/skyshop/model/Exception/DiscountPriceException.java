package org.skypro.skyshop.model.Exception;

public class DiscountPriceException extends Exception {
    public DiscountPriceException() {
        super();
    }

    public DiscountPriceException(String message) {
        super(message);
    }

    public DiscountPriceException(String message, Throwable t) {
        super(message, t);
    }

    public DiscountPriceException(Throwable t) {
        super(t);
    }


}
