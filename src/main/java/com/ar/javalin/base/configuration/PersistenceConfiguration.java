package com.ar.javalin.base.configuration;

import jakarta.persistence.EntityManagerFactory;

public interface PersistenceConfiguration {
    EntityManagerFactory configure();
}