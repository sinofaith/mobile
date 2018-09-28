$(function () {
    $( "#role_name" ).autocomplete({

    });
    $( "#role" ).autocomplete({

    });
})



function getRole_nameOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#role_name").trigger(e);
    $( "#role_name" ).autocomplete({
        source: "/mobile/caseRegion/getRoleName",
        minLength: 0
    });
}
function getRole_nameName() {
    $( "#role_name" ).autocomplete({
        source: "/mobile/caseRegion/getRoleName",
        minLength: 2
    });
};

function getRoleOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#role").trigger(e);
    $( "#role" ).autocomplete({
        source: "/mobile/caseRegion/getRole",
        minLength: 0
    });
}
function getRoleName() {
    $( "#role" ).autocomplete({
        source: "/mobile/caseRegion/getRole",
        minLength: 2
    });
};
function destroyTooltip(name) {
    $("."+name).tooltip('destroy');
}


// function getRegion() {
//     var flag=false;
//     var unitId = $("#unitId").val().trim();
//     var regionname = $("#regionname").val().trim();
//     if(unitId===''||regionname==''){
//         return flag
//     }
//     $.ajax({
//         url: "/mobile/caseRegion/getRegion?unitId="+unitId+"&regionName="+regionname,
//         type: 'get',
//         async: false,
//         dataType: 'text',
//         success: function(result,status) {
//             if(result==="303"){
//                 $("#regionname").attr('title',"区域已存在").tooltip('show');
//                 flag=false;
//             }else{
//                 if(status==="success"){
//                     flag= true;
//                 }else{
//                     flag=false;
//                 }
//             }
//         }
//     });
//     return flag;
// }

// function addRegion() {
//     var flag = getRegion;
//     var unitId = $("#unitId").val().trim();
//     if(unitId=='') {
//         $("#brandname").attr('title', "品牌名不能为空,请从品牌列表选择后添加区域").tooltip('show');
//         $("#unitname").attr('title', "立案单位不能为空,请从品牌列表选择后添加区域").tooltip('show');
//         flag = false;
//     }
//     var regionname = $("#regionname").val().trim();
//     if(regionname==''){
//         $("#regionname").attr('title',"区域不能为空").tooltip('show');
//         flag=false;
//     }
//     var rolename = $("#rolename").val().trim();
//     if(rolename==''){
//         $("#rolename").attr('title',"角色不能为空").tooltip('show');
//         flag=false;
//     }
//     if(flag==false){
//         return;
//     }
//     $(".btn").attr("disabled","true");
//     var Controller = "/mobile/caseRegion/add"; // 接收后台地址
//     // FormData 对象
//     var form = new FormData();
//     form.append("unitId",unitId);
//     form.append("regionName",regionname);
//     form.append("roleName",rolename);
//     var xhr = new XMLHttpRequest();                // XMLHttpRequest 对象
//     xhr.open("post", Controller, true);
//     xhr.onload = function() {
//         if(xhr.responseText==200){
//             alertify.alert("添加完成!");
//             $(".btn").attr("disabled","true");
//             $('#myModal').modal('hide');
//             setTimeout(function () {document.getElementById("seachDetail").submit()},1000);
//         }
//         if(xhr.responseText==303){
//             $("#regionname").attr('title',"区域已存在").tooltip('show');
//         }
//         if(xhr.responseText==404||xhr.responseText==400){
//             alertify.alert("添加失败")
//         }
//         $(".btn").removeAttr("disabled","disabled");
//     };
//     xhr.send(form);
// }


function caseSkip(a){
    var totalPage = $("#totalPage").text();
    var onPage = $("#num").val();
    if(onPage ==="" || onPage === 0 || parseInt(onPage) <=0){
        alertify.set('notifier','position', 'top-center');
        alertify.error("请输入你要跳转的页数！");
        return;
    }
    if(parseInt(onPage)>parseInt(totalPage)){
        $("#num").val(totalPage);
        return;
    } else {
        location="/mobile/"+a+"/seach?pageNo="+onPage;
    }
}