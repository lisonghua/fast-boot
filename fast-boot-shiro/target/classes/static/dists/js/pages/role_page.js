/**
 * Created by sunxyz on 2017/3/17.
 */
var vue_app;
//首先使用一个闭包,防止污染全局对象
(function () {
    var api_path = config.api.role;
    var frame = config.frame.role;
    Vue.filter('date-formatter', function (value) {
        return moment(value).format('YYYY.MM.DD');
    })
    var app = new Vue({
        el: "#app",
        data: {
            roles: [],
            name: "",
            selectObj: undefined,
            pageTotal: 0,
            pageCurrent: 1,
            pageSize: 20,
        },
        computed: {
            indexs: function () {
                var left = 1;
                var right = this.pageTotal;
                var ar = [];
                if (this.pageTotal >= 11) {
                    if (this.pageCurrent > 5 && this.pageCurrent < this.pageTotal - 4) {
                        left = this.pageCurrent - 5;
                        right = this.pageCurrent + 4;
                    } else {
                        if (this.pageCurrent <= 5) {
                            left = 1;
                            right = 10;
                        } else {
                            right = this.pageTotal;
                            left = this.pageTotal - 9;
                        }
                    }
                }
                while (left <= right) {
                    ar.push(left);
                    left++;
                }
                return ar;
            },
            showLast: function () {
                if (this.pageCurrent == this.pageTotal || this.pageTotal == 0) {
                    return false;
                }
                return true;
            },
            showFirst: function () {
                if (this.pageCurrent == 1) {
                    return false;
                }
                return true;
            }
        },
        watch: {},
        created: function () {
            //加载数据
            this.loadData();
        },
        methods: {
            pageIndexClick: function (index) {
                if (index != this.pageCurrent) {
                    this.pageCurrent = index;
                }
                this.loadData();
            },
            search: function () {
                this.pageCurrent = 1;
                this.loadData();
            },
            loadData: function () {
                var _this = this;
                var param = {
                    page: _this.pageCurrent - 1,
                    size: _this.pageSize,
                    name: _this.name
                };

                getRoleData(param, function (data) {
                    common_function.reload(data);
                    var ds = data.content;
                    for (var i in ds) {
                        var d = ds[i];
                        d.select = false;
                    }
                    _this.roles = ds;
                    _this.pageTotal = data.totalPages;
                });
            },
            select: function (obj) {//添加鼠标选中效果
                obj.select = obj.select ? false : true;
                if (obj.select) {
                    $("tbody tr").removeClass("select");
                    var f = function () {
                        $("#" + obj.id).addClass("select")
                    }
                    setTimeout(f, 100);
                }
//                    console.log("-----------")
                this.selectObj = obj;
            },
            edit: function () {
                edit(this.selectObj);
            },
            add: function () {
                add();
            },
            deleteObj: function () {
                deleteObj(this.selectObj)
            },
            load: function () {
                this.loadData();
            }
        }

    });


    vue_app = app;

    function getRoleData(param, cb) {
        $.get(api_path, param, cb);
    }


    function edit(obj) {
        if (!obj) {
            layer.msg('请选中要修改的列');
            return;
        }
        obj.isParent = false;
        open_page(obj);
    }

    function add() {
        var obj = {method: "POST"};
        open_page(obj)
    }

    function deleteObj(obj) {
        if (!obj) {
            layer.msg('请选中要删除的列');
            return;
        }
        layer.confirm('您确认删除？', {
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
            area: ['816px', '636px'],
            content: frame,
            success: function (layero, index) {
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                body.find('#json').val(json)
            }
        });
    }

})();