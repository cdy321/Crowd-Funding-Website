
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn1").click(function () {
                var array = [5,8,12];
                console.log(array.length);
                var requestBody = JSON.stringify(array);
                $.ajax({
                    url:"send/array.html",
                    type:"post",
                    data:requestBody,
                    contentType:"application/json;charset=UTF-8",
                    dataType:"text",
                    success:function (resp) {
                        alert(resp);
                    }
                })
            })
        })
    </script>
</head>
<body>
<button id="btn1">send [5,8,12]one</button>
<a href="admin1/do/login.html">test exception</a>
</body>
</html>
