package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

public class TransactionManager extends BaseManager{
    private static final Logger logger = LogManager.getLogger(TransactionManager.class);

    public static void saveTransaction(Transaction transaction) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        logger.info("Nouvelle transaction : " + transaction.toString());
    }
}
