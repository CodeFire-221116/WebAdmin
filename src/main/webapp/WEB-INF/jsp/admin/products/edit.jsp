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
<form class="container" method="post">
    <div class="container">
        <header class="page-header">
            <h1>Edit Product</h1>
        </header>

        <div class="row">
            <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        </div>

        <div class="row">
            <div class="col-md-9">
                <input class="form-control" type="text" name="productType" value="${TYPEtoedit}"
                       placeholder="Enter type here"/>
                <br>
                <input class="form-control" type="text" name="productBrand" value="${BRANDtoedit}"
                       placeholder="Enter brand here"/>
                <br>
                <input class="form-control" type="text" name="productModel" value="${MODELtoedit}"
                       placeholder="Enter model here"/>
                <br>
                <input class="form-control" type="text" name="productPrice" value="${PRICEtoedit}"
                       placeholder="Enter price here"/>
                <br>
                <input class="btn btn-default" type="submit" name="button" value="Back">
                <c:if test="${action != null}">
                    <input class="btn btn-primary" type="submit" name="add" value="Add">
                </c:if>
                <c:if test="${IDtoedit != null}">
                    <input class="btn btn-primary" type="submit" name="button" value="Apply">
                    <input class="btn btn-danger" type="submit" name="button" value="Delete">
                </c:if>
            </div>
        </div>
        <c:if test="${errors != null}">
            <br>
            <div class="row">
                <div class="col-md-9">
                    <div class="alert alert-danger">${errors}</div>
                </div>
            </div>
        </c:if>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
