package com.ar.javalin.base.exceptions.api;
import java.util.HashMap;
import java.util.Map;
import com.ar.javalin.base.exceptions.api.exceptions.InternalServerException;
import com.ar.javalin.base.exceptions.api.exceptions.NotFoundException;
import io.javalin.http.Context;

public class ExceptionHandlerContext {
    private final Map<Class<? extends Exception>, ExceptionHandlerStrategy<? extends Exception>> strategies = new HashMap<>();

    public ExceptionHandlerContext(){
        registerStrategy(IllegalArgumentException.class, new IllegalArgumentExceptionStrategy());
        registerStrategy(NotFoundException.class, new NotFoundExceptionStrategy());
        registerStrategy(InternalServerException.class, new InternalServerExceptionStrategy());
    }


    public <T extends Exception> void registerStrategy(Class<T> exceptionClass, ExceptionHandlerStrategy<T> strategy) {
        strategies.put(exceptionClass, strategy);
    }

    @SuppressWarnings("unchecked")
    public void handle(Exception exception, Context ctx) {
        ExceptionHandlerStrategy strategy = strategies.get(exception.getClass());
        strategy.handle(exception,ctx);
    }

    
    public static ExceptionHandlerContext newInstance(){
        return new ExceptionHandlerContext();
    }
}
