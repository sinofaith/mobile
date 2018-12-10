<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../template/sideBar_left.jsp" %>
<%@include file="../template/newmain1.jsp" %>

<%--详情模块脚本--%>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/bootstrap-theme.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/css.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/map.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font.css"/>" rel="stylesheet" media="screen">

<link href="<c:url value="/resources/thirdparty/alertify/css/bootstrap.css"/> " rel="stylesheet">

<script src="<c:url value="/resources/jquery/jquery.js"/> "></script>
<script src="<c:url value="/resources/jquery/jquery.media.js"/> "></script>
<script src="<c:url value="/resources/js/jquery-1.9.1.min.js"/> "></script>
<script src="<c:url value="/resources/js/toggle.js"/> "></script>

<script src="<c:url value="/resources/js/echars/echarts.min.js"/> "></script>
<%--<script src="<c:url value="/resources/js/echars/echarts-gl.min.js"/> "></script>--%>
<script src="<c:url value="/resources/js/echars/ecStat.min.js"/> "></script>
<script src="<c:url value="/resources/js/echars/dataTool.min.js"/> "></script>
<script src="<c:url value="/resources/js/echars/china.js"/> "></script>
<script src="<c:url value="/resources/js/echars/world.js"/> "></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script src="<c:url value="/resources/js/echars/bmap.min.js"/> "></script>
<script src="<c:url value="/resources/js/echars/simplex.js"/> "></script>

<style type="text/css">
    .qgg-table{
        border-collapse: collapse;
        width:100%;
        border:1px solid #c6c6c6 !important;
        margin-bottom:20px;
    }
    .qgg-table th{
        border-collapse: collapse;
        border-right:1px solid #c6c6c6 !important;
        border-bottom:1px solid #c6c6c6 !important;
        /*background-color:#ddeeff !important;*/
        padding:5px 9px;
        font-size:14px;
        font-weight:normal;
        text-align:center;
    }
    .qgg-table td{
        border-collapse: collapse;
        border-right:1px solid #c6c6c6 !important;
        border-bottom:1px solid #c6c6c6 !important;
        padding:5px 9px;
        font-size:12px;
        font-weight:normal;
        text-align:center;
        word-break: break-all;
    }
    .qgg-table tr:nth-child(odd){
        background-color:#fff !important;
    }
    .qgg-table tr:nth-child(even){
        background-color: #f8f8f8 !important;
    }
    .back_btn{position: absolute;left: 700px;
        z-index: 1000;
        width: 40px;height: 40px;border-radius:50%;
        line-height: 40px;background: #14163A;color: #fff;
        text-align: center;cursor: pointer;display: none;}
    .back_btn1{position: absolute;left: 1390px;top: -10px;
        z-index: 1000;
        width: 40px;height: 40px;border-radius:50%;
        line-height: 40px;background: #1B316B;color: #fff;
        text-align: center;cursor: pointer;display: none;}
    .back_btn2{position: absolute;left: 1390px;top: -10px;
        z-index: 500;
        width: 40px;height: 40px;border-radius:50%;
        line-height: 40px;background: #1B316B;color: #fff;
        text-align: center;cursor: pointer;}
    .sun-title {
        width: 640px;
        height: 33px;
        margin-top: 10px;
        margin-left: 20px;
        position: absolute;
        }
</style>

