<%--
  Created by IntelliJ IDEA.
  User: 张鸿志
  Date: 2019/12/1
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入jstl依赖 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户详情</title>
</head>
<body>
<center>
        <table border="1px">
            <tr>
                <td>标签</td>
                <td>值</td>
            </tr>
            <tr>
                <td>用户编号</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>用户名称</td>
                <td><c:out value="${user.userName}"></c:out></td>
            </tr>
            <tr>
                <td>用户备注</td>
                <td><c:out value="${user.note}"></c:out></td>
            </tr>
        </table>
</center>
</body>
</html>
