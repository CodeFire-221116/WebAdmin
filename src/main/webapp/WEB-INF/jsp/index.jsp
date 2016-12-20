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

    <link rel="stylesheet" href="./res/css/ankysStyles.css"/>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<h1 class="text-center">Welcome!</h1>
<%@include file="/WEB-INF/jsp/menu.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <a href="/admin/" class="btn btn-success btn-lg btn-block" type="button">Sign in</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <section class="panel panel-primary">
                <ul id="messagesList" class="list-group" style="max-height: 300px; overflow-y: scroll;">
                    <li class="list-group-item">ok</li>
                </ul>
                <div class="panel-body">
                    <form onsubmit="sendMessage(); return false;">
                        <input class="form-control" id="messageInput"/>
                        <button type="submit" class="btn btn-block btn-primary">SEND</button>
                    </form>
                </div>
            </section>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
