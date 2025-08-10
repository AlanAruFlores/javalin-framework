package com.ar.javalin.base.configuration;

import com.ar.javalin.base.utils.annotations.Profile;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.Set;

@Slf4j
public class PersistenceLoadConfiguration{

    public EntityManagerFactory load() {
        String activeProfile = System.getenv("PROFILE");
        Reflections reflections = new Reflections("com.ar.javalin.base.configuration");

        Set<Class<? extends PersistenceConfiguration>> configs = reflections.getSubTypesOf(PersistenceConfiguration.class);

        for (Class<? extends PersistenceConfiguration> configClass : configs) {
            Profile profile = configClass.getAnnotation(Profile.class);
            if (profile != null && profile.value().equals(activeProfile)) {
                try {
                    log.info("Loading persistence to profile: {}", activeProfile);
                    PersistenceConfiguration configInstance = configClass.getDeclaredConstructor().newInstance();
                    EntityManagerFactory emf = configInstance.configure();
                    log.info("Persistence loaded successfully");
                    return emf;
                } catch (Exception e) {
                    log.error("Error to load the persistence: {}", e.getMessage());
                }
            }
        }
        return null;
    }
}
