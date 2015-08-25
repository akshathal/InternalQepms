<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.qepms.infra.dto.login.UserDTO"%>
<%@page import="com.qepms.web.constants.QEPMSWebConstants"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="com.qepms.web.util.RESTUtil"%>
<%
	UserDTO userDTO=(UserDTO)request.getAttribute("userDTO");
	ApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
	RESTUtil restUtil = (RESTUtil)context.getBean("restUtil");
	UserDTO userDetails=(UserDTO) restUtil.getData(QEPMSWebConstants.Login.LOGIN_WS_PAGE_PATH+"?userName="+userDTO.getUserName()+"&password="+userDTO.getPassword(), UserDTO.class);
	session.setAttribute("user", userDetails);
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/styles/home.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/common/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/common/distributormgr.js"/>"></script>
</head>

<body>
<div id="header"> 
  <%@include file="../common/header.jsp" %>
  <script>
  var role="<%=user.getRole()%>";
  distributormgr_header(role);
  </script> 
</div>
<!-- end of header !-->
<div id="content">
  <div id="contenPanFull">
    <div class="advertisement">
      <marquee behavior="scroll" direction="left" scrollamount="6" id="mymarquee">
       QUINNOX EMPLOYEE PROFILE MANAGEMENT SYSTEM
      </marquee>
      <div class="marqueebtn"> <a href="javascript:" onclick="document.getElementById('mymarquee').setAttribute('scrollamount', 6, 0);"></a></div>
    </div>
    
  </div>
  <!-- end of content Panel !--> 
</div>
<div id="footer"> 
  <script>footer_01();</script> 
</div>
<!-- end of footer !-->

</body>
</html>
