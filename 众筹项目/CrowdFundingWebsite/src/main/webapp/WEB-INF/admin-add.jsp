<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/to/main/page.html">首页</a></li>
                <li><a href="/admin/get/page.html">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float: right; cursor: pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" action="admin/save.html" method="post">
                        <p>${requestScope.exception.message}</p>
                        <div class="form-group">
                            <label for="exampleInputPassword1">登录账号</label>
                            <input name="loginAcct" class="form-control" id="exampleInputPassword1" type="text" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">登录密码</label>
                            <input name="userPswd" class="form-control" id="exampleInputPassword1" type="text" placeholder="请输入登陆密码">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户名</label>
                            <input name="userName" class="form-control" id="exampleInputPassword1" type="text" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input name="email" class="form-control" id="exampleInputEmail1" type="email" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button class="btn btn-danger" type="reset"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>