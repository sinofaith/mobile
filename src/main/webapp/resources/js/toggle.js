function toggle1(obj) {
    $("#"+obj).css("display", "none");
    $("#div1").css("display", "block");
    $("#div2").css("display", "block");
    $("#div3").css("display", "block");
    $("#div4").css("display", "block");
}
function toggle(){
    $("#con1").css("display", "block");
    $("#div2").css("display", "none");
    $("#div3").css("display", "none");
    $("#div4").css("display", "none");
    var content = "";
    $.ajax({
        url : './data/gain',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content = data;
        }
    });
    var dom8 = document.getElementById("container4");
    var myChart8 = echarts.init(dom8);
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
            left: 'left',
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
                        for (i = 0; i < content.length; i++) {
                            table += '<tr><td>' + content[i]['brand_name'] + '</td>'
                                + '<td>' + content[i]['name'] + '</td>'
                                + '<td>' + content[i]['value'] + '</td></tr>'
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
        myChart8.setOption(option, true);
    }
}
function toggle2(){
    $("#con2").css("display", "block");
    $("#div1").css("display", "none");
    $("#div2").css("display", "none");
    $("#div3").css("display", "none");
    $("#div4").css("display", "none");
    $.ajax({
        url : '${pageContext.request.contextPath}/data/staff',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content9 = data;
        }
    });
    console.log(content9)
    var data10 = [];
    var data11 = {};
    var data12 = [];
    var data13 = {};
    var data14 = [];
    var t = true;
    for(i=0;i<content9.length;i++){
        data10.push(content9[i].name);
        if((content9[i].qqNum+content9[i].wxNum)>0){
            if(t){
                data11 = {value:content9[i].qqNum+content9[i].wxNum,name:content9[i].name,selected:true};
                t = false;
            }else{
                data11 = {value:content9[i].qqNum+content9[i].wxNum,name:content9[i].name};
            }
            data12.push(data11);
            data13 = {value:content9[i].qqNum,name:"QQ总数"};
            data14.push(data13);
            data13 = {value:content9[i].wxNum,name:"微信总数"};
            data14.push(data13);
        }
    }
    data10.push("QQ总数");data10.push("微信总数");
    var dom1 = document.getElementById("container5");
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
            formatter: function (params) {

                for(i=0;i<content9.length;i++){
                    var num = content9[i].qqNum+content9[i].wxNum;
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
                    }else if(params.name=="微信总数"  && content9[i].wxNum==params.data['value'] && (num-content9[i].qqNum)==params.data['value']){
                        return ('<span style="font-size: 16px">'+params.name+'</span>'+'('+content9[i].name+')'
                            +'</br>微信好友个数:'+content9[i].wxfriendNum
                            +'</br>微信群好友个数:'+content9[i].wxfriendsNum
                            +'</br>微信好友聊天总数:'+content9[i].wxltNum
                            +'</br>微信群好友聊天总数:'+content9[i].wxltsNum
                        );
                    }
                }
            }
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            x: 'left',
            data: data10
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
                            + '<td style="font-weight: bold;">姓名</td>'
                            + '<td style="font-weight: bold;">通讯录总数</td>'
                            + '<td style="font-weight: bold;">通话清单总数</td>'
                            + '<td style="font-weight: bold;">短信总数</td>'
                            + '<td style="font-weight: bold;">QQ好友总数</td>'
                            + '<td style="font-weight: bold;">QQ群好友总数</td>'
                            + '<td style="font-weight: bold;">QQ好友聊天总数</td>'
                            + '<td style="font-weight: bold;">QQ群好友聊天总数</td>'
                            + '<td style="font-weight: bold;">微信好友总数</td>'
                            + '<td style="font-weight: bold;">微信群好友总数</td>'
                            + '<td style="font-weight: bold;">微信好友聊天总数</td>'
                            + '<td style="font-weight: bold;">微信群好友聊天总数</td>'
                            + '</tr>';
                        for (i = 0; i < content9.length; i++) {
                            if((content9[i].qqNum+content9[i].wxNum)>0){
                                table += '<tr><td>' + content9[i]['name'] + '</td>'
                                    + '<td>' + content9[i]['txlNum'] + '</td>'
                                    + '<td>' + content9[i]['thqdNum'] + '</td>'
                                    + '<td>' + content9[i]['dxNum'] + '</td>'
                                    + '<td>' + content9[i]['qqfriendNum'] + '</td>'
                                    + '<td>' + content9[i]['qqfriendsNum'] + '</td>'
                                    + '<td>' + content9[i]['qqltNum'] + '</td>'
                                    + '<td>' + content9[i]['qqltsNum'] + '</td>'
                                    + '<td>' + content9[i]['wxfriendNum'] + '</td>'
                                    + '<td>' + content9[i]['wxfriendsNum'] + '</td>'
                                    + '<td>' + content9[i]['wxltNum'] + '</td>'
                                    + '<td>' + content9[i]['wxltsNum'] + '</td></tr>';
                            }
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
                data: data14
            }
        ]
    };
    if (option1 && typeof option1 === "object") {
        myChart1.setOption(option1, true);
    }
}

