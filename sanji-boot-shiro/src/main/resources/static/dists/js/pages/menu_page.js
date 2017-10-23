/**
 * Created by sunxyz on 2017/3/17.
 */
var vue_app;
(function () {
    var api_path = config.api.menu;
    var frame = config.frame.menu;
    Vue.filter('visible-formatter', function (value) {
        return value ? "可见" : "不可见";
    })
    var app = new Vue({
        el: '#app',
        data: {
            menus: [],
            selectObj: undefined
        },
        created: function () {
            this.load();
        },
        methods: {
            load: function () {
                var _this = this;
                $.get(api_path, function (data) {
                    common_function.reload(data);
                    for (var i in data) {
                        var d = data[i];
                        d.select = false;
                    }
                    // console.log(data)
                    _this.menus = data;
                    _this.buildTreeTable();
                })
            },
            buildTreeTable: function () {
                var f = function () {
                    $("#example-advanced").treetable({
                        expandable: true
                    });
                }
                setTimeout(f, 160);
            },
            select: function (menu) {//添加鼠标选中效果
                menu.select = menu.select ? false : true;
                if (menu.select) {
                    $("tbody tr").removeClass("select");
                    var f = function () {
                        $("#" + menu.id).addClass("select")
                    }
                    setTimeout(f, 100);
                }
                this.selectObj = menu;
            },
            edit: function () {
                edit(this.selectObj);
            },
            add: function () {
                add(this.selectObj);
            },
            deleteMenu: function () {
                deleteMenu(this.selectObj)
            },
            reload: function () {
                location.reload();
            }
        }
    })

    vue_app = app;
    function edit(obj) {
        if (!obj) {
            layer.msg('请选中要修改的列');
            return;
        }
        obj.isParent = false;
        open_page(obj);
    }

    function add(obj) {
        if (!obj) {
            layer.msg('请选中要添加子菜单的列');
            return;
        }
        var parent = {p_id: obj.id, p_name: obj.name, isParent: true, visible: true};
        open_page(parent)
    }

    function deleteMenu(obj) {
        if (!obj) {
            layer.msg('请选中要删除的菜单列');
            return;
        }
        layer.confirm('您确认删除？建议在编辑中设置对用户不可见！！', {
            icon: 0,
            btn: ['确定', '取消'] //按钮
        }, function () {
            var id = obj.id;
            $.post(api_path + id, {_method: "DELETE"}, function (data) {
                layer.msg(data.msg, {icon: data.status == "SUCCESS" ? 1 : 2});
                app.load();
            }).error(function () {
                layer.msg("删除失败，不建议删除", {icon: 2});
            })
        }, function () {
            layer.msg('取消成功', {icon: 1});
        });
    }

    function open_page(obj) {
        var json = JSON.stringify(obj);
        // 当你试图在当前页获取iframe页的DOM元素时，你可以用此方法。selector即iframe页的选择器
        layer.open({
            type: 2,
            title: '编辑菜单',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '636px'],
            content: frame,
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                body.find('#json').val(json)
            }
        });
    }
})();