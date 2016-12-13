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
<form class="container" method="post">
    <div class="container">
        <header class="page-header">
            <h1>Edit Article</h1>
        </header>

        <div class="row">
            <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        </div>

        <div class="row">
            <div class="col-md-9">
                <input class="form-control" type="text" name="articleTitle" value="${Titletoedit}"
                       placeholder="Enter title here"/>
                <input class="form-control" type="text" name="articleAuthor" value="${Authortoedit}"
                       placeholder="Enter content here"/>
                <input class="form-control" type="text" name="articleContent" value="${Contenttoedit}"
                       placeholder="Enter content here"/>
                <input type="submit" class="btn btn-primary" value="OK">
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
</body>
</html>