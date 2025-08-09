package com.ar.javalin.base.exceptions.api.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Resource not found");
    }
}