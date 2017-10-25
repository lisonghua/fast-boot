/**
 * Created by sunxyz on 2017/3/17.
 */
var vue_app ;
(function () {
    var id = 0;
    var path = config.api.menu;
    var frame = config.frame.icons;
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
            form.render();
            if (id) {
                edit()
            } else {
                add();
            }
            return false;
        });
        var f = function () {
            var obj = JSON.parse($("#json").val());
            id = obj.id;
//            console.log(obj)
            var app = new Vue({
                el: '#app',
                data: {
                    obj: obj,
                    icon: obj.icon,
                    isChild: obj.isParent
                },
                methods:{
                    click_icon:function () {
                        // console.log("------")
                        open_page();
                    }
                }

            });
            vue_app = app;
            if(obj.visible){
                $("#visible").attr("checked",true);
                // console.log("------------------")
            }
            form.render();
        }
        setTimeout(f, 200)
    });

    function edit() {
        var url = path + id;
        var method = "PUT";
        save(url, method);
    }

    function add() {
        var url = path;
        var method = "POST";
        save(url, method);
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
            console.log( parent.vue_app)
            parent.vue_app.load();
        }, 3000);
    }
    function open_page() {
        // 当你试图在当前页获取iframe页的DOM元素时，你可以用此方法。selector即iframe页的选择器
        layer.open({
            type: 2,
            title: '选择图标',
            shadeClose: true,
            shade: false,
            area: ['693px', '536px'],
            content: frame
        });
    }
})();