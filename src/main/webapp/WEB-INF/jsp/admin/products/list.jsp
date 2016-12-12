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
        <h1>Products</h1>
    </header>
    <div class="row">
        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        <h4>Products count</h4><span class="badge">${count}</span>
        <div style="display: inline-block" class="text-right">
            <a class="btn btn-success" href="/admin/products?action=new">+</a>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Type</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productsList}" var="ListItem">
            <tr>
                <td>${ListItem.getId()}</td>
                <td>${ListItem.getType()}</td>
                <td>${ListItem.getBrand()}</td>
                <td>${ListItem.getModel()}</td>
                <td>${ListItem.getPrice()}</td>
                <td>
                    <a href="/admin/products?id=${ListItem.getId()}" class="btn" type="reset"
                       style="background-color: aquamarine">Edit product</a>
                </td>
                <td>
                    <a href="/admin/products?id=${ListItem.getId()}" class="btn" type="reset"
                       style="background-color: antiquewhite">Delete product</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
