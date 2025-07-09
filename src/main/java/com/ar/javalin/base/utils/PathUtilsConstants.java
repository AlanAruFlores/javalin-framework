package com.ar.javalin.base.utils;

import com.ar.javalin.base.AppModule;
import com.ar.javalin.base.settings.AppSettings;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.experimental.UtilityClass;


@UtilityClass
public final class PathUtilsConstants {

    public static String WEB_RESOURCE_PATH;

    static {
        loadResources();
    }

    private static void loadResources(){
        Injector injector = Guice.createInjector(new AppModule());
        AppSettings settings =  injector.getInstance(AppSettings.class);

        WEB_RESOURCE_PATH = settings.getHtmlResourcePath();
    }

}
