function destroyTooltip(name) {
    $("."+name).tooltip('destroy');
}

function getCase() {
    var flag=false;
    var casename = $("#casename").val().trim();
    $.ajax({
        url: "/mobile/case/getCase?casename="+casename,
        type: 'get',
        async: false,
        dataType: 'text',
        success: function(result,status) {
            if(result==="303"){
                $("#casename").attr('title',"案件名已存在").tooltip('show');
                flag=false;
            }else{
                if(status==="success"){
                    flag= true;
                }else{
                    flag=false;
                }
            }
        }
    });
    return flag;
}

function addCase() {
    var flag = getCase();
    var casename = $("#casename").val().trim();
    if(casename==''){
        $("#casename").attr('title',"案件名不能为空").tooltip('show');
        flag=false;
    }
    var brandname = $("#brandname").val().trim();
    if(brandname==''){
        $("#brandname").attr('title',"所属品牌不能为空").tooltip('show');
        flag=false;
    }
    var regionname = $("#regionname").val().trim();
    if(regionname==''){
        $("#regionname").attr('title',"所属区域不能为空").tooltip('show');
        flag=false;
    }
    if(flag==false){
        return;
    }
    $(".btn").attr("disabled","true");
    var Controller = "/mobile/brand/add"; // 接收后台地址
    // FormData 对象
    var form = new FormData();
    form.append("brandname", brandname); // 可以增加表单数据
    var xhr = new XMLHttpRequest();                // XMLHttpRequest 对象
    xhr.open("post", Controller, true);
    xhr.onload = function() {
        if(xhr.responseText==200){
            alertify.alert("添加完成!");
            $(".btn").attr("disabled","true");
            $('#myModal').modal('hide');
            setTimeout(function () {document.getElementById("seachDetail").submit()},1000);
        }
        if(xhr.responseText==303){
            $("#brandname").attr('title',"品牌名已存在").tooltip('show');
        }
        if(xhr.responseText==404||xhr.responseText==400){
            alertify.alert("添加失败")
        }
        $(".btn").removeAttr("disabled","disabled");
    };
    xhr.send(form);
}
