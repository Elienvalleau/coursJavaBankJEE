package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;

public class PasswordManager extends BaseManager{
    private static final Logger logger = LogManager.getLogger(UtilisateurManager.class);

    public static String checkPass(String password){
        if(password.length() > 8){
            if(password.matches(".*[0-9].*")){
                if(password.matches("(.*[A-Z].*)")) {
                    if(password.matches("(.*[^ \\w].*)")) {
                        if(password.matches("(.*[À-ÿ].*)")) {
                            logger.debug("errAccent");
                            return "errAccent";
                        }
                        else {
                            return "passed";
                        }
                    }
                    else{
                        logger.debug("errSpe");
                        return "errSpe";
                    }
                }
                else {
                    logger.debug("errMaj");
                    return "errMaj";
                }
            }
            else {
                logger.debug("errNumber");
                return "errNumber";
            }
        }
        else {
            logger.debug("errChar");
            return "errChar";
        }
    }

    public static void updatePass(Utilisateur utilisateur) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(utilisateur);
        em.getTransaction().commit();
        logger.debug(utilisateur);
    }
}
