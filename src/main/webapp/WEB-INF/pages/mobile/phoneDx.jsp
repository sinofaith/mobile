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
<script src="<c:url value="/resources/js/aj.js"/> "></script>
<script src="<c:url value="/resources/js/qq/qq.js"/> "></script>

<div class="tab_div">
    <%@include file="../phone/title.jsp" %>
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
                                                <strong>短信信息</strong>

                                            </div>
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <td width="3%">序号</td>
                                        <td width="5%">用户姓名</td>
                                        <td width="10%">用户号码</td>
                                        <td width="10%"><a href="${pageContext.request.contextPath}/phoneDx/seach?pageNo=1&orderby=sendTime">发送时间</a></td>
                                        <td width="8%">对方姓名</td>
                                        <td width="12%">对方号码</td>
                                        <td width="5%">发送类型</td>
                                        <td width="25%">短信内容</td>
                                    </tr>
                                    <%--<form action="" method="post" id="_form">--%>
                                    <%--</form>--%>
                                    <c:forEach items="${detailinfo}" var="item" varStatus="st">
                                        <tr class="${st.index%2==1 ? '':'odd' }">
                                            <td align="center" >${item.id}</td>
                                            <td align="center">${item.uName}</td>
                                            <td align="center">${item.uNumber}</td>
                                            <td align="center">${item.sendTime}</td>
                                            <td align="center">${item.pName}</td>
                                            <td align="center">${item.pNumber}</td>
                                            <td align="center">${item.sendType eq "被叫" ? "接收":"发送"}</td>
                                            <td align="center" title="${item.content}"><div style="width:300px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;">${item.content}</div></td>
                                            <%--<td align="center" title="${item.mac}"><div style="width:140px;white-space: nowrap;text-overflow:ellipsis; overflow:hidden;">${item.mac}</div></td>--%>
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
                                        <a href="${pageContext.request.contextPath}/phoneDx/seach?pageNo=${page.topPageNo }"><input type="button" name="fristPage" value="首页" /></a>
                                        <c:choose>
                                            <c:when test="${page.pageNo!=1}">
                                                <a href="${pageContext.request.contextPath}/phoneDx/seach?pageNo=${page.previousPageNo }"><input type="button" name="previousPage" value="上一页" /></a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" name="previousPage" value="上一页" />
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${page.pageNo != page.totalPages}">
                                                <a href="${pageContext.request.contextPath}/phoneDx/seach?pageNo=${page.nextPageNo }"><input type="button" name="nextPage" value="下一页" /></a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" name="nextPage" value="下一页" />
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="${pageContext.request.contextPath}/phoneDx/seach?pageNo=${page.bottomPageNo }"><input type="button" name="lastPage" value="尾页" /></a>
                                        <input type="number" id="num" max="${page.totalPages}" style="width: 9%" min="1">
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
                                <form action="${pageContext.request.contextPath}/phoneDx/seachCode" method="post">
                                    <div class="form-group_search  fl_l width100" >
                                        <span style="margin-left: 10px;color: #444;padding-bottom: 10px;">查询方式</span>
                                        <select name="seachCondition" class="width100" STYLE="margin-bottom: 20px;">
                                            <option value="uName" <c:if test="${phoneDxSeachCondition=='uName'}">selected="selected"</c:if>>用户姓名</option>
                                            <option value="uNumber"<c:if test="${phoneDxSeachCondition=='uNumber'}">selected="selected"</c:if>>用户号码</option>
                                            <option value="pName"<c:if test="${phoneDxSeachCondition=='pName'}">selected="selected"</c:if>>对方姓名</option>
                                            <option value="pNumber" <c:if test="${phoneDxSeachCondition=='pNumber'}">selected="selected"</c:if>>对方号码</option>
                                            <option value="content" <c:if test="${phoneDxSeachCondition=='content'}">selected="selected"</c:if>>短信内容</option>
                                        </select>
                                        <%--<input  style="margin-left: 10px;" type="checkbox" name="usable" value="1" <c:if test="${usable eq '1'}">checked="checked"</c:if>>上次条件有效--%>
                                        <textarea  class="form-control02 seachCode fl_l width100" id="seachCode" placeholder="请输入要查询内容" name="seachCode" >${phoneDxSeachCode}</textarea>
                                    </div>

                                    <button type="submit" class="right_a_nav margin_none" >查询</button>
                                    <%--<button type="button" class="right_a_nav margin_none add_button" onclick="AddCrimeterrace()">新增人员信息</button>--%>
                                </form>
                            </div>

                            <%--<div class="width100" style="margin-top: 10px;float: left;">--%>

                                <%--<span style="margin-left: 10px;color: #444;padding-bottom: 10px;margin-top: 20px;">导入/导出</span>--%>
                                <%--&lt;%&ndash;<div class="demo">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="drag-area" id="upload-area">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<strong>将Word文件拖拽到这里</strong>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<br>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<strong>(10个以内)</strong>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--<div class="form-group_search loadFile width100" style="margin-top: 5px;height: auto;">--%>
                                    <%--<div class="if_tel width100">--%>
                       <%--<span class="fl_l width100 " style="padding-bottom: 10px;margin-top: 10px;">--%>

                <%--<button class="sideBar_r_button" data-toggle="modal"--%>
                        <%--data-target="#myModal">文件夹导入</button>--%>
                           <%--<button  type="button"  class="sideBar_r_button"  onclick="location.href='${pageContext.request.contextPath}/phone/download'" >数据导出</button>--%>
                       <%--</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
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
                    <%--<br>--%>
                    <%--案件名:<input type="text" name = 'aj' id ='aj' class='txt' readonly="readonly" value="${aj.aj}">--%>
                    <br>
                    <input type="hidden" id="checkbox1" value="1" >
                    <%--<label for="checkbox1" style="padding-top: 8px">导入数据绑定用户</label>--%>
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
</div>

<%@include file="../template/newfooter.jsp" %>
