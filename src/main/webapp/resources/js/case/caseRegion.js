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
        minLength: 0,
        select:function () {
            destroyTooltip('role_name');
        }
    });
}
function getRole_nameName() {
    $( "#role_name" ).autocomplete({
        source: "/mobile/caseRegion/getRoleName",
        minLength: 1
    });
};

function getRoleOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#role").trigger(e);
    $( "#role" ).autocomplete({
        source: "/mobile/caseRegion/getRole",
        minLength: 0,
        select:function () {
            destroyTooltip('role');
        }
    });
}
function getRoleName() {
    $( "#role" ).autocomplete({
        source: "/mobile/caseRegion/getRole",
        minLength: 1
    });
};

function destroyTooltip(name) {
    $("."+name).tooltip('destroy');
}

function getPerson(id) {

    $.ajax({
        url: "/mobile/caseRegion/getPerson?role_id="+id,
        type: 'get',
        dataType: 'json',
        success: function(result) {
            $("#editname").attr("value",result.role_name);
            $("#editsfzhm").attr("value",result.sfzhm);
            $("#editrole").attr("value",result.role);
            $("#editrole_id").attr("value",result.role_id);
            $("#inserttime").attr("value",result.insertTime);
            $("#region_id").attr("value",result.region_id);
        }
    });
}

function editRole() {
    var flag = true;
    var personname = $("#editname").val().trim();
    var sfzhm = $("#editsfzhm").val().trim();
    var role = $("#editrole").val().trim();
    var role_id = $("#editrole_id").val().trim();
    var inserttime = $("#inserttime").val().trim();
    var region_id = $("#region_id").val().trim();
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(sfzhm!='' && reg.test(sfzhm) === false&&sfzhm!='未获取到信息') {
        $("#editsfzhm").attr('data-original-title', "身份证号码不合法").tooltip('show');
        flag = false;
    }
    if(personname==""){
        $("#editname").attr('data-original-title', "姓名不能为空").tooltip('show');
        flag= false;
    }
    if(sfzhm===''&&sfzhm!='未获取到信息'){
        $("#editsfzhm").attr('data-original-title', "身份证号码不能为空").tooltip('show');
        flag= false;
    }
    if(role===''){
        $("#editrole").attr('data-original-title', "角色不能为空").tooltip('show');
        flag= false;
    }
    if(role_id===''){
        alertify.set('notifier','position', 'top-center');
        alertify.error("错误!请刷新页面后重新点击编辑按钮");
        return;
    }
    if(flag){
        $.post(
            "./editRole",
            {role_name:personname,sfzhm:sfzhm,role:role,role_id:role_id,inserttime:inserttime,region_id:region_id},
            function(result){
                if(result == "200"){
                    alertify.set('notifier','position', 'top-center');
                    alertify.success("修改成功!");
                    $(".btn").attr("disabled","true");
                    $('#editModal').modal('hide');
                    setTimeout(function () {document.getElementById("seachDetail").submit()},1000);
                }else{
                    alertify.set('notifier','position', 'top-center');
                    alertify.error("修改失败...");
                }
            },
            "json"
        )
    }
    $(".btn").removeAttr("disabled","disabled");
}

