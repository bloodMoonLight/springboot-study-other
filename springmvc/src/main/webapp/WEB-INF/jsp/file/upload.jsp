<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/3 0003
  Time: 下午 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>文件上传</title>
</head>
<body>

    <form action="/user/requestFile" method="post" enctype="multipart/form-data">

        <input type="file" name="file" value="请选择上传的文件">
        <input type="submit" value="提交">
    </form>
</body>
</html>
