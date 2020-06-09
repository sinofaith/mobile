$(function () {
    $( "#seachLtjl" ).autocomplete({

    });
});

function  cnm() {

    var targetDom =$("#qqContent")
    var copyDom = targetDom.clone();
    copyDom.width(targetDom.width() + "px");
    copyDom.height(document.getElementById("qqContent").height+"px");
    $('body').append(copyDom);
    console.log(copyDom);
    html2canvas(
        copyDom,
        {
            height:document.getElementById("qqContent").height,
            allowTaint: true,
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
                var position = 0;
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
            },
            //背景设为白色（默认为黑色）
            background: "#fff",

        })
}
//
// function exportPdf(id,name) {
//     layer.open({type:3,icon:2});//mask遮罩层开启，没有使用layui的可以去掉
//     html2canvas($("#"+id), {
//         allowTaint: true,//允许跨域，具体啥意思不清楚
//         height: document.getElementById(id).scrollHeight,//
//         width: document.getElementById(id).scrollWidth,//为了使横向滚动条的内容全部展示，这里必须指定
//         background: "#FFFFFF",//如果指定的div没有设置背景色会默认成黑色,这里是个坑
//         onrendered:function(canvas) {
//
//             var contentWidth = canvas.width;
//             var contentHeight = canvas.height;
//
//             //一页pdf显示html页面生成的canvas高度;
//             var pageHeight = contentWidth / 595.28 * 841.89;
//             //未生成pdf的html页面高度
//             var leftHeight = contentHeight;
//             //pdf页面偏移
//             var position = 0;
//             //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
//             var imgWidth = 555.28;
//             var imgHeight = 555.28/contentWidth * contentHeight;
//
//             var pageData = canvas.toDataURL('image/jpeg', 1.0);
//
//             var pdf = new jsPDF('', 'pt', 'a4');
//             //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
//             //当内容未超过pdf一页显示的范围，无需分页
//             if (leftHeight < pageHeight) {
//                 pdf.addImage(pageData, 'JPEG', 20, 0, imgWidth, imgHeight );
//             } else {
//                 while(leftHeight > 0) {
//                     pdf.addImage(pageData, 'JPEG', 20, position, imgWidth, imgHeight)
//                     leftHeight -= pageHeight;
//                     position -= 841.89;
//                     //避免添加空白页
//                     if(leftHeight > 0) {
//                         pdf.addPage();
//                     }
//                 }
//             }
//             layer.close(layer.index);//关闭遮罩层
//             pdf.save(name+'.pdf');
//         }
//     })
// };
//
// $(function () {
//     $("#downloadPdf").click(function () {
//         var targetDom = $("#qqContent");
//         //把需要导出的pdf内容clone一份，这样对它进行转换、微调等操作时才不会影响原来界面
//         var copyDom = targetDom.clone();
//         //新的div宽高跟原来一样，高度设置成自适应，这样才能完整显示节点中的所有内容（比如说表格滚动条中的内容）
//         copyDom.width(targetDom.width() + "px");
//         copyDom.height(targetDom.height() + "px");
//
//         $('body').append(copyDom);//ps:这里一定要先把copyDom append到body下，然后再进行后续的glyphicons2canvas处理，不然会导致图标为空
//
//         svg2canvas(copyDom);
//         html2canvas(copyDom, {
//             onrendered: function (canvas) {
//                 var imgData = canvas.toDataURL('image/jpeg');
//                 var img = new Image();
//                 img.src = imgData;
//                 //根据图片的尺寸设置pdf的规格，要在图片加载成功时执行，之所以要*0.225是因为比例问题
//                 img.onload = function () {
//                     //此处需要注意，pdf横置和竖置两个属性，需要根据宽高的比例来调整，不然会出现显示不完全的问题
//                     if (this.width > this.height) {
//                         var doc = new jsPDF('l', 'mm', [this.width * 0.225, this.height * 0.225]);
//                     } else {
//                         var doc = new jsPDF('p', 'mm', [this.width * 0.225, this.height * 0.225]);
//                     }
//                     doc.addImage(imgData, 'jpeg', 0, 0, this.width * 0.225, this.height * 0.225);
//                     //根据下载保存成不同的文件名
//                     doc.save('pdf_' + new Date().getTime() + '.pdf');
//                 };
//                 //删除复制出来的div
//                 copyDom.remove();
//             },
//             background: "#fff",
//             //这里给生成的图片默认背景，不然的话，如果你的html根节点没设置背景的话，会用黑色填充。
//             allowTaint: true //避免一些不识别的图片干扰，默认为false，遇到不识别的图片干扰则会停止处理html2canvas
//         });
//     });
// });
//
// function svg2canvas(targetElem) {
//     var svgElem = targetElem.find('svg');
//     svgElem.each(function (index, node) {
//         var parentNode = node.parentNode;
//         //由于现在的IE不支持直接对svg标签node取内容，所以需要在当前标签外面套一层div，通过外层div的innerHTML属性来获取
//         var tempNode = document.createElement('div');
//         tempNode.appendChild(node);
//         var svg = tempNode.innerHTML;
//         var canvas = document.createElement('canvas');
//         //转换
//         canvg(canvas, svg);
//         parentNode.appendChild(canvas);
//     });
// }
//
// function glyphicons2canvas(targetElem, fontClassName, fontFamilyName) {
//     var iconElems = targetElem.find('.' + fontClassName);
//     iconElems.each(function (index, inconNode) {
//         var fontSize = $(inconNode).css("font-size");
//         var iconColor = $(inconNode).css("color");
//         var styleContent = $(inconNode).attr('style');
//         //去掉"px"
//         fontSize = fontSize.replace("px", "");
//         var charCode = getCharCodeByGlyphiconsName(iconName);
//         var myCanvas = document.createElement('canvas');
//         //把canva宽高各增加2是为了显示图标完整
//         myCanvas.width = parseInt(fontSize) + 2;
//         myCanvas.height = parseInt(fontSize) + 2;
//         myCanvas.style = styleContent;
//         var ctx = myCanvas.getContext('2d');
//         //设置绘图内容的颜色
//         ctx.fillStyle = iconColor;
//         //设置绘图的字体大小以及font-family的名字
//         ctx.font = fontSize + 'px ' + fontFamilyName;
//         ctx.fillText(String.fromCharCode(charCode), 1, parseInt(fontSize) + 1);
//         $(inconNode).replaceWith(myCanvas);
//     });
// }
// //根据glyphicons/glyphicon图标的类名获取到对应的char code
// function getCharCodeByGlyphiconsName(iconName) {
//     switch (iconName) {
//         case("glyphicons-resize-full"):
//             return "0xE216";
//         case ("glyphicons-chevron-left"):
//             return "0xE225";
//         default:
//             return "";
//     }
// }

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
                url: "/mobile/phoneqqFriendChat/getDetailsByFilter",
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
            $( "#project-id" ).val( ui.item.value );
            var wxContent = $("#qqContent");
            is_running = true;
            wxContent.html("");
            getByRn(ui.item.rn);
            $("#project").blur();

            return false;
        }
    })
        .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
        return $( "<li>" )
            .append( "<a><div style='line-height: 20px'><div style='width:120px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden; float: left'><b style='font-size: 12px; '>" + item.nickname + "</b></div>" +
                "<span style='font-size: 10px; float: right;color: grey'>"+item.fstime+"</span><br>" +
                "<span style='font-size: 14px; color: grey'><pre>" + item.label + "</pre></span></div></a>" +"<hr style='margin: 0;padding: 0'>")
            .appendTo( ul );
    };
}

