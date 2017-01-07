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
<script type = "text/javascript" src="/res/js/check_product.js"></script>
<div class="container">

    <header class="page-header">
        <h1>Edit Product</h1>
    </header>

    <div class="row">

        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>

        <div class="col-md-9">
            <form name="edit_form" method="post" onsubmit="return checkProduct()">
                <div class="form-group row">
                    <label class="col-md-1" for="productType">Type</label>

                    <div class="col-md-8">
                        <input class="form-control" type="text" name="productType" value="${TYPEtoedit}"
                               placeholder="Enter type here" id="productType"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-1" for="productBrand">Brand</label>

                    <div class="col-md-8">
                        <input class="form-control" type="text" name="productBrand" value="${BRANDtoedit}"
                               placeholder="Enter brand here" id="productBrand"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-1" for="productModel">Model</label>

                    <div class="col-md-8">
                        <input class="form-control" type="text" name="productModel" value="${MODELtoedit}"
                               placeholder="Enter model here" id="productModel"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-1" for="productPrice">Price</label>

                    <div class="col-md-8">
                        <input class="form-control" type="text" name="productPrice" value="${PRICEtoedit}"
                               placeholder="Enter price here" id="productPrice"/>
                    </div>
                </div>

                <div class="col-md-1"></div>
                <div class="col-md-8">
                    <div class="pull-left">
                        <c:if test="${IDtoedit != null}">
                            <a href="./delete?id=${IDtoedit}"
                               onclick="return confirm('Are you sure want delete ${item.id} item?');"
                               class="btn btn-md btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </c:if>
                    </div>
                    <div class="pull-right">
                        <%--<input class="btn btn-md btn-info" type="submit" name="button" value="Back">--%>
                        <a href="./"
                           class="btn btn-md btn-info">
                            <i class="fa fa-mail-reply"></i>
                        </a>
                        <%--<c:choose>--%>
                        <%--<c:when test="${IDtoedit != null}">--%>
                        <%--<input class="btn btn-md btn-primary" type="submit" name="button" value="Apply">--%>
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>
                        <%--<input class="btn btn-md btn-primary" type="submit" name="add" value="Add">--%>
                        <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <button class="btn btn-md btn-primary" type="submit" name="add">
                            <i class="fa fa-check-square-o"></i>
                        </button>
                    </div>
                </div>
            </form>
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
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
