<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 11/29/16
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<form class="container" method="post">
    <header class="page-header">
        <h1>Add user</h1>
    </header>

    <div div style="color: red; font-weight: bold;">
        <p>${errorMessage}</p>
    </div>

    <div class="row">
        <div class="col-md-9">

            <div class="form-group">
                <label for="username">Enter user name</label>
                <div class="form-inline ${classAdditionForUsername}">
                    <input class="form-control" type="text" name="username" value="${usernameValue}" id="username"
                           placeholder="username"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password">Enter password and then confirm it</label>
                <div class="form-inline">
                    <div class="form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="password" id="password"
                               placeholder="password"/>
                    </div>
                    <div class="form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="confirmpassword" id="confirmpassword"
                               placeholder="password again"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-success" name="submition" value="SUBMIT">
                <input type="submit" class="btn btn-primary" name="submition" value="GO BACK">
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
