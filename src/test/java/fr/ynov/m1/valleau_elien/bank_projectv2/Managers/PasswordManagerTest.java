package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordManagerTest extends UtilisateurManager{

    @Test
    public final void charaTestKO() {
        String password = "No7!";
        String checked = PasswordManager.checkPass(password);
        assertEquals("errChar", checked);
    }

    @Test
    public final void numberTestKO() {
        String password = "Nouveaumotdepasse!";
        String checked = PasswordManager.checkPass(password);
        assertEquals("errNumber", checked);
    }

    @Test
    public final void majTestKO() {
        String password = "nouveaumotdepasse7!";
        String checked = PasswordManager.checkPass(password);
        assertEquals("errMaj", checked);
    }

    @Test
    public final void speTestKO() {
        String password = "Nouveaumotdepasse7";
        String checked = PasswordManager.checkPass(password);
        assertEquals("errSpe", checked);
    }

    @Test
    public final void accentTestKO() {
        String password = "Nouveaumotdepasse7!é";
        String checked = PasswordManager.checkPass(password);
        assertEquals("errAccent", checked);
    }

    @Test
    public final void passedTest() {
        String password = "Nouveaumotdepasse7!";
        String checked = PasswordManager.checkPass(password);
        assertEquals("passed", checked);
    }

    @Test
    public final void updatePassTest() {
        Utilisateur utilisateur = loadUtilisateurById(6);
        utilisateur.setPassword("Nouveaumotdepasse7!");

        PasswordManager.updatePass(utilisateur);

        Utilisateur utilisateur2 = loadUtilisateurById(6);
        assertEquals("Nouveaumotdepasse7!", utilisateur2.getPassword());
    }

}
