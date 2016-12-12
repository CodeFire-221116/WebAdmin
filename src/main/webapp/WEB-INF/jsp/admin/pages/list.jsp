<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Pages</title>
    <c:import var="css" url="/WEB-INF/jsp/common/stylesheet.jsp"/>
</head>
<body>
<div class="container">
    <header class="page-header">

        <h1>Hello MAVEN</h1>
    </header>

    <div class="row">
        <c:import url="/WEB-INF/jsp/admin/menu.jsp"/>

        <div class="col-md-9">
            Pages count <span class="badge">${page_count}</span>
        </div>

        <div style="alignment: right;">
            <a class="btn btn-success" href="admin/pages?action=new">+</a>
        </div>

        <c:forEach var="page" items="${pages}">
            <a href="/pages?id=${page.getId()}" class="btn btn-success" type="reset">${page.getTitle()}</a>
            <br>
        </c:forEach>
    </div>
</div>
<c:import var="list.jsp" url="/WEB-INF/jsp/common/javascript.jsp"/>
</body>
</html>
