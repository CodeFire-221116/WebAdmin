<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2016
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <label class="col-md-3 control-label" for="username">Username</label>
    <div class="col-md-6 ${classAdditionForUsername}">
        <input class="form-control" type="text"
               name="username"
               value="${userName}"
               id="username"
               placeholder="username"/>
    </div>
</div>