<div class="tab_div" style="background-size: 100% 100%;background-repeat: no-repeat;background-image:url('${pageContext.request.contextPath}/resources/image/sybj.jpg'); height: 725px;">
    <div class="herder_bg" style="width: 100%;height:70px;background: url('${pageContext.request.contextPath}/resources/image/tjtopbg.png');background-size: 100% 100%;
    -moz-background-size: 100% 100%;-webkit-background-size: 100% 100%;"></div>

    <div id="div2" style="background-size: 100% 100%;background: url('${pageContext.request.contextPath}/resources/image/1-4.png') no-repeat;
            width: 680px; height: 300px;margin-left: 40px;">
        <div class="sun-title" style="background: url('${pageContext.request.contextPath}/resources/image/2-1.png')
                no-repeat;-moz-background-size:100% 100%;background-size:100% 100%;"></div>
        <div id="container1" style="height: 100%"></div>
    </div>
    <div id="con2" style="background-size: 100% 100%;background-repeat: no-repeat;background-image:url('${pageContext.request.contextPath}/resources/image/sybj.jpg');
            display: none;left: 4px;position:relative;">
        <span class="back_btn2" onclick="toggle1('con2')">关闭</span>
        <div id="container5" style="height: 80%"></div>
    </div>

    <div id="div1" style="background-size: 100% 100%;
            background: url('${pageContext.request.contextPath}/resources/image/1-4.png') no-repeat;
            width: 680px; height: 300px;margin-top: -300px;margin-left: 750px;">
        <div class="sun-title" style="background: url('${pageContext.request.contextPath}/resources/image/2-1.png')
                no-repeat;-moz-background-size:100% 100%;background-size:100% 100%;"></div>
        <div id="container" style="height: 100%;"></div>
    </div>
    <div id="con1" style="background-size: 100% 100%;background-repeat: no-repeat;background-image:url('${pageContext.request.contextPath}/resources/image/sybj.jpg');
            display: none;left: 4px;position:relative;">
        <span class="back_btn2" onclick="toggle1('con1')">关闭</span>
        <div id="container4" style="height: 80%"></div>
    </div>


    <div id="div3" style="background-size: 100% 100%;background: url('${pageContext.request.contextPath}/resources/image/1-4.png') no-repeat;
            width: 680px; height: 300px;margin-left: 40px;margin-top: 20px;">
        <div class="sun-title" style="background: url('${pageContext.request.contextPath}/resources/image/2-1.png')
                no-repeat;-moz-background-size:100% 100%;background-size:100% 100%;"></div>
        <span class="back_btn">返回</span>
        <div id="container2" style="height: 100%"></div>
    </div>
    <div id="con3" style="background-size: 100% 100%;background-repeat: no-repeat;background-image:url('${pageContext.request.contextPath}/resources/image/sybj.jpg');
            display: none;left: 4px;position:relative;">
        <span class="back_btn1">返回</span>
        <span class="back_btn2" onclick="toggle1('con3')">关闭</span>
        <div id="container6" style="height: 80%"></div>
    </div>

    <div id="div4" style="background-size: 100% 100%;background: url('${pageContext.request.contextPath}/resources/image/1-4.png') no-repeat;
            width: 680px; height: 300px; margin-top: -300px;margin-left: 750px;">
        <div class="sun-title" style="background: url('${pageContext.request.contextPath}/resources/image/2-1.png')
                no-repeat;-moz-background-size:100% 100%;background-size:100% 100%;"></div>
        <div id="container3" style="height: 100%"></div>
    </div>
    <div id="con4" style="background-size: 100% 100%;background-repeat: no-repeat;background-image:url('${pageContext.request.contextPath}/resources/image/sybj.jpg');
            display: none;left: 4px;position:relative;">
        <span class="back_btn2" onclick="toggle1('con4')">关闭</span>
        <div id="container7" style="height: 80%"></div>
    </div>
</div>

