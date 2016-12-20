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


<div class="container">

    <header class="page-header">
        <h1>Add user</h1>
    </header>

    <div class="row">

        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>


        <div class="col-md-9">

            <form class="container" method="post">

                <div class="form-group row">
                    <label class="col-md-2" for="username">Username</label>
                    <div class="col-md-7 form-group ${classAdditionForUsername}">
                        <input class="form-control" type="text" name="username" value="${usernameValue}" id="username"
                               placeholder="username"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-2" for="password">Password</label>
                    <div class="col-md-7 form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="password" id="password"
                               placeholder="password"/>
                    </div>
                </div>


                <div class="form-group row">
                    <label class="col-md-2" for="confirm_password">Confirm password</label>
                    <div class="col-md-7 form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="confirm_password" id="confirm_password"
                               placeholder="password again"/>
                    </div>
                </div>


                <div class="col-md-9">
                    <div class="pull-right">
                        <input type="submit" class="btn btn-primary" name="submission" value="GO BACK">
                        <input type="submit" class="btn btn-success" name="submission" value="SUBMIT">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<p class="text-error"><strong>${errorMessage}</strong></p>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>