
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 模态框（Modal） -->
<div id="ConfirmModal" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <h4>请确认是否要删除下列角色</h4>
                <div id="roleNameDiv" style="text-align: center"></div>
            </div>
            <div class="modal-footer">
                <button id="removeRoleBtn" type="button" class="btn btn-primary">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
