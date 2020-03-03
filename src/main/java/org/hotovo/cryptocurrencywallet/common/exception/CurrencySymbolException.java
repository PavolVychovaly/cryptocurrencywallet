package org.hotovo.cryptocurrencywallet.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CurrencySymbolException extends RuntimeException {

    public CurrencySymbolException(String message) {
        super(message);
    }
}
