<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/8/23
  Time: 1:48 PM
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加角色</title>
</head>
<body>
<form id="form" method="post" action="<%=path%>/roleServlet">
    <input type="hidden" value="1" name="type" />
    <input type="hidden" value="${flag}" name="flag" id="flag" />
    <input type="hidden" value="${role.roleId}" name="roleId" />
    角色名称：<input type="text" id="roleName" name="roleName" value="${role.roleName}" /> <br>
    <button type="button" onclick="add();" >保 存</button>
    <button type="button" onclick="cancel();" >取 消</button>
</form>
</body>
</html>

<script>
    function add() {
        var roleName = document.getElementById("roleName").value;

        if (roleName == null || roleName == '') {
            alert("请输入一个角色名称！");
            return;
        }

        // submit
        document.getElementById("form").submit();
    }

    function cancel() {
        window.location.href = "<%=path%>/roleServlet?type=0";
    }
</script>