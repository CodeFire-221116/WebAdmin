<%--
  Created by IntelliJ IDEA.
  User: Katya
  Date: 12.12.2016
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">webapp</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li ${page_type == null ? 'class="active"' : ''}><a href="/index/">Home</a></li>
                        <li ${page_type.equals('products') ? 'class="active"' : ''}><a href="/products/">Products</a></li>
                        <li ${page_type.equals('pages') ? 'class="active"' : ''}><a href="/pages/">Pages</a></li>
                        <li ${page_type.equals('articles') ? 'class="active"' : ''}><a href="/articles/">Articles</a></li>
                        ${$CURR_USER.canChangeAccessLvl(UserEntity.AccessLevel.User) ? '<li><a href="/admin/">Admin. menu</a></li>' : ''}
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/register/"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="/auth/"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
</div>
</body>
</html>
