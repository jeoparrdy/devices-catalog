package org.globaltest.registry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such configuration. Please add configuration for the device")
public class ConfigNotFoundException extends RuntimeException{
    public ConfigNotFoundException(String message) {
        super(message);
    }
}
