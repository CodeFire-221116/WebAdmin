<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                <a class="btn btn-success pull-right" href="./new">
                    <i class="fa fa-fw fa-plus"></i>
                </a>
            </h3>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Author</th>
                    <th>When</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${articlesList}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.content}</td>
                        <td>${item.authors}</td>
                        <td>
                            <fmt:formatDate value="${item.date}" pattern="dd.MM.yyyy"/>
                            <span class="text-muted"><fmt:formatDate value="${item.date}" pattern="HH:mm"/></span>
                        </td>
                        <td nowrap>
                            <a href="./edit?id=${item.id}"
                               class="btn btn-sm btn-warning">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>
                            <a href="./delete?id=${item.id}"
                               onclick="return confirm('Do you really want to delete page ${item.id}?')"
                               class="btn btn-sm btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>

                            <c:choose>
                                <c:when test="${currUserArticles.contains(item)}">
                                <c:set var="starClass" value="glyphicon-star"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="starClass" value="glyphicon-star-empty"/>
                            </c:otherwise>
                            </c:choose>
                            <span onclick="markArticleFavourite(${item.id}, event)" style="font-size: 25px; color: gold"
                                  class="star glyphicon ${starClass}"></span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
<script type="text/javascript" src="/res/js/article_js.js"></script>
</body>
</html>