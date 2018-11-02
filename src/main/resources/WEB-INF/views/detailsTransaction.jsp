<%@include file="includes/include.jsp"%>
    <title><fmt:message key = "transac_details"/></title>
</head>
<body>
    <div class="col-md-1 offset-md-11">
        <a href="?idTransac=${param.idTransac}&lang=en"><img src="https://images-na.ssl-images-amazon.com/images/I/41cfK9pPRpL._SX355_.jpg" alt=<fmt:message key="lang.en" /> class="img-circle" width="22" height="19"/></a>
        <a href="?idTransac=${param.idTransac}&lang=fr"><img src="https://images-na.ssl-images-amazon.com/images/I/21EAOCdUbKL._SX355_.jpg" alt=<fmt:message key="lang.fr" /> class="img-circle" width="22" height="19"/></a>
    </div>

    <div class="col-md-4 offset-md-4 bg-light">
        <h2><fmt:message key = "transac_details"/></h2>
        <table>
            <c:forEach items="${utilisateur.getComptes()}" var="compte">
                <c:forEach items="${compte.getTransactions()}" var="transaction">
                    <c:if test="${param.idTransac==transaction.getId_transaction()}">
                        <a href="/detailsCompte?idCompte=${transaction.getLeSuperCompte().getId_compte()}"><img src="http://cdn.onlinewebfonts.com/svg/img_225291.png" alt="arrowLeft" height="15px"></a> <br>
                        <tr>
                            <td>${transaction.getLibelle()}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key = "amountTransac"/> : ${transaction.getMontant()} <fmt:message key = "devise"/></td>
                        </tr>
                        <tr>
                            <td><fmt:message key = "accountSource"/> : ${transaction.getCpt_source()}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key = "accountDest"/> : ${transaction.getCpt_dest()}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key = "dateTransac"/> : ${transaction.getDate()}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </table>

    </div>
</body>
</html>