package com.ar.javalin.base.exceptions.api;
import io.javalin.http.Context;

public interface ExceptionHandlerStrategy <T extends Exception>{
    void handle(T exception, Context ctx);
}
