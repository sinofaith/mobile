$(function () {
    $( "#seachLtjl" ).autocomplete({

    });
});
var is_running = false;
function getLtjlOnfocus() {
    var seach = $("#project").val().trim();
        var e = jQuery.Event("keydown");//模拟一个键盘事件
        e.keyCode = 8;//keyCode=8是空格
        $("#project").trigger(e);
        $( "#project" ).autocomplete({
            minLength: 1,
            delay:500,
            source: function(request, response) {
                $.ajax({
                    url: "/mobile/phonewxFriendChat/getDetailsByFilter",
                    type:"post",
                    dataType: "json",
                    data: {
                        term: $("#project").val() ,//搜索栏里的内容
                        zhxx: $("#zhxx").val() ,
                        dszh: $("#dszh").val()   //额外参数
                    },
                    success: function(data) {
                        response(data);
                    }
                });
            },
            focus: function( event, ui ) {
                return false;
            },
            select: function( event, ui ) {
                is_running = true;
                $( "#project-id" ).val( ui.item.value );
                var wxContent = $("#wxContent");
                wxContent.html("");
                getByRn(ui.item.rn);
                $("#project").blur();
                is_running = false
                return false;
            }
        })
            .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
            return $( "<li>" )
                .append( "<a><div style='line-height: 20px'><div style='width:120px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden; float: left'><b style='font-size: 12px; '>" + item.nickname + "</b></div>" +
                    "<span style='font-size: 10px; float: right;color: grey'>"+item.fstime+"</span><br>" +
                    "<span style='font-size: 14px; color: grey'><xmp>" + item.label + "</xmp></span></div></a>" +"<hr style='margin: 0;padding: 0'>")
                .appendTo( ul );
        };
}
function  cnm() {

    var targetDom =$("#wxContent")

    var copyDom = targetDom.clone();
    copyDom.removeClass("pdf");
    copyDom.width(targetDom.width() + "px");
    copyDom.height(document.getElementById("wxContent").height + "px");

    $('body').append(copyDom);
    html2canvas(
        copyDom,
        {
            height:document.getElementById("wxContent").height,
            allowTaint: false,
            taintTest: false,
            dpi: 1000,//导出pdf清晰度
            onrendered: function (canvas) {
                var contentWidth = canvas.width;
                var contentHeight = canvas.height;

                //一页pdf显示html页面生成的canvas高度;
                var pageHeight = contentWidth / 592.28 * 841.89;
                //未生成pdf的html页面高度
                var leftHeight = contentHeight;
                //pdf页面偏移
                var position = -600;
                //html页面生成的canvas在pdf中图片的宽高（a4纸的尺寸[595.28,841.89]）
                var imgWidth = 595.28;
                var imgHeight = 592.28 / contentWidth * contentHeight;

                var pageData = canvas.toDataURL('image/jpeg', 1.0);
                var pdf = new jsPDF('', 'pt', 'a4');

                //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                //当内容未超过pdf一页显示的范围，无需分页
                if (leftHeight < pageHeight) {
                    pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
                } else {
                    while (leftHeight > 0) {
                        pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                        leftHeight -= pageHeight;
                        position -= 841.89;
                        //避免添加空白页
                        if (leftHeight > 0) {
                            pdf.addPage();
                        }
                    }
                }
                pdf.save('content.pdf');
                copyDom.remove();
                targetDom.addClass("pdf");
            },
            //背景设为白色（默认为黑色）
            background: "#fff",

        });
}
function getByRn(rn) {
    var seach = $("#project").val().trim();
    var zhxx = $("#zhxx").val();
    var dszh = $("#dszh").val();
    window.pageUp=parseInt(rn);
    window.pageDown=parseInt(rn);
    var wxContent = window.document.getElementById("wxContent")
    var url = "/mobile/phonewxFriendChat/getDetails";
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            zhxx:zhxx,
            dszh:dszh,
            pageNo:parseInt(pageDown),
            pageSize:parseInt(99)
        },
        success:function (msg) {
            var data = msg.list;
            var str = "";
            for(i=0;i<data.length;i++){
                str += insertDiv(data[i],seach);
            }
            wxContent.innerHTML = str;
            $("#wxContent").scrollTop(30);
            $("#zhxx").attr("value",zhxx);
            $("#dszh").attr("value",dszh);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
}

