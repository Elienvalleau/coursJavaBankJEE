package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UtilisateurManager extends BaseManager{

    private static final Logger logger = LogManager.getLogger(UtilisateurManager.class);

    public static void saveUtilisateur(Utilisateur utilisateur) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(utilisateur);
        logger.info("Nouvel utilisateur : " + utilisateur.toString());
        em.getTransaction().commit();
    }

    public static Utilisateur loadUtilisateurById(Integer utilisateurId) {
        EntityManager em = getEntityManager();
        Utilisateur user = em.find(Utilisateur.class, utilisateurId);
        logger.info("Utilisateur chargé : " + utilisateurId.toString());
        return user;
    }

    public static Utilisateur loadUtilisateurByLoginAndPassword(String login, String password){
        EntityManager em = getEntityManager();
        TypedQuery<Utilisateur> query = em.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.login='" + login +
                   "'and u.password='" + password + "'", Utilisateur.class);
        if (query.getResultList().isEmpty()){
            logger.error("Utilisateur introuvable : " + login);
            return null;
        }
        Utilisateur utilisateur = query.getSingleResult();

        logger.info("Utilisateur chargé : " + utilisateur.toString());

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
        logger.info("Utilisateur supprimé : " + utilisateur.toString());
        em.getTransaction().commit();
    }
}
