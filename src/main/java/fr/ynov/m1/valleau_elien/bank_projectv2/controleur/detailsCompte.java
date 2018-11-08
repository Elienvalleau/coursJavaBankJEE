package fr.ynov.m1.valleau_elien.bank_projectv2.controleur;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.CompteManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.UtilisateurManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

@WebServlet("/detailsCompte")
public class DetailsCompte extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
//        request.setAttribute( "utilisateur", utilisateur );

        String idCompte = request.getParameter("idCompte");
//        URL url = new URL( "http://"+request.getContextPath() + "/solde?id=" + idCompte);
//        System.out.println(url);

        URL url = new URL( "http://localhost:8080/solde?id=" + idCompte);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String str = "";
        while (null != (str = br.readLine())) {
            JSONObject jsonObject = new JSONObject(str);
            String solde = (String) jsonObject.get("solde");
            request.setAttribute( "solde", solde );
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/detailsCompte.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Float noCheckAmountTransac = Float.valueOf(request.getParameter("amountTransac"));
        Float amountTransac = Math.abs(noCheckAmountTransac);
        Integer idDest = Integer.valueOf(request.getParameter("idDest"));
        Integer idCompte = Integer.valueOf(request.getParameter("idCompte"));
        String label = request.getParameter("labelTransac");

        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        for(Compte compte : utilisateur.getComptes()) {
            if (compte.getId_compte().equals(idCompte)){
                if(amountTransac <= compte.getSolde()) {

                    Compte destCompte = CompteManager.getCompteById(idDest);

                    if(destCompte != null){
                        destCompte.setSolde(destCompte.getSolde() + amountTransac);
                        CompteManager.updateCompte(destCompte);
                        compte.setSolde(compte.getSolde() - amountTransac);
                        CompteManager.updateCompte(compte);

//                        Transaction transaction = new Transaction();
//                        transaction.setMontant(amountTransac);
//                        transaction.setDate(new Date());
//                        transaction.setLibelle(label);
//                        transaction.setCpt_source(idCompte);
//                        transaction.setCpt_dest(idDest);
//                        transaction.setLeSuperCompte(compte);
//
//                        TransactionManager.saveTransaction(transaction);

                        JsonObject jsonObject = Json.createObjectBuilder()
                                .add("montant", amountTransac)
                                .add("date", String.valueOf(new Date()))
                                .add("label", label)
                                .add("idSource", idCompte)
                                .add("idDest", idDest)
                                .add("compteAsso", compte.getId_compte())
                                .build();
                        HttpClient httpClient = HttpClientBuilder.create().build();
                        HttpPost req = new HttpPost("http://localhost:8080/newTransaction");
                        StringEntity entity = new StringEntity(String.valueOf(jsonObject), ContentType.APPLICATION_FORM_URLENCODED);
                        req.setEntity(entity);
                        HttpResponse res = httpClient.execute(req);
                        System.out.println(res.getStatusLine().getStatusCode());

                        Utilisateur utilisateur2 = UtilisateurManager.loadUtilisateurById(utilisateur.getId_utilisateur());
//                        compte.getSolde();
//                        compte.getTransactions();

                        request.getSession().setAttribute("utilisateur", utilisateur2);
                        request.getSession().setMaxInactiveInterval(120);

                        response.sendRedirect(request.getContextPath() + "/detailsCompte?idCompte=" + idCompte);
                    }
                    else{
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/detailsCompte.jsp");
                        request.setAttribute("errorMsgDest", "errorDest");
                        dispatcher.forward(request, response);
                    }

                }
                else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/detailsCompte.jsp");
                    request.setAttribute("errorMsgFunds", "insufficientFunds");
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
