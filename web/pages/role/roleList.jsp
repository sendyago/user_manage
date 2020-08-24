<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/8/23
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
</head>
<body>
    <jsp:include page="/index.jsp" />
    <button type="button" onclick="addRole();">添 加</button>
    <form method="post" id="queryForm" action="<%=path%>/roleServlet">
        <input type="text" name="rName" value="${role.roleName}" placeholder="角色名称"/>
        <input type="hidden" name="type" value="0" />
        <input type="hidden" name="pageNum" value="${pageNum}" />
        <input type="hidden" id="changeNum" name="changeNum" value="" />
        <button onclick="changePage(0);">查 询</button>
    </form>
    <table border="1">
        <tr>
            <td>序号</td>
            <td>角色名称</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${roleList}" var="l" varStatus="vs">
            <tr>
                <td>${vs.index + 1}</td>
                <td>${l.roleName}</td>
                <td>
                    <button type="button" onclick="editRole('${l.roleId}');">编 辑</button>
                    <button type="button" onclick="deleteRole('${l.roleId}');">删 除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button onclick="changePage(-1);">上一页</button>
    <button onclick="changePage(1);">下一页</button>
    当前第 ${pageNum} 页，共 ${totalPageNum} 页，共 ${totalNum} 条记录
</body>
</html>
<script>
    function changePage(num) {
        document.getElementById("changeNum").value = num;
        document.getElementById("queryForm").submit();
    }

    function addRole() {
        window.location.href = "<%=path%>/pages/role/addRole.jsp";
    }

    function editRole(roleId) {
        window.location.href = "<%=path%>/roleServlet?type=2&roleId="+roleId;
    }

    function deleteRole(roleId) {
        if (window.confirm("您确定要删除此条记录吗？")) {
            window.location.href = "<%=path%>/roleServlet?type=3&roleId="+roleId;
        }
    }
</script>