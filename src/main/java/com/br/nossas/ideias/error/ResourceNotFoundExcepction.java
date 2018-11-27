package com.br.nossas.ideias.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepction extends RuntimeException {

    public ResourceNotFoundExcepction(String message) {
        super(message);
    }
}
