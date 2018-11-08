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
                            logger.error("error check password : errAccent");
                            return "errAccent";
                        }
                        else {
                            logger.info("ok password");
                            return "passed";
                        }
                    }
                    else{
                        logger.error("error check password : errSpe");
                        return "errSpe";
                    }
                }
                else {
                    logger.error("error check password : errMaj");
                    return "errMaj";
                }
            }
            else {
                logger.error("error check password : errNumber");
                return "errNumber";
            }
        }
        else {
            logger.error("error check password : errChar");
            return "errChar";
        }
    }

    public static void updatePass(Utilisateur utilisateur) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(utilisateur);
        em.getTransaction().commit();
        logger.info(em);
    }
}
