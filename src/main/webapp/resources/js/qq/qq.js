function phoneSkip(a){
    var totalPage = $("#totalPage").text();
    var onPage = $("#num").val();
    if(onPage ==="" || onPage === 0 || parseInt(onPage) <=0){
        alertify.set('notifier','position', 'top-center');
        alertify.success("请输入你要跳转的页数！");
        return;
    }
    if(parseInt(onPage)>parseInt(totalPage)){
        $("#num").val(totalPage);
        return;
    } else {
        location="/mobile/"+a+"/seach?pageNo="+onPage;
    }
}

function getQqFirendDetails(obj) {
    var zhxx = $(obj).closest("tr").find("td:eq(1)").text()
    var dszh = $(obj).closest("tr").find("td:eq(3)").text()
    window.page = 1;
    var qqContent = window.document.getElementById("qqContent")
    var url = "/mobile/phoneqqFriendChat/getDetails";
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            zhxx:zhxx,
            dszh:dszh,
            pageNo:parseInt(page)
        },
        success:function (msg) {
            var data = msg.list;
            var str = "";
            for(i=0;i<data.length;i++){
                str += insertDiv(data[i]);
            }
            qqContent.innerHTML = str;
            $("#zhxx").attr("value",zhxx);
            $("#dszh").attr("value",dszh);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
}

var is_running = false;
function scrollF(){
    var qqContent = window.document.getElementById("qqContent")
    // 买家用户Id
    var zhxx = $("#zhxx").val();
    var dszh = $("#dszh").val();
    var allRow = $("#allRow").val();
    var scrollT = parseFloat(qqContent.scrollTop) + parseFloat(qqContent.clientHeight)
    var scrollH = parseFloat(qqContent.scrollHeight)
    if (1 >= scrollH - scrollT && qqContent.scrollTop != 0 && qqContent.childNodes.length < allRow) {
        if (is_running == false) {
            is_running = true;
            window.page = page += 1;
            var url = "/mobile/phoneqqFriendChat/getDetails";
            $.ajax({
                type:"post",
                dataType:"json",
                url:url,
                data:{
                    zhxx:zhxx,
                    dszh:dszh,
                    pageNo:parseInt(window.page)
                },
                success:function (msg) {
                    var data = msg.list;
                    var str = "";
                    for(i=0;i<data.length;i++){
                        str += insertDiv(data[i]);
                    }
                    qqContent.innerHTML += str;
                    $("#zhxx").attr("value",zhxx);
                    $("#dszh").attr("value",dszh);
                    $("#allRow").attr("value",msg.totalRecords);
                    is_running = false;
                }
            })
        }
    }
}

function insertDiv(data){
    var str = "";
    if(data.fslx == "文字" || data.fslx == ""){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
            if(data.dsnc!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<i class=\"triangle-admin\"></i><span class=\"admin-reply\">"+data.lujing+"</span></div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<span class=\"user-reply\">"+data.lujing+"</span><i class=\"triangle-user\"></i></div>";
            str += "<img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "mp3"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
            if(data.dsnc!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<span class=\"admin-reply\"><audio controls=\"controls\">";
            str += "<source src="+data.lujing.replace("D:\\test","\\file")+" type=\"audio/mp3\"/></audio></span>";/*+data.lujing.replace("D:\\test","\\mobile")+*/
            str += "<i class=\"triangle-admin\"></i></div></div>"
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<span class=\"user-reply\"><audio controls=\"controls\">";
            str += "<source src="+data.lujing.replace("D:\\test","\\file")+" type=\"audio/mp3\"/></audio></span>";
            str += "<i class=\"triangle-user\"></i></div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "mp4" || data.fslx == "mp4_temp"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
            if(data.dsnc!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<i class=\"triangle-admin\"></i><span class=\"admin-reply\">";
            str += "<video width=\"320\" height=\"180\" controls=\"controls\"><source src="+data.lujing.replace("D:\\test","\\file")+" type=\"video/mp4\"/>";
            str += "</video></span></div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<i class=\"triangle-user\"></i><span class=\"user-reply\">";
            str += "<video width=\"320\" height=\"180\" controls=\"controls\"><source src="+data.lujing.replace("D:\\test","\\file")+" type=\"video/mp4\"/>";
            str += "</video></span></div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "jfif" || data.fslx == "png"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
            if(data.dsnc!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<span class=\"admin-reply\"><img style='max-height: 300px;max-width: 150px;' src="+data.lujing.replace("D:\\test","\\file")+" /></span><i class=\"triangle-admin\"></i>";
            str += "</div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<span class=\"user-reply\"><img style='max-height: 300px;max-width: 150px;' src="+data.lujing.replace("D:\\test","\\file")+" /></span><i class=\"triangle-user\"></i>";
            str += "</div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "pdf" || data.fslx == "xlsx" || data.fslx == "xls" || data.fslx == "docx"){
        var file=data.lujing.substr(data.lujing.lastIndexOf('\\')+1);
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
            if(data.dsnc!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            if(data.fslx == "pdf"){
                str += "<span class=\"admin-reply\" style=\"text-align:center;\"><a href="+data.lujing.replace("D:\\test","\\file")+" target=\"_blank\"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/pdf.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "docx"){
                str += "<span class=\"admin-reply\" style=\"text-align:center;\"><a href="+data.lujing.replace("D:\\test","\\file")+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/doc.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "xlsx" || data.fslx == "xls"){
                str += "<span class=\"admin-reply\" style=\"text-align:center;\"><a href="+data.lujing.replace("D:\\test","\\file")+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/excel.png\"/></br><span>"+file+"</span></a></span>";
            }
            str += "<i class=\"triangle-admin\"></i></div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            if(data.fslx == "pdf"){
                str += "<span class=\"user-reply\" style=\"text-align:center;\"><a href="+data.lujing.replace("D:\\test","\\file")+" target=\"_blank\"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/pdf.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "docx"){
                str += "<span class=\"user-reply\" style=\"text-align:center;\"><a href="+data.lujing.replace("D:\\test","\\file")+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/doc.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "xlsx" || data.fslx == "xls"){
                str += "<span class=\"user-reply\" style=\"text-align:center;\"><a href="+data.lujing.replace("D:\\test","\\file")+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/excel.png\"/></br><span>"+file+"</span></a></span>";
            }
            str += "<i class=\"triangle-user\"></i></div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }
    return str;
}

$(function(){
    $('#myModal').on('hide.bs.modal', function () {
        var qqContent = window.document.getElementById("qqContent");
        page = 1;
        if(qqContent!=null) {
            qqContent.innerHTML = "";
        }
    });
});