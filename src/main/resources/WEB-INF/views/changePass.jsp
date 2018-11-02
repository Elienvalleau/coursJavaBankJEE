<%@include file="includes/include.jsp"%>
    <title><fmt:message key = "change_pass"/></title>
</head>
<body>
    <div class="col-md-1 offset-md-11">
        <a href="?lang=en"><img src="https://images-na.ssl-images-amazon.com/images/I/41cfK9pPRpL._SX355_.jpg" alt=<fmt:message key="lang.en" /> class="img-circle" width="22" height="19"/></a>
        <a href="?lang=fr"><img src="https://images-na.ssl-images-amazon.com/images/I/21EAOCdUbKL._SX355_.jpg" alt=<fmt:message key="lang.fr" /> class="img-circle" width="22" height="19"/></a>
        <a href="?lang=eo"><img src="https://images-na.ssl-images-amazon.com/images/I/51J9SCoEZzL._SX355_.jpg" alt=<fmt:message key="lang.eo" /> class="img-circle" width="22" height="19"/></a>
        <br><br><a href="${pageContext.request.contextPath}/deco"><button><fmt:message key="deco"/></button></a>
    </div>

    <div class="col-md-8 offset-md-2 bg-light">
        <h2><fmt:message key = "change_pass"/></h2>
        <p><fmt:message key = "passRules"/></p>
        <a href="${pageContext.request.contextPath}/listeComptes"><img src="http://cdn.onlinewebfonts.com/svg/img_225291.png" alt="arrowLeft" height="15px"></a> <br>
        <form method="post" action="${pageContext.request.contextPath}/changePass">
            <table>
                <tr>
                    <td><label for="oldPassword"><fmt:message key = "oldPassword"/></label></td>
                    <td><input type="password" name="oldPassword" id="oldPassword" required/></td>
                </tr>
                <tr>
                    <td><label for="newPassword"><fmt:message key = "newPassword"/></label></td>
                    <td><input type="password" name="newPassword" id="newPassword" required/></td>
                </tr>
                <tr>
                    <td><label for="confirmPassword"><fmt:message key = "confirmPassword"/></label></td>
                    <td><input type="password" name="confirmPassword" id="confirmPassword" required/></td>
                </tr>
            </table>
            <c:if test="${requestScope.errorMsg!=null}">
                <div class="alert alert-danger">
                    <fmt:message key = "${requestScope.errorMsg}"/>
                </div>
            </c:if>
            <c:if test="${requestScope.errorMsgOldPass!=null}">
                <div class="alert alert-danger">
                    <fmt:message key = "${requestScope.errorMsgOldPass}"/>
                </div>
            </c:if>
            <c:if test="${requestScope.errorMsgPassword!=null}">
                <div class="alert alert-danger">
                    <fmt:message key = "${requestScope.errorMsgPassword}"/>
                </div>
            </c:if>
            <p>
                <input type="submit" value=<fmt:message key = "valid"/> />
            </p>
        </form>
    </div>
</body>
</html>