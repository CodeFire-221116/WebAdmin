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
        <h1>Edit Article</h1>
    </header>

    <div class="row">

        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>

        <div class="col-md-9">

            <div class="container">
                <form method="post">

                    <div class="form-group row">
                        <label class="col-md-1" for="articleTitle">Title</label>
                        <div class=" col-md-8">
                            <input class="form-control" type="text" name="articleTitle" value="${Titletoedit}"
                                   placeholder="Enter title here" id="articleTitle"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-1" for="articleAuthor">Author</label>
                        <div class=" col-md-8">
                            <input class="form-control" type="text" name="articleAuthor" value="${Authortoedit}"
                                   placeholder="Enter content here" id="articleAuthor"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-1" for="articleContent">Content</label>
                        <div class=" col-md-8">
                            <input class="form-control" type="text" name="articleContent" value="${Contenttoedit}"
                                   placeholder="Enter content here" id="articleContent"/>
                        </div>
                    </div>

                    <div class="col-md-9">

                        <div class="pull-right">
                            <input type="submit" class="btn btn-success" value="OK">
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/javascript.jsp" %>
</body>
</html>