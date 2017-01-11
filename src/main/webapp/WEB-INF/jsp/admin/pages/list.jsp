<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Pages</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<div class="container">
    <header class="page-header">

        <h1>Pages</h1>
    </header>

    <div class="row">

        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>

        <div class="col-md-9">
            <h3>Pages count
                <sup class="badge">${page_count}</sup>

                <a class="btn btn-success pull-right" href="./new">
                    <i class="fa fa-fw fa-plus"></i>
                </a>
            </h3>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>content</th>
                    <th style="width: 1%;"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="page" items="${pages}">
                    <tr>
                        <td>${page.id}</td>
                        <td>${page.title}</td>
                        <td>${page.content}</td>
                        <td nowrap>
                            <a href="./edit?id=${page.id}"
                               class="btn btn-sm btn-warning">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="./delete?id=${page.id}"
                               onclick="return confirm('Do you really want to delete page ${page.id}?')"
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
