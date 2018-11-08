<%@include file="includes/include.jsp"%>
    <title><fmt:message key = "accueil"/></title>
</head>
<body>
    <div class="col-md-1 offset-md-11">
        <a href="?lang=en"><img src="https://images-na.ssl-images-amazon.com/images/I/41cfK9pPRpL._SX355_.jpg" alt=<fmt:message key="lang.en" /> class="img-circle" width="22" height="19"/></a>
        <a href="?lang=fr"><img src="https://images-na.ssl-images-amazon.com/images/I/21EAOCdUbKL._SX355_.jpg" alt=<fmt:message key="lang.fr" /> class="img-circle" width="22" height="19"/></a>
        <a href="?lang=eo"><img src="https://images-na.ssl-images-amazon.com/images/I/51J9SCoEZzL._SX355_.jpg" alt=<fmt:message key="lang.eo" /> class="img-circle" width="22" height="19"/></a>
    </div>

    <div class="col-md-4 offset-md-4 bg-light">
        <h1><fmt:message key = "welcome_msg"/></h1>
        ${requestScope.date}
        <br>
        <form method="post" action="${pageContext.request.contextPath}/accueil">
            <table>
            <tr>
                <td><label for="login"><fmt:message key = "login"/></label></td>
                <td><input type="text" name="login" id="login" required/></td>
            </tr>
            <tr>
                <td><label for="password"><fmt:message key = "password"/></label></td>
                <td><input type="password" name="password" id="password" required/></td>
            </tr>
            </table>
            <c:if test="${requestScope.errorMsg!=null}">
                <div class="alert alert-danger">
                    <fmt:message key = "${requestScope.errorMsg}"/>
                </div>
            </c:if>
            <input type="submit" value=<fmt:message key = "valid"/> />
        </form><br/>
    </div>
</body>
</html>