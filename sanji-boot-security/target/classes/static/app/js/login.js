$(function () {
    // Waves初始化
    Waves.displayEffect();
    // 输入框获取焦点后出现下划线
    $('.form-control').focus(function () {
        $(this).parent().addClass('fg-toggled');
    }).blur(function () {
        $(this).parent().removeClass('fg-toggled');
    });
});
Checkbix.init();
$(function () {
    // 点击登录按钮
    $('#login-bt').click(function () {
        login();
    });
    // 回车事件
    $('#username, #password').keypress(function (event) {
        if (13 == event.keyCode) {
            login();
        }
    });
});
// 登录
function login() {
    $.ajax({
        url: 'login',
        type: 'POST',
        data: {
            username: $('#username').val(),
            password: $('#password').val(),
            'remember-me': $('#rememberMe').is(':checked'),
        },
        beforeSend: function () {
            if ($('#username').val() == '') {
                $('#username').focus();
                return false;
            }
            if ($('#password').val() == '') {
                $('#password').focus();
                return false;
            }
        },
        success: function (json) {
            console.log(json);
            if (json.status == 'SUCCESS') {
                location.href = 'http://' + location.host;
            } else {
                alert(json.msg);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}