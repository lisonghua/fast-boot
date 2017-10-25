/**
 * Created by sunxyz on 2017/4/19.
 */
(function () {

    function getRootMenu(cb) {
        $.get("/api/sys/menu/tree", cb)
    }

    function getHomeUrl(cb) {
        $.get("/api/sys/menu/home_url", cb)
    }

    function getUserInfo(cb) {
        $.get("/api/sys/user_info", cb);
    }


    $(".frame").css("padding-right", '0px');
    function fixContentIframe() {
        var contentIframe = $("#content-iframe");
        var neg = $('.main-header').outerHeight() + $('.main-footer').outerHeight();
        var window_height = $(window).height();
        var content_header_height = $(".content-header").height();
        contentIframe.css("min-height", window_height - neg - content_header_height - 26);
        function frame() {
            /*contentIframe.contents().find("body").css('background-color', "#EEE");*/
            vue.pageHeader = contentIframe.contents().find("title").text()
        }

        setTimeout(frame, 300)
    };

    //设置用户信息
    setTimeout(function () {
        getUserInfo(function (userInfo) {
            if (userInfo.status == "SUCCESS") {
                user.userInfo.name = userInfo.content.loginName;
                vue.userInfo.name = userInfo.content.loginName;
            }
        })
    }, 300);

    //用户信息 为了使用admin LTE 换肤功能
    var user = new Vue({
        el: '#user-info',
        data: {
            userInfo: {
                name: "user",
                description: "hello world",
                avater: "/plugins/adminLTE/img/user2-160x160.jpg"
            }
        }
    })

    var vue = new Vue({
        el: '#app',
        data: {
            menus: [],
            pageHeader: "首页",
            frame_src: "",
            userInfo: {
                name: "user",
                description: "hello world",
                avater: "/plugins/adminLTE/img/user2-160x160.jpg"
            },
            menu_click: 0
        }, created: function () {
            this.load();
        }, methods: {
            load: function () {
                var _this = this;
                getRootMenu(function (root) {
                    _this.menus = root[0].menuTrees;
                })
                fixContentIframe();
            },
            setMenuClick: function (menu_id) {
                this.menu_click = menu_id;
            }
        }, watch: {
            frame_src: function (data) {
                fixContentIframe();
            },
            pageHeader: function (data) {
                var login_page = "用户登录";
                //判断是否session超时
                if (data.indexOf(login_page) > -1) {
                    var domain = window.location.host;
                    var arrUrl = document.location.toString().split("//");
                    window.location.href = arrUrl[0] + "//" + domain;
                }
            }
        }

    })
    //等待vue初始化完成
    setTimeout(function () {
        var routes = {
            '/?((\w|.)*)': function (path) {
                vue.frame_src = path
            },
        };
        var router = Router(routes);
        router.init();
        getHomeUrl(function (url) {
            if (vue.frame_src == "") {
                vue.frame_src = url;
            }
        });
    }, 200);

})()