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
    <title><fmt:message key = "account_details"/></title>
</head>
<body>
    <h2><fmt:message key = "account_details"/></h2>

    <c:forEach items="${utilisateur.getComptes()}" var="compte">
        <c:if test="${param.idCompte==compte.getId_compte()}">
            ${compte.getTypecpt()} <br>
            ${compte.getSolde()} <br>
            ${compte.getDate_creation()} <br>
            <br>
            <c:forEach items="${compte.getTransactions()}" var="transaction">
                ${transaction.getLibelle()} <button><a href="/detailsTransaction?idTransac=${transaction.getId_transaction()}"><fmt:message key = "see_more"/></a></button> <br>
            </c:forEach>
        </c:if>
    </c:forEach>

</body>
<br>
<li><a href="?idCompte=${param.idCompte}&lang=en"><fmt:message key="lang.en" /></a></li>
<li><a href="?idCompte=${param.idCompte}&lang=fr"><fmt:message key="lang.fr" /></a></li>
</html>