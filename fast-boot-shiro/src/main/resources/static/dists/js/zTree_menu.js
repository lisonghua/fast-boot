/**
 * Created by sunxyz on 2017/3/17.
 */

//根据角色对菜单进行选中
function buildRoleMenusChecked(id, cb) {
    var api_path_role = config.api.role;
    var api_path_menu =config.api.menu+"/all";
    function getAllMenus(cb) {
        var menus = [];
        $.get(api_path_menu, function (data) {
            for (var index in data) {
                var d = data[index];
                var obj = {};
                obj.id = d.id;
                obj.pId = d.parentId;
                obj.name = d.name;
                obj.open = true;
                menus.push(obj);
            }

            cb(menus);
        });
    }

    function getRoleMenus(roleId, cb) {
        $.get(api_path_role+ roleId + "/menu", cb);
    }

    getAllMenus(function (allMenus) {
        getRoleMenus(id, function (roleMenuIds) {
//                console.log("roleMenuIds",roleMenuIds)
            for (var index in roleMenuIds) {
                var roleMenuId = roleMenuIds[index]
                for (var i in allMenus) {
                    var menu = allMenus[i];
                    if (menu.id == roleMenuId) {
                        menu.checked = true;
                        break;
                    }
                }
            }
            cb(allMenus, roleMenuIds);
        })
    })
}


