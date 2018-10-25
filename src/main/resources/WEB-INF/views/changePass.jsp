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
    <title><fmt:message key = "change_pass"/></title>
</head>
<body>
<h2><fmt:message key = "change_pass"/></h2>
<p><fmt:message key = "passRules"/></p>
<a href="/listeComptes"><-----</a> <br>
<form method="post" action="${pageContext.request.contextPath}/changePass">
    <p>
        <label for="oldPassword"><fmt:message key = "oldPassword"/></label><br />
        <input type="password" name="oldPassword" id="oldPassword" required/>
    </p>
    <p>
        <label for="newPassword"><fmt:message key = "newPassword"/></label><br />
        <input type="password" name="newPassword" id="newPassword" required/>
    </p>
    <p>
        <label for="confirmPassword"><fmt:message key = "confirmPassword"/></label><br />
        <input type="password" name="confirmPassword" id="confirmPassword" required/>
    </p>
    <c:if test="${requestScope.errorMsg!=null}">
        <fmt:message key = "${requestScope.errorMsg}"/>
    </c:if>
    <c:if test="${requestScope.errorMsgOldPass!=null}">
        <fmt:message key = "${requestScope.errorMsgOldPass}"/>
    </c:if>
    <c:if test="${requestScope.errorMsgPassword!=null}">
        <fmt:message key = "${requestScope.errorMsgPassword}"/>
    </c:if>
    <p>
        <input type="submit" value=<fmt:message key = "valid"/> />
    </p>
</form>
</body>
<br>
<li><a href="?lang=en"><fmt:message key="lang.en" /></a></li>
<li><a href="?lang=fr"><fmt:message key="lang.fr" /></a></li>
</html>