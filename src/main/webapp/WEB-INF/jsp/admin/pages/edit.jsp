<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<div class="container">
    <header class="page-header">
        <h1>Edit Page</h1>
    </header>

    <div class="row">
        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>

        <div class="col-md-9">

            <h4 class="text-right"><strong>
                <c:choose>
                    <c:when test="${not empty id}">
                        Page ID ${id}
                    </c:when>
                    <c:otherwise>
                        New Page
                    </c:otherwise>
                </c:choose>
            </strong></h4>

            <div class="container">
                <form method="post">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-group row">
                        <label class="col-md-1" for="title">Title</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" name="title" value="${title}"
                                   placeholder="Title" id="title"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-1" for="content">Content</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" name="content" value="${content}"
                                   placeholder="Content" id="content"/>
                        </div>
                    </div>

                    <div class="col-md-1"></div>
                    <div class="col-md-8">
                        <c:if test="${not empty id}">
                            <input type="submit" class="btn btn-danger pull-left" value="Delete" name="btn_delete">
                        </c:if>

                        <div class="pull-right">
                            <input type="submit" class="btn btn-info" value="Cancel" name="btn_cancel">
                            <input type="submit" class="btn btn-success" value="OK" name="btn_ok">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <p class="text-error"><strong>${error_message}</strong></p>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
