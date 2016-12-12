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
<form class="container" method="post">
    <header class="page-header">
        <h1>Change user password</h1>
    </header>

    <div div style="color: red; font-weight: bold;">
        <p>${errorMessage}</p>
    </div>

    <div class="row">
        <div class="col-md-9">

            <div class="form-group">
                <label for="currentpassword">Enter current or old <mark><strong>${userName}</strong></mark>'s password</label>
                <div class="form-inline ${classAdditionForCurrentPassword}">
                    <input class="form-control" type="password" name="currentpassword" id="currentpassword"
                           placeholder="current password"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password">Enter new password and then confirm it</label>
                <div class="form-inline">
                    <div class="form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="password" id="password"
                               placeholder="new password"/>
                    </div>
                    <div class="form-group ${classAdditionForNewPassword}">
                        <input class="form-control" type="password" name="confirmpassword" id="confirmpassword"
                               placeholder="new password again"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-warning" name="submition" value="SUBMIT">
                <input type="submit" class="btn btn-primary" name="submition" value="GO BACK">
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
