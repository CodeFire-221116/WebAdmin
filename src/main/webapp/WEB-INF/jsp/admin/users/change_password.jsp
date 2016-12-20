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
    <title>Change user password</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>

<div class="container">
    <header class="page-header">
        <h1>Change user password</h1>
    </header>

    <div class="row">

        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        <div class="col-md-9">

            <form class="container" method="post">

                <div class="form-group row">

                    <label class="col-md-2" for="current_password">
                        <mark>
                            <strong>${userName} </strong>
                        </mark>
                        password
                    </label>

                    <div class="col-md-7 form-group ${classAdditionForCurrentPassword}">
                        <input class="form-control" type="password" name="current_password" id="current_password"
                               placeholder="current password"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-2" for="password">New password</label>
                    <div class="col-md-7 form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="password" id="password"
                               placeholder="new password"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-2" for="confirm_password">Confirm password</label>
                    <div class="col-md-7 form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="confirm_password" id="confirm_password"
                               placeholder="new password again"/>
                    </div>
                </div>

                <div class="col-md-9">
                    <div class="pull-right">
                        <input type="submit" class="btn btn-primary" name="submission" value="GO BACK">
                        <input type="submit" class="btn btn-warning" name="submission" value="SUBMIT">
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
