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
<form id="form" method="post" action="<%=path%>/roleMenuServlet">
    <input type="hidden" value="2" name="type" />
    <input type="hidden" name="roleId" value="${roleId}">
    <c:forEach items="${menuList}" var="m" >
        <input type="checkbox" name="roleMenu" value="${m.menuId}" <c:if test="${m.checkId !=null}">checked</c:if> /> ${m.menuName} <br>
    </c:forEach>
    <button type="submit" >保 存</button>
    <button type="button" onclick="cancel();" >取 消</button>
</form>
</body>
</html>

<script>

    function cancel() {
        window.location.href = "<%=path%>/roleMenuServlet?type=0";
    }
</script>