package org.practise.chapter2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

public class CustomerManager {

    private final Logger logger = LoggerFactory.getLogger(CustomerManager.class);

    private EntityManagerFactory managerFactory;
    private EntityManager entityManager;

    public void init() {
        managerFactory = Persistence.createEntityManagerFactory("UnitCustomer", new HashMap());
        logger.info(managerFactory.getClass().getSimpleName());
        entityManager = managerFactory.createEntityManager();
        logger.info(entityManager.getClass().getSimpleName());
    }
}
