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
  <h1>您好，${userName}</h1>
  <a href="<%=path%>/logoutServlet">退出系统</a>
  <br><br>
  <c:forEach items="${menus}" var="m">
    <c:if test="${m.checkId != null}">
    <a href="<%=path%>${m.menuPath}">${m.menuName}</a> &nbsp;
    </c:if>
  </c:forEach>
  <hr  />
  <br/>
  </body>
</html>
