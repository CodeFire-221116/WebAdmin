<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3">

    <div class="panel panel-success">
        <div class="panel-heading">
            <c:choose>
                <c:when test="${empty applicationScope['users'].size()
                 || applicationScope['users'].size() == 1}">
                    User
                </c:when>
                <c:otherwise>
                    Users
                </c:otherwise>
            </c:choose>
            online
            <sup class="badge">
                ${applicationScope['users'].size()}
            </sup>
        </div>

        <ul class="list-group">
            <c:forEach items="${applicationScope['users']}" var="user">
                <li class="list-group-item">${user.value}</li>
            </c:forEach>
        </ul>
    </div>
</div>