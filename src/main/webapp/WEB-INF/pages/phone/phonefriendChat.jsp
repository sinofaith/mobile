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
<link href="<c:url value="/resources/css/qq/qq.css"/> " rel="stylesheet">

<script src="<c:url value="/resources/jquery/jquery.js"/> "></script>
<script src="<c:url value="/resources/jquery/jquery.media.js"/> "></script>
<script src="<c:url value="/resources/js/jquery-1.9.1.min.js"/> "></script>
<link href="<c:url value="/resources/thirdparty/gojs/css/jquery-ui.min.css"/> " rel="stylesheet">
<script src="<c:url value="/resources/thirdparty/gojs/js/jquery/jquery-ui.min.js"/> "></script>
<script src="<c:url value="/resources/js/aj.js"/> "></script>
<script src="<c:url value="/resources/js/qq/qq.js"/> "></script>
<script src="<c:url value="/resources/thirdparty/jquery-form/jquery.form.js"/>" type="text/javascript"></script>
<%--<script src="<c:url value="/resources/js/pdf/canvg2.js"/>" type="text/javascript"></script>--%>
<script src="<c:url value="/resources/js/pdf/html2canvas-0.4.1.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/pdf/jspdf.debug.js"/>" type="text/javascript"></script>

<div class="tab_div">
    <%@include file="title.jsp" %>
    <ul >
        <div class="main-container-inner " style="margin-bottom: 10px">
            <div class="width_100 pos_re_block">
                <div class="cantent_block ">
                    <div class="sidebar_left ">
                        <div class="ddr">
                            <div >
                                <input name="label" id="label" hidden="hidden">

                                <table class="table  table-hover table_style table_list1 " id="aa" style="border-left: 1px solid #ccc; border-right: 1px solid #ccc!important;">
                                    <tr>
                                        <td colspan="8"  align="center" class="dropdown_index" style="background-color: #eee;">
                                            <div class="dropdown " style="color: #333">
                                                <strong>QQ好友聊天信息</strong>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <td width="3%">序号</td>
                                        <td width="4%">QQ号</td>
                                        <td width="4%">QQ昵称</td>
                                        <td width="4%">对方QQ号</td>
                                        <td width="4%">对方QQ昵称</td>
                                        <%--<td width="9%">机主姓名</td>--%>
                                        <td width="6%"><a href="${pageContext.request.contextPath}/phoneqqFriendChat/order?orderby=num">聊天次数</a></td>
                                        <td width="6%">详情</td>
                                    </tr>
                                    <c:forEach items="${detailinfo}" var="item" varStatus="st">
                                        <tr class="${st.index%2==1 ? '':'odd' }">
                                            <td align="center">${item.id}</td>
                                            <td align="center" title='${item.zhxx}'><div style="width:120px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;">${item.zhxx}</div></td>
                                            <td align="center" title='${item.zhnc}'><div style="width:200px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;">${item.zhnc}</div></td>
                                            <td align="center" title='${item.dszh}'><div style="width:120px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;">${item.dszh}</div></td>
                                            <td align="center" title='${item.dsnc}'><div style="width:200px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;">${item.dsnc}</div></td>
                                            <%--<td align="center">${item.u_name}</td>--%>
                                            <td align="center">${item.num}</td>
                                            <td align="center">
                                                <button  data-toggle="modal" data-target="#myModal" onclick="getQqFirendDetails(this)">详情</button></td>
                                            <%--<td align="center" title='${item.lujing}'>
                                                <div style="width:230px; height: 15px;">
                                                    <xmp style="margin-top: 0px; font-family: 'Microsoft YaHei UI'; width:230px; white-space: nowrap;text-overflow:ellipsis; overflow:hidden">${item.lujing}</xmp>
                                                </div>
                                            </td>--%>
                                        </tr>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${detailinfo ==null || detailinfo.size()==0}">
                                            <tr>
                                                <td colspan="8" align="center"> 无数据 </td>
                                            </tr>
                                        </c:when>
                                    </c:choose>

                                </table>

                            </div>
                            <c:choose>
                                <c:when test="${detailinfo!=null && detailinfo.size()!=0}">
                                    <div  class="page_nmber">
                                        <div class="mar_t_15">共${page.totalRecords}条记录 共<span id="totalPage">${page.totalPages}</span>页 当前第${page.pageNo}页<br></div>
                                        <a href="${pageContext.request.contextPath}/phoneqqFriendChat/seach?pageNo=${page.topPageNo }"><input type="button" name="fristPage" value="首页" /></a>
                                        <c:choose>
                                            <c:when test="${page.pageNo!=1}">
                                                <a href="${pageContext.request.contextPath}/phoneqqFriendChat/seach?pageNo=${page.previousPageNo }"><input type="button" name="previousPage" value="上一页" /></a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" name="previousPage" value="上一页" />
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${page.pageNo != page.totalPages}">
                                                <a href="${pageContext.request.contextPath}/phoneqqFriendChat/seach?pageNo=${page.nextPageNo }"><input type="button" name="nextPage" value="下一页" /></a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" name="nextPage" value="下一页" />
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="${pageContext.request.contextPath}/phoneqqFriendChat/seach?pageNo=${page.bottomPageNo }"><input type="button" name="lastPage" value="尾页" /></a>
                                        <input type="number" id="num" max="${page.totalPages}" style="width: 9%" min="1"/>
                                        <input type="button" value="跳转" onclick="phoneSkip('${phone}')"/>
                                            <%--<input type="button" value="多案件分析" onclick="wordsCount()">--%>
                                        <%--<input type="button" data-toggle="modal" data-target="#myModal1" onclick="getWordList()" value="删除文档"/>--%>
                                    </div>

                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="sidebar_right pos_re">

                        <div class=" ">

                            <div>
                                <form action="${pageContext.request.contextPath}/phoneqqFriendChat/seachCode" method="post">
                                    <div class="form-group_search  fl_l width100" >
                                        <span style="margin-left: 10px;color: #444;padding-bottom: 10px;">查询方式</span>
                                        <select name="seachCondition" class="width100" STYLE="margin-bottom: 20px;">
                                            <option value="zhxx" <c:if test="${friendChatxxSeachCondition=='zhxx'}">selected="selected"</c:if> >QQ号</option>
                                            <option value="zhnc" <c:if test="${friendChatxxSeachCondition=='zhnc'}">selected="selected"</c:if> >QQ昵称</option>
                                            <option value="dszh" <c:if test="${friendChatxxSeachCondition=='dszh'}">selected="selected"</c:if> >对方QQ号</option>
                                            <option value="dsnc" <c:if test="${friendChatxxSeachCondition=='dsnc'}">selected="selected"</c:if> >对方QQ昵称</option>
                                            <option value="lujing" <c:if test="${friendChatxxSeachCondition=='lujing'||friendChatxxSeachCondition==null}">selected="selected"</c:if> >聊天内容</option>
                                        </select>
                                        <%--<input  style="margin-left: 10px;" type="checkbox" name="usable" value="1" <c:if test="${usable eq '1'}">checked="checked"</c:if>>上次条件有效--%>
                                        <textarea  class="form-control02 seachCode fl_l width100" id="seachCode" placeholder="请输入要查询内容" name="seachCode" >${friendChatxxSeachCode}</textarea>
                                    </div>

                                    <button type="submit" class="right_a_nav margin_none" >查询</button>
                                    <%--<button type="button" class="right_a_nav margin_none add_button" onclick="AddCrimeterrace()">新增人员信息</button>--%>
                                </form>
                            </div>

                            <%--
                            <div class="width100" style="margin-top: 10px;float: left;">

                                <span style="margin-left: 10px;color: #444;padding-bottom: 10px;margin-top: 20px;">导入/导出</span>
                                &lt;%&ndash;<div class="demo">&ndash;%&gt;
                                &lt;%&ndash;<div class="drag-area" id="upload-area">&ndash;%&gt;
                                &lt;%&ndash;<strong>将Word文件拖拽到这里</strong>&ndash;%&gt;
                                &lt;%&ndash;<br>&ndash;%&gt;
                                &lt;%&ndash;<strong>(10个以内)</strong>&ndash;%&gt;
                                &lt;%&ndash;</div>&ndash;%&gt;
                                &lt;%&ndash;</div>&ndash;%&gt;
                                <div class="form-group_search loadFile width100" style="margin-top: 5px;height: auto;">
                                    <div class="if_tel width100">
                                       <span class="fl_l width100 " style="padding-bottom: 10px;margin-top: 10px;">
                                            <button class="sideBar_r_button" data-toggle="modal" data-target="#myModal">文件夹导入</button>
                                            <button  type="button"  class="sideBar_r_button"  onclick="location.href='${pageContext.request.contextPath}/qqFriend/download'" >数据导出</button>
                                       </span>
                                    </div>
                                </div>
                            </div>
                            --%>
                        </div>
                    </div>
                </div>
                <%--<form id="uploadFileForm" action="/word/uploadFolder" method="post"  style="display: none;">--%>
                <%--<input type="file" webkitdirectory name="file" id="file" style="display: none;">--%>
                <%--<input type="text" id="updatestate" name="updatestate" style="display: none;" value="1">--%>
                <%--</form>--%>

                <form id="seachDetail" action="<c:url value=""/>"  method="post" style="display: none;">
                </form>

            </div>
        </div>
    </ul>
