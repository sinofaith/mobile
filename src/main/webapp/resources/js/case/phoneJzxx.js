function getEditPerson(id){
    $.ajax({
        url: "/mobile/phone/getEditPerson?id="+id,
        type: 'get',
        dataType: 'json',
        success: function(result) {
            $("#id").attr("value",result.id);$("#biem").attr("value",result.biem);
            $("#mac").attr("value",result.mac);$("#yhsbm").attr("value",result.yhsbm);
            $("#sbsbm").attr("value",result.sbsbm);$("#xzzqh").attr("value",result.xzzqh);
            $("#hjdqh").attr("value",result.hjdqh);$("#beizhu").attr("value",result.beizhu);
            $("#zjlx").attr("value",result.zjlx);$("#cjsj").attr("value",result.cjsj);
            $("#sbbh").attr("value",result.sbbh);$("#daorusj").attr("value",result.daorusj);
            $("#gsd").attr("value",result.gsd);$("#dataType").attr("value",result.dataType);
            $("#insertTime").attr("value",result.insertTime);$("#iccid").attr("value",result.iccid);
            $("#aj_id").attr("value",result.aj_id);$("#editname").attr("value",result.name);
            $("#editsex").attr("value",result.sex);$("#editsfzhm").attr("value",result.zjhm);
            $("#editmobile").attr("value",result.sjhm);$("#editsjxh").attr("value",result.sjxh);
            $("#editgzdw").attr("value",result.gzdw);$("#editxzz").attr("value",result.xzz);
            $("#edithjd").attr("value",result.hjd);
        }
    });
}

function editPerson() {
    var flag = true;
    var id = $("#id").val().trim();var name = $("#editname").val().trim();
    var sex = $("#editsex").val().trim();var zjhm = $("#editsfzhm").val().trim();
    var sjhm = $("#editmobile").val().trim();var sjxh = $("#editsjxh").val().trim();
    var gzdw = $("#editgzdw").val().trim();var xzz = $("#editxzz").val().trim();
    var hjd = $("#edithjd").val().trim();var biem = $("#biem").val().trim();
    var mac = $("#mac").val().trim();var yhsbm = $("#yhsbm").val().trim();
    var sbsbm = $("#sbsbm").val().trim();var xzzqh = $("#xzzqh").val().trim();
    var hjdqh = $("#hjdqh").val().trim();var beizhu = $("#beizhu").val().trim();
    var zjlx = $("#zjlx").val().trim();var cjsj = $("#cjsj").val().trim();
    var sbbh = $("#sbbh").val().trim();var daorusj = $("#daorusj").val().trim();
    var gsd = $("#gsd").val().trim();var insertTime = $("#insertTime").val().trim();
    var dataType = $("#dataType").val().trim();var iccid = $("#iccid").val().trim();
    var aj_id = $("#aj_id").val().trim();



    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(zjhm!='' && reg.test(zjhm) === false&&zjhm!='未获取到信息') {
        $("#editsfzhm").attr('data-original-title', "身份证号码不合法").tooltip('show');
        flag = false;
    }
    if(name==""){
        $("#editname").attr('data-original-title', "姓名不能为空").tooltip('show');
        flag = false;
    }
    if(sex == ''){
        $("#editsex").attr('data-original-title', "性别不能为空").tooltip('show');
        flag = false;
    }else if(sex!='男' && sex!='女'){
        $("#editsex").attr('data-original-title', "请输入男或女").tooltip('show');
        flag = false;
    }
    if(zjhm===''&&zjhm!='未获取到信息'){
        $("#editsfzhm").attr('data-original-title', "身份证号码不能为空").tooltip('show');
        flag = false;
    }
    if(sjhm==''){
        $("#editmobile").attr('data-original-title', "手机号码不能为空").tooltip('show');
        flag = false;
    }
    if(flag){
        $.ajax({
            url: "/mobile/phone/editPerson",
            type: "POST",
            data: JSON.stringify({id:id,name:name,sex:sex,zjhm:zjhm,sjhm:sjhm,sjxh:sjxh,gzdw:gzdw,xzz:xzz,hjd:hjd,biem:biem,mac:mac,yhsbm:yhsbm,
                sbsbm:sbsbm,xzzqh:xzzqh,hjdqh:hjdqh,beizhu:beizhu,zjlx:zjlx,cjsj:cjsj,sbbh:sbbh,daorusj:daorusj,gsd:gsd,insertTime:insertTime,
                dataType:dataType,iccid:iccid,aj_id:aj_id}),
            contentType : 'application/json;charset=UTF-8',
            success: function (result) {
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
            dataType:"json"
        })
    }
    $(".btn").removeAttr("disabled","disabled");
}