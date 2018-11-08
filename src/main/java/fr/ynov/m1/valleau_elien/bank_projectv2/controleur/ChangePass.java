package fr.ynov.m1.valleau_elien.bank_projectv2.controleur;

import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.PasswordManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.Managers.UtilisateurManager;
import fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changePass")
public class ChangePass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/changePass.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");

        if (oldPassword.equals(utilisateur.getPassword())){
            String checkPassword = PasswordManager.checkPass(password);
            if (checkPassword.equals("passed")){
                if(password.equals(confirmPassword)){
                    utilisateur.setPassword(password);
                    PasswordManager.updatePass(utilisateur);
                    Utilisateur utilisateur2 = UtilisateurManager.loadUtilisateurById(utilisateur.getId_utilisateur());
                    request.getSession().setAttribute("utilisateur", utilisateur2);
                    request.getSession().setMaxInactiveInterval(120);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/changePass.jsp");
                    request.setAttribute("confirmMsg", "okChangePass");
                    dispatcher.forward(request, response);
                }
                else{
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/changePass.jsp");
                    request.setAttribute("errorMsg", "passNotMatch");
                    dispatcher.forward(request, response);
                }
            }
            else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/changePass.jsp");
                request.setAttribute("errorMsgPassword", checkPassword);
                dispatcher.forward(request, response);
            }
        }
        else{
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/classes/WEB-INF/views/changePass.jsp");
            request.setAttribute("errorMsgOldPass", "oldPassNotMatch");
            dispatcher.forward(request, response);
        }
    }
}
