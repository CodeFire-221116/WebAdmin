<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 12/1/16
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>

    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<h1 class="text-center">Welcome!</h1>
<%@include file="/WEB-INF/jsp/menu.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <a href="/auth/" class="btn btn-success btn-lg btn-block" type="button" >Sign in</a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <form action="/mail" method="POST">
                <input class="form-control" name="to" value="melnyk@codefire.com.ua" />
                <input class="form-control" name="to" value="giologi@gmail.com" />

                <button class="btn btn-warning" type="submit">SEND</button>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
