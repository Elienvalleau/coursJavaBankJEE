<%@include file="includes/include.jsp"%>
<!doctype html>
<html lang="${param.lang}">
<c:if test="${param.lang!=null}">
    <fmt:setLocale value = "${param.lang}" scope="session"/>
</c:if>
<fmt:setBundle basename = "langues.bank"/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key = "list_account"/></title>
</head>
<body>
    <p><fmt:message key = "welcome_msg"/><c:out value=" ${utilisateur.getPrenom()} ${utilisateur.getNom()}" /></p>
    <h2><fmt:message key = "list_account"/></h2>
    <c:forEach items="${utilisateur.getComptes()}" var="compte">
        ${compte.getTypecpt()} <button><a href="/detailsCompte?idCompte=${compte.getId_compte()}"><fmt:message key = "see_more"/></a></button> <br>
    </c:forEach>
</body>
<br>
<li><a href="?lang=en"><fmt:message key="lang.en" /></a></li>
<li><a href="?lang=fr"><fmt:message key="lang.fr" /></a></li>
</html>
