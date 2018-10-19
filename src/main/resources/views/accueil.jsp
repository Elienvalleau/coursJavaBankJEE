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
    <title><fmt:message key = "accueil"/></title>
</head>
<body>
    <h1><fmt:message key = "welcome_msg"/></h1>
    ${requestScope.date}

    <form method="post" action="${pageContext.request.contextPath}/accueil">
        <p>
            <label for="login"><fmt:message key = "login"/></label><br />
            <input type="text" name="login" id="login" required/>
        </p>
        <p>
            <label for="password"><fmt:message key = "password"/></label><br />
            <input type="password" name="password" id="password" required/>
        </p>
        <c:if test="${requestScope.errorMsg!=null}">
            <fmt:message key = "${requestScope.errorMsg}"/>
        </c:if>
        <p>
            <input type="submit" value=<fmt:message key = "valid"/> />
        </p>
    </form>

    <ul>
        <li><a href="?lang=en"><fmt:message key="lang.en" /></a></li>
        <li><a href="?lang=fr"><fmt:message key="lang.fr" /></a></li>
    </ul>

</body>
</html>