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

                <a class="btn btn-success pull-right" href="/admin/pages?action=new">
                    <i class="fa fa-fw fa-plus"></i>
                </a>
            </h3>

            <table class="table">

                <thead>
                <tr>
                    <td>id</td>
                    <td>title</td>
                    <td>content</td>
                    <td style="width: 1%;"></td>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="page" items="${pages}">
                    <tr>
                        <td>${page.id}</td>
                        <td>${page.title}</td>
                        <td >${page.content}</td>
                        <td nowrap>
                            <a href="/admin/pages?id=${page.getId()}" class="btn btn-warning" type="reset">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="/admin/pages?id=${page.getId()}&action=delete"
                               onclick="return confirm('Do you really want to delete page ${page.id}?')"
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
