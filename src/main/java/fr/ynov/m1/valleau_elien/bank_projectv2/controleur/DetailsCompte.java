package fr.ynov.m1.valleau_elien.bank_projectv2.controleur;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.CompteManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.TransactionManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.UtilisateurManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Transaction;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/detailsCompte")
public class DetailsCompte extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/views/detailsCompte.jsp");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        request.setAttribute( "utilisateur", utilisateur );
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Float amountTransac = Float.valueOf(request.getParameter("amountTransac"));
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

                        Transaction transaction = new Transaction();
                        transaction.setMontant(amountTransac);
                        transaction.setDate(new Date());
                        transaction.setLibelle(label);
                        transaction.setCpt_source(idCompte);
                        transaction.setCpt_dest(idDest);
                        transaction.setLeSuperCompte(compte);
                        TransactionManager.saveTransaction(transaction);

                        Utilisateur utilisateur2 = UtilisateurManager.loadUtilisateurByLoginAndPassword(utilisateur.getLogin(), utilisateur.getPassword());
                        request.getSession().setAttribute("utilisateur", utilisateur2);
                        request.getSession().setMaxInactiveInterval(120);

                        response.sendRedirect(request.getContextPath() + "/detailsCompte?idCompte=" + idCompte);
                    }
                    else{
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/views/detailsCompte.jsp");
                        request.setAttribute("errorMsgDest", "errorDest");
                        dispatcher.forward(request, response);
                    }

                }
                else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/views/detailsCompte.jsp");
                    request.setAttribute("errorMsgFunds", "insufficientFunds");
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
