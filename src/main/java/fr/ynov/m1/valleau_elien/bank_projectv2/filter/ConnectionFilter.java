package fr.ynov.m1.valleau_elien.bank_projectv2.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/accueil";
        String restUrl = request.getContextPath() + "/solde";
        String restUrlTransaction = request.getContextPath() + "/newTransaction";

        boolean loggedIn = session != null && session.getAttribute("utilisateur") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean restSolde = request.getRequestURI().equals(restUrl);
        boolean restTransaction = request.getRequestURI().equals(restUrlTransaction);

        if (loggedIn || loginRequest || restSolde || restTransaction) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy(){}
}
