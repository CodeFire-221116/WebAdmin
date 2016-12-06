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
</head>
<body>
    <h1>Authenticate please...</h1>

    <form method="post">
        <div>
            Username:
            <input name="username" type="text" />
        </div>
        <div>
            Password:
            <input name="password" type="password" />
        </div>
        <div>
            <button type="submit">LOGIN</button>
        </div>
        ${flash_message}
    </form>
</body>
</html>
