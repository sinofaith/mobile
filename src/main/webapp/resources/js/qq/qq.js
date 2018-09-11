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

function getFriendChatDetails(obj) {
    var fsqq = $(obj).closest("tr").find("td:eq(4)").text()
    var jsqq = $(obj).closest("tr").find("td:eq(6)").text()
    window.page = 1
   /* var type = ""
    if( /^[a-zA-Z]([-_a-zA-Z0-9])*$/.test(jylx)){
        type="dfzh"
    }else{
        type="jylx"
    }*/
    var tbody = window.document.getElementById("result")
    var url = "/mobile/qqFriendChat/getDetails"
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            fsqq:fsqq,
            jsqq:jsqq,
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
                    "<td width=\"8%\">"+data[i].fsqq+"</td>"+
                    "<td width=\"7%\">"+data[i].fsqqnc+"</td>"+
                    "<td width=\"10%\">"+data[i].fstime+"</td>"+
                    "<td width=\"8%\">"+data[i].jsqqno+"</td>"+
                    "<td width=\"7%\">"+data[i].jsqqnc+"</td>"+
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
            $("#fsqq").attr("value",fsqq);
            $("#jsqq").attr("value",jsqq);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
}

function orderByFilter(filter) {
    var tbody = window.document.getElementById("result")
    if(tbody!=null) {
        tbody.innerHTML = ""
    }
    var fsqq = $("#fsqq").val();
    var jsqq = $("#jsqq").val();
    window.page = 1
    var url = "/mobile/qqFriendChat/getDetails"
    $.ajax({
        type:"post",
        dataType:"json",
        url:url,
        data:{
            fsqq:fsqq,
            jsqq:jsqq,
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
                    "<td width=\"8%\">"+data[i].fsqq+"</td>"+
                    "<td width=\"7%\">"+data[i].fsqqnc+"</td>"+
                    "<td width=\"10%\">"+data[i].fstime+"</td>"+
                    "<td width=\"8%\">"+data[i].jsqqno+"</td>"+
                    "<td width=\"7%\">"+data[i].jsqqnc+"</td>"+
                    "<td width=\"7%\">"+data[i].fslx+"</td>"+
                    "<td width=\"25%\" title="+data[i].fanrs+">" +
                    "<div style=\"width: 100%;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;\">"+data[i].fanrs+"</div>" +
                    "</td>"+
                    "<td width=\"10%\" title="+data[i].lujing+">" +
                    "<div style=\"width:100%;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;\">"+data[i].lujing+"</div>" +
                    "</td>"+
                    "<td width=\"8%\">"+data[i].dataType+"</td>"+
                    "</tr>";
            }
            tbody.innerHTML = str
            $("#fsqq").attr("value",fsqq);
            $("#jsqq").attr("value",jsqq);
            $("#allRow").attr("value",msg.totalRecords)
        }
    })
}