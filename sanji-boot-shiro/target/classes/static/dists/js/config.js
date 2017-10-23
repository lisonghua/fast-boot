var domain = '/api/'
var config = {
    api: {
        role: domain + 'sys/role/',
        menu: domain + 'sys/menu/',
        log: domain + 'sys/log'
    },
    frame: {
        role: 'role/frame',
        menu: 'menu/frame',
        icons: '/page/sys/icons/frame',
    },
    site: location.href,
}

var common_function = {
    reload: function (data) {
        console.log(data)
        if (data.status == "KICKOUT") {
            if (window.top) {//第一级iframe的父窗口
                window.top.location.reload();
            }
        }
    }
}