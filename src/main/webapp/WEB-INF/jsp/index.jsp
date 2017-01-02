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
    <title>Web application</title>

    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu.jsp" %>

<div class="container">

    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <form action="/mail" method="POST">
                <input class="form-control" name="to" value="melnyk@codefire.com.ua" />
                <input class="form-control" name="to" value="giologi@gmail.com" />

                <button class="btn btn-warning" type="submit">SEND</button>
            </form>
        </div>
    </div>

    <!-- FOOTER -->
    <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; 2016 codefire-ee-221116</p>
    </footer>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