<script type="text/javascript">
    var content1 = "";
    $.ajax({
        url : '${pageContext.request.contextPath}/data/gain',
        //async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content1 = data;
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            var list = [];
            for(i=0;i<content1.length;i++){
                list.push(content1[i]['name']);
            }
            option = null;
            option = {
                title : {
                    text: '立案单位',
                    x:'center',
                    top:13,
                    textStyle: {
                        color: '#FFFFFF',
                        fontSize:16
                    }
                },
                tooltip : {
                    trigger: 'item',
                    // formatter: "{a} <br/>{b} : {c} ({d}%)"
                    formatter: function (params) {
                        return ('涉及品牌:'+params.data['brand_name']
                            +'</br>立案单位:'+params.data['name']
                            +'</br>合作次数:'+params.data['value']);
                    }
                },
                //color:['#000066','#990000','#00CC00','#FFFF00'],
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    top:42,
                    left: 'left',
                    textStyle:{
                        fontSize:12,
                        color: '#6cbbe6'
                    },
                    data: list
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 630,
                    top: 'center',
                    feature: {
                        dataView: {
                            readOnly: true,
                            optionToContent: function dataView(opt) {
                                var series = opt.series;
                                var table = '<div class="qgg-table"><table style="width:100%;"><tbody><tr>'
                                    + '<td style="font-weight: bold;">涉及品牌</td>'
                                    + '<td style="font-weight: bold;">立案单位</td>'
                                    + '<td style="font-weight: bold;">合作次数</td>'
                                    + '</tr>';
                                for (i = 0; i < content1.length; i++) {
                                    table += '<tr><td>' + content1[i]['brand_name'] + '</td>'
                                        + '<td>' + content1[i]['name'] + '</td>'
                                        + '<td>' + content1[i]['value'] + '</td></tr>'
                                }
                                table += '</tbody></table></div>';
                                return table;
                            }
                        },
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series : [
                    {
                        name: '立案单位',
                        type: 'pie',
                        radius : '55%',
                        data:content1,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        label: {
                            normal: {
                                position: 'outside',
                                formatter: '{b}',
                                textStyle: {
                                    color: '#3db3cb',
                                    fontSize: 12
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: true,
                                lineStyle: {
                                    color: '#3db3cb'
                                }
                            }
                        },
                    }
                ]
            };
            myChart.on('dblclick',function(params){
                toggle();
            });
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    });

    /**
     * 第二个
     * @type {HTMLElement | null}
     */
    var content9 = "";
    $.ajax({
        url : '${pageContext.request.contextPath}/data/staff',
        //async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content9 = data;
            var data10 = [];
            var data11 = {};
            var data12 = [];
            var data13 = {};
            var data14 = [];
            var t = content9[0].num;
            for(i=1;i<content9.length;i++){
                if(t<content9[i].num){
                    t = content9[i].num
                }
            }
            for(i=0;i<content9.length;i++){
                data10.push(content9[i].brand_name);
                if(t==content9[i].num){
                    data11 = {value:content9[i].num, name:content9[i].brand_name, selected:true};
                }else{
                    data11 = {value:content9[i].num, name:content9[i].brand_name};
                }
                data13 = {value:content9[i].num, name:content9[i].brand_name};
                data12.push(data11);
                data14.push(data13);
            }
            var dom1 = document.getElementById("container1");
            var myChart1 = echarts.init(dom1);
            var app = {};
            option1 = null;
            app.title = '嵌套环形图';

            option1 = {
                title : {
                    text: '品牌人员',
                    top:13,
                    x:'center',
                    textStyle: {
                        color: '#FFFFFF',
                        fontSize:16
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        for(i=0;i<content9.length;i++){
                            if(params.name==content9[i].brand_name) {
                                if(params.seriesName=='访问来源'){
                                    return ('品牌名:' + content9[i].brand_name + '(' + params.percent + '%)'
                                        + '</br>包含人员:' + content9[i].role_name
                                        + '</br>数据总条数:' + content9[i].num
                                    );
                                }else{
                                    return ('品牌名:' + content9[i].brand_name + '(' + params.percent + '%)'
                                        + '</br>通讯录总数:' + content9[i].txlNum
                                        + '</br>通话清单总数:' + content9[i].thqdNum
                                        + '</br>短信总数:' + content9[i].dxNum
                                        + '</br>QQ好友总数:' + content9[i].qfriendNum
                                        + '</br>QQ聊天总数:' + content9[i].qltjlNum
                                        + '</br>微信好友总数:' + content9[i].wfriendNum
                                        + '</br>微信聊天总数:' + content9[i].wltjlNum
                                    );
                                }
                            }
                            /*var num = content9[i].qqNum+content9[i].wxNum;
                            if(params.name==content9[i].name &&(params.name!="QQ总数" || params.name!="微信总数")){
                                return ('人员名:'+params.name+'('+params.percent+'%)'
                                    +'</br>通讯录个数:'+content9[i].txlNum
                                    +'</br>通话清单总数:'+content9[i].thqdNum
                                    +'</br>短信总数:'+content9[i].dxNum
                                );
                            }else if(params.name=="QQ总数" && content9[i].qqNum==params.data['value'] && (num-content9[i].wxNum)==params.data['value']){
                                return ('<span style="font-size: 16px">'+params.name+'</span>'+'('+content9[i].name+')'
                                    +'</br>qq好友个数:'+content9[i].qqfriendNum
                                    +'</br>qq群好友个数:'+content9[i].qqfriendsNum
                                    +'</br>qq好友聊天总数:'+content9[i].qqltNum
                                    +'</br>qq群好友聊天总数:'+content9[i].qqltsNum
                                );
                            }else if(params.name=="微信总数" && content9[i].wxNum==params.data['value'] && (num-content9[i].qqNum)==params.data['value']){
                                return ('<span style="font-size: 16px">'+params.name+'</span>'+'('+content9[i].name+')'
                                    +'</br>微信好友个数:'+content9[i].wxfriendNum
                                    +'</br>微信群好友个数:'+content9[i].wxfriendsNum
                                    +'</br>微信好友聊天总数:'+content9[i].wxltNum
                                    +'</br>微信群好友聊天总数:'+content9[i].wxltsNum
                                );
                            }*/
                        }
                    }
                },
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    x: 'left',
                    top:42,
                    left: 'left',
                    textStyle:{
                        fontSize:12,
                        color: '#6cbbe6'
                    },
                    data: data10
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 635,
                    top: 'center',
                    feature: {
                        dataView: {
                            readOnly: true,
                            optionToContent: function dataView(opt) {
                                var table = '<div class="qgg-table"><table style="width:100%;"><tbody><tr>'
                                    + '<td style="font-weight: bold;">品牌名</td>'
                                    + '<td style="font-weight: bold;">包含人员</td>'
                                    + '<td style="font-weight: bold;">通讯录总数</td>'
                                    + '<td style="font-weight: bold;">通话清单总数</td>'
                                    + '<td style="font-weight: bold;">短信总数</td>'
                                    + '<td style="font-weight: bold;">QQ好友总数</td>'
                                    + '<td style="font-weight: bold;">QQ聊天总数</td>'
                                    + '<td style="font-weight: bold;">微信好友总数</td>'
                                    + '<td style="font-weight: bold;">微信聊天总数</td>'
                                    + '</tr>';
                                for (i = 0; i < content9.length; i++) {
                                    table += '<tr><td>' + content9[i]['brand_name'] + '</td>'
                                        + '<td>' + content9[i]['role_name'] + '</td>'
                                        + '<td>' + content9[i]['txlNum'] + '</td>'
                                        + '<td>' + content9[i]['thqdNum'] + '</td>'
                                        + '<td>' + content9[i]['dxNum'] + '</td>'
                                        + '<td>' + content9[i]['qfriendNum'] + '</td>'
                                        + '<td>' + content9[i]['qltjlNum'] + '</td>'
                                        + '<td>' + content9[i]['wfriendNum'] + '</td>'
                                        + '<td>' + content9[i]['wltjlNum'] + '</td>'
                                }
                                table += '</tbody></table></div>';
                                return table;
                            }
                        },
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name:'访问来源',
                        type:'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],
                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: data12
                    },
                    {
                        name:'数据来源',
                        type:'pie',
                        radius: ['40%', '55%'],
                        label: {
                            normal: {
                                formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                backgroundColor: '#666',
                                borderColor: '#aaa',
                                borderWidth: 1,
                                borderRadius: 4,
                                adding: [0, 7],
                                rich: {
                                    a: {
                                        color: '#999',
                                        lineHeight: 22,
                                        align: 'center'
                                    },
                                    hr: {
                                        borderColor: '#aaa',
                                        width: '100%',
                                        borderWidth: 0.5,
                                        height: 0
                                    },
                                    b: {
                                        fontSize: 13,
                                        lineHeight: 30,
                                    },
                                    per: {
                                        color: '#eee',
                                        backgroundColor: '#334455',
                                        padding: [2, 4],
                                        borderRadius: 2
                                    }
                                }
                            }
                        },
                        data: data14
                    }
                ]
            };
            myChart1.on('dblclick',function(params){
                toggle2();
            });
            if (option1 && typeof option1 === "object") {
                myChart1.setOption(option1, true);
            }
        }
    });

    /**
     * 第三个
     * @type {*[]}
     */
    // 绘制图表，准备数据
    creatChinaMap();
    // 关闭按钮
    $('.back_btn').click(function(){
        creatChinaMap();
    })
    $('.back_btn1').click(function(){
        toggle3();
    })
    function creatChinaMap(){
        $(".back_btn").hide();
        var content = "";
        $.ajax({
            url : '${pageContext.request.contextPath}/data/area',
            async : false,
            type : "POST",
            dataType : 'json',
            success : function (data){
                content = data;
            }
        });
        var list1 = [];
        $.ajax({
            url : '${pageContext.request.contextPath}/data/brand',
            //async : false,
            type : "POST",
            dataType : 'json',
            success : function (data){
                for(i=0;i<data.length;i++){
                    list1.push(data[i]['brand_name']);
                }
                var cont = {};
                var map = [];
                var temp = [];
                var data = {};
                for(j=0;j<list1.length;j++){
                    map = [];
                    for(i=0;i<content.length;i++){
                        if(list1[j]==content[i]['brand_name']){
                            cont = {name:content[i]['name'],value:content[i]['value']};
                            map.push(cont)
                        }
                    }
                    data = {
                        name: list1[j],
                        type: 'map',
                        zoom: 1.2,
                        mapType: 'china',
                        showLegendSymbol: false,//  点点
                        roam: true, //缩放
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        itemStyle:{
                            normal:{
                                /*opacity:0.7,*/
                                areaColor:'#1B2351',
                                label:{
                                    show:true,
                                    textStyle: {
                                        color: "#fff",
                                    }
                                },
                                borderColor: '#fff',
                                borderWidth: 1
                            },
                            emphasis:{
                                label:{
                                    show:false,
                                    textStyle: {
                                        fontSize:12,
                                        fontStyle:'bold',
                                        color: "#fff",
                                    }
                                },
                                borderColor: '#fff',
                                borderWidth: 2
                            }
                        },
                        animation: true,
                        data: map
                    }
                    temp.push(data)
                }
                option2 = {
                    title: {
                        text: '品牌分布区域',
                        top:13,
                        left: 'center',
                        textStyle: {
                            color: '#FFFFFF',
                            fontSize:16
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: function (params) {
                            if(params.data==null){
                                return '暂无涉及';
                            }
                            var data = [];
                            var data1 = [];
                            var data2 = [];
                            var flag = true;
                            var flag1 = true;
                            var flag2 = true;
                            for(i=0;i<content.length;i++){
                                if(selected==null){
                                    if(content[i]['name']==params.data['name']){
                                        var list4 = content[i]['area'].split(',');
                                        for(k=0;k<list4.length;k++){
                                            for(j=0;j<data.length;j++){
                                                if(data[j] == list4[k]){
                                                    flag = false;
                                                }
                                            }
                                            if(flag){
                                                data.push(list4[k]);
                                            }
                                            flag = true;
                                        }
                                        // 品牌去重
                                        for(h=0;h<data1.length;h++){
                                            if(data1[h] == content[i]['brand_name']){
                                                flag1 = false;
                                            }
                                        }
                                        if(flag1){
                                            data1.push(content[i]['brand_name']);
                                        }
                                        flag1 = true;
                                        // 案件去重
                                        for(l=0;l<data2.length;l++){
                                            if(data2[l] == content[i]['case_name']){
                                                flag2 = false;
                                            }
                                        }
                                        if(flag2){
                                            data2.push(content[i]['case_name']);
                                        }
                                        flag2 = true;
                                    }
                                }else{
                                    // 当工具条点击后
                                    if(selected[content[i]['brand_name']]==true) {
                                        if(content[i]['name']==params.data['name']){
                                            var list4 = content[i]['area'].split(',');
                                            for(q=0;q<list4.length;q++){
                                                for(w=0;w<data.length;w++){
                                                    if(data[w] == list4[q]){
                                                        flag = false;
                                                    }
                                                }
                                                if(flag){
                                                    data.push(list4[q]);
                                                }
                                                flag = true;
                                            }
                                            // 品牌去重
                                            for(h=0;h<data1.length;h++){
                                                if(data1[h] == content[i]['brand_name']){
                                                    flag1 = false;
                                                }
                                            }
                                            if(flag1){
                                                data1.push(content[i]['brand_name']);
                                            }
                                            flag1 = true;

                                            // 案件去重
                                            for(l=0;l<data2.length;l++){
                                                if(data2[l] == content[i]['case_name']){
                                                    flag2 = false;
                                                }
                                            }
                                            if(flag2){
                                                data2.push(content[i]['case_name']);
                                            }
                                            flag2 = true;
                                        }
                                    }
                                }
                            }
                            var con = '涉及品牌:';
                            con += data1.join(',');
                            con += '</br>涉及案件:';
                            con += data2.join(',');
                            con += '</br>省:'+params.data['name'] + '</br>包含市:';
                            con += data.join(',');
                            con += '</br>区域数:' + params.value;
                            return con;
                        }
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        top:42,
                        left: 'left',
                        textStyle:{
                            fontSize:12,
                            color: '#6cbbe6'
                        },
                        data: list1,
                    },
                    visualMap: {
                        show: false,
                        min: 0,
                        max: 10,
                        left: 'left',
                        top: 'bottom',
                        text: ['高','低'],           // 文本，默认为数值文本
                        /*inRange: {
                            color: ['#e0ffff', '#02FFFA']
                        },*/
                        calculable: true
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 630,
                        top: 'center',
                        feature: {
                            dataView: {
                                readOnly: true,
                                optionToContent: function dataView(opt){
                                    var series = opt.series;
                                    var table = '<div class="qgg-table"><table style="width:100%;"><tbody><tr>'
                                        + '<td style="font-weight: bold;">品牌名</td>'
                                        + '<td style="font-weight: bold;">案件名</td>'
                                        + '<td style="font-weight: bold;">所在省</td>'
                                        + '<td style="font-weight: bold;">包含市</td>'
                                        // + '<td style="font-weight: bold;">区域数</td>'
                                        + '</tr>';
                                    for(i=0;i<content.length;i++){
                                        // 地区去重
                                        var split = content[i].area.split(",");
                                        $.unique(split.sort());

                                        table += '<tr><td>'+content[i]['brand_name']+'</td>'
                                            +'<td>'+content[i]['case_name']+'</td>'
                                            +'<td>'+content[i]['name']+'</td>'
                                            +'<td>'+split.join(",");+'</td></tr>'
                                        // +'<td>'+content[i]['value']+'</td>'
                                    }
                                    table += '</tbody></table></div>';
                                    return table;
                                }
                            },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    series: temp
                };
                //初始化echarts实例
                var myChart2 = echarts.init(document.getElementById('container2'));
                //使用制定的配置项和数据显示图表
                myChart2.setOption(option2);
                // 调用双击事件放大缩小
                myChart2.on('dblclick',function (params) {
                    toggle3();
                });
                var selected;
                // 获取当前legend的状态
                myChart2.on('legendselectchanged', function(obj) {
                    selected = obj.selected;
                });
                // 点击事件具体到市
                myChart2.on('click',function(params){
                    var list2 = [];
                    for(i=0;i<content.length;i++){
                        if(selected==null){
                            if(content[i]['name']==params.name){
                                var list3 = content[i]['area'].split(',');
                                for(j=0;j<list3.length;j++){
                                    if(list3[j]=="巢湖"){
                                        list2.push("合肥市");
                                    }else{
                                        list2.push(list3[j]+'市');
                                    }
                                }
                            }
                        }else{
                            if(selected[content[i]['brand_name']]==true) {
                                if (content[i]['name'] == params.name) {
                                    var list3 = content[i]['area'].split(',');
                                    for (j = 0; j < list3.length; j++) {
                                        if (list3[j] == "巢湖") {
                                            list2.push("合肥市");
                                        } else {
                                            list2.push(list3[j] + '市');
                                        }
                                    }
                                }
                            }
                        }
                    }
                    creatProvince(params.name,'container2',list2,1,content,selected);
                });
            }
        });
    }

    /**
     * 第四个图
     * @type {HTMLElement | null}
     */
    // 创建一个用于接收数据的content
    var content5 = "";
    $.ajax({
        url : './data/annualData',
        //async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content5 = data;
            var con1 = content5["0"];
            var list = [];
            for(i=con1.length-1;i>=0;i--){
                list.push(con1[i].year);
            }
            var dom2 = document.getElementById("container3");
            var myChart3 = echarts.init(dom2);
            var app = {};

            option3 = null;
            var posList = [
                'left', 'right', 'top', 'bottom',
                'inside',
                'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
            ];

            app.configParameters = {
                rotate: {
                    min: -90,
                    max: 90
                },
                align: {
                    options: {
                        left: 'left',
                        center: 'center',
                        right: 'right'
                    }
                },
                verticalAlign: {
                    options: {
                        top: 'top',
                        middle: 'middle',
                        bottom: 'bottom'
                    }
                },
                position: {
                    options: echarts.util.reduce(posList, function (map, pos) {
                        map[pos] = pos;
                        return map;
                    }, {})
                },
                distance: {
                    min: 0,
                    max: 100
                }
            };

            app.config = {
                rotate: 90,
                align: 'left',
                verticalAlign: 'middle',
                position: 'insideBottom',
                distance: 15,
                onChange: function () {
                    var labelOption = {
                        normal: {
                            rotate: app.config.rotate,
                            align: app.config.align,
                            verticalAlign: app.config.verticalAlign,
                            position: app.config.position,
                            distance: app.config.distance
                        }
                    };
                    myChart3.setOption({
                        series: [{
                            label: labelOption
                        }, {
                            label: labelOption
                        }, {
                            label: labelOption
                        }, {
                            label: labelOption
                        }]
                    });
                }
            };


            var labelOption = {
                normal: {
                    show: true,
                    position: app.config.position,
                    distance: app.config.distance,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    rotate: app.config.rotate,
                    formatter: '{c}  {name|{a}}',
                    fontSize: 16,
                    rich: {
                        name: {
                            textBorderColor: '#fff'
                        }
                    }
                }
            };

            var series = [];
            var series1 = [];
            if(content5["0"].length>0){
                for(j=content5["0"].length-1;j>=0;j--){
                    if(content5["0"][j].num!=null){
                        series1.push(content5["0"][j].num);
                    }else{
                        series1.push(content5["0"][j].num);
                    }
                }
                var se1 = {
                    name: '立案单位',
                    type: 'bar',
                    label: labelOption,
                    data: series1
                }
                series.push(se1);
            }
            var series2 = [];
            if(content5["1"].length>0){
                for(j=content5["1"].length-1;j>=0;j--){
                    if(content5["1"][j].num!=null){
                        series2.push(content5["1"][j].num);
                    }
                }
                var se2 = {
                    name: '案件',
                    type: 'bar',
                    label: labelOption,
                    data: series2
                }
                series.push(se2);
            }
            var series3 = [];
            if(content5["2"].length>0){
                for(j=content5["2"].length-1;j>=0;j--){
                    if(content5["2"][j].num!=null){
                        series3.push(content5["2"][j].num);
                    }
                }
                var se3 = {
                    name: '区域',
                    type: 'bar',
                    label: labelOption,
                    data: series3
                }
                series.push(se3);
            }
            var series4 = [];
            if(content5["3"].length>0){
                for(j=content5["3"].length-1;j>=0;j--){
                    if(content5["3"][j].num!=null){
                        series4.push(content5["3"][j].num);
                    }
                }
                var se4 = {
                    name: '人员',
                    type: 'bar',
                    label: labelOption,
                    data: series4
                }
                series.push(se4);
            }

            option3 = {
                title : {
                    text: '年度数据',
                    x:'center',
                    top:13,
                    textStyle: {
                        color: '#FFFFFF',
                        fontSize:16
                    }
                },
                grid:{
                    x:80,
                    y:85,
                    x2:80,
                    y2:30,
                    borderWidth:1
                },
                color: ['#68838B','#61A0A8', '#4cabce', '#006699'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    data: ['立案单位', '案件', '区域', '人员'],
                    top:42,
                    left: 'left',
                    textStyle:{
                        fontSize:12,
                        color: '#6cbbe6'
                    },
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 630,
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {
                            show: true,
                            readOnly: true,
                            optionToContent: function dataView(opt) {
                                var series = opt.series;
                                var table = '<div class="qgg-table"><table style="width:100%;"><tbody><tr>'
                                    + '<td style="font-weight: bold;">名称/年份</td>';
                                for(i=0;i<list.length;i++){
                                    table += '<td style="font-weight: bold;">' + list[i] + '</td>';
                                }

                                table += '</tr><tr><td style="font-weight: bold;">立案单位</td>';
                                for(i=0;i<series1.length;i++){
                                    table += '<td>' + series1[i] + '</td>';
                                }
                                table += '</tr><tr><td style="font-weight: bold;">案件</td>';
                                for(i=0;i<series2.length;i++){
                                    table += '<td>' + series2[i] + '</td>';
                                }

                                table += '</tr><tr><td style="font-weight: bold;">区域</td>';
                                for(i=0;i<series3.length;i++){
                                    table += '<td>' + series3[i] + '</td>';
                                }

                                table += '</tr><tr><td style="font-weight: bold;">人员</td>';
                                for(i=0;i<series4.length;i++){
                                    table += '<td>' + series4[i] + '</td>';
                                }
                                table += '</tr></tbody></table></div>';
                                return table;
                            }
                        },
                        magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {show: false},
                        data: list,
                        axisLine:{
                            lineStyle:{
                                color:'#0087ED',
                                width:1
                            }
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        splitLine:{
                            lineStyle:{
                                color:'#0087ED'
                            }
                        },
                        nameTextStyle:{
                            lineStyle:{
                                color:'#0087ED'
                            }
                        },
                        axisLine:{
                            lineStyle:{
                                color:'#0087ED',
                                width:1
                            }
                        }
                    }
                ],
                series: series
            };
            // 调用双击事件放大缩小
            myChart3.on('dblclick',function(params){
                toggle4();
            });
            if (option3 && typeof option3 === "object") {
                myChart3.setOption(option3, true);
            }
        }
    });
</script>