function phoneSkip(a){
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

function getWxFirendDetails(obj) {
    var seach = $("#project").val().trim();
    var zhxx = $(obj).closest("tr").find("td:eq(1)").text()
    var dszh = $(obj).closest("tr").find("td:eq(3)").text()
    window.pageDown = 1;
    var wxContent = window.document.getElementById("wxContent")
    var url = "/mobile/phonewxFriendChat/getDetails";
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            zhxx:zhxx,
            dszh:dszh,
            pageNo:parseInt(pageDown),
            pageSize:99
        },
        success:function (msg) {
            var data = msg.list;
            var str = "";
            for(i=0;i<data.length;i++){
                str += insertDiv(data[i],seach);
            }
            wxContent.innerHTML = str;
            $("#zhxx").attr("value",zhxx);
            $("#dszh").attr("value",dszh);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
};
function scrollF(){
    var qqContent = window.document.getElementById("wxContent");
    if(qqContent.textContent===""){
        return
    }
    var seach = $("#project").val().trim();
    // 买家用户Id
    var zhxx = $("#zhxx").val();
    var dszh = $("#dszh").val();
    var allRow = $("#allRow").val();
    var scrollT = parseFloat(qqContent.scrollTop) + parseFloat(qqContent.clientHeight)
    var scrollH = parseFloat(qqContent.scrollHeight)
    if (1 >= scrollH - scrollT && qqContent.scrollTop != 0 && window.pageDown <= allRow) {
        if (is_running == false) {
            is_running = true;
            window.pageDown = window.pageDown+100;
            var url = "/mobile/phonewxFriendChat/getDetails";
            $.ajax({
                type:"post",
                dataType:"json",
                url:url,
                data:{
                    zhxx:zhxx,
                    dszh:dszh,
                    pageNo:parseInt(window.pageDown),
                    pageSize:99
                },
                success:function (msg) {
                    var data = msg.list;
                    var str = "";
                    for(i=0;i<data.length;i++){
                        str += insertDiv(data[i],seach);
                    }
                    $("#wxContent").append(str) ;
                    $("#zhxx").attr("value",zhxx);
                    $("#dszh").attr("value",dszh);
                    $("#allRow").attr("value",msg.totalRecords);
                    is_running = false;
                }
            })
        }
    }
    if(qqContent.scrollTop==0&window.pageUp>1){
        var before = qqContent.scrollHeight;
        if (is_running == false) {
            is_running = true;
            var pageSize;
            if(window.pageUp-100<1){
                pageSize=window.pageUp-2
                window.pageUp=1
            }else{
                window.pageUp=window.pageUp-100
                pageSize = 99;
            }
            var url = "/mobile/phonewxFriendChat/getDetails";
            $.ajax({
                type:"post",
                dataType:"json",
                url:url,
                data:{
                    zhxx:zhxx,
                    dszh:dszh,
                    pageNo:parseInt(window.pageUp),
                    pageSize:pageSize
                },
                success:function (msg) {
                    var data = msg.list;
                    var str = "";
                    for(i=0;i<data.length;i++){
                        str += insertDiv(data[i],seach);
                    }
                    $("#wxContent").prepend(str);
                    var after = qqContent.scrollHeight;
                    $("#wxContent").scrollTop(after-before);
                    $("#zhxx").attr("value",zhxx);
                    $("#dszh").attr("value",dszh);
                    $("#allRow").attr("value",msg.totalRecords);
                    is_running = false;
                }
            })
        }
    }
}
function lujingReplace(lujing) {
    return lujing.replace("/usr/etc/file","/file")
}

function insertDiv(data,seach){
    if(data.lujing==null){
        data.lujing="";
    }
    if(data.dsnc==null){
        data.dsnc="";
    }
    if(data.zhnc==null){
        data.zhnc=""
    }
    var str = "";
    if(data.fslx == "文字" || data.fslx == ""){
        if(data.fsfx == "接收"|| data.fsfx == null || data.fsfx==""){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/weichart.png\"/>";
            if(data.dsnc!=null||data.dszh!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dszh+"&nbsp"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<i class=\"triangle-admin\"></i><span class=\"admin-reply\"><xmp>"+data.lujing.replace(new RegExp(seach,"g"),"</xmp><span class='seachL'>"+seach+"</span><xmp>")+"</xmp></span></div></div>";
        }else if(data.fsfx == "发送" ){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null||data.zhxx!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhxx+"&nbsp"+data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<span class=\"user-reply\"><xmp>"+data.lujing.replace(new RegExp(seach,"g"),"</xmp><span class='seachL'>"+seach+"</span><xmp>")+"</xmp></span><i class=\"triangle-user\"></i></div>";
            str += "<img class=\"user-img\" src=\"/mobile/resources/image/weichart.png\"/></div>";
        }
    }else if(data.fslx == "mp3"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/weichart.png\"/>";
            if(data.dsnc!=null||data.dszh!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dszh+"&nbsp"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<span class=\"admin-reply\"><audio controls=\"controls\">";
            str += "<source src="+lujingReplace(data.lujing)+" type=\"audio/mp3\"/></audio></span>";/*+data.lujing.replace("D:\\test","\\mobile")+*/
            str += "<i class=\"triangle-admin\"></i></div></div>"
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null||data.zhxx!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.dszh+"&nbsp"+data.dsnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<span class=\"user-reply\"><audio controls=\"controls\">";
            str += "<source src="+lujingReplace(data.lujing)+" type=\"audio/mp3\"/></audio></span>";
            str += "<i class=\"triangle-user\"></i></div><img class=\"user-img\" src=\"/mobile/resources/image/weichart.png\"/></div>";
        }
    }else if(data.fslx == "mp4" || data.fslx == "mp4_temp" || data.fslx == "mpeg"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/weichart.png\"/>";
            if(data.dsnc!=null||data.dszh!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dszh+"&nbsp"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<i class=\"triangle-admin\"></i><span class=\"admin-reply\">";
            str += "<video width=\"320\" height=\"180\" controls=\"controls\"><source src="+lujingReplace(data.lujing)+" type=\"video/mp4\"/>";
            str += "</video></span></div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null||data.zhxx!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhxx+"&nbsp"+data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<i class=\"triangle-user\"></i><span class=\"user-reply\">";
            str += "<video width=\"320\" height=\"180\" controls=\"controls\"><source src="+lujingReplace(data.lujing)+" type=\"video/mp4\"/>";
            str += "</video></span></div><img class=\"user-img\" src=\"/mobile/resources/image/weichart.png\"/></div>";
        }
    }else if(data.fslx == "jfif" || data.fslx == "png" || data.fslx=="jpg"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/weichart.png\"/>";
            if(data.dsnc!=null||data.dszh!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dszh+"&nbsp"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            str += "<a href="+lujingReplace(data.lujing)+" target='blank'> <span class=\"admin-reply\"><img style='max-height: 300px;max-width: 150px;' src="+lujingReplace(data.lujing)+"></span></a><i class=\"triangle-admin\"></i>";
            str += "</div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhxx+"&nbsp"+data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            str += "<a href="+lujingReplace(data.lujing)+" target='blank'> <span class=\"user-reply\"><img style='max-height: 300px;max-width: 150px;' src="+lujingReplace(data.lujing)+"></span></a><i class=\"triangle-user\"></i>";
            str += "</div><img class=\"user-img\" src=\"/mobile/resources/image/weichart.png\"/></div>";
        }
    }else if(data.fslx == "pdf" || data.fslx == "xlsx" || data.fslx == "xls" || data.fslx == "docx"){
        var file=data.lujing.substr(data.lujing.lastIndexOf('\\')+1);
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/weichart.png\"/>";
            if(data.dsnc!=null||data.dszh!=null){
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.dszh+"&nbsp"+data.dsnc+"</span>&nbsp<span class=\"time\">"+data.fstime+"</span></div>";
            }else{
                str += "<div class=\"admin-msg\"><div class=\"time\"><span class=\"time\">"+data.fstime+"</span></div>";
            }
            if(data.fslx == "pdf"){
                str += "<span class=\"admin-reply\" style=\"text-align:center;\"><a href="+lujingReplace(data.lujing)+" target=\"_blank\"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/pdf.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "docx"){
                str += "<span class=\"admin-reply\" style=\"text-align:center;\"><a href="+lujingReplace(data.lujing)+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/doc.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "xlsx" || data.fslx == "xls"){
                str += "<span class=\"admin-reply\" style=\"text-align:center;\"><a href="+lujingReplace(data.lujing)+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/excel.png\"/></br><span>"+file+"</span></a></span>";
            }
            str += "<i class=\"triangle-admin\"></i></div></div>";
        }else if(data.fsfx == "发送" || data.fsfx == ""){
            str += "<div class=\"user-group\"><div class=\"user-msg\">";
            if(data.zhnc!=null||data.zhxx!=null) {
                str += "<div class=\"time\"><span class=\"time\">" + data.zhxx+" "+data.zhnc + "</span>&nbsp<span class=\"time\">" + data.fstime + "</span></div>";
            }else{
                str += "<div class=\"time\"><span class=\"time\">" + data.fstime + "</span></div>";
            }
            if(data.fslx == "pdf"){
                str += "<span class=\"user-reply\" style=\"text-align:center;\"><a href="+lujingReplace(data.lujing)+" target=\"_blank\"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/pdf.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "docx"){
                str += "<span class=\"user-reply\" style=\"text-align:center;\"><a href="+lujingReplace(data.lujing)+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/doc.png\"/></br><span>"+file+"</span></a></span>";
            }else if(data.fslx == "xlsx" || data.fslx == "xls"){
                str += "<span class=\"user-reply\" style=\"text-align:center;\"><a href="+lujingReplace(data.lujing)+"><img style=\"max-height:150px;max-width:100px;\" src=\"/mobile/resources/image/excel.png\"/></br><span>"+file+"</span></a></span>";
            }
            str += "<i class=\"triangle-user\"></i></div><img class=\"user-img\" src=\"/mobile/resources/image/weichart.png\"/></div>";
        }
    }
    return str;
}

$(function(){
    $('#myModal').on('hide.bs.modal', function () {
        var qqContent = window.document.getElementById("wxContent");
        page = 1;
        if(qqContent!=null) {
            qqContent.innerHTML = "";
        }

        var content  = $("#project");
        if(content!=null){
            content.val("")
        }
    });

    $('#myModal').on('shown.bs.modal', function () {
        var select = $('select[name="seachCondition"]').val();
        var seach = $("#seachCode").val();
        if(select=='lujing'){
            $("#project").val(seach)
        }
        $("#project").focus();
    });
});

function getFriendChatDetails(obj) {
    var fswechatno = $(obj).closest("tr").find("td:eq(4)").text()
    var jswechatno = $(obj).closest("tr").find("td:eq(6)").text()
    window.page = 1
   /* var type = ""
    if( /^[a-zA-Z]([-_a-zA-Z0-9])*$/.test(jylx)){
        type="dfzh"
    }else{
        type="jylx"
    }*/
    var tbody = window.document.getElementById("result")
    var url = "/mobile/phonewxFriendChat/getDetails"
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            fswechatno:fswechatno,
            jswechatno:jswechatno,
            order:'fstime',
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
                str+="<td width=\"3%\">"+data[i].id+"</td>"+
                    "<td width=\"8%\">"+data[i].fswechatno+"</td>"+
                    "<td width=\"7%\">"+data[i].fswechatnc+"</td>"+
                    "<td width=\"10%\">"+data[i].fstime+"</td>"+
                    "<td width=\"8%\">"+data[i].jswechatno+"</td>"+
                    "<td width=\"7%\">"+data[i].jsfriendnc+"</td>"+
                    "<td width=\"7%\">"+data[i].fslx+"</td>"+
                    "<td width=\"25%\" title='"+data[i].fanrs+"'>" +
                    "<div style=\"width: 100%;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;\">"+data[i].fanrs+"</div>" +
                    "</td>"+
                    "<td width=\"10%\" title='"+data[i].lujing+"'>" +
                    "<div style=\"width:100%;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;\">"+data[i].lujing+"</div>" +
                    "</td>"+
                    "<td width=\"8%\">"+data[i].dataType+"</td>"+
                    "</tr>";
            }
            tbody.innerHTML = str
            $("#fswechatno").attr("value",fswechatno);
            $("#jswechatno").attr("value",jswechatno);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
}

