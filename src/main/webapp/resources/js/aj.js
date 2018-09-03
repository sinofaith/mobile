$(function () { $('#myModal').on('hide.bs.modal', function () {
    var tbody = window.document.getElementById("result")
    if(tbody!=null) {
        tbody.innerHTML = ""
    }
})
});

$(function () { $('#myModal1').on('hide.bs.modal', function () {
    var tbody = window.document.getElementById("result")
    if(tbody!=null) {
        tbody.innerHTML = ""
    }
})
});


$(function () {
    $(".delete").click(function () {
        var label = $(this).next(":hidden").val();
        var flag = confirm("确定删除 "+label+" ?");
        if(flag){
            var urla = $(this).attr("href");
            $("#_form").ajaxSubmit({
                url:urla,
                type:"GET",
                beforeSend: function () {
                    $('.delete').css('disabled','true')
                    setTimeout(function () {document.getElementById("seachDetail").submit()},1500);
                },
                success:function (data) {
                    if(data==305){
                        alertify.set('notifier','position', 'top-center');
                        alertify.error("权限不足")
                    }
                    if(data==200){
                        alertify.set('notifier','position', 'top-center');
                        alertify.success("删除成功")
                    }
                },
                error:function (e) {
                    alertify.set('notifier','position', 'top-center');
                    alertify.error("错误")
                }
            })
        }
        return false;
    })
})
$(function(){
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
    $('.hebings').each(function(index, element) {
        if(!$(this).hasClass('hide'))
        {    var next=$(this).parent('tr').next('tr').children('.hebings');//下一个合并的对象
            $(this).attr('rowspan',1);
            while($(this).text()==next.text()&&$(this).prev().prev().text()==next.prev().prev().text())
            {
                $(this).attr('rowspan',parseInt($(this).attr('rowspan'))+1);
                next.hide();
                next.addClass('hide');
                next=next.parent('tr').next('tr').children('.hebings');//下一个合并的对象
            }
        }
    });
    $('.hebingss').each(function(index, element) {

        if(!$(this).hasClass('hide'))
        {    var next=$(this).parent('tr').next('tr').children('.hebingss');//下一个合并的对象
            $(this).attr('rowspan',1);
            while($(this).text()==next.text()&&$(this).prev().prev().text()==next.prev().prev().text())
            {
                $(this).attr('rowspan',parseInt($(this).attr('rowspan'))+1);
                next.hide();
                next.addClass('hide');
                next=next.parent('tr').next('tr').children('.hebingss');//下一个合并的对象
            }
        }
    });
});

function deleteWord() {
   var obj = document.getElementsByName("idval");
   var check_val = [];
    for (k in obj) {
        if (obj[k].checked) {
            check_val.push(obj[k].value);
        }
    }
    if(check_val.length<1){
        alertify.set('notifier','position', 'top-center');
        alertify.error("请勾选至少一个文档名")
        return;
    }
    var flag = confirm("确定删除?");
    var  url = "/word/title/delete?ids="+check_val
    if(flag){
        $("#_form").ajaxSubmit({
            url:url,
            type:"GET",
            beforeSend: function () {
                $('#myModal1').modal('hide');
                setTimeout(function () {document.getElementById("seachDetail").submit()},1500);
            },
            success:function (data) {
                // if(data==305){
                //     alertify.alert("权限不足")
                // }
                if(data==200){
                    alertify.set('notifier','position', 'top-center');
                    alertify.success("删除成功")
                }
            },
            error:function (e) {
                alertify.set('notifier','position', 'top-center');
                alertify.error("错误")
            }
        })
    }
    return false;
}

function wordSkip(a){
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
        location="/word/"+a+"/seach?pageNo="+onPage;
    }
}

