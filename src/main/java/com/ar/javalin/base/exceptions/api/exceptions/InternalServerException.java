package com.ar.javalin.base.exceptions.api.exceptions;

public class InternalServerException extends Exception{
    public InternalServerException() {
        super("Internal server error");
    }
}
