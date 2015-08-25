

<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/common/distributormgr.js" />" ></script>
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/simpletreemenu.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/common.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/SpryTabbedPanels.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/datepicker.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/validation.js" />" type="text/javascript"></script>
</head>

<body>
<div id="header">
  <%@include file="../common/header.jsp" %>
  <script>
  var role="<%=user.getRole()%>";
  distributormgr_header(role);
  selmenu('mn_armg');
  </script>
</div>
<!-- end of header !-->
<div id="content">
  <div id="leftNavigation">
    <script>distributormgr_armg_submenu('0','m_2_uploadExceltemplate');</script>
  </div>
  <!-- end of left navigation !-->
  <div id="contenPan">
    <div id="contenPan_LeftNavToggle"><a href="javascript:" onclick="toggle_leftmenu();"><img id="img_leftmenu" src="<c:url value="/resources/images/menuarrowopen.png" />" alt="Close Left menu" title="Close Left menu" /></a></div>

    <div id="contenPan_main">
    <form  id="target" method="post" action="/qepms/armg/uploadExcelFile" enctype="multipart/form-data">
        <h1>Upload Employee Details</h1>
        <div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
            <li class="TabbedPanelsTab" tabindex="0">Upload Employee Details</li>
          </ul>
            <!-- end of TabbedPanelsContent !-->

            <div class="TabbedPanelsContent">
              <div class="subsection uplaodpan">
                <h4>Upload an Excel File</h4>
                <div class="Ucontentpan">
                  <p class="redtxt">Note: File Type needs to be in Excel format only with size not more than 5 MB.</p>
                  <span class="brdr">
                  <input type="file" name="file" id="id_file" value=""  />
                   <input type="hidden" name="empId"  value="${user.empId}"></input>
     			   <input type="hidden" name="employeeMail"  value="${user.employeEmail}"></input>
     			   <input type="hidden" name="reportingManager"  value="${user.employeeManager}"></input>
                  </span>
                  <input type="button" id ="uploadButtonExcel" value=" " class="uploadButton"  />
                </div>
              </div>
            </div>
          </div>
          </form>
          <div><font color="red">${responseMessageWrapper.responseMessage}</font></div>
        </div>

    </div>

  </div>

  <!-- end of content Panel !-->
<!-- end of content !-->
<div id="footer">
  <script>footer_01();</script>
</div>
<!-- end of footer !-->
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</body>
</html>
