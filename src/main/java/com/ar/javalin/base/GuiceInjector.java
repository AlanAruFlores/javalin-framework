package com.ar.javalin.base;

import javax.inject.Inject;

import com.google.inject.Injector;

/**
 * GuiceInjector is a class that implements the Injector interface
 * It is used to inject dependencies into the application
 */
public final class GuiceInjector implements com.truncon.javalin.mvc.api.Injector{

    private final Injector injector;

    @Inject
    public GuiceInjector(Injector injector){
        this.injector = injector;
    }

    
    @Override
    public <T> T getInstance(Class<T> clz) {
        return injector.getInstance(clz);
    }

    @Override
    public Object getHandle() {
        return injector;
    }

}