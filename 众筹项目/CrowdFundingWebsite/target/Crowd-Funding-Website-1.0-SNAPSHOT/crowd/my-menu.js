// 修改默认的图标
function myAddDiyDom(treeId, treeNode) {
// treeId 是整个树形结构附着的 ul 标签的 id
    console.log("treeId="+treeId);
// 当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性
    console.log(treeNode);
// zTree 生成 id 的规则
// 例子：treeDemo_7_ico
// 解析：ul 标签的 id_当前节点的序号_功能
// 提示：“ul 标签的 id_当前节点的序号”部分可以通过访问 treeNode 的 tId 属性得到
// 根据 id 的生成规则拼接出来 span 标签的 id
    var spanId = treeNode.tId + "_ico";
// 根据控制图标的 span 标签的 id 找到这个 span 标签
// 删除旧的 class
// 添加新的 class
    $("#"+spanId).removeClass().addClass(treeNode.icon);
}

function myRemoveHoverDom(treeId, treeNode) {
// 拼接按钮组的 id
    var btnGroupId = treeNode.tId + "_btnGrp";
// 移除对应的元素
    $("#"+btnGroupId).remove();
}

// 在鼠标移入节点范围时添加按钮组
function myAddHoverDom(treeId, treeNode) {
// 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
// 按钮组出现的位置：节点中 treeDemo_n_a 超链接的后面
// 为了在需要移除按钮组的时候能够精确定位到按钮组所在 span，需要给 span 设置有规律的 id
    var btnGroupId = treeNode.tId + "_btnGrp";
// 判断一下以前是否已经添加了按钮组
    if($("#"+btnGroupId).length > 0) {
        return ;
    }
// 准备各个按钮的 HTML 标签
    var addBtn = "<a id='"+treeNode.id+"' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;'   title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='"+treeNode.id+"' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 删 除 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='"+treeNode.id+"' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' title=' 修 改 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg'></i></a>";
    // 获取当前节点的级别数据
    var level = treeNode.level;
// 声明变量存储拼装好的按钮代码
    var btnHTML = "";
// 判断当前节点的级别
    if(level == 0) {
// 级别为 0 时是根节点，只能添加子节点
        btnHTML = addBtn;
    }
    if(level == 1) {
// 级别为 1 时是分支节点，可以添加子节点、修改
        btnHTML = addBtn + " " + editBtn;
// 获取当前节点的子节点数量
        var length = treeNode.children.length;
// 如果没有子节点，可以删除
        if(length == 0) {
            btnHTML = btnHTML + " " + removeBtn;
        }
    }
    if(level == 2) {
// 级别为 2 时是叶子节点，可以修改、删除
        btnHTML = editBtn + " " + removeBtn;
    }
// 找到附着按钮组的超链接
    var anchorId = treeNode.tId + "_a";
// 执行在超链接后面附加 span 元素的操作
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
                // 2.创建 JSON 对象用于存储对 zTree 所做的设置
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
                // 3.从响应体中获取用来生成树形结构的 JSON 数据
                var zNodes = response.data;
                // 4.初始化树形结构
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if(result == "FAILED") {
                layer.msg(response.message);
            }
        }
    })
}