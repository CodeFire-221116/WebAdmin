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
    <h3>${url}</h3>
    <h4>
        ${exception}
    </h4>
</div>

<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
