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
    <div class="container">
        <header class="page-header">
            <h1>Change user password</h1>
        </header>

        ${errorMessage}

        <div class="row">
            <div class="col-md-9">
                <input class="form-control" type="password" name="oldpassword"
                       placeholder="enter old password here"/>
                <input class="form-control" type="password" name="password"
                       placeholder="enter new password here"/>
                <input class="form-control" type="password" name="confirmpassword"
                       placeholder="confirm new password here"/>
                <input type="submit" class="btn btn-primary" name="submition" value="SUBMIT">
                <input type="submit" class="btn btn-primary" name="submition" value="RETURN">
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
