// �޸�Ĭ�ϵ�ͼ��
function myAddDiyDom(treeId, treeNode) {
// treeId ���������νṹ���ŵ� ul ��ǩ�� id
    console.log("treeId="+treeId);
// ��ǰ���νڵ��ȫ�������ݣ������Ӻ�˲�ѯ�õ��� Menu �����ȫ������
    console.log(treeNode);
// zTree ���� id �Ĺ���
// ���ӣ�treeDemo_7_ico
// ������ul ��ǩ�� id_��ǰ�ڵ�����_����
// ��ʾ����ul ��ǩ�� id_��ǰ�ڵ����š����ֿ���ͨ������ treeNode �� tId ���Եõ�
// ���� id �����ɹ���ƴ�ӳ��� span ��ǩ�� id
    var spanId = treeNode.tId + "_ico";
// ���ݿ���ͼ��� span ��ǩ�� id �ҵ���� span ��ǩ
// ɾ���ɵ� class
// ����µ� class
    $("#"+spanId).removeClass().addClass(treeNode.icon);
}

function myRemoveHoverDom(treeId, treeNode) {
// ƴ�Ӱ�ť��� id
    var btnGroupId = treeNode.tId + "_btnGrp";
// �Ƴ���Ӧ��Ԫ��
    $("#"+btnGroupId).remove();
}

// ���������ڵ㷶Χʱ��Ӱ�ť��
function myAddHoverDom(treeId, treeNode) {
// ��ť��ı�ǩ�ṹ��<span><a><i></i></a><a><i></i></a></span>
// ��ť����ֵ�λ�ã��ڵ��� treeDemo_n_a �����ӵĺ���
// Ϊ������Ҫ�Ƴ���ť���ʱ���ܹ���ȷ��λ����ť������ span����Ҫ�� span �����й��ɵ� id
    var btnGroupId = treeNode.tId + "_btnGrp";
// �ж�һ����ǰ�Ƿ��Ѿ�����˰�ť��
    if($("#"+btnGroupId).length > 0) {
        return ;
    }
// ׼��������ť�� HTML ��ǩ
    var addBtn = "<a id='"+treeNode.id+"' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'   title='����ӽڵ�'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='"+treeNode.id+"' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' ɾ �� �� �� '>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='"+treeNode.id+"' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' title=' �� �� �� �� '>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg'></i></a>";
    // ��ȡ��ǰ�ڵ�ļ�������
    var level = treeNode.level;
// ���������洢ƴװ�õİ�ť����
    var btnHTML = "";
// �жϵ�ǰ�ڵ�ļ���
    if(level == 0) {
// ����Ϊ 0 ʱ�Ǹ��ڵ㣬ֻ������ӽڵ�
        btnHTML = addBtn;
    }
    if(level == 1) {
// ����Ϊ 1 ʱ�Ƿ�֧�ڵ㣬��������ӽڵ㡢�޸�
        btnHTML = addBtn + " " + editBtn;
// ��ȡ��ǰ�ڵ���ӽڵ�����
        var length = treeNode.children.length;
// ���û���ӽڵ㣬����ɾ��
        if(length == 0) {
            btnHTML = btnHTML + " " + removeBtn;
        }
    }
    if(level == 2) {
// ����Ϊ 2 ʱ��Ҷ�ӽڵ㣬�����޸ġ�ɾ��
        btnHTML = editBtn + " " + removeBtn;
    }
// �ҵ����Ű�ť��ĳ�����
    var anchorId = treeNode.tId + "_a";
// ִ���ڳ����Ӻ��渽�� span Ԫ�صĲ���
    $("#"+anchorId).after("<span id='"+btnGroupId+"'>"+btnHTML+"</span>");
}

function generateTree() {
    $.ajax({
        "url":"menu/get/whole/tree.json",
        "type":"post",
        "dataType":"json",
        "success":function(response){
            var result = response.result;
            if(result == "SUCCESS") {
                // 2.���� JSON �������ڴ洢�� zTree ����������
                var setting = {
                    "view":{
                        "addDiyDom":myAddDiyDom,
                        "addHoverDom":myAddHoverDom,
                        "removeHoverDom":myRemoveHoverDom
                    },
                    "data": {
                        "key": {
                            "url": "maomi"
                        }
                    }
                };
                // 3.����Ӧ���л�ȡ�����������νṹ�� JSON ����
                var zNodes = response.data;
                // 4.��ʼ�����νṹ
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if(result == "FAILED") {
                layer.msg(response.message);
            }
        }
    })
}