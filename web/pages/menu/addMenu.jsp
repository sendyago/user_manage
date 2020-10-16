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
    <title>添加菜单</title>
</head>
<body>
<form id="form" method="post" action="<%=path%>/menuServlet">
    <input type="hidden" value="1" name="type" />
    <input type="hidden" value="${flag}" name="flag" id="flag" />
    <input type="hidden" value="${menu.menuId}" name="menuId" />
    菜单名称：<input type="text" id="menuName" name="menuName" value="${menu.menuName}" /> <br>
    菜单路径：<input type="text" id="menuPath" name="menuPath" value="${menu.menuPath}" /> <br>
    <button type="button" onclick="add();" >保 存</button>
    <button type="button" onclick="cancel();" >取 消</button>
</form>
</body>
</html>

<script>
    function add() {
        var menuName = document.getElementById("menuName").value;
        var menuPath = document.getElementById("menuPath").value;

        if (menuName == null || menuName == '') {
            alert("请输入一个菜单名称！");
            return;
        }
        if (menuPath == null || menuPath == '') {
            alert("请输入一个菜单路径！");
            return;
        }

        // submit
        document.getElementById("form").submit();
    }

    function cancel() {
        window.location.href = "<%=path%>/menuServlet?type=0";
    }
</script>