function UploadWord() {
    var fileObj = document.getElementById("file");// js 获取文件对象
    var checkBox = 0
    var file = $("#file").val();
    if($("#checkbox1").is(':checked')){
        checkBox =1
    }
    if(file==''){
        alertify.set('notifier','position', 'top-center');
        alertify.error('请选择要上传的文件夹');
        return;
    }
    var FileController = "/word/uploadWord"; // 接收上传文件的后台地址
    // FormData 对象
    var form = new FormData();
    // form.append("aj", aj); // 可以增加表单数据
    form.append("checkBox",checkBox);
    for(i=0;i<fileObj.files.length;i++){
        form.append("file", fileObj.files[i]); // 文件对象
    }
    var xhr = new XMLHttpRequest();                // XMLHttpRequest 对象
    xhr.open("post", FileController, true);
    xhr.onload = function() {
        if(this.status == 200||this.status == 304){
            alertify.set('notifier','position', 'top-center');
            alertify.success("导入完成!");
            $('#myModal').modal('hide');
            setTimeout(function () {document.getElementById("seachDetail").submit()},1500);
        }else{
            alertify.set('notifier','position', 'top-center');
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

        if((evt.loaded/evt.total) ==1 ){
            alertify.set('notifier','position', 'top-center');
            alertify.set('notifier','delay', 0);
            alertify.success("文件上传成功\n请等待数据导入...");
        }
    }
}

function destroyTooltip() {
    $(".txt").tooltip('destroy');
}

var page = 1
var is_running = false
function getSfDetails(obj,type) {
    var hm = $(obj).closest("tr").find("td:eq(0)").text().trim()
    if(type=="sfzhm"){
        hm = hm.substr(0,18)
    }else{
        hm = hm.substr(0,11)
    }

    window.page = 1
    var tbody = window.document.getElementById("result")
    var url = "/word/title/getDetails"
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            hm:hm,
            page:parseInt(page)
        },
        success:function (msg) {
            var data = msg.list
            var str = ""
            for (i in data){
                if(i%2==0){
                    str+="<tr align='center' style='display:table;width:100%;table-layout:fixed;'>"
                }else{
                    str+="<tr align='center' class='odd' style='display:table;width:100%;table-layout:fixed;'>"
                }
                str+="<td width=\"4%\">"+data[i].xh+"</td>"+
                    "<td width=\"8%\">"+data[i].sfzhm+"</td>"+
                    "<td width=\"15%\">"+data[i].title+"</td>"+
                    "<td width=\"8%\">"+data[i].inserttime+"</td>"+
                    "</tr>";
            }
            tbody.innerHTML = str
            $("#hm").attr("value",hm);
            $("#allRow").attr("value",msg.totalRecords)
            // title.innerText ="<"+jyzh+","+jylx+">"
        }
    })
}


function getWordList() {
    var tbody = window.document.getElementById("result")
    window.page = 1
    var url = "/word/title/getWordList"
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            page:parseInt(page)
        },
        success:function (msg) {
            var data = msg.list
            var str = ""
            for (i in data){
                if(i%2==0){
                    str+="<tr align='center' style='display:table;width:100%;table-layout:fixed;'>"
                }else{
                    str+="<tr align='center' class='odd' style='display:table;width:100%;table-layout:fixed;'>"
                }
                str+="<td width=\"4%\"><input type='checkbox'id='checkbox' name = 'idval' value='"+data[i].id+"'/>&nbsp;"+data[i].xh+"</td>"+
                    "<td width=\"15%\">"+data[i].title+"</td>"+
                    "<td width=\"5%\">"+data[i].inserttime+"</td>"+
                    "</tr>";
            }
            tbody.innerHTML = str
            $("#allRow").attr("value",msg.totalRecords)
            // title.innerText ="<"+jyzh+","+jylx+">"
            // table_Check();
        }
    })
}

