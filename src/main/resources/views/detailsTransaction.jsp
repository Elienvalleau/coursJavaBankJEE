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
    <title><fmt:message key = "transac_details"/></title>
</head>
<body>
    <h2><fmt:message key = "transac_details"/></h2>
    <c:forEach items="${utilisateur.getComptes()}" var="compte">
        <c:forEach items="${compte.getTransactions()}" var="transaction">
            <c:if test="${param.idTransac==transaction.getId_transaction()}">
                ${transaction.getLibelle()} <br>
                ${transaction.getMontant()} <br>
                ${transaction.getCpt_source()} <br>
                ${transaction.getCpt_dest()} <br>
                ${transaction.getDate()} <br>
            </c:if>
        </c:forEach>
    </c:forEach>
</body>
<br>
<li><a href="?idTransac=${param.idTransac}&lang=en"><fmt:message key="lang.en" /></a></li>
<li><a href="?idTransac=${param.idTransac}&lang=fr"><fmt:message key="lang.fr" /></a></li>
</html>