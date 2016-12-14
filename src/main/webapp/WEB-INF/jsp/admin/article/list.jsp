<%--
  Created by IntelliJ IDEA.
  User: mkoval
  Date: 10.12.2016
  Time: 15:30
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
        <h1>Article</h1>
    </header>

    <div class="row">

        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>

        <div class="col-md-9">

            <h3>Article count
                <sup class="badge">${count}</sup>

                <a class="btn btn-success pull-right" href="/article?action=new">
                    <i class="fa fa-fw fa-plus"></i>
                </a>
            </h3>

            <table class="table">

                <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Author</th>
                    <th>Timestamp</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${articleList}" var="ListItem">
                    <tr>
                        <td>${ListItem.getId()}</td>
                        <td>${ListItem.getTitle()}</td>
                        <td>${ListItem.getContent()}</td>
                        <td>${ListItem.getAuthor()}</td>
                        <td>${ListItem.getTimestamp()}</td>
                        <td nowrap>
                            <a href="/article?id=${ListItem.getId()}" class="btn btn-warning" type="reset">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="/article?id=${ListItem.getId()}"
                               onclick="return confirm('Do you really want to delete page ${ListItem.getId()}?')"
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
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
</body>
</html>