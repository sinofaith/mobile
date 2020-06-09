$(function () {
    $( "#casename" ).autocomplete({

    });
    $( "#creater" ).autocomplete({

    });
    $('.hebing').each(function(index, element) {
        if(!$(this).hasClass('hide'))
        {    var next=$(this).parent('tr').next('tr').children('.hebing');//下一个合并的对象
            $(this).attr('rowspan',1);
            // while($(this).text()==next.text()&&$(this).next().next().text()==next.next().next().text())
            while($(this).text()==next.text())
            {
                $(this).attr('rowspan',parseInt($(this).attr('rowspan'))+1);
                next.hide();
                next.addClass('hide');
                next=next.parent('tr').next('tr').children('.hebing');//下一个合并的对象
            }
        }
    });


    $("#region").selectpicker({
        noneResultsText:"未搜索到城市{0}",
        noneSelectedText:"请选择区域",
        width:"176px",
        liveSearch:true,
        style:"background-color: white"
    });

    $.ajax({
        type: 'Get',
        url: '/mobile/Area/getArea',
        dataType: "Json",
        success: function (data) {
            var str = "";
            for(var i = 0;i<data.length;i++){
                    str+="<optgroup label=\""+data[i].pName+"\">";
                for(var j=0;j<data[i].listCity.length;j++){
                     str+="<option value=\"" + data[i].listCity[j].id + "\">" + data[i].listCity[j].name + "</option>"
                }
                str+="</optgroup>"
            }
            $("#region").append(str);
            $('.selectpicker').selectpicker('refresh');
            $('.selectpicker').selectpicker('render');
        }
    });

    $('#region').on('show.bs.select', function () {
        $("#region").tooltip("hide");
    });

})



function getCaseNameOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#casename").trigger(e);
    $( "#casename" ).autocomplete({
        source: "/mobile/case/getCaseName",
        minLength: 0,
        select: function(e, ui) {
            destroyTooltip('casename');
        }
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
        minLength: 0,
        select: function(e, ui) {
            destroyTooltip('creater');
        }
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
    var brandId = $("#brandid").val().trim();
    var caseName = $("#casename").val().trim();
    var regionName = $("#region").val();
    if(caseName===''||brandId===''||regionName===''){
        return flag;
    }
    $.ajax({
        url: "/mobile/case/getCase?caseName="+caseName+"&brandId="+brandId+"&regionName="+regionName,
        type: 'get',
        async: false,
        dataType: 'text',
        success: function(result,status) {
            if(result==="303"){
                $("#casename").attr('data-original-title',"案件及区域已存在,请勿重复添加").tooltip('show');
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
    var flag = true;

    var brandId = $("#brandid").val().trim();
    if(brandId==''){
        $("#brandname").attr('data-original-title',"所属品牌不能为空,请从品牌列表选择后添加案件").tooltip('show');
        flag=false;
    }
    var regionId = $("#region").val();
    if(regionId==null){
        $("#region").attr('data-original-title',"区域不能为空").tooltip('show');
        flag=false;
    }
    var caseName = $("#casename").val().trim();
    if(caseName==''){
        $("#casename").attr('data-original-title',"案件名不能为空").tooltip('show');
        flag=false;
    }
    var creater = $("#creater").val().trim();
    if(creater==''){
        $("#creater").attr('data-original-title',"创建人不能为空").tooltip('show');
        flag=false;
    }
    if(flag==false){
        return;
    }
    $(".btn").attr("disabled","true");
    var Controller = "/mobile/case/add"; // 接收后台地址
    // FormData 对象
    var form = new FormData();
    form.append("brandId",brandId);
    form.append("areaId",regionId);
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
            $("#casename").attr('data-original-title',"案件名已存在").tooltip('show');
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