function toggle3(){
    $(".back_btn1").hide();
    $("#con3").css("display", "block");
    $("#div1").css("display", "none");
    $("#div2").css("display", "none");
    $("#div3").css("display", "none");
    $("#div4").css("display", "none");
    // 绘制图表，准备数据
    var content = "";
    $.ajax({
        url : './data/area',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content = data;
        }
    });
    var list1 = [];
    $.ajax({
        url : './data/brand',
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
            showLegendSymbol: false,
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
    option2 = null
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
                /*return ('涉及品牌:'+params.data['brand_name']
                    +'</br>立案单位:'+params.data['name']
                    +'</br>合作次数:'+params.data['value']);*/
            }
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            left: 'left',
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
    var myChart2 = echarts.init(document.getElementById('container6'));
    //使用制定的配置项和数据显示图表
    myChart2.setOption(option2);

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
        creatProvince(params.name,'container6',list2,2,content,selected);
    });

}
function toggle4(){
    $("#con4").css("display", "block");
    $("#div1").css("display", "none");
    $("#div2").css("display", "none");
    $("#div3").css("display", "none");
    $("#div4").css("display", "none");
    // 创建一个用于接收数据的content
    var content = "";
    $.ajax({
        url : './data/annualData',
        async : false,
        type : "POST",
        dataType : 'json',
        success : function (data){
            content = data;
        }
    });
    var con1 = content["0"];
    var list = [];
    for(i=con1.length-1;i>=0;i--){
        list.push(con1[i].year);
    }
    // console.log(list)
    var dom2 = document.getElementById("container7");
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
    if(content5["2"]!=null){
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
    if(content5["3"]!=null){
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
            data: ['立案单位', '案件', '区域', '人员']
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
                    optionToContent: function dataView(opt){
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
    if (option3 && typeof option3 === "object") {
        myChart3.setOption(option3, true);
    }
}

function pyChinese(pro){
    var str = '';
    switch(pro){
        case '黑龙江':
            str = 'heilongjiang';
            break;
        case '河北':
            str = 'hebei';
            break;
        case '甘肃':
            str = 'gansu';
            break;
        case '云南':
            str = 'yunnan';
            break;
        case '四川':
            str = 'sichuan';
            break;
        case '吉林':
            str = 'jilin';
            break;
        case '辽宁':
            str = 'liaoning';
            break;
        case '青海':
            str = 'qinghai';
            break;
        case '陕西':
            str = 'shanxi';
            break;
        case '河南':
            str = 'henan';
            break;
        case '山东':
            str = 'shandong';
            break;
        case '山西':
            str = 'shanxi';
            break;
        case '安徽':
            str = 'anhui';
            break;
        case '湖北':
            str = 'hubei';
            break;
        case '湖南':
            str = 'hunan';
            break;
        case '江苏':
            str = 'jiangsu';
            break;
        case '贵州':
            str = 'guizhou';
            break;
        case '浙江':
            str = 'zhejiang';
            break;
        case '江西':
            str = 'jiangxi';
            break;
        case '广东':
            str = 'guangdong';
            break;
        case '福建':
            str = 'fujian';
            break;
        case '台湾':
            str = 'taiwan';
            break;
        case '海南':
            str = 'hainan';
            break;
        case '广西':
            str = 'guangxi';
            break;
        case '内蒙古':
            str = 'neimenggu';
            break;
        case '宁夏':
            str = 'ningxia';
            break;
        case '新疆':
            str = 'xinjiang';
            break;
        case '西藏':
            str = 'xizang';
            break;
        case '澳门':
            str = 'aomen';
            break;
        case '北京':
            str = 'beijing';
            break;
        case '上海':
            str = 'shanghai';
            break;
        case '香港':
            str = 'xianggang';
            break;
        case '天津':
            str = 'tianjin';
            break;
        case '重庆':
            str = 'chongqing';
            break;
        default:
            str = '';
            break;
    }
    return str;
}

function creatProvince(name,id,list,num,content,selected){
    var data = [];
    for(i=0;i<list.length;i++){
        var cont = {name:list[i],value:1};
        data.push(cont);
    }

    var pro = pyChinese(name);
    if(pro==''){
        return false;
    }
    if(num==1){
        $('.back_btn').show();
    }else if(num==2){
        $('.back_btn1').show();
    }
    var childChart = echarts.init(document.getElementById(id));
    var option = {
        title: {
            text: name,
            left: 'center',
            textStyle: {
                color: '#000'
            }
            ,top:30
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                if(params.name==''){
                    return "暂无涉及";
                }
                var area = params.name.slice(0,params.name.length-1);
                if(area == "合肥"){
                    area = "巢湖";
                }
                var con = "涉及品牌:";
                var data = [];
                var data2 = [];
                var flag = true;
                var flag2 = true;
                for(i=0;i<content.length;i++){
                    if(selected==null){
                        var list = content[i]['area'].split(',');
                        for(j=0;j<list.length;j++){
                            if(area == list[j]){
                                // 品牌去重
                                for(k=0;k<data.length;k++){
                                    if(content[i]['brand_name'] == data[k]){
                                        flag = false;
                                    }
                                }
                                if(flag){
                                    data.push(content[i]['brand_name']);
                                }
                                flag = true;
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
                    }else{
                        if(selected[content[i]['brand_name']] == true){
                            var list = content[i]['area'].split(',');
                            for(j=0;j<list.length;j++){
                                if(area == list[j]){
                                    // 品牌去重
                                    for(k=0;k<data.length;k++){
                                        if(content[i]['brand_name'] == data[k]){
                                            flag = false;
                                        }
                                    }
                                    if(flag){
                                        data.push(content[i]['brand_name']);
                                    }
                                    flag = true;
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
                }
                con += data.join(',');
                con += "</br>涉及案件:"; con += data2.join(',');
                con += "</br>市:"; con += params.name;
                con += "</br>区域数:"; con += params.value;
                return con;
            }
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
        series: [{
            name: '区域数',
            type: 'map',
            mapType: pro,
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
            data: data
        }]
    };
    $.get('resources/json/'+pro+'.json', function(gdMap) {
        echarts.registerMap(pro, gdMap);
        childChart.setOption(option, true);
    });
}