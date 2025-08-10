package com.ar.javalin.base;

import com.ar.javalin.base.configuration.EntityInitalizeConfiguration;
import com.ar.javalin.base.configuration.H2ConsoleConfiguration;
import com.ar.javalin.base.configuration.PersistenceConfiguration;
import com.ar.javalin.base.configuration.PersistenceLoadConfiguration;
import com.ar.javalin.base.exceptions.api.ExceptionHandlerContext;
import com.ar.javalin.base.settings.AppSettings;
import com.ar.javalin.base.settings.ApplicationSettings;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.truncon.javalin.mvc.api.Injector;

public final class AppModule extends AbstractModule{
    
    @Override
    protected void configure() {
        super.configure();
        bind(Injector.class).toProvider(RequestInjectorProvider.class);
        bind(JavalinFactory.class);
        bind(App.class);
        bind(PersistenceLoadConfiguration.class).in(Scopes.SINGLETON);
        bind(EntityInitalizeConfiguration.class).in(Scopes.SINGLETON);
        bind(H2ConsoleConfiguration.class).in(Scopes.SINGLETON);

        //Settings
        bind(ApplicationSettings.class).to(AppSettings.class);
        bind(AppSettings.class).toProvider(AppSettings::newInstance).in(Scopes.SINGLETON);

        //Exception Handler
        bind(ExceptionHandlerContext.class).toProvider(ExceptionHandlerContext::newInstance).in(Scopes.SINGLETON);
    }

}
