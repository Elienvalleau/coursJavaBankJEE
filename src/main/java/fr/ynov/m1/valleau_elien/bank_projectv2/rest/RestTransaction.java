package fr.ynov.m1.valleau_elien.bank_projectv2.rest;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.CompteManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.TransactionManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/newTransaction")
public class RestTransaction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> l = new ArrayList<String>(req.getParameterMap().keySet());
        for (String json : l){
            JSONObject jsonObject = new JSONObject(json);
            Transaction transaction = new Transaction();
            transaction.setMontant(jsonObject.getFloat("montant"));
            transaction.setDate(new Date());
            transaction.setLibelle(jsonObject.getString("label"));
            transaction.setCpt_source(jsonObject.getInt("idSource"));
            transaction.setCpt_dest(jsonObject.getInt("idDest"));
            Compte compte = CompteManager.getCompteById(jsonObject.getInt("idSource"));
            transaction.setLeSuperCompte(compte);
            TransactionManager.saveTransaction(transaction);
        }
    }
}
