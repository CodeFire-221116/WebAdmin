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
    <div class="container">
        <header class="page-header">
            <h1>User delete confirmation</h1>
        </header>

        <div class="row">
            <div class="col-md-9">
                <p>Press CONFIRM button to delete user "${userName}"</p>
                <input type="submit" class="btn btn-danger" name="confirmation" value="CONFIRM">
                <input type="submit" class="btn btn-primary" name="confirmation" value="CANCEL">
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
