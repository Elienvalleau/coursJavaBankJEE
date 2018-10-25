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
        <fmt:message key="account.${compte.getTypecpt()}" /> : ${compte.getSolde()}  <fmt:message key = "devise"/> <button><a href="/detailsCompte?idCompte=${compte.getId_compte()}"><fmt:message key = "see_more"/></a></button> <br>
    </c:forEach>
    <br> <br>

    <h3><fmt:message key = "createAccount"/></h3>
    <form method="post" action="${pageContext.request.contextPath}/listeComptes">
        <label for="accountType"><fmt:message key = "accountType"/></label><br />
        <select id="accountType" name="accountType">
            <option value="1" selected><fmt:message key="account.1" /></option>
            <option value="2"><fmt:message key="account.2" /></option>
            <option value="3"><fmt:message key="account.3" /></option>
        </select>
        <input type="submit" value=<fmt:message key = "valid"/> />
    </form>
    <br> <br>
    <c:if test="${requestScope.confirmMsg!=null}">
        <fmt:message key = "${requestScope.confirmMsg}"/>
    </c:if> <br>
    <button><a href="/changePass"><fmt:message key = "change_pass"/></a></button>
    <br> <br>
</body>
<br>
<li><a href="?lang=en"><fmt:message key="lang.en" /></a></li>
<li><a href="?lang=fr"><fmt:message key="lang.fr" /></a></li>
</html>
