package org.skypro.skyshop.model.Exception;

public class NameIsBlankException extends Exception {

    public NameIsBlankException() {
        super();
    }

    public NameIsBlankException(String message) {
        super(message);
    }

    public NameIsBlankException(String message, Throwable t) {
        super(message, t);
    }

    public NameIsBlankException(Throwable t) {
        super(t);
    }


}

