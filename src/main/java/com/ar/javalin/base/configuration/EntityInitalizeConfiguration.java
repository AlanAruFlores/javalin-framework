package com.ar.javalin.base.configuration;

import com.ar.javalin.base.models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class EntityInitalizeConfiguration {

    public void initEntityConfiguration(EntityManagerFactory emf){
        try{
            log.info("Initializing Entity Configuration");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            initCreatingEntities(em);
            em.getTransaction().commit();
            em.close();
            log.info("Succesfully Entity Configuration");
        }catch(Exception ex){
            log.error("Error initializing entities: {}", ex.getMessage());
        }

    }

    private void initCreatingEntities(EntityManager em) {
        em.persist(Item.builder().description("init").build());
    }
}
