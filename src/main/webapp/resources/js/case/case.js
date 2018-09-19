$(function () {
    $( "#casename" ).autocomplete({

    });
    $( "#creater" ).autocomplete({

    });
})



function getCaseNameOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#casename").trigger(e);
    $( "#casename" ).autocomplete({
        source: "/mobile/case/getCaseName",
        minLength: 0
    });
}
function getCaseName() {
    $( "#casename" ).autocomplete({
        source: "/mobile/case/getCaseName",
        minLength: 2
    });
};

function getCreaterOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#creater").trigger(e);
    $( "#creater" ).autocomplete({
        source: "/mobile/case/getCreater",
        minLength: 0
    });
}
function getCreater() {
    $( "#creater" ).autocomplete({
        source: "/mobile/case/getCreater",
        minLength: 2
    });
};
function destroyTooltip(name) {
    $("."+name).tooltip('destroy');
}


function getCase() {
    var flag=false;
    var regionId = $("#regionId").val().trim();
    var casename = $("#casename").val().trim();
    if(casename===''||regionId===''){
        return flag;
    }
    $.ajax({
        url: "/mobile/case/getCase?caseName="+casename+"&regionId="+regionId,
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

    var regionId = $("#regionId").val().trim();
    if(regionId==''){
        $("#brandname").attr('title',"所属品牌不能为空,请从品牌列表选择后添加案件").tooltip('show');
        $("#regionname").attr('title',"所属区域不能为空,请从品牌列表选择后添加案件").tooltip('show');

        flag=false;
    }
    var caseName = $("#casename").val().trim();
    if(caseName==''){
        $("#casename").attr('title',"案件名不能为空").tooltip('show');
        flag=false;
    }
    var creater = $("#creater").val().trim();
    if(creater==''){
        $("#creater").attr('title',"创建人不能为空").tooltip('show');
        flag=false;
    }
    if(flag==false){
        return;
    }
    $(".btn").attr("disabled","true");
    var Controller = "/mobile/case/add"; // 接收后台地址
    // FormData 对象
    var form = new FormData();
    form.append("regionId",regionId);
    form.append("caseName",caseName);
    form.append("creater",creater);
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
            $("#brandname").attr('title',"案件名已存在").tooltip('show');
        }
        if(xhr.responseText==404||xhr.responseText==400){
            alertify.alert("添加失败")
        }
        $(".btn").removeAttr("disabled","disabled");
    };
    xhr.send(form);
}


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