$(function () {
    $( "#brandname" ).autocomplete({
    });
    $( "#unitname" ).autocomplete({
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
});

function getBrandName() {
    $( "#brandname" ).autocomplete({
        source: "/mobile/caseBrand/getBrandName",
        minLength: 2
    });
};

function getBrandNameOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#brandname").trigger(e);
    $( "#brandname" ).autocomplete({
        source: "/mobile/caseBrand/getBrandName",
        minLength: 0
    });
}

function getUnitName() {
    $( "#unitname" ).autocomplete({
        source: "/mobile/caseBrand/getUnitName",
        minLength: 2
    });
};

function getUnitNameOnfocus() {
    var e = jQuery.Event("keydown");//模拟一个键盘事件
    e.keyCode = 8;//keyCode=8是空格
    $("#unitname").trigger(e);
    $( "#unitname" ).autocomplete({
        source: "/mobile/caseBrand/getUnitName",
        minLength: 0
    });
}


function destroyTooltip(name) {
    $("."+name).tooltip('destroy');
}


function addBrand() {
    var flag = true;
    var brandname = $("#brandname").val().trim();
    if(brandname==''){
        $("#brandname").attr('title',"品牌名不能为空").tooltip('show');
        flag=false;
    }
    var unitname = $("#unitname").val().trim();
    if(unitname==''){
        $("#unitname").attr('title',"主办单位不能为空").tooltip('show');
        flag=false;
    }
    if(flag==false){
        return;
    }
    $(".btn").attr("disabled","true");
    var Controller = "/mobile/caseBrand/add"; // 接收后台地址
    // FormData 对象
    var form = new FormData();
    form.append("brandname",brandname);
    form.append("unitname",unitname);
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
            $("#brandname").attr('title',"品牌-主办单位关系已存在").tooltip('show');
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