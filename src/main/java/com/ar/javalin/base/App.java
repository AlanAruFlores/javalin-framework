package com.ar.javalin.base;

import java.io.IOException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ar.javalin.base.models.Item;
import com.ar.javalin.base.settings.ApplicationSettings;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public final class App {

    private static final Logger LOGGER;
    private final Javalin app;
    private final ApplicationSettings settings;
    static {
        LOGGER = LoggerFactory.getLogger(App.class);
    }

    @Inject
    public App(JavalinFactory javalinFactory, ApplicationSettings settings) {
        this.app = javalinFactory.create();
        this.settings = settings;
    }

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(Item.builder().description("init").build());
        em.getTransaction().commit();
        em.close();
        try{
            Injector injector = Guice.createInjector(new AppModule());
            App app = injector.getInstance(App.class);
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