<%--
  Created by IntelliJ IDEA.
  User: 95645
  Date: 2016/11/30
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="<c:url value="/resources/js/jquery-1.9.1.min.js"/> "></script>
<script src="<c:url value="/resources/jquery/jquery.js"/> "></script>
<link href="<c:url value="/resources/thirdparty/gojs/css/jquery-ui.min.css"/> " rel="stylesheet">
<script src="<c:url value="/resources/thirdparty/gojs/js/jquery/jquery-ui.min.js"/> "></script>
<html>
<%--新加样式--%>
<head>
    <title>手机取证报告分析系统</title>
</head>
<body>
<aside class="sideMenu_left" style="top: 0px;">
    <a class="logo" ><img src="<c:url value="/resources/img/logo02.png"/>"style="padding:20px 0 20px 10px; width: 63px ;float: left"/> </a>
 <span>
     <a href="${pageContext.request.contextPath}/case" class="sidebar_left_addative"><i class=" icon-untitled26 "></i>品牌案件</a>
     <a href="${pageContext.request.contextPath}/user"><i class="icon-untitled54" id="userInfo"></i>用户管理</a>
     <a href="${pageContext.request.contextPath}/phone"><i class="icon-untitled47"></i>手机分类</a>
    <%--<a href="/SINOFAITH/bank"><i class="icon-untitled47"></i>银行卡</a>--%>
    <%--<a href="/AMD/criteriaquery"><i class="icon-search"></i>资金</a>--%>
 </span>
</aside>
</body>
</html>
