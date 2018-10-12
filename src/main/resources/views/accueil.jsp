<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Accueil</title>
</head>
<body>
    <h1>Bienvenue sur votre banque</h1>
    <%=request.getAttribute("date")%>

    <form method="post" action="${pageContext.request.contextPath}/accueil">
        <p>
            <label for="login">Entrez votre identifiant :</label><br />
            <input type="text" name="login" id="login" required/>
        </p>
        <p>
            <label for="password">Entrez votre mot-de-passe :</label><br />
            <input type="password" name="password" id="password" required/>
        </p>
        <p>
            <input type="submit" value="Valider" />
        </p>
    </form>

</body>
</html>