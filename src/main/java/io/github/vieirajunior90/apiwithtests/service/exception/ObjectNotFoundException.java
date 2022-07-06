package io.github.vieirajunior90.apiwithtests.service.exception;

import java.util.function.Supplier;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
