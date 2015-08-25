
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/common/distributormgr.js"/>"></script>
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/simpletreemenu.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/common.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/SpryTabbedPanels.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/datepicker.js" />" type="text/javascript"></script>
<script type="text/JavaScript">
$(document).ready(function() 
{
	$("#paymentsection,#paymentbydebitcreditcard,#paymentbyfundtransfer,#paymentbydd,#paymentbycheque,#paymentbycash").hide();
});
</script>
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
    <script>distributormgr_armg_submenu('0','m_importresume');</script> 
  </div>
  <!-- end of left navigation !-->
  <div id="contenPan">
    <div id="contenPan_LeftNavToggle"><a href="javascript:" onclick="toggle_leftmenu();"><img id="img_leftmenu" src="<c:url value="/resources/images/menuarrowopen.png" />" alt="Close Left menu" title="Close Left menu" /></a></div>

    <div id="contenPan_main">
      <form>
        <h1>Import Resume
        </h1>
        <div id="TabbedPanels1" class="TabbedPanels">
          <ul class="TabbedPanelsTabGroup">
           
            <li class="TabbedPanelsTab" tabindex="0">Upload Resume</li>
           
          </ul>
         
            <!-- end of TabbedPanelsContent !-->
            
            <div class="TabbedPanelsContent">
              <div class="subsection uplaodpan">
                <h4>Upload a File</h4>
                <div class="Ucontentpan">
                  <p class="redtxt">Note: File Type needs to be WORD format only with size not more than 5 MB.</p>
                  <span class="brdr">
                  <input type="file" value=""  />
                  </span>
                  <input type="button" value=" " class="uploadButton"  />
                </div>
              </div>
             
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
  <!-- end of content Panel !--> 
</div>
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
