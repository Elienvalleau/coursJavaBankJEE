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
            <a href="/listeComptes"><-----</a> <br>
            <fmt:message key="account.${compte.getTypecpt()}" /><br>
            ${compte.getSolde()} <fmt:message key = "devise"/><br>
            ${compte.getDate_creation()} <br>
            <br>
            <c:forEach items="${compte.getTransactions()}" var="transaction">
                ${transaction.getLibelle()} <button><a href="/detailsTransaction?idTransac=${transaction.getId_transaction()}"><fmt:message key = "see_more"/></a></button> <br>
            </c:forEach>
        </c:if>
    </c:forEach>
    <br>
    <h3><fmt:message key = "createTransac"/></h3>
    <form method="post" action="${pageContext.request.contextPath}/detailsCompte?idCompte=${param.idCompte}">
        <p>
            <label for="amountTransac"><fmt:message key = "amountTransac"/></label>
            <input type="number" name="amountTransac" id="amountTransac" required/> <fmt:message key = "devise"/> <br>
        </p>
        <p>
            <label for="labelTransac"><fmt:message key = "labelTransac"/></label>
            <input type="text" name="labelTransac" id="labelTransac" required/> <br>
        </p>
        <p>
            <label for="idDest"><fmt:message key = "idDest"/></label>
            <input type="number" name="idDest" id="idDest" required/> <br>
        <input type="hidden" value="${param.idCompte}" name="idCompte" id="idCompte">
        <c:if test="${requestScope.errorMsgDest!=null}">
            <fmt:message key = "${requestScope.errorMsgDest}"/>
        </c:if>
        <c:if test="${requestScope.errorMsgFunds!=null}">
            <fmt:message key = "${requestScope.errorMsgFunds}"/>
        </c:if>
        <br> <input type="submit" value=<fmt:message key = "valid"/> />
    </form>

</body>
<br>
<li><a href="?idCompte=${param.idCompte}&lang=en"><fmt:message key="lang.en" /></a></li>
<li><a href="?idCompte=${param.idCompte}&lang=fr"><fmt:message key="lang.fr" /></a></li>
</html>