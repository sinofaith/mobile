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
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>


<div class="tab_div">
    <%--<%@include file="../phone/title.jsp" %>--%>
    <div style="width: 730px; height: 300px; float:left;">
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
    </div>

</div>
<script type="text/javascript">
    var content = "";
    $.ajax({
        url : '${pageContext.request.contextPath}/data/gain',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content = data;
        }
    });
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var list = [];
    for(i=0;i<content.length;i++){
        list.push(content[i]['name']);
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
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: list
        },
        series : [
            {
                name: '立案单位',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:content,
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
    ;
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
            text: 'xxx',
            subtext: 'Filing unit',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
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
                    {value:1048, name:'百度'},
                    {value:251, name:'谷歌'},
                    {value:147, name:'必应'},
                    {value:102, name:'其他'}
                ]
            }
        ]
    };;
    if (option1 && typeof option1 === "object") {
        myChart1.setOption(option1, true);
    }
    /**
     * 第三个
     * @type {*[]}
     */
    // 绘制图表，准备数据
    content = "";
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
            roam: false,
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            data:map
        }
        temp.push(data)
    }
    console.log("sad",temp)

    option2 = {
        title: {
            text: '品牌分布区域',
            subtext: 'Brand distribution area',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            /*formatter: function(params) {
                var res = params.name + '<br/>';
                var myseries = option2.series;
                for (var i = 0; i < myseries.length; i++) {
                    for (var j = 0; j < myseries[i].data.length; j++) {
                        if (myseries[i].data[j].name == params.name) {
                            res += myseries[i].name + ' : ' + myseries[i].data[j].value + '</br>';
                        }
                    }
                }
                return res;
            }*/
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:list1,
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

    /**
     * 第四个图
     * @type {HTMLElement | null}
     */
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

    option3 = {
        title : {
            text: '年度比较',
            subtext: 'Filing unit',
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
            x: 'left',
            data: ['Forest', 'Steppe', 'Desert', 'Wetland']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: true},
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
                data: ['2012', '2013', '2014', '2015', '2016']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: 'Forest',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: [320, 332, 301, 334, 390]
            },
            {
                name: 'Steppe',
                type: 'bar',
                label: labelOption,
                data: [220, 182, 191, 234, 290]
            },
            {
                name: 'Desert',
                type: 'bar',
                label: labelOption,
                data: [150, 232, 201, 154, 190]
            },
            {
                name: 'Wetland',
                type: 'bar',
                label: labelOption,
                data: [98, 77, 101, 99, 40]
            }
        ]
    };
    if (option3 && typeof option3 === "object") {
        myChart3.setOption(option3, true);
    }
</script>