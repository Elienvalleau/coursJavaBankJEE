package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CompteManager extends BaseManager{
    private static final Logger logger = LogManager.getLogger(CompteManager.class);

    public static void saveCompte(Compte compte) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(compte);
        em.getTransaction().commit();
        logger.debug(compte);
    }

    public static void updateCompte(Compte compte) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(compte);
        em.getTransaction().commit();
        logger.debug(compte);
    }

    public static Compte getCompteById(Integer idCompte) {
        EntityManager em = getEntityManager();
        TypedQuery<Compte> query = em.createQuery(
                "SELECT c FROM Compte c WHERE c.id_compte=" + idCompte, Compte.class);
        if (query.getResultList().isEmpty()){
            return null;
        }
        Compte compte = query.getSingleResult();
        return compte;
    }
}
