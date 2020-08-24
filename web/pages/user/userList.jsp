<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/7/30
  Time: 1:27 上午
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
    <jsp:include page="/index.jsp" />
    <button type="button" onclick="addUser();">添 加</button>
    <form method="post" id="queryForm" action="<%=path%>/userServlet">
        <input type="text" name="uId" value="${user.userId}" placeholder="用户ID"/>
        <input type="text" name="uName" value="${user.userName}" placeholder="用户姓名"/>
        <select name="gdr">
            <option value="0">性别</option>
            <option value="1" <c:if test="${user.gender == 1}">selected</c:if>>男</option>
            <option value="2" <c:if test="${user.gender == 2}">selected</c:if>>女</option>
        </select>
        <input type="hidden" name="type" value="0" />
        <input type="hidden" name="pageNum" value="${pageNum}" />
        <input type="hidden" id="changeNum" name="changeNum" value="" />
        <button onclick="changePage(0);">查 询</button>
    </form>
    <table border="1">
        <tr>
            <td>序号</td>
            <td>用户ID</td>
            <td>姓名</td>
            <td>性别</td>
            <td>角色名称</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${userList}" var="l" varStatus="vs">
            <tr>
                <td>${vs.index + 1}</td>
                <td>${l.userId}</td>
                <td>${l.userName}</td>
                <td>${l.gender == 1 ? '男' : '女'}</td>
                <td>${l.roleName}</td>
                <td>
                    <button type="button" onclick="editUser('${l.userId}');">编 辑</button>
                    <button type="button" onclick="deleteUser('${l.userId}');">删 除</button>
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

    function addUser() {
        window.location.href = "<%=path%>/userServlet?type=2";
    }

    function editUser(userId) {
        window.location.href = "<%=path%>/userServlet?type=2&userId="+userId;
    }

    function deleteUser(userId) {
        if (window.confirm("您确定要删除此条记录吗？")) {
            window.location.href = "<%=path%>/userServlet?type=3&userId="+userId;
        }
    }
</script>