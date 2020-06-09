<%--
  Created by IntelliJ IDEA.
  User: 47435
  Date: 2018/9/17
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<script src="<c:url value="/resources/thirdparty/alertify/js/alertify.min.js"/> "></script>
<script src="<c:url value="/resources/thirdparty/jquery-form/jquery.form.js"/>" type="text/javascript"></script>
<link href="<c:url value="/resources/thirdparty/gojs/css/jquery-ui.min.css"/> " rel="stylesheet">
<script src="<c:url value="/resources/thirdparty/gojs/js/jquery/jquery-ui.min.js"/> "></script>
<script src="<c:url value="/resources/js/bootstrap.js"/> "></script>
<script src="<c:url value="/resources/js/case/caseRegion.js"/>" type="text/javascript"></script>
<link href="<c:url value="/resources/css/bootstrap-select.css"/>" rel="stylesheet" media="screen">
<script src="<c:url value="/resources/js/bootstrap-select.js"/> "></script>

<%--详情模块脚本--%>
<script type="text/javascript">
    try{ace.settings.check('main-container','fixed')}catch(e){}
</script>
<style type="text/css">
    .crimeterrace{ background-color: #636B75 !important;}
</style>

<div class="tab_div">
            <span class="tab_nav"><a href="/mobile/caseBrand" >品牌列表</a>
                <a href="/mobile/case" >案件列表</a>
             <a href="/mobile/caseRegion" class="addactive">人员列表</a>
                </span>
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
                                        <td colspan="10"  align="center" class="dropdown_index" style="background-color: #eee;">
                                            <div class="dropdown " style="color: #333">
                                                <strong>人员列表(${brand.brandName}-${brand.unitName}-${caseName}-${regionName})</strong>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <td width="6%">序号</td>
                                        <td width="10%">姓名</td>
                                        <td width="13%">身份证号码</td>
                                        <td width="10%">角色</td>
                                        <td width="7%">创建时间</td>
                                        <td width="6%">操作</td>
                                    </tr>
                                    <form action="" method="post" id="_form">

                                    </form>
                                    <c:forEach items="${detailinfo}" var="item" varStatus="st">
                                        <tr class="${st.index%2==1 ? '':'odd' }">
                                            <td align="center">${(st.index+1)+(page.pageNo-1)*page.pageSize}</td>
                                            <td align="center"><a href="${pageContext.request.contextPath}/phone?aj_id=${item.role_id}">${item.role_name}</a></td>
                                            <td align="center">${fn:replace(item.sfzhm,fn:substring(item.sfzhm,6,14),"********")}</td>
                                            <td align="center">${item.role}</td>
                                            <td align="center">${item.insertTime}</td>
                                            <td align="center"><button data-toggle="modal"
                                                                       data-target="#editModal" onclick="getPerson('${item.role_id}')">编辑</button></td>
                                        </tr>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${detailinfo ==null || detailinfo.size()==0}">
                                            <tr>
                                                <td colspan="10" align="center"> 无数据 </td>
                                            </tr>
                                        </c:when>
                                    </c:choose>

                                </table>

                            </div>
                            <c:choose>
                                <c:when test="${detailinfo!=null && detailinfo.size()!=0}">
                                    <div  class="page_nmber">
                                        <div class="mar_t_15">共${page.totalRecords}条记录 共<span id="totalPage">${page.totalPages}</span>页 当前第${page.pageNo}页<br></div>
                                        <a href="/mobile/caseRegion/seach?pageNo=${page.topPageNo }"><input type="button" name="fristPage" value="首页" /></a>
                                        <c:choose>
                                            <c:when test="${page.pageNo!=1}">
                                                <a href="/mobile/caseRegion/seach?pageNo=${page.previousPageNo }"><input type="button" name="previousPage" value="上一页" /></a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" name="previousPage" value="上一页" />
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${page.pageNo != page.totalPages}">
                                                <a href="/mobile/caseRegion/seach?pageNo=${page.nextPageNo }"><input type="button" name="nextPage" value="下一页" /></a>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" name="nextPage" value="下一页" />
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="/mobile/caseRegion/seach?pageNo=${page.bottomPageNo }"><input type="button" name="lastPage" value="尾页" /></a>
                                        <input type="number" id="num" max="${page.totalPages}" style="width: 9%" min="1">
                                        <input type="button" value="跳转" onclick="caseSkip('caseRegion')">
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
                                <form action="/mobile/caseRegion/SeachCode" method="post">
                                    <div class="form-group_search  fl_l width100" >
                                        <span style="margin-left: 10px;color: #444;padding-bottom: 10px;">查询方式</span>
                                        <select name="seachCondition" class="width100" STYLE="margin-bottom: 20px;">
                                            <option value="role_name"<c:if test="${rbSeachCondition=='role_name'}">selected="selected"</c:if>>姓名</option>
                                            <option value="role"<c:if test="${rbSeachCondition=='role'}">selected="selected"</c:if>>角色</option>
                                        </select>
                                        <%--<input  style="margin-left: 10px;" type="checkbox" name="usable" value="1" <c:if test="${usable eq '1'}">checked="checked"</c:if>>上次条件有效--%>
                                        <textarea  class="form-control02 seachCode fl_l width100" id="seachCode" placeholder="请输入要查询内容" name="seachCode" >${rbSeachCode}</textarea>
                                    </div>

                                    <button type="submit" class="right_a_nav margin_none" >查询</button>
                                    <%--<button type="button" class="right_a_nav margin_none add_button" onclick="AddCrimeterrace()">新增人员信息</button>--%>
                                </form>
                            </div>

                            <div class="width100" style="margin-top: 10px;float: left;">

                                <span style="margin-left: 10px;color: #444;padding-bottom: 10px;margin-top: 20px;">人员操作</span>
                                <div class="form-group_search loadFile width100" style="margin-top: 5px;height: auto;">
                                    <div class="if_tel width100">
                       <span class="fl_l width100 " style="padding-bottom: 10px;margin-top: 10px;">
                               <button class="sideBar_r_button" data-toggle="modal"
                                       data-target="#myModal">新增人员</button>
                               <button class="sideBar_r_button" data-toggle="modal"
                                       data-target="#filemyModal">取证报告数据导入</button>
                               <button class="sideBar_r_button" data-toggle="modal"
                                       data-target="#myModalUp">微信Excel聊天记录导入</button>

                       </span>
                                    </div>
                                </div>
                            </div>
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

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <span id="percentage" style="color:blue;"></span> <br>
                <div class="file-box">
                    <input type="hidden" id="unitId" value="${brand.brandId}">
                    品  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌:<input type="text" name = 'brandname' id ='brandname' value="${brand.brandName}"
                           readonly="readonly"  class='txt brandname'  data-toggle="tooltip" data-placement="top">
                    <br>
                    立案单位:<input type="text" name = 'unitname' id ='unitname' value="${brand.unitName}"
                                readonly="readonly"    class='txt unitname'  data-toggle="tooltip" data-placement="top">
                    <br>
                    案  &nbsp;件  &nbsp;名:<input type="text" name = 'caseName' id ='caseName' value="${caseName}"
                                readonly="readonly"    class='txt caseName'  data-toggle="tooltip" data-placement="top">
                    <br>
                    区  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:<input type="text" name = 'regionName' id ='regionName' value="${regionName}"
                                readonly="readonly"    class='txt regionName'  data-toggle="tooltip" data-placement="top">
                    <br>
                    姓  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:<input type="text" name = 'role_name' id ='role_name'
                                 class='txt role_name'  data-toggle="tooltip" data-placement="top"
                                     oninput="destroyTooltip('role_name');getRole_nameName()" onfocus="getRole_nameOnfocus()" />
                    <br>
                    身  &nbsp;份  &nbsp;证:<input type="text" name = 'sfzhm' id ='sfzhm'
                                 class='txt sfzhm' oninput="destroyTooltip('sfzhm')" onblur="getSFZHM()" data-toggle="tooltip" data-placement="top">
                    <br>
                    角  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:<input type="text" name = 'role' id ='role'
                                class='txt role' data-toggle="tooltip" data-placement="top"
                                  oninput="destroyTooltip('role');getRoleName()" onfocus="getRoleOnfocus()" />


                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" name="submit" class="btn" value="确定"
                       onclick="addRole()"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>


<div class="modal fade" id="filemyModal" tabindex="-1" role="dialog"
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
                <span id="percentagea" style="color:blue;"></span> <br>
                <br>
                <div class="file-box">
                    文  &nbsp;件  &nbsp;夹:<input type='text' name='textfield' id='textfield' class='txt'/>
                    <input type='button' class='btn' value='浏览...' />
                    <input
                            type="file" name="file" webkitdirectory class="file" id="file" size="28"
                            onchange="document.getElementById('textfield').value=this.value;" onclick="destroyTooltip('file')"/>
                    <br>
                    机  &nbsp;主  &nbsp;名:<input type="text" name = 'jzm' id ='jzm'
                                                   class='txt jzm'  data-toggle="tooltip" data-placement="top" oninput="destroyTooltip('jzm')">
                    <br>
                    数据来源:<input type = "text" name = 'sjy' id="sjy" class="txt sjy" data-toggle="tooltip" data-placement="top" oninput="destroyTooltip('sjy')">
                    <br>
                    角  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:<input type="text" name = 'froleName' id ='froleName'
                                                   class='txt froleName'  data-toggle="tooltip" data-placement="top" oninput="destroyTooltip('froleName')">
                    <br>
                    品  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌:<input type="text" name = 'brandname' id ='fbrandname' value="${brand.brandName}"
                                                                    readonly="readonly"  class='txt brandname'  data-toggle="tooltip" data-placement="top">
                    <br>
                    立案单位:<input type="text" name = 'unitname' id ='funitname' value="${brand.unitName}"
                                readonly="readonly"    class='txt unitname'  data-toggle="tooltip" data-placement="top">
                    <br>
                    案  &nbsp;件  &nbsp;名:<input type="text" name = 'caseName' id ='fcaseName' value="${caseName}"
                                               readonly="readonly"    class='txt caseName'  data-toggle="tooltip" data-placement="top">
                    <br>
                    区  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:<input type="text" name = 'regionName' id ='fregionName' value="${regionName}"
                                                                    readonly="readonly"    class='txt regionName'  data-toggle="tooltip" data-placement="top">
                    <input type="hidden" name = 'regionName' id ='regionId' value="${regionId}"
                              class='txt regionName'  data-toggle="tooltip" data-placement="top">
                    <br>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" name="submit" class="btn" value="上传"
                       onclick="UploadQZ()" />
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<div class="modal fade" id="myModalUp" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel1">文件上传进度</h4>
            </div>
            <div class="modal-body">
                <progress id="progressBar1" value="0" max="100"
                          style="width: 100%;height: 20px; "> </progress>
                <span id="percentage1" style="color:blue;"></span> <br>
                <br>
                <div class="file-box">
                    文&nbsp;&nbsp;件&nbsp;&nbsp;夹:<input type='text' name='textfield1' id='textfield1' class='txt'/>
                    <input type='button' class='btn' value='浏览...' />
                    <input
                            type="file" name="file1" webkitdirectory class="file" id="file1" size="28" data-toggle="tooltip" data-placement="top"
                            onchange="document.getElementById('textfield1').value=this.value;destroyTooltip('file')"/>
                    <br>
                    机  &nbsp;主  &nbsp;名:<select id="jzm1" name="jzm1" class="selectpicker" title="请选择人员" onchange="destroyTooltip('selectpicker')"></select>
                    <br>
                    <input type="hidden" id="unitId1" value="${brand.brandId}">
                    品  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌:<input type="text" name = 'brandname1' id ='brandname1' value="${brand.brandName}"
                                                                    readonly="readonly"  class='txt brandname'  data-toggle="tooltip" data-placement="top">
                    <br>
                    立案单位:<input type="text" name = 'unitname1' id ='unitname1' value="${brand.unitName}"
                                readonly="readonly"    class='txt unitname'  data-toggle="tooltip" data-placement="top">
                    <br>
                    案  &nbsp;件  &nbsp;名:<input type="text" name = 'caseName1' id ='caseName1' value="${caseName}"
                                               readonly="readonly"    class='txt caseName'  data-toggle="tooltip" data-placement="top">
                    <br>
                    区  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域:<input type="text" name = 'regionName1' id ='regionName1' value="${regionName}"
                                                                    readonly="readonly"    class='txt regionName'  data-toggle="tooltip" data-placement="top">
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" name="submit" class="btn" value="上传"
                       onclick="UploadExcel()" />
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>



<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <span id="percentageedit" style="color:blue;"></span> <br>
                <div class="file-box">
                    <input type="hidden" id="editrole_id">
                    <input type="hidden" id="inserttime">
                    <input type="hidden" id="region_id">
                    姓  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:<input type="text" name = 'editname' id ='editname'
                                                                    class='txt editname'  data-toggle="tooltip" data-placement="top"
                                                                    oninput="destroyTooltip('editname');" />
                    <br>
                    身  &nbsp;份  &nbsp;证:<input type="text" name = 'editsfzhm' id ='editsfzhm'
                                               class='txt editsfzhm' oninput="destroyTooltip('editsfzhm')"  data-toggle="tooltip" data-placement="top">
                    <br>
                    角  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:<input type="text" name = 'editrole' id ='editrole'
                                                                    class='txt editrole' data-toggle="tooltip" data-placement="top"
                                                                    oninput="destroyTooltip('editrole');" />


                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" name="submit" class="btn" value="确定"
                       onclick="editRole()"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<%@include file="../template/newfooter.jsp" %>

