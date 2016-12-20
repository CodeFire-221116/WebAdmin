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
                    <!--<th style="width: 1%" class="text-right">id</th>
                    <th style="width: 1%" ></th>-->
                    <th>username</th>
                    <!--
                    <th class="text-right">email</th>-->
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${usersList}" var="item">
                    <tr>
                        <!--<td class="text-right">${item.id}</td>
                        <td></td>-->
                        <td>${item.username}</td>
                        <!--
                        <td class="text-right">
                            <div class="input-group">
                            <a class="btn btn-xs btn-primary" href="mailto:${item.email}">${item.email}</a>
                                <span class="btn-addon">@</span></div>
                        </td>-->
                        <td class="text-right" nowrap>
                            <c:if test="${item.email != null && !item.email.isEmpty()}">
                                <a href="/admin/users?action=email_validation&id=${item.id}"
                                        <c:choose>
                                            <c:when test="${item.emailKey == 1}">class="btn btn-sm btn-success" disabled</c:when>
                                            <c:otherwise>class="btn btn-sm btn-warning"</c:otherwise>
                                        </c:choose>>
                                    <i class="fa fa-fw fa-envelope"></i>
                                </a>
                            </c:if>
                            <a href="/admin/users?action=change_password&id=${item.id}"
                               class="btn btn-sm btn-warning">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="/admin/users?action=delete&id=${item.id}"
                               class="btn btn-sm btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
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
