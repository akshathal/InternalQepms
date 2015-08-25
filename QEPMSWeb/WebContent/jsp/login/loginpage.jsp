<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.qepms.infra.misc.wrapper.ResponseMessageWrapper"%>
<%@page import="com.qepms.infra.constants.CommonConstants"%>
<%
String error=CommonConstants.LOGIN_FAILURE_ERROR;
String customMessage=CommonConstants.LOGIN_CUSTOM_MESSAGE;
pageContext.setAttribute("error", error);
pageContext.setAttribute("customMessage", customMessage);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Management System</title>
<link rel="stylesheet" href="<c:url value="/resources/styles/login.css"/>" type="text/css" />
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>"></script>
<script type="text/JavaScript">
$(document).ready(function() 
{
      if($('.loginerrormsg').text()=="" || $('.loginerrormsg').text()==null)
    	  {
    	  $('.loginerrormsg').hide();
    	  }
      else
    	  {
    	  $('.loginerrormsg').show();
    	  }
	  $( "#btn_submit" ).click(function() {
		  
		  $( "#myform" ).submit();
		 
	  });
});

	

</script>
</head>

<body>
<br><br>
<div id="container"><img src="<c:url value="/resources/images/quinnox_logo.png"/>" alt="Quinnox Logo" title="Quinnox Logo" />
  <div id="content">
  
    <span class="loginerrormsg">${responseMessageWrapper.responseMessage}</span>
    <div id="loginsection">
     
      <h4>Please enter your User Name and Password.</h4>
      <div id="loginform">
     
        <form:form id="myform" action="/qepms/login/authenticate" modelAttribute="userDTO" commandName="userDTO">
        
     		 
          <ul>
          
            <li>
             
              <div class="left">User Name:</div>
              <div class="right">
                <input type="text" value="" name="userName" id="txtusername" />
              </div>
              <div class="clear"></div>
            </li>
            <li>
              <div class="left">Password: </div>
              <div class="right">
                <input type="password" value="" name="password" id="txtpassword"  />
              </div>
              <div class="clear"></div>
            </li>
            <li class="frmbtn">
              <div class="left">&nbsp;</div>
              <input type="button" value="Login" name="btn_submit" id="btn_submit"  >
            </li>
          </ul>
        </form:form>
      </div>
    </div>
 </div>   
</div>
</body>
</html>