</div>
<script src="<c:url value="/resources/js/bootstrap.js"/> "></script>
<script src="<c:url value="/resources/thirdparty/alertify/js/alertify.min.js"/> "></script>
<%--详情模块脚本--%>
<script src="<c:url value="/resources/thirdparty/jquery-form/jquery.form.js"/>" type="text/javascript"></script>
<script type="text/javascript">
    try{ace.settings.check('main-container','fixed')}catch(e){}
</script>

<style type="text/css">
    .crimeterrace{ background-color: #636B75 !important;}
</style>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="top: 0%; min-width: 50%;left: 25%;">
        <div class="modal-content" style="background-color: #F5F5F5;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">QQ好友聊天信息详情<span id="title"></span></h4>
                <div id="project-label" style="font-size: 14px;padding-left: 45%">聊天记录搜索：
                    <input id="project" type="text"  style="width: 260px;" class="txt ui-autocomplete-input" onfocus="getLtjlOnfocus()" autofocus="autofocus">
                    <input type="hidden" id="project-id">
                </div>
            </div>
            <div class="modal-body" >
                <input name="label" id="zhxx" hidden="hidden" value="">
                <input name="label" id="dszh" hidden="hidden" value="">
                <input name="label" id="allRow" hidden="hidden" value="">
                <div class="mobile-page pdf" id="qqContent" onscroll="scrollF()">
                    <%--<div class="admin-group">
                        <img class="admin-img" src="${pageContext.request.contextPath}/resources/image/qq.png"/>
                        <div class="admin-msg"><div class="time"><span class="time">2018-11-30 12:12:12</span></div>
                            <i class="triangle-admin"></i><span class="admin-reply">欢迎来抢楼！欢迎来抢楼！欢迎来抢楼！欢迎来抢楼！欢迎来抢楼！欢迎来抢楼！</span>
                        </div>
                    </div>
                    <div class="user-group">
                        <div class="user-msg">
                            <div class="time"><span class="time">2018-11-30 12:12:12</span></div>
                            <span class="user-reply">我要抢楼我要抢楼我要抢楼。</span>
                            <i class="triangle-user"></i>
                        </div>
                        <img class="user-img" src="${pageContext.request.contextPath}/resources/image/qq.png"/>
                    </div>--%>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="downloadPdf" onclick="cnm()">导出</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>


<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">文件上传进度</h4>
            </div>
            <div class="modal-body">
                <progress id="progressBar" value="0" max="100"
                          style="width: 100%;height: 20px; "> </progress>
                <span id="percentage" style="color:blue;"></span> <br>
                <br>
                <div class="file-box">
                    文件夹:<input type='text' name='textfield' id='textfield' class='txt'/>
                    <input type='button' class='btn' value='浏览...' />
                    <input
                            type="file" name="file" webkitdirectory class="file" id="file" size="28"
                            onchange="document.getElementById('textfield').value=this.value;" />
                    &lt;%&ndash;<br>&ndash;%&gt;
                    &lt;%&ndash;案件名:<input type="text" name = 'aj' id ='aj' class='txt' readonly="readonly" value="${aj.aj}">&ndash;%&gt;
                    <br>
                    <input type="hidden" id="checkbox1" value="1" >
                    &lt;%&ndash;<label for="checkbox1" style="padding-top: 8px">导入数据绑定用户</label>&ndash;%&gt;
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" name="submit" class="btn" value="上传"
                       onclick="UploadWord()" />
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>


<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="top: 0%; min-width: 80%;left: 10%;right: 10%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel1">文档列表<span id="title"></span></h4>
            </div>
            <div class="modal-body">
                <table class="table  table-hover table_style table_list1 " style="border-left: 1px solid #ccc; border-right: 1px solid #ccc!important;">
                    <thead style="display:table;width:100%;table-layout:fixed;width: calc( 100% - 16.5px );">
                    <tr align="center">
                        <td width="4%">序号</td>
                        <td width="15%">文档名</td>
                        <td width="5%">导入时间</td>
                    </tr>
                    <input name="label" id="hm" hidden="hidden" value="">
                    <input name="label" id="allRow" hidden="hidden" value="">
                    </thead>
                    <tbody id="result" style="display:block;height:340px;overflow-y:scroll;" onscroll="scrollH()">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="deleteWord()">删除</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>--%>

<%@include file="../template/newfooter.jsp" %>
