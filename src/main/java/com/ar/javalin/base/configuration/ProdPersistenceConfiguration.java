package com.ar.javalin.base.configuration;

import com.ar.javalin.base.utils.annotations.Profile;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

@Profile("prod")
@Slf4j
@Getter
public class ProdPersistenceConfiguration implements PersistenceConfiguration{

    @Override
    public EntityManagerFactory configure(){
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

        return new HibernatePersistenceProvider().createEntityManagerFactory("prod", settings);
    }
}
