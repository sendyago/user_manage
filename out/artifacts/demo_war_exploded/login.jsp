<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/7/20
  Time: 4:50 下午
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登录</title>
</head>
<body>
    <form method="post" id="form" action="<%=path%>/loginServlet">
    <input type="text" id="username" name="username" value="${username}" placeholder="请输入用户名" />
    <input type="password" id="password" name="password" placeholder="请输入密码" />
    <button type="button" name="loginButton" onclick="loginVerify();">登 录</button>
        ${error}
    </form>
</body>
</html>
<script>
    function loginVerify() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        if (username == '') {
            alert('用户名不能为空，请您输入！');
            return;
        }
        if (password == '') {
            alert('密码不能为空，请您输入！');
            return;
        }
        // 调用后端servlet，并将数据进行传递
        document.getElementById("form").submit();
    }
</script>
