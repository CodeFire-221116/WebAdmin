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

        <div class="col-md-9">

            <h3>Products count
                <sup class="badge">${count}</sup>

                <a class="btn btn-success pull-right" href="/admin/products?action=new">
                    <i class="fa fa-fw fa-plus"></i>
                </a>
            </h3>

            <table class="table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Type</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Price</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productsList}" var="ListItem">
                    <tr>
                        <td>${ListItem.id}</td>
                        <td>${ListItem.type}</td>
                        <td>${ListItem.brand}</td>
                        <td>${ListItem.model}</td>
                        <td>${ListItem.price}</td>
                        <td nowrap>
                            <a href="/admin/products?id=${ListItem.getId()}"
                               class="btn btn-warning" type="reset">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="/admin/products?id=${ListItem.getId()}&action=delete"
                               onclick="return confirm('Are you sure want delete ${ListItem.id} item?');"
                               class="btn btn-danger" type="reset">
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