function table_Check() {
    $(".table_list1 tr").each(function(){
        $(this).children().click(function(e){
            $(e.target).parent("tr").each(function(){
                if($(this).find(":checkbox").is(":checked")){
                    $(this).find(":checkbox").attr("checked",false);
                }else{
                    $(this).find(":checkbox").attr("checked",true);
                }
            });
        });
    });
}
function scrollH(){
    var tbody = window.document.getElementById("result")
    var allRow = $("#allRow").val()
    var scrollT = parseFloat(tbody.scrollTop) + parseFloat(tbody.clientHeight)
    var scrollH = parseFloat(tbody.scrollHeight)
    if (1 >= scrollH - scrollT && tbody.scrollTop != 0 && tbody.childNodes.length < allRow) {
        if(is_running==false) {
            is_running = true
            window.page = page += 1

            var url = "/word/title/getWordList"
            $.ajax({
                type: "post",
                dataType: "json",
                url: url,
                data: {
                    page: parseInt(window.page)
                },
                success: function (msg) {
                    var data = msg.list
                    var str = ""
                    for (i in data) {
                        if(i%2==0){
                            str+="<tr align='center' style='display:table;width:100%;table-layout:fixed;'>"
                        }else{
                            str+="<tr align='center' class='odd' style='display:table;width:100%;table-layout:fixed;'>"
                        }
                        str+="<td width=\"4%\"><input type='checkbox'id='checkbox' name = 'idval' value='"+data[i].id+"'/>&nbsp;"+data[i].xh+"</td>"+
                            "<td width=\"15%\">"+data[i].title+"</td>"+
                            "<td width=\"5%\">"+data[i].inserttime+"</td>"+
                            "</tr>";
                    }
                    $("#result").append(str)
                    $("#allRow").attr("value", msg.totalRecords)
                    // title.innerText ="<"+jyzh+","+jylx+">"
                    // table_Check();
                    is_running = false
                }
            })
        }
    }
}
function scrollF() {
    var tbody = window.document.getElementById("result")
    var allRow = $("#allRow").val()
    var scrollT = parseFloat(tbody.scrollTop) + parseFloat(tbody.clientHeight)
    var scrollH = parseFloat(tbody.scrollHeight)
    if (1 >= scrollH - scrollT && tbody.scrollTop != 0 && tbody.childNodes.length < allRow) {
        if(is_running==false) {
            is_running = true
            var hm = $("#hm").val();
            window.page = page += 1

            var url = "/word/title/getDetails"
            $.ajax({
                type: "post",
                dataType: "json",
                url: url,
                data: {
                    hm: hm,
                    page: parseInt(window.page)
                },
                success: function (msg) {
                    var data = msg.list
                    var str = ""
                    for (i in data) {
                        if(i%2==0){
                            str+="<tr align='center' style='display:table;width:100%;table-layout:fixed;'>"
                        }else{
                            str+="<tr align='center' class='odd' style='display:table;width:100%;table-layout:fixed;'>"
                        }
                        str+="<td width=\"4%\">" + data[i].xh + "</td>" +
                            "<td width=\"8%\">" + data[i].sfzhm + "</td>" +
                            "<td width=\"15%\">" + data[i].title + "</td>"+
                            "<td width=\"8%\">" + data[i].inserttime + "</td>"+
                            "</tr>";
                    }
                    $("#result").append(str)
                    $("#hm").attr("value", hm);
                    $("#allRow").attr("value", msg.totalRecords)
                    // title.innerText ="<"+jyzh+","+jylx+">"
                    is_running = false
                }
            })
        }
    }
}
function downDetailByHm(){
    var hm = $("#hm").val();
    location="/word/title/downDetailByHm?hm="+hm;
}


function addUser() {
    var username = $("#username").val();
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    var role = $("#role").val();
    if(username==''){
        $(".username").attr('title',"用户名不能为空").tooltip('show');
        return
    }
    if(password==''){
        $(".password").attr('title',"密码不能为空").tooltip('show');
        return
    }
    if(repassword==''){
        $(".password").attr('title',"重复密码不能为空").tooltip('show');
        return
    }
    if(password != repassword){
        $(".repassword").attr('title',"两次密码不一致").tooltip('show');
        return
    }
    $(".btn").attr("disabled","true")
    var Controller = "/word/user/add"; // 接收后台地址
    // FormData 对象
    var form = new FormData();
    form.append("username", username); // 可以增加表单数据
    form.append("password", password); // 可以增加表单数据
    form.append("role", role); // 可以增加表单数据
    var xhr = new XMLHttpRequest();                // XMLHttpRequest 对象
    xhr.open("post", Controller, true);
    xhr.onload = function() {
        if(xhr.responseText==200){
            alertify.alert("添加完成!");
            $(".btn").attr("disabled","true")
            $('#myModal').modal('hide');
            setTimeout(function () {document.getElementById("seachDetail").submit()},1000);
        }
        if(xhr.responseText==303){
            $(".username").attr('title',"用户名已存在").tooltip('show');
        }
        if(xhr.responseText==404||xhr.responseText==400){
            alertify.alert("添加失败")
        }
        $(".btn").removeAttr("disabled","disabled");
    };
    xhr.send(form);
}

