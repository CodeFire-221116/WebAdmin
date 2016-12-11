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
    <title>Authorization</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
    <link rel="stylesheet" href="/res/css/ankysStyles.css" />
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in</h1>
            <div class="account-wall">
                <img class="profile-img" src=""
                     alt="">
                <form class="form-signin" method="post">
                    <input type="text" name="username" class="form-control" placeholder="Nickname" required autofocus>
                    <input type="password" name="password" class="form-control" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign in</button>
                    <label class="checkbox" style="text-align: center">
                        <input type="checkbox" value="remember-me">
                        Remember me
                    </label>
                </form>
            </div>
            <a href="/register" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>
    <%--<h1>Authenticate please...</h1>--%>

    <%--<form method="post">--%>
        <%--<div>--%>
            <%--Username:--%>
            <%--<input name="username" type="text" />--%>
        <%--</div>--%>
        <%--<div>--%>
            <%--Password:--%>
            <%--<input name="password" type="password" />--%>
        <%--</div>--%>
        <%--<div>--%>
            <%--<button type="submit">LOGIN</button>--%>
        <%--</div>--%>
        <%--${flash_message}--%>
    <%--</form>--%>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
