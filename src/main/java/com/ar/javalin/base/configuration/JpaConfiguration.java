package com.ar.javalin.base.configuration;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;

import com.ar.javalin.base.settings.AppSettings;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

public final class JpaConfiguration {
    private final EntityManagerFactory emf;


    @Inject
    public JpaConfiguration(AppSettings appSettings) {
        this.emf = buildEntityManagerFactory(appSettings);
    }

    private EntityManagerFactory buildEntityManagerFactory(AppSettings appSettings) {
        Map<String, Object> settings = new HashMap<>();
        settings.put(AvailableSettings.JAKARTA_JDBC_URL, appSettings.getDbUrl());
        settings.put(AvailableSettings.JAKARTA_JDBC_USER, appSettings.getDbUser());
        settings.put(AvailableSettings.JAKARTA_JDBC_PASSWORD, appSettings.getDbPassword());
        settings.put(AvailableSettings.JAKARTA_JDBC_DRIVER, appSettings.getDbDriver());
        settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
        settings.put(AvailableSettings.SHOW_SQL, "true");
    
        // Pool de conexiones con HikariCP
        settings.put("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        settings.put("hibernate.hikari.maximumPoolSize", "10");
        settings.put("hibernate.hikari.minimumIdle", "2");
        settings.put("hibernate.hikari.idleTimeout", "30000");
        settings.put("hibernate.hikari.poolName", "JavalinHikariCP");

        return new HibernatePersistenceProvider()
                .createEntityManagerFactory("default", settings);
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
