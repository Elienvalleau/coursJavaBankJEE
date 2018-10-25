package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager extends BaseManager{

    private static final Logger logger = LogManager.getLogger(UtilisateurManager.class);

    public static void saveUtilisateur(Utilisateur utilisateur) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(utilisateur);
        em.getTransaction().commit();
    }

    public static Utilisateur loadUtilisateurById(Integer utilisateurId) {
        EntityManager em = getEntityManager();
        Utilisateur user = em.find(Utilisateur.class, utilisateurId);
        return user;
    }

    public static Utilisateur loadUtilisateurByLoginAndPassword(String login, String password){
        EntityManager em = getEntityManager();
        TypedQuery<Utilisateur> query = em.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.login='" + login +
                   "'and u.password='" + password + "'", Utilisateur.class);
        if (query.getResultList().isEmpty()){
            return null;
        }
        Utilisateur utilisateur = query.getSingleResult();
//        //pour aller chercher les comptes
//        for(Compte compte:utilisateur.getComptes()){
////            System.out.println(compte.getId_compte());
//            logger.trace(compte.getId_compte());
//
//            for (Transaction transac:compte.getTransactions()){
////                System.out.println(transac.getId_transaction());
//                logger.trace(transac.getId_transaction());
//            }
//        }

//        logger.error("error");

        return utilisateur;
    }

    public static void purgeTable() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Transaction").executeUpdate();
        em.createQuery("delete from Compte").executeUpdate();
        em.createQuery("delete from Utilisateur").executeUpdate();
        em.getTransaction().commit();
    }

    public static void deleteUtilisateur(Utilisateur utilisateur){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(utilisateur);
        em.getTransaction().commit();
    }
}
