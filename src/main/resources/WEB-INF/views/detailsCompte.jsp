<%@include file="includes/include.jsp"%>
    <title><fmt:message key = "account_details"/></title>
</head>
<body>
    <div class="col-md-1 offset-md-11">
        <a href="?idCompte=${param.idCompte}&lang=en"><img src="https://images-na.ssl-images-amazon.com/images/I/41cfK9pPRpL._SX355_.jpg" alt=<fmt:message key="lang.en" /> class="img-circle" width="22" height="19"/></a>
        <a href="?idCompte=${param.idCompte}&lang=fr"><img src="https://images-na.ssl-images-amazon.com/images/I/21EAOCdUbKL._SX355_.jpg" alt=<fmt:message key="lang.fr" /> class="img-circle" width="22" height="19"/></a>
        <a href="?idCompte=${param.idCompte}&lang=eo"><img src="https://images-na.ssl-images-amazon.com/images/I/51J9SCoEZzL._SX355_.jpg" alt=<fmt:message key="lang.eo" /> class="img-circle" width="22" height="19"/></a>
        <br><br><a href="${pageContext.request.contextPath}/deco"><button><fmt:message key="deco"/></button></a>
    </div>

    <div class="col-md-8 offset-md-2 bg-light">
        <h2><fmt:message key = "account_details"/></h2>
        <table>
            <c:forEach items="${utilisateur.getComptes()}" var="compte">
                <c:if test="${param.idCompte==compte.getId_compte()}">
                    <a href="${pageContext.request.contextPath}/listeComptes"><img src="http://cdn.onlinewebfonts.com/svg/img_225291.png" alt="arrowLeft" height="15px"></a><br>
                    ${compte.getId_compte()} <br>
                    <fmt:message key="account.${compte.getTypecpt()}" /><br>
                    <%--${compte.getSolde()} <fmt:message key = "devise"/><br>--%>
                    ${solde} <fmt:message key = "devise"/><br>
                    ${compte.getDate_creation()} <br>
                    <br>
                        <h3><fmt:message key = "listTransac"/></h3>
                    <c:forEach items="${compte.getTransactions()}" var="transaction">
                        <tr>
                            <td>${transaction.getLibelle()}</td>
                            <td><a href="${pageContext.request.contextPath}/detailsTransaction?idTransac=${transaction.getId_transaction()}"><button class="btn btn-info"><fmt:message key = "see_more"/></button></a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </table>

        <br>
        <h3><fmt:message key = "createTransac"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/detailsCompte?idCompte=${param.idCompte}">
            <table>
                <tr>
                    <td><label for="amountTransac"><fmt:message key = "amountTransac"/></label></td>
                    <td><input type="number" name="amountTransac" id="amountTransac" required/> <fmt:message key = "devise"/></td>
                </tr>
                <tr>
                    <td><label for="labelTransac"><fmt:message key = "labelTransac"/></label></td>
                    <td><input type="text" name="labelTransac" id="labelTransac" required/></td>
                </tr>
                <tr>
                    <td><label for="idDest"><fmt:message key = "idDest"/></label></td>
                    <td><input type="number" name="idDest" id="idDest" required/></td>
                    <input type="hidden" value="${param.idCompte}" name="idCompte" id="idCompte">
                </tr>
                <c:if test="${requestScope.errorMsgDest!=null}">
                    <div class="alert alert-danger">
                        <fmt:message key = "${requestScope.errorMsgDest}"/>
                    </div>
                </c:if>
                <c:if test="${requestScope.errorMsgFunds!=null}">
                    <div class="alert alert-danger">
                        <fmt:message key = "${requestScope.errorMsgFunds}"/>
                    </div>
                </c:if>
            </table>
            <input type="submit" value=<fmt:message key = "valid"/> /> <br><br>
        </form>
    </div>
</body>
</html>