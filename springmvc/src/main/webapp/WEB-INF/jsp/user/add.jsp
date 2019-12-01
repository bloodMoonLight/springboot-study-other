<%--
  Created by IntelliJ IDEA.
  User: 张鸿志
  Date: 2019/12/1
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#submit").click(function(){
                debugger;
                var userName = $("#userName").val();
                var note = $("#note").val();
                if($.trim(userName) == ''){
                    alert("用户名不能为空!");
                    return;
                }
                var params = {userName : userName,note : note};
                $.ajax({
                    url: "/user/insert",
                    type: "POST",
                    contentType: "application/json",
                    data : JSON.stringify(params),
                    success : function (result) {
                        if(result == null || result.id == null){
                            alert("插入失败");
                            return;
                        }
                        alert("插入成功");
                    }
                });
            });

        });
    </script>
</head>
<body>

    <div style="margin: 20px 0;"></div>
    <form id="insertForm">
        <table>
            <tr>
                <td>用户名称：</td>
                <td><input  id="userName" name="userName" /></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input  id="note" name="note" /></td>
            </tr>
            <tr>
                <td></td>
                <td align="right"><input id="submit" type="button" value="提交"/></td>
            </tr>
        </table>

    </form>
</body>
</html>
