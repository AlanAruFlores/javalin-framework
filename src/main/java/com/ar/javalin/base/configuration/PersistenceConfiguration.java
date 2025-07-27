package com.ar.javalin.base.configuration;

import com.ar.javalin.base.models.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class PersistenceConfiguration{
    private EntityManagerFactory emf;

    public PersistenceConfiguration(){
        emf = Persistence.createEntityManagerFactory("default");
    }

    public void initPersistenceConfiguration(){
        log.info("Initializing Persistence Configuration");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        initCreatingEntities(em);
        em.getTransaction().commit();
        em.close();
        log.info("Persistence Configuration initialized successfully");
       }   

    private void initCreatingEntities(EntityManager em) {
        em.persist(Item.builder().description("init").build());
    }
}