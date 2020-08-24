<%--
  Created by IntelliJ IDEA.
  User: rickhau
  Date: 2020/7/20
  Time: 4:34 下午
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="<%=path%>/userServlet?type=0">用户管理</a> &nbsp;
  <a href="<%=path%>/roleServlet?type=0">角色管理</a>
  <hr  />
  <br/>
  </body>
</html>
