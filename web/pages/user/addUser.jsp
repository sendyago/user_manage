<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/8/2
  Time: 2:18 下午
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <form id="form" method="post" action="<%=path%>/userServlet">
        <input type="hidden" value="1" name="type" />
    用户ID：<input type="text" id="userId" name="userId" /> <br>
    登录密码：<input type="password" id="userPassword" name="userPassword" /> <br>
    用户姓名：<input type="text" id="userName" name="userName" /> <br>
    性别：<input type="radio" id="gender1" name="gender" value="1"/> 男 <input type="radio" id="gender2" name="gender" value="2"/> 女 <br>
    角色：<select id="roleId" name="roleId">
            <option value="">请选择...</option>
            <option value="1">系统管理员</option>
            <option value="2">普通用户</option>
         </select><br>
        <button type="button" onclick="add();" >添 加</button>
        <button type="button" onclick="cancel();" >取 消</button>
    </form>
</body>
</html>

<script>
    function add() {
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
        // 判断用户名是否重复
        // ajax
        var url = '<%=path%>/userServlet', params='type=4&userId='+userId;
        var ret = getDataByAjax(url, params);

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
        window.location.href = "<%=path%>/userServlet";
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