package com.ar.javalin.base.configuration;

import com.ar.javalin.base.utils.annotations.Profile;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

@Profile("dev")
@Getter
@Slf4j
public class LocalPersistenceConfiguration implements PersistenceConfiguration{

    @Override
    public EntityManagerFactory configure(){
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

        return new HibernatePersistenceProvider().createEntityManagerFactory("dev", settings);
    }
}
