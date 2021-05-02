<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<%@include file="/WEB-INF/model-role-add.jsp"%>
<%@include file="/WEB-INF/model-role-edit.jsp"%>
<%@include file="/WEB-INF/model-role-confirm.jsp"%>
<%@include file="/WEB-INF/modal-role-assign-auth.jsp"%>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css">
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-role.js"></script>
<script type="text/javascript">

    $(function(){
// 1.为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.Keyword = "";
// 2.调用执行分页的函数，显示分页效果
        generatePage();
        $("#searchBtn").click(function () {
            window.Keyword = $("#keywordInput").val();
            generatePage();
        });
        
        $("#showAddModalBtn").click(function () {
            $("#AddModal").modal("show")
        });
        
        $("#saveRoleBtn").click(function () {
            var roleName = $.trim($("#AddModal [name=roleName]").val());
            $.ajax({ "url": "role/save.json", "type":"post", "data": { "name": roleName
            },"dataType": "json", "success":function(response){
                var result = response.result;
                if(result == "SUCCESS") {
                    layer.msg("操作成功！");
                    // 将页码定位到最后一页
                    window.pageNum = 99999999;
                    // 重新加载分页数据
                    generatePage();
                }
                if(result == "FAILED") {
                    layer.msg("操作失败！"+response.message);
                }
            },"error":function(response){
                layer.msg(response.status+" "+response.statusText);
            }
            });
            // 关闭模态框
            $("#AddModal").modal("hide");
           // 清理模态框
            $("#AddModal [name=roleName]").val("");
        })

        $("#rolePageBody").on("click",".pencilBtn",function(){
               // 打开模态框
            $("#EditModal").modal("show");
              // 获取表格中当前行中的角色名称
            var roleName = $(this).parent().prev().text();
            // 获取当前角色的 id
              // 依据是：var pencilBtn = "<button id='"+roleId+"' ……这段代码中我们把 roleId 设置到id 属性了
              // 为了让执行更新的按钮能够获取到 roleId 的值，把它放在全局变量上
            window.roleId = this.id;
              // 使用 roleName 的值设置模态框中的文本框
            $("#EditModal [name=roleName]").val(roleName);
        });

        $("#updateRoleBtn").click(function(){
            // ①从文本框中获取新的角色名称
            var roleName = $("#EditModal [name=roleName]").val();
// ②发送 Ajax 请求执行更新
            $.ajax({ "url":"role/update.json", "type":"post", "data":{ "id":window.roleId, "name":roleName
            },"dataType":"json", "success":function(response){
                var result = response.result;
                if(result == "SUCCESS") {
                    layer.msg("操作成功！");
// 重新加载分页数据
                    generatePage();
                }
                if(result == "FAILED") {
                    layer.msg("操作失败！"+response.message);
                }
            },"error":function(response){
                layer.msg(response.status+" "+response.statusText);
            }
            });
// ③关闭模态框
            $("#EditModal").modal("hide");
        });


        $("#removeRoleBtn").click(function () {
            var requestBody = JSON.stringify(window.roleIdArray);
            $.ajax({
                "url":"/role/delete/by/role/id/array.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    if(result == "SUCCESS") {
                        layer.msg("操作成功！");
                        // 重新加载分页数据
                        generatePage();
                    }
                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }
                },"error":function(response){
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            $("#ConfirmModal").modal("hide");
        });

        $("#rolePageBody").on("click",".removeBtn",function(){
            var roleName = $(this).parent().prev().text();
            var roleArray = [{
                roleId:this.id,
                roleName:roleName
            }];
            showConfirmModal(roleArray);
        });
        
        
        $("#summaryBox").click(function () {
            var currentStatus = this.checked;
            $(".itemBox").prop("checked",currentStatus);
        });

        $("#rolePageBody").on("click",".itemBox",function(){
            var sumBox = $(".itemBox:checked").length;
            var totalBox = $(".itemBox").length;
            $("#summaryBox").prop("checked", sumBox == totalBox);
        });

        //批量删除
        
        $("#batchRemove").click(function () {
            var roleArray = [];
            $(".itemBox:checked").each(function () {
                var roleId = this.id;
                var roleName = $(this).parent().next().text();
                roleArray.push({
                    "roleId":roleId,
                    "roleName":roleName
                });
            });
            if(roleArray.length==0){
                layer.msg("请至少选择一个执行删除!");
                return;
            };
            showConfirmModal(roleArray);

        })

        $("#rolePageBody").on("click",".checkBtn",function(){
            window.roleId = this.id;
            $("#roleAssignAuthModal").modal("show");
            fillAuthTree();
        });

        $("#roleAssignAuthBtn").click(function () {
            var authIdArray = [];
            var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
            var checkedNodes = zTreeObj.getCheckedNodes();
            for(var i = 0; i < checkedNodes.length;i++){
                var checkedNode = checkedNodes[i];
                var authId = checkedNode.id;
                authIdArray.push(authId);
            }
            var requestBody = {
                "authIdArray":authIdArray,
                "roleId":[window.roleId],
            };
            requestBody = JSON.stringify(requestBody);
            $.ajax({
                "url":"assign/do/role/assign/auth.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "success":function (response) {
                    var result = response.result;
                    if(result == "SUCCESS"){
                        layer.msg("操作成功!");
                    }
                    if(result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            $("#roleAssignAuthModal").modal("hide");
        })

    });
    
</script>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" class="btn btn-warning" type="button"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button id="batchRemove" class="btn btn-danger" style="margin-left: 10px; float: right;" type="button"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button id="showAddModalBtn" class="btn btn-primary" style="float: right;"  type="button"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">
                            </tbody>
                            <tfoot>
                            <tr>
                                <td align="center" colspan="6">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>