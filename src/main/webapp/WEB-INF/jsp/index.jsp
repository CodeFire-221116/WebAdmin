<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 12/1/16
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web application</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu.jsp" %>

<div class="container">

    <c:choose>
        <c:when test="${page_type.equals('products')}">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Type</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${productsList}" var="item">
                            <tr>
                                <td>${item.productType}</td>
                                <td>${item.productBrand}</td>
                                <td>${item.productModel}</td>
                                <td>${item.productPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>
        <c:when test="${page_type.equals('pages')}">
            <c:forEach items="${pagesList}" var="item">
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <h4>${item.title}</h4>
                        <p>${item.content}</p>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:when test="${page_type.equals('articles')}">
            <c:forEach items="${articlesList}" var="item">
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                        <h4>${item.title} by ${item.authors}</h4>
                        <p>${item.content}</p>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-lg-4">
                    <h2>Articles</h2>
                    <p>Various articles to read:)... </p>
                    <p><a class="btn btn-primary" href="/articles/" role="button">View all articles &raquo;</a></p>
                </div>
                <div class="col-lg-4">
                    <h2>Products</h2>
                    <p>Various products to see and choose... </p>
                    <p><a class="btn btn-primary" href="/products/" role="button">View all products &raquo;</a></p>
                </div>
                <div class="col-lg-4">
                    <h2>Pages</h2>
                    <p>Variuos pages to see... </p>
                    <p><a class="btn btn-primary" href="/pages/" role="button">View all pages &raquo;</a></p>
                </div>
            </div>
            <%--<div class="row">--%>
                <%--<div class="col-sm-6 col-md-4 col-md-offset-4">--%>
                    <%--<form action="/mail" method="POST">--%>
                        <%--<input class="form-control" name="to" value="example@gmail.com"/>--%>
                        <%--<input class="form-control" name="to" value="example_2@gmail.com"/>--%>

                        <%--<button class="btn btn-warning" type="submit">SEND</button>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>
        </c:otherwise>
    </c:choose>
    ${page_content}

    <!-- FOOTER -->
    <footer class="navbar navbar-inverse navbar-fixed-bottom">
        <div class="container" style="color: white;">

            <p class="pull-right"><a href="#">Back to top</a></p>
            <p>&copy; 2016 codefire-ee-221116</p>
        </div>
    </footer>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
