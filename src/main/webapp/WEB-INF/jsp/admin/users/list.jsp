<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>PostData</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<div class="container">
    <header class="page-header">
        <h1>Users</h1>
    </header>
    <div class="row">
        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        <h4>Users count</h4><span class="badge">${usersCount}</span>
        <div style="display: inline-block" class="text-right">
            <a class="btn btn-success" href="/admin/users?action=new">+</a>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>username</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersList}" var="ListItem">
            <tr>
                <td>${ListItem.getId()}</td>
                <td>${ListItem.getUsername()}</td>
                <td>
                    <a href="/admin/users?action=changepassword&id=${ListItem.getId()}" class="btn btn-info" type="reset">Change password</a>
                    <a href="/admin/users?action=delete&id=${ListItem.getId()}" class="btn btn-danger" type="reset">Delete user</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
