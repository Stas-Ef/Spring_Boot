package org.skypro.skyshop.model.Exception;

public class BestResultNotFound extends Exception {

    public BestResultNotFound() {
        super();
    }

    public BestResultNotFound(String message) {
        super(message);
    }

    public BestResultNotFound(String message, Throwable t) {
        super(message, t);
    }


    public BestResultNotFound(Throwable t) {
        super(t);
    }

}

