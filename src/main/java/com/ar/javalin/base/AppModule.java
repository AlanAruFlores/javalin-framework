package com.ar.javalin.base;

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

        //Settings
        bind(ApplicationSettings.class).to(AppSettings.class);
        bind(AppSettings.class).toProvider(AppSettings::newInstance).in(Scopes.SINGLETON);

    }

}
