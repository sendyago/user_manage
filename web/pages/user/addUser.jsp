<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/8/2
  Time: 2:18 下午
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <form id="form" method="post" action="<%=path%>/userServlet">
        <input type="hidden" value="1" name="type" />
        <input type="hidden" value="${flag}" name="flag" id="flag" />
    用户ID：<input type="text" id="userId" name="userId" value="${user.userId}" /> <br>
    登录密码：<input type="password" id="userPassword" name="userPassword" value="${user.userPassword}" /> <br>
    用户姓名：<input type="text" id="userName" name="userName" value="${user.userName}"/> <br>
        性别：<input type="radio" id="gender1" name="gender" value="1" <c:if test="${user.gender == 1}">checked</c:if>/> 男 <input type="radio" id="gender2" name="gender" value="2" <c:if test="${user.gender == 2}">checked</c:if>/> 女 <br>
    角色：<select id="roleId" name="roleId">
            <option value="">请选择...</option>
            <c:forEach items="${roleList}" var="r">
                <option value="${r.roleId}" <c:if test="${user.roleId == r.roleId}">selected</c:if>>${r.roleName}</option>
            </c:forEach>
         </select><br>
        <button type="button" onclick="add();" >保 存</button>
        <button type="button" onclick="cancel();" >取 消</button>
    </form>
</body>
</html>

<script>
    function add() {
        var flag = document.getElementById("flag").value;
        var userId = document.getElementById("userId").value;
        var userPassword = document.getElementById("userPassword").value;
        var roleId = document.getElementById("roleId").value;
        if (userId == null || userId == '') {
            alert("请输入用户名！");
            return;
        }
        if (userPassword == null || userPassword == '') {
            alert("请输入密码！");
            return;
        }
        if (roleId == null || roleId == '') {
            alert("请选择角色！");
            return;
        }

        var ret ;
        if (flag != "1") {
            // 判断用户名是否重复
            // ajax
            var url = '<%=path%>/userServlet', params='type=4&userId='+userId;
            ret = getDataByAjax(url, params);
        } else {
            ret = "1";
        }

        if (ret == "1") {
            // submit
            document.getElementById("form").submit();
        } else {
            // return
            alert("您输入的用户ID已被占用，请重新输入！");
            return;
        }

    }

    function cancel() {
        window.location.href = "<%=path%>/userServlet?type=0";
    }

    // params: "a=1&b=2&c=3"
    function getDataByAjax(url, params) {
        var ajaxObj = null;
        if (window.ActiveXObject) {
            ajaxObj = new ActiveXObject("Microsoft.XMLHTTP");
        } else {
            ajaxObj = new XMLHttpRequest();
        }
        ajaxObj.open('POST', url, false);
        ajaxObj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ajaxObj.send(params);
        return ajaxObj.responseText;
    }
</script>