function orderByFilter(filter) {
    var tbody = window.document.getElementById("result")
    if(tbody!=null) {
        tbody.innerHTML = ""
    }
    var fswechatno = $("#fswechatno").val();
    var jswechatno = $("#jswechatno").val();
    window.page = 1
    var url = "/mobile/phonewxFriendChat/getDetails"
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            fswechatno:fswechatno,
            jswechatno:jswechatno,
            order:filter,
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
                str+="<td width=\"3%\">"+data[i].id+"</td>"+
                    "<td width=\"8%\">"+data[i].fswechatno+"</td>"+
                    "<td width=\"7%\">"+data[i].fswechatnc+"</td>"+
                    "<td width=\"10%\">"+data[i].fstime+"</td>"+
                    "<td width=\"8%\">"+data[i].jswechatno+"</td>"+
                    "<td width=\"7%\">"+data[i].jsfriendnc+"</td>"+
                    "<td width=\"7%\">"+data[i].fslx+"</td>"+
                    "<td width=\"25%\" title='"+data[i].fanrs+"'>" +
                    "<div style=\"width: 100%;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;\">"+data[i].fanrs+"</div>" +
                    "</td>"+
                    "<td width=\"10%\" title='"+data[i].lujing+"'>" +
                    "<div style=\"width:100%;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;\">"+data[i].lujing+"</div>" +
                    "</td>"+
                    "<td width=\"8%\">"+data[i].dataType+"</td>"+
                    "</tr>";
            }
            tbody.innerHTML = str
            $("#fswechatno").attr("value",fswechatno);
            $("#jswechatno").attr("value",jswechatno);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
}