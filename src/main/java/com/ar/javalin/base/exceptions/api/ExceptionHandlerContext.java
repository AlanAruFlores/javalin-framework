package com.ar.javalin.base.exceptions.api;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerContext {
    private final Map<Class<? extends Exception>, ExceptionHandlerStrategy<? extends Exception>> strategies = new HashMap<>();


    public ExceptionHandlerContext(){
    }

    public <T extends Exception> void registerStrategy(Class<T> exceptionClass, ExceptionHandlerStrategy<T> strategy) {
        strategies.put(exceptionClass, strategy);
    }

    @SuppressWarnings("unchecked")
    public void handle(Exception exception, io.javalin.http.Context ctx) {
        ExceptionHandlerStrategy strategy = strategies.get(exception.getClass());
        if (strategy != null) {
            strategy.handle(exception, ctx);
        } else {
            defaultStrategy.handle(exception, ctx);
        }
    }
}