function getSFZHM(){
    var SFZHM = $('#sfzhm').val().trim();
    var flag = true;
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(SFZHM!='' && reg.test(SFZHM) === false) {
        $("#sfzhm").attr('data-original-title', "身份证号码不合法").tooltip('show');
        flag = false;
    }
    // 信息完整时
    if(SFZHM!='' && flag){
        $.get(
            "./getSFZHM?sfzhm="+SFZHM,
            function(result,status){
                if(result == "303"){
                    $("#sfzhm").attr('data-original-title', "身份证号码在此区域已注册").tooltip('show');
                    flag = false;
                }else{
                    if(status==="success"){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
            },
            "json"
        );
    }
    return flag;
}

function addRole(){
    var flag = getSFZHM();
    var brandname = $('#brandname').val().trim();
    var unitname = $('#brandname').val().trim();
    var caseName = $('#caseName').val().trim();
    var regionName = $('#regionName').val().trim();
    var role_name = $('#role_name').val().trim();
    var sfzhm = $('#sfzhm').val().trim();
    var role = $('#role').val().trim();
    if(brandname == ''){
        $("#brandname").attr('data-original-title',"品牌名不能为空,请从品牌列表选择后添加人员").tooltip('show');
        flag = false;
    }
    if(unitname == ""){
        $("#unitname").attr('data-original-title',"立案单位不能为空,请从品牌列表选择后添加人员").tooltip('show');
        flag = false;
    }
    if(caseName == ''){
        $("#caseName").attr('data-original-title',"案件名不能为空,请从案件列表选择后添加人员").tooltip('show');
        flag = false;
    }
    if(regionName == ''){
        $("#regionName").attr('data-original-title',"区域不能为空,请从案件列表选择后添加人员").tooltip('show');
        flag = false;
    }
    if(role_name == ''){
        $("#role_name").attr('data-original-title',"姓名不能为空").tooltip('show');
        flag = false;
    }
    if(sfzhm == ''){
        $("#sfzhm").attr('data-original-title',"身份证号码不能为空").tooltip('show');
        flag = false;
    }
    if(role == ''){
        $("#role").attr('data-original-title',"角色不能为空").tooltip('show');
        flag = false;
    }
    $(".btn").attr("disabled","true");
    if(flag){
        $.post(
            "./addRole",
            {role_name:role_name,sfzhm:sfzhm,role:role},
            function(result){
                if(result == "200"){
                    alertify.set('notifier','position', 'top-center');
                    alertify.success("添加完成!");
                    $(".btn").attr("disabled","true");
                    $('#myModal').modal('hide');
                    setTimeout(function () {document.getElementById("seachDetail").submit()},1000);
                }else if(result == "303"){
                    alertify.set('notifier','position', 'top-center');
                    alertify.error("添加失败!");
                }
            },
            "json"
        );
    }
    $(".btn").removeAttr("disabled","disabled");
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


function UploadQZ() {
    $(".btn").attr("disabled","true");
    var fileObj = document.getElementById("file");// js 获取文件对象
    var file = $("#file").val();
    var flag = true;
    if(file==''){
        $("#file").attr('data-original-title',"请选择取证报告report文件夹").tooltip('show');
        flag = false;
    }
    var regionId = $("#regionId").val();
    if(regionId==''){
        $("#fregionName").attr('data-original-title',"区域不能为空,请从区域列表选择后添加取证报告").tooltip('show');
        flag = false;
    }
    var myjzm = $("#jzm").val();
    if(myjzm==''){
        $("#jzm").attr('data-original-title',"机主名不能为空").tooltip('show');
        flag = false;
    }
    var mysjy = $("#sjy").val();
    if(mysjy==''){
        $("#sjy").attr('data-original-title',"数据来源不能为空").tooltip('show');
        flag = false;
    }
    var froleName = $("#froleName").val();
    if(froleName==''){
        $("#froleName").attr('data-original-title',"角色不能为空").tooltip('show');
        flag = false;
    }
    if(!flag){
        $(".btn").removeAttr("disabled","disabled");
        return;
    }
    var FileController = "/mobile/Upload/importmeiya"; // 接收上传文件的后台地址
    // FormData 对象
    var form = new FormData();
    form.append("regionId", regionId);
    form.append("myjzm",myjzm);// 可以增加表单数据
    form.append("mysjy",mysjy);// 可以增加表单数据
    form.append("role",froleName);// 可以增加表单数据
    for(i=0;i<fileObj.files.length;i++){
        form.append("file", fileObj.files[i]);
        // 文件对象
    }
    var xhr = new XMLHttpRequest();                // XMLHttpRequest 对象
    xhr.open("post", FileController, true);
    xhr.onload = function(e) {
        if((this.status == 200||this.status == 304)){
            var responseText = xhr.responseText;
            alertify.set('notifier','position', 'top-center');
            if(responseText=='200') {
                alertify.success("导入完成!");
                $('#filemyModal').modal('hide');
                setTimeout(function () {
                    document.getElementById("seachDetail").submit()
                }, 1500);
            }else if(responseText=='500'){
                $(".btn").removeAttr("disabled","disabled");
                alertify.error("未找到Report目录");
            }else if(responseText=='404'){
                $(".btn").removeAttr("disabled","disabled");
                alertify.error("错误!请联系管理员")
            }
        }else {
            $(".btn").removeAttr("disabled","disabled");
            alertify.error("错误!请联系管理员")
            return
        }
    };
    xhr.upload.addEventListener("progress", progressFunction, false);
    xhr.send(form);
}



function progressFunction(evt) {

    var progressBar = document.getElementById("progressBar");

    var percentageDiv = document.getElementById("percentage");

    if (evt.lengthComputable) {

        progressBar.max = evt.total;

        progressBar.value = evt.loaded;

        percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100)+ "%";

        if((evt.loaded/evt.total) ==1){
            alertify.set('notifier','position', 'top-center');
            alertify.set('notifier','delay', 0);
            alertify.success("文件上传成功\n请等待数据导入...");
        }
    }
}