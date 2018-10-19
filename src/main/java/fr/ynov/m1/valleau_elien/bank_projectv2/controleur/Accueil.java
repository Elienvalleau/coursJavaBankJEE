package fr.ynov.m1.valleau_elien.bank_projectv2.controleur;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.UtilisateurManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class Accueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        String today = date.toString();
        request.setAttribute( "date", today );
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/views/accueil.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/views/accueil.jsp");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Utilisateur utilisateur = UtilisateurManager.loadUtilisateurByLoginAndPassword(login, password);
        if (utilisateur == null) {
            request.setAttribute("errorMsg", "errUserMdp");
            Date date = new Date();
            String today = date.toString();
            request.setAttribute( "date", today );
            dispatcher.forward(request, response);
        } else  {
            request.getSession().setAttribute("utilisateur", utilisateur);
            request.getSession().setMaxInactiveInterval(120);
            response.sendRedirect(request.getContextPath() + "/listeComptes");
        }
    }
}
