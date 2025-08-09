package com.ar.javalin.base.exceptions.app.exceptions;

public class DatabaseInitializeException extends RuntimeException{
    public DatabaseInitializeException(String message){
        super(message);
    }
}
