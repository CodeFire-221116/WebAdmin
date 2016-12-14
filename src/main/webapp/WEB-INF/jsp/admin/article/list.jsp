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
    <%@include file="/WEB-INF/jsp/stylesheet.jsp" %>
</head>
<body>
<div class="container">
    <header class="page-header">
        <h1>Article</h1>
    </header>
    <div class="row">
        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        <h4>Article count</h4><span class="badge">${count}</span>
        <div style="display: inline-block" class="text-right">
            <a class="btn btn-success" href="/article?action=new">+</a>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Content</th>
            <th>Author</th>
            <th>Timestamp</th>
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
                <td>
                    <a href="/article?id=${ListItem.getId()}" class="btn" type="reset"
                       style="background-color: aquamarine">Edit article</a>
                </td>
                <td>
                    <a href="/article?id=${ListItem.getId()}" class="btn" type="reset"
                       style="background-color: antiquewhite">Delete article</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
</body>
</html>