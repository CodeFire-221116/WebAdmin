<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page</title>
    <c:import var="css" url="/WEB-INF/jsp/common/stylesheet.jsp"/>
</head>
<body>
<div class="container">
    <header class="page-header">
        <h1>Edit Page</h1>
    </header>

    <div class="row">
        <c:import var="menu" url="/WEB-INF/jsp/admin/menu.jsp"/>
    </div>

    <div class="row">
        <form class="container" method="post">
            <div>
                <div class="col-md-9">
                    <input class="form-control" disabled type="text" name="id" value="${id}"/>
                    <input class="form-control" type="text" name="title" value="${title}"
                           placeholder="Title"/>
                    <input class="form-control" type="text" name="content" value="${content}"
                           placeholder="Content"/>
                    <input type="submit" class="btn btn-primary" value="OK" name="btn_ok">
                </div>
            </div>
        </form>

        <c:choose>
            <c:when test="${not empty id}">
                <form method="post">
                    <input class="form-control" type="hidden" name="id" value="${id}"/>
                    <input type="submit" class="btn btn-primary" value="Delete" name="btn_delete">
                </form>
            </c:when>
        </c:choose>

        ${error_message}

    </div>
</div>
<c:import var="css" url="/WEB-INF/jsp/common/javascript.jsp"/>
</body>
</html>
