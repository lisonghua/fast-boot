/**
 * Created by sunxyz on 2017/7/12.
 */
// jQuery 3.0
var iAjax = function (url, data, methodType, beforeSend, cb, ecb) {
    if (data == null) {
        return $.ajax({
            url: url,
            type: methodType,
            async: false,
            dataType: "json",
            contentType: "application/json;charset=utf-8", //设置请求头信息
            beforeSend: beforeSend,
        }).done(function (data) {
            cb(data)
        }).fail(function (jqXHR, textStatus) {
            ecb(jqXHR, textStatus);
        })
    } else {
        return $.ajax({
            url: url,
            data: JSON.stringify(data),
            type: methodType,
            async: false,
            dataType: "json",
            contentType: "application/json;charset=utf-8", //设置请求头信息
            beforeSend: beforeSend,
        }).done(function (data) {
            cb(data)
        }).fail(function (jqXHR, textStatus) {
            ecb(jqXHR, textStatus);
        })
    }

}

var iPost = function (url, data, beforeSend, cb, ecb) {
    return iAjax(url, data, "POST", beforeSend, cb, ecb)
}

var iDelete = function (url, data, beforeSend, cb, ecb) {
    return iAjax(url, data, "DELETE", beforeSend, cb, ecb)
}

var iPut = function (url, data, beforeSend, cb, ecb) {
    return iAjax(url, data, "PUT", beforeSend, cb, ecb)
}

var iGet = function (url, cb, ecb) {
    return iAjax(url, null, "GET", null, cb, ecb)
}