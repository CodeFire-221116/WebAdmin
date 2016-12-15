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

    <link rel="stylesheet" href="./res/css/ankysStyles.css" />
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">Welcome!</h1>
    </div>

    <%@include file="/WEB-INF/jsp/menu.jsp" %>

    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <a href="/admin/" class="btn btn-success btn-lg btn-block" type="button" >Sign in</a>
        </div>
    </div>

    <button type="button" class="btn btn-lg btn-primary" onclick="send();">SEND HELLO WORLD</button>
    <button type="button" class="btn btn-lg btn-danger" onclick="getTimeNow();">GET TIME NOW</button>

    <div id="time_display" class="label label-primary">00:00:00</div>
</div>

<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
