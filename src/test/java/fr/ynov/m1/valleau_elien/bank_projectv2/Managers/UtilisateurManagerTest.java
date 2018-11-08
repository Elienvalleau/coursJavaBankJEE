package fr.ynov.m1.valleau_elien.bank_projectv2.Managers;

import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilisateurManagerTest extends UtilisateurManager{

    @Test
    public final void saveUtilisateurTest() throws ParseException {
        Utilisateur luckyLuke = new Utilisateur();
        luckyLuke.setNom("Luke3");
        luckyLuke.setPrenom("Lucky3");
        luckyLuke.setEmail("luckyluke@jetireplusvitequemonombre.org");
        luckyLuke.setLogin("test3");
        luckyLuke.setPassword("test3");
        luckyLuke.setPhone("06.12.13.14.15");
        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC+2"));
        Date uDate = isoFormat.parse("07-12-1946T09:00:00");
        luckyLuke.setDate_of_birth(uDate);
        luckyLuke.setAddress("loin3");

        saveUtilisateur(luckyLuke);
    }

    @Test
    public final void loadUtilisateurByIdTest() throws ParseException {
        Utilisateur luckyLuke = new Utilisateur();
        luckyLuke.setId_utilisateur(11);
        luckyLuke.setNom("Luke3");
        luckyLuke.setPrenom("Lucky3");
        luckyLuke.setEmail("luckyluke@jetireplusvitequemonombre.org");
        luckyLuke.setLogin("test3");
        luckyLuke.setPassword("test3");
        luckyLuke.setPhone("06.12.13.14.15");
        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC+2"));
        Date uDate = isoFormat.parse("07-12-1946T09:00:00");
        luckyLuke.setDate_of_birth(uDate);
        luckyLuke.setAddress("loin3");

        assertEquals(luckyLuke.getNom(), loadUtilisateurById(11).getNom());
    }

    @Test
    public final void loadUtilisateurByLoginAndPasswordTest() throws ParseException {
        Utilisateur luckyLuke = new Utilisateur();
        luckyLuke.setId_utilisateur(18);
        luckyLuke.setNom("Luke3");
        luckyLuke.setPrenom("Lucky3");
        luckyLuke.setEmail("luckyluke@jetireplusvitequemonombre.org");
        luckyLuke.setLogin("test14");
        luckyLuke.setPassword("test14");
        luckyLuke.setPhone("06.12.13.14.15");
        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC+2"));
        Date uDate = isoFormat.parse("07-12-1946T09:00:00");
        luckyLuke.setDate_of_birth(uDate);
        luckyLuke.setAddress("loin3");

        assertEquals(luckyLuke.getPrenom(), loadUtilisateurByLoginAndPassword("test14", "test14").getPrenom());
    }

    @Test
    public final void saveUtilisateurCompteTest() throws ParseException {
        Utilisateur luckyLuke = new Utilisateur();
        Set<Compte> testCompte = new LinkedHashSet<Compte>();
        Compte superCompte = new Compte();
        Set<Transaction> testTransaction = new LinkedHashSet<Transaction>();
        Transaction transaction1 = new Transaction();

        //create user
        luckyLuke.setNom("Lukev2");
        luckyLuke.setPrenom("Luckyv2");
        luckyLuke.setEmail("luckylukev2@jetireplusvitequemonombre.org");
        luckyLuke.setLogin("mySuperLoginv2");
        luckyLuke.setPassword("mySuperPasswordv2");
        luckyLuke.setPhone("06.12.13.14.51");
        luckyLuke.setAddress("encore plus loin");

        //create transaction
        transaction1.setLeSuperCompte(superCompte);
        transaction1.setCpt_dest(123);
        transaction1.setCpt_source(1);
        transaction1.setLibelle("je rends l'argent");
        transaction1.setDate(new Date());
        transaction1.setMontant((float) 2);
        //transaction to list
        testTransaction.add(transaction1);

        //create compte
        superCompte.setSolde((float) 1000);
        superCompte.setDate_creation(new Date());
        superCompte.setTypecpt(1);
        superCompte.setRobert(luckyLuke);
        superCompte.setTransactions(testTransaction);

        //compte to list
        testCompte.add(superCompte);

        luckyLuke.setComptes(testCompte);
        saveUtilisateur(luckyLuke);
    }

}
