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
    <title>User delete confirmation</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<form class="container" method="post">
    <header class="page-header">
        <h1>User delete confirmation</h1>
    </header>

    <div class="row">
        <div class="col-md-9">
            <p>Click
                <input type="submit" class="btn btn-danger btn-xs" name="confirmation" value="CONFIRM">
                to delete user
                <mark><strong>${userName}</strong></mark>
                or just
                <input type="submit" class="btn btn-primary btn-xs" name="confirmation" value="GO BACK">
                and nothing happens
            </p>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
