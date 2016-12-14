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
    <title>Users</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<div class="container">
    <header class="page-header">
        <h1>Users</h1>
    </header>
    <div class="row">
        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        <div class="col-md-9">
            <h3>Users count <sup class="badge">${usersCount}</sup>
                <a class="btn btn-success pull-right" href="/admin/users?action=new">
                    <i class="fa fa-fw fa-plus"></i>
                </a>
            </h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${usersList}" var="ListItem">
                    <tr>
                        <td>${ListItem.getId()}</td>
                        <td>${ListItem.getUsername()}</td>
                        <td nowrap>
                            <a href="/admin/users?action=changepassword&id=${ListItem.getId()}"
                               class="btn btn-info btn-xs"
                               type="reset">Change password</a>
                            <a href="/admin/users?action=delete&id=${ListItem.getId()}"
                               class="btn btn-danger btn-sm"
                               type="reset"><i class="fa fa-fw fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
