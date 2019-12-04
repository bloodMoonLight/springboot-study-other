<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4 0004
  Time: 上午 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>表单定义动作</title>
</head>
<body>
    <form action="/rest/userss" method="post">
        <input type="hidden" name="_method" id="_method" value="PATCH">
        <table>
            <tr>
                <td>用户编号</td>
                <td><input id="id" name="id"></td>
            </tr>
            <tr>
                <td>用户名称</td>
                <td><input id="userName" name="userName"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                    <input type="submit" id="submit" name="submit" >
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
