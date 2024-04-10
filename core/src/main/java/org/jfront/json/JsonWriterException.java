package org.jfront.json;

public class JsonWriterException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    JsonWriterException(String message) {
        super(message);
    }

    JsonWriterException(Throwable t) {
        super(t);
    }
}
