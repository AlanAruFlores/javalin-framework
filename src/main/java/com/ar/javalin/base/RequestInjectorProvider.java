package com.ar.javalin.base;

import com.google.inject.Injector;

import javax.inject.Inject;
import javax.inject.Provider;

public final class RequestInjectorProvider implements Provider<GuiceInjector> {
    
    private final Injector injector;
    private final RequestModule module;

    @Inject
    public RequestInjectorProvider(Injector injector) {
        this.injector = injector;
        this.module = new RequestModule();
    }

    @Override
    public GuiceInjector get() {
        Injector requestInjector = injector.createChildInjector(module);
        return new GuiceInjector(requestInjector);
    }


}
