<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/2 0002
  Time: 下午 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据验证，测试JSR-303</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            //请求验证的POJO
            var pojo = {
                id: null,
                date: '2017-08-09',
                doubleValue : 99999.09,
                integer : 100,
                range: 1000,
                email: 'email',
                size: 'sadkkljflsdkfsa',
                regexp: 'a,b,c,d'
            }
            $.post({
                url: "/user/validate",
                contentType: "application/json",
                data: JSON.stringify(pojo),
                success: function (result) {
                    alert(result);
                }
            });
        });
    </script>
</head>
<body>
    
    


</body>
</html>
