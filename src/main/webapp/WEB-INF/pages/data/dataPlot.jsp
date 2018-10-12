<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../template/sideBar_left.jsp" %>
<%@include file="../template/newmain.jsp" %>

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

<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
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
        background-color:#ddeeff !important;
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
    .close {
        background: orange;
        color: red;
        border-radius: 12px;
        line-height: 20px;
        text-align: center;
        height: 25px;
        width: 25px;
        font-size: 18px;
        padding: 1px;
    }
    .close::before {
        content: "\2716";
    }
    .close {
        top: -10px;
        right: -10px;
        position: absolute;
    }
    .back_btn{position: absolute;left: 700px;
        z-index: 1000;
        width: 40px;height: 40px;border-radius:50%;
        line-height: 40px;background: #c1c1c1;color: #fff;
        text-align: center;cursor: pointer;display: none;}
    .back_btn1{position: absolute;left: 1390px;top: -10px;
        z-index: 1000;
        width: 40px;height: 40px;border-radius:50%;
        line-height: 40px;background: #c1c1c1;color: #fff;
        text-align: center;cursor: pointer;display: none;}
    .back_btn2{position: absolute;left: 1390px;top: -10px;
        z-index: 500;
        width: 40px;height: 40px;border-radius:50%;
        line-height: 40px;background: #c1c1c1;color: #fff;
        text-align: center;cursor: pointer;}
</style>

<div class="tab_div">
    <%--<%@include file="../phone/title.jsp" %>--%>
    <%--<div style="width: 730px; height: 300px; float:left;">
        <div id="container" style="height: 100%"></div>
    </div>
    <div style="width: 730px; height: 300px; float:right;">
        <div id="container1" style="height: 100%"></div>
    </div>
    <div style="width: 730px; height: 300px; float:left;">
        <div id="container2" style="height: 115%"></div>
    </div>
    <div style="width: 730px; height: 300px; float:right;">
        <div id="container3" style="height: 115%"></div>
    </div>--%>


    <div id="div1" style="width: 730px; height: 300px;">
        <div id="container" style="height: 100%"></div>
    </div>
    <div id="con1" style="display: none;left: 4px;top: -300px;position:relative; background: #FFFFFF;">
        <span class="back_btn2" onclick="toggle1('con1')">关闭</span>
        <div id="container4" style="height: 80%"></div>
    </div>

    <div id="div2" style="width: 730px; height: 300px; margin-top: -300px;margin-left: 730px;">
        <div id="container1" style="height: 100%"></div>
    </div>
    <div id="con2" style="display: none;left: 4px;position:relative; background: #FFFFFF;">
        <span class="back_btn2" onclick="toggle1('con2')">关闭</span>
        <div id="container5" style="height: 80%"></div>
    </div>

    <div id="div3" style="width: 730px; height: 300px;">
        <span class="back_btn">返回</span>
        <div id="container2" style="height: 115%"></div>
    </div>
    <div id="con3" style="display: none;left: 4px;position:relative; background: #FFFFFF;">
        <span class="back_btn1">返回</span>
        <span class="back_btn2" onclick="toggle1('con3')">关闭</span>
        <div id="container6" style="height: 80%"></div>
    </div>

    <div id="div4" style="width: 730px; height: 300px; margin-top: -300px;margin-left: 730px;">
        <div id="container3" style="height: 115%"></div>
    </div>
    <div id="con4" style="display: none;left: 4px;position:relative; background: #FFFFFF;">
        <span class="back_btn2" onclick="toggle1('con4')">关闭</span>
        <div id="container7" style="height: 80%"></div>
    </div>

</div>

<script type="text/javascript">
    var content1 = "";
    $.ajax({
        url : '${pageContext.request.contextPath}/data/gain',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content1 = data;
        }
    });
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
            subtext: 'Filing unit',
            x:'center'
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
        legend: {
            type: 'scroll',
            orient: 'vertical',
            left: 15,
            data: list
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
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
                }
            }
        ]
    };
    myChart.on('dblclick',function(params){
        toggle();
    });
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    /**
     * 第二个
     * @type {HTMLElement | null}
     */
    var dom1 = document.getElementById("container1");
    var myChart1 = echarts.init(dom1);
    var app = {};
    option1 = null;
    app.title = '嵌套环形图';

    option1 = {
        title : {
            text: '人员分析',
            subtext: 'Man analysis',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            x: 'left',
            data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他','百','谷','应','他']
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
                data:[
                    {value:335, name:'直达', selected:true},
                    {value:679, name:'营销广告'},
                    {value:1548, name:'搜索引擎'}
                ]
            },
            {
                name:'访问来源',
                type:'pie',
                radius: ['40%', '55%'],
                label: {
                    normal: {
                        formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                        backgroundColor: '#eee',
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
                                fontSize: 16,
                                lineHeight: 33
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
                data:[
                    {value:335, name:'直达'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:548, name:'百度'},
                    {value:151, name:'谷歌'},
                    {value:77, name:'必应'},
                    {value:82, name:'其他'},
                    {value:500, name:'百'},
                    {value:100, name:'谷'},
                    {value:70, name:'应'},
                    {value:20, name:'他'}
                ]
            }
        ]
    };
    myChart1.on('dblclick',function(params){
        toggle2();
    });
    if (option1 && typeof option1 === "object") {
        myChart1.setOption(option1, true);
    }
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
            async : false,
            type : "POST",
            dataType : 'json',
            success : function (data){
                for(i=0;i<data.length;i++){
                    list1.push(data[i]['brand_name']);
                }
            }
        });

        var cont = {};
        var map = [];
        var temp = []
        var data = {}
        for(j=0;j<list1.length;j++){
            map = []
            for(i=0;i<content.length;i++){
                if(list1[j]==content[i]['brand_name']){
                    cont = {name:content[i]['name'],value:content[i]['value']}
                    map.push(cont)
                }
            }
            data = {
                name: list1[j],
                type: 'map',
                mapType: 'china',
                roam: true,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
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
                subtext: 'Brand distribution area',
                left: 'center'
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
                left: 15,
                data: list1,
                /*selected: {
                    //不想显示的都设置成false
                    '雀巢' : false,
                    '可口可乐' : false
                }*/
            },
            visualMap: {
                show: false,
                min: 0,
                max: 20,
                left: 'left',
                top: 'bottom',
                text: ['高','低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
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
                                + '<td style="font-weight: bold;">区域数</td>'
                                + '</tr>';
                            for(i=0;i<content.length;i++){
                                table += '<tr><td>'+content[i]['brand_name']+'</td>'
                                    +'<td>'+content[i]['case_name']+'</td>'
                                    +'<td>'+content[i]['name']+'</td>'
                                    +'<td>'+content[i]['area']+'</td>'
                                    +'<td>'+content[i]['value']+'</td></tr>'
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

    /**
     * 第四个图
     * @type {HTMLElement | null}
     */
    // 创建一个用于接收数据的content
    var content5 = "";
    $.ajax({
        url : './data/annualData',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content5 = data;
        }
    });
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
            subtext: 'Annual data',
            x:'center'
        },
        color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            left: 'left',
            /*data: ['Forest', 'Steppe', 'Desert', 'Wetland']*/
            data: ['立案单位', '案件', '区域', '人员'],
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
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
                data: list
            }
        ],
        yAxis: [
            {
                type: 'value'
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
</script>