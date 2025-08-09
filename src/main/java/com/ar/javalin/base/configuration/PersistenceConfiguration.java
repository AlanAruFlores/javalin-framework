package com.ar.javalin.base.configuration;

import com.ar.javalin.base.exceptions.app.exceptions.DatabaseInitializeException;
import com.ar.javalin.base.models.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Getter
public class PersistenceConfiguration{
    private EntityManagerFactory emf;

    public void startPersistenceConfiguration(){
        String profile = System.getenv("JPA_PROFILE");
        try{
            log.info("Initializing Persistence Configuration on: {}", profile);
            switch (profile){
                case "development":
                    initLocalPersistenceConfig();
                    break;
                case "production":
                    initProdPersistenceConfig();
                    break;
                default:
                    throw new DatabaseInitializeException("Error: Cannot Initialize the persistence configuration");
            }
            log.info("Configuration to persistence completed succesfully");
        }catch (Exception ex){
            log.error("Error initializing persistence: {}", ex.getMessage());
        }
    }

    private void initProdPersistenceConfig(){
        String host = System.getenv("GCP_DB_HOST");
        String port = System.getenv("GCP_DB_PORT");
        String user = System.getenv("GCP_DB_USER");
        String password = System.getenv("GCP_DB_PASSWORD");
        String name = System.getenv("GCP_DB_NAME");

        Map<String, String> settings = new HashMap<>();
        settings.put("jakarta.persistence.jdbc.url", "jdbc:mysql://" + host + ":" + port + "/" + name + "?useSSL=false&serverTimezone=UTC");
        settings.put("jakarta.persistence.jdbc.user", user);
        settings.put("jakarta.persistence.jdbc.password", password);
        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.hbm2ddl.auto", "create-drop");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        settings.put("hibernate.archive.autodetection", "class");

        settings.put("hibernate.hikari.connectionTimeout", "20000");
        settings.put("hibernate.hikari.maximumPoolSize", "10");
        settings.put("hibernate.hikari.minimumIdle", "5");
        settings.put("hibernate.hikari.idleTimeout", "300000");
        settings.put("hibernate.hikari.maxLifetime", "1200000");

        settings.put("hibernate.connection.characterEncoding", "utf8");
        settings.put("hibernate.connection.useUnicode", "true");
        settings.put("hibernate.connection.autoReconnect", "true");

        emf = new HibernatePersistenceProvider().createEntityManagerFactory("production", settings);
    }

    private void initLocalPersistenceConfig(){
        Map<String, String> settings = new HashMap<>();
        settings.put("jakarta.persistence.jdbc.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        settings.put("jakarta.persistence.jdbc.user", "sa");
        settings.put("jakarta.persistence.jdbc.password", "");
        settings.put("jakarta.persistence.jdbc.driver", "org.h2.Driver");

        settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.hbm2ddl.auto", "create-drop");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        settings.put("hibernate.archive.autodetection", "class");

        emf =  new HibernatePersistenceProvider().createEntityManagerFactory("development", settings);
    }

}