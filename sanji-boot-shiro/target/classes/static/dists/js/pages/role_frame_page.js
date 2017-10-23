/**
 * Created by sunxyz on 2017/3/17.
 */
(function () {
    var id = 0;
    var app;
    var api_path = config.api.role
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form()
            , layer = layui.layer

        //自定义验证规则
        form.verify({
            name: function (value) {
                if (value.length < 3) {
                    return '名称至少得3个字符啊';
                }
            }
        });

        //监听提交
        form.on('submit(demo1)', function (data) {
            if (id == 0) {
                add();
            } else {
                edit()
            }
            return false;
        });
        var f = function () {
            //渲染页面
            var obj = JSON.parse($("#json").val());
            id = obj.id == undefined ? 0 : obj.id;
//                console.log('json-obj', obj)
            app = new Vue({
                el: '#app',
                data: {
                    obj: obj,
                    menuIds: []
                }
            })
            var role_id = id;
            buildZTreeMenus(role_id);
        }
        setTimeout(f, 200)
    });

    function edit() {
        var url = api_path + id;
        var method = "PUT";
        save(url, method);
//            console.log("PUT")
    }

    function add() {
        var url = api_path;
        var method = "POST";
        save(url, method);
//            console.log("post")
    }

    function save(url, method) {
        var options = {
            url: url,
            method: method,
            success: function (data) {
                layer.msg(data.msg)
            }
        };
        $("#app").ajaxSubmit(options)
        setTimeout(function () {
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            parent.vue_app.loadData();
        }, 3000);
    }

    var menus = [];
    <!--z_tree-->
    function buildZTreeMenus(role_id) {
        buildRoleMenusChecked(role_id, function (zNodes, roleMenuIds) {
            menus = roleMenuIds;
            app.menuIds = menus;
            function onCheck(e, treeId, treeNode) {
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = treeObj.getCheckedNodes(true);
                menus = [];
                for (var i = 0; i < nodes.length; i++) {
                    menus.push(nodes[i].id); //获取选中节点的值
                }
                app.menuIds = menus;
            }

            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: onCheck
                }
            };
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        })
    }

})();