function getByRn(rn) {
    var seach = $("#project").val().trim();
    var zhxx = $("#zhxx").val();
    var dszh = $("#dszh").val();
    window.pageUp=parseInt(rn);
    window.pageDown=parseInt(rn);
    var wxContent = window.document.getElementById("qqContent")
    var url = "/mobile/phoneqqFriendChat/getDetails";
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
            $("#qqContent").scrollTop(30);
            is_running = false;
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
    var seach = $("#project").val().trim();
    var zhxx = $(obj).closest("tr").find("td:eq(1)").text()
    var dszh = $(obj).closest("tr").find("td:eq(3)").text()
    window.pageDown = 1;
    var wxContent = window.document.getElementById("qqContent")
    var url = "/mobile/phoneqqFriendChat/getDetails";
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

var is_running = false;
function scrollF(){
    var qqContent = window.document.getElementById("qqContent");
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
            var url = "/mobile/phoneqqFriendChat/getDetails";
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
                    $("#qqContent").append(str) ;
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
            var url = "/mobile/phoneqqFriendChat/getDetails";
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
                    $("#qqContent").prepend(str);
                    var after = qqContent.scrollHeight;
                    $("#qqContent").scrollTop(after-before);
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
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
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
            str += "<img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "mp3"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
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
            str += "<i class=\"triangle-user\"></i></div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "mp4" || data.fslx == "mp4_temp"|| data.fslx == "mpeg"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
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
            str += "</video></span></div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "jfif" || data.fslx == "png" || data.fslx=="jpg"){
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
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
            str += "</div><img class=\"user-img\" src=\"/mobile/resources/image/qq.png\"/></div>";
        }
    }else if(data.fslx == "pdf" || data.fslx == "xlsx" || data.fslx == "xls" || data.fslx == "docx"){
        var file=data.lujing.substr(data.lujing.lastIndexOf('\\')+1);
        if(data.fsfx == "接收"){
            str += "<div class=\"admin-group\"><img class=\"admin-img\" src=\"/mobile/resources/image/qq.png\"/>";
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