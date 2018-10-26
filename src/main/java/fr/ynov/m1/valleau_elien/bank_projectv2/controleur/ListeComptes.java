package fr.ynov.m1.valleau_elien.bank_projectv2.controleur;

import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.CompteManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.UtilisateurManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Compte;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/listeComptes")
public class ListeComptes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/listeComptes.jsp");
//        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
//        request.setAttribute( "utilisateur", utilisateur );
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer accountType = Integer.valueOf(request.getParameter("accountType"));
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        Compte compte = new Compte();
        compte.setSolde((float) 0);
        compte.setDate_creation(new Date());
        compte.setTypecpt(accountType);
        compte.setRobert(utilisateur);
        CompteManager.saveCompte(compte);

//        Utilisateur utilisateur2 = UtilisateurManager.loadUtilisateurByLoginAndPassword(utilisateur.getLogin(), utilisateur.getPassword());
        Utilisateur utilisateur2 = UtilisateurManager.loadUtilisateurById(utilisateur.getId_utilisateur());
        request.getSession().setAttribute("utilisateur", utilisateur2);
        request.getSession().setMaxInactiveInterval(120);

        response.sendRedirect(request.getContextPath() + "/listeComptes");
    }
}