function refresh(type) {
    var flg = 0
    alertify.set('notifier','position', 'top-center');
    if($("#checkbox1").is(":checked")){
        flg=1
        alertify.success("最近导入文档统计结果")
    }else{
        alertify.success("全部文档统计结果")
    }
    var url = "/word/sfzhm/flg?flg="+flg
    $.get(url,function (data) {
        if(data==200){
            setTimeout(function (){document.getElementById("seachDetail").submit()},1800);
        }
    })
}

$(function(){
    document.ondragleave=function(e){
        e.preventDefault();
        console.info("文件离开执行了我！！");
    };
    //鼠标松开文件
    document.ondrop=function(e){
        e.preventDefault();
        console.info("松开以后执行了我！");
    };
    //鼠标移动文件
    document.ondragover=function(e){
        e.preventDefault();
        console.info("文件移动以后执行了我！");
    };

    function tm_upload(){
        var uploadArea=document.getElementById("upload-area");
        //2、通过HTML5拖拽事件，ondrop，然后通过拖动区域监听浏览器的drop事件达到文件上传的目的
        var checkBox = 0
        uploadArea.addEventListener("drop", function(e){
            e.preventDefault();
            //3、从事件event中获取拖拽到浏览器的文件信息
            var fileList=e.dataTransfer.files;
            if(fileList.length>10){
                alertify.set('notifier','position', 'top-center');
                alertify.error("拖拽上传只支持10个以内文件")
                return
            }
            var xhr = new XMLHttpRequest();
            xhr.open("post", "/word/uploadWord", true);
            var formData = new FormData();//动态给表单赋值，传递二进制文件
            for(var i=0;i<fileList.length;i++){
                // 此处判断只能上传图片
                var fileName=fileList[i].name;
                console.info(fileName);
                var index1=fileName.lastIndexOf(".");
                var index2=fileName.length;
                var suffix=fileName.substring(index1,index2);
                if(suffix==".doc"||suffix==".docx") {
                    var fileSize = fileList[i].size;
                    console.info(fileSize);
                    //4、通过XMLHttpRequest上传文件到服务器  就是一个ajax请求

                    //5、这里需要先写好一个data.jsp的上传文件，当然，你写成servlet或者是action都可以
                    // xhr.setRequestHeader("X-Request-Width", "XMLHttpRequest");
                    //6、通过HTML5 FormData动态设置表单元素
                    //其实就相当于<input type="file" name="file"/>
                    formData.append("file", fileList[i]);
                    formData.append("checkBox",checkBox);
                    xhr.onload = function() {
                        if(this.status == 200||this.status == 304){
                            alertify.set('notifier','position', 'top-center');
                            alertify.success("导入完成!");
                            $('#myModal').modal('hide');
                            setTimeout(function () {document.getElementById("seachDetail").submit()},3000);
                        }else{
                            alertify.set('notifier','position', 'top-center');
                            alertify.error("错误!请联系管理员")
                            return
                        }
                    };
                    xhr.upload.addEventListener("progress", progressFunction, false);
                }else{
                    alertify.set('notifier','position', 'top-center');
                    alertify.error("拖拽文件中包含非Word文件")
                    // if(fileList.length==1){
                        return
                    // }
                }
            }
            xhr.send(formData);
        });
    }

    tm_upload();
//直接调用
});


