<%@ page import="fr.ynov.m1.valleau_elien.bank_projectv2.modele.Utilisateur" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Liste des comptes</title>
</head>
<body>
    <p><c:out value="Bonjour monsieur ${utilisateur.getPrenom()} ${utilisateur.getNom()}" /></p>
    <h2> Liste des comptes</h2>
    <c:forEach items="${utilisateur.getComptes()}" var="compte">
        ${compte.getTypecpt()} <button><a href="www.google.com">Voir plus</a></button> <br>
    </form>
    </c:forEach>
</body>
</html>
