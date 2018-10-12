package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//à recup avec Maven
//Logger logger = LogManager.getLogger(LoginServlet.class);

public class UtilisateurManager extends BaseManager{

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
        Utilisateur utilisateur = query.getSingleResult();

        //pour aller chercher les comptes
        for(Compte compte:utilisateur.getComptes()){
            System.out.println(compte.getId_compte());

            for (Transaction transac:compte.getTransactions()){
                System.out.println(transac.getId_transaction());
            }
        }

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
