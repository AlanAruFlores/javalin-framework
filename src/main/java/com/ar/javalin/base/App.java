package com.ar.javalin.base;

import java.io.IOException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.javalin.base.configuration.H2ConsoleConfiguration;
import com.ar.javalin.base.configuration.PersistenceConfiguration;
import com.ar.javalin.base.settings.ApplicationSettings;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.javalin.Javalin;



public final class App {

    private static final Logger LOGGER;
    private final Javalin app;
    private final ApplicationSettings settings;
    private final H2ConsoleConfiguration h2Configuration;
    private final PersistenceConfiguration persistenceConfiguration;
    static {
        LOGGER = LoggerFactory.getLogger(App.class);
    }

    @Inject
    public App(JavalinFactory javalinFactory, ApplicationSettings settings, H2ConsoleConfiguration h2ConsoleConfiguration, PersistenceConfiguration persistenceConfiguration) { 
        this.app = javalinFactory.create();
        this.settings = settings;
        this.h2Configuration = h2ConsoleConfiguration;
        this.persistenceConfiguration = persistenceConfiguration;
    }

    public static void main(String[] args) throws IOException {
        try{
            Injector injector = Guice.createInjector(new AppModule());
            App app = injector.getInstance(App.class);

            app.persistenceConfiguration.initPersistenceConfiguration();
            app.h2Configuration.startH2Console(String.valueOf(app.settings.getH2Port()));
            app.start();
            LOGGER.info("Javalin application started on port: {}", app.settings.getPort());
        }catch(Exception e) {
            LOGGER.error("Failed to start Javalin application", e);
            throw new IOException("Failed to start Javalin application", e);
        }
    }


    /**
     * Starts the Javalin application on the port specified in the settings.
     */

    public void start(){
        app.start(settings.getPort());
    }

    /**
     * Stops the Javalin application gracefully.
     */

    public void stop(){
        app.stop();
    }

}