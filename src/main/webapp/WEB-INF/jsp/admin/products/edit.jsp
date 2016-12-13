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
                       placeholder="Enter title here"/>
                <input class="form-control" type="text" name="productBrand" value="${BRANDtoedit}"
                       placeholder="Enter content here"/>
                <input class="form-control" type="text" name="productModel" value="${MODELtoedit}"
                       placeholder="Enter content here"/>
                <input class="form-control" type="text" name="productPrice" value="${PRICEtoedit}"
                       placeholder="Enter content here"/>
                <input type="submit" class="btn btn-primary" value="OK">
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
