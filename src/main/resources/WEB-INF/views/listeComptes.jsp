<%@include file="includes/include.jsp"%>
    <title><fmt:message key = "list_account"/></title>
</head>
<body>
    <div class="col-md-1 offset-md-11">
        <a href="?lang=en"><img src="https://images-na.ssl-images-amazon.com/images/I/41cfK9pPRpL._SX355_.jpg" alt=<fmt:message key="lang.en" /> class="img-circle" width="22" height="19"/></a>
        <a href="?lang=fr"><img src="https://images-na.ssl-images-amazon.com/images/I/21EAOCdUbKL._SX355_.jpg" alt=<fmt:message key="lang.fr" /> class="img-circle" width="22" height="19"/></a>
        <a href="?lang=eo"><img src="https://images-na.ssl-images-amazon.com/images/I/51J9SCoEZzL._SX355_.jpg" alt=<fmt:message key="lang.eo" /> class="img-circle" width="22" height="19"/></a>
        <br><br><a href="${pageContext.request.contextPath}/deco"><button><fmt:message key="deco"/></button></a>
    </div>

    <div class="col-md-8 offset-md-2 bg-light">
        <p><fmt:message key = "welcome_msg"/><c:out value=" ${utilisateur.getPrenom()} ${utilisateur.getNom()}" /></p>
        <h2><fmt:message key = "list_account"/></h2>
        <table>
            <c:forEach items="${utilisateur.getComptes()}" var="compte">
                <tr>
                    <td><fmt:message key="account.${compte.getTypecpt()}" /> : ${compte.getSolde()}  <fmt:message key = "devise"/></td>
                    <td><a href="/detailsCompte?idCompte=${compte.getId_compte()}"><button class="btn btn-info"><fmt:message key = "see_more"/></button></a></td>
                </tr>
            </c:forEach>
        </table><br><br>
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
            <div class="alert alert-danger">
                <fmt:message key = "${requestScope.confirmMsg}"/>
            </div>
        </c:if> <br>
        <a href="${pageContext.request.contextPath}/changePass"><button><fmt:message key = "change_pass"/></button></a><br/>
    </div>
</body>
</html>
