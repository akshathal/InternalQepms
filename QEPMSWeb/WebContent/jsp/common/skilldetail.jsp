<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="com.qepms.web.constants.QEPMSWebConstants"%>
<%@page import="com.qepms.web.util.RESTUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.qepms.infra.dto.armg.StringlistDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css"/>" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/common.js"/>" type="text/javascript"></script>
<script>
$(document).ready(function() 
{
	
	$('.skilltype').live("click",function(){

		$value=$(this).text();
		window.opener.$("#skillid").val($(this).text());
		window.close();

    });
	
	$('#add').live("click",function(){
	$value=$('#skill').val();
         $('#table tbody').append('<tr><td class="skilltype"><a href="#">'+$value+'</td></tr>');
         $( "tr:even" ).addClass("alt_even");
         $( "tr:odd" ).addClass("alt_odd");
		 $('#skill').val('');

    });
	
	
});
</script>
</head>

<body>
<div id="contenPanPopup">
  <h1>Skyll Type</h1>
  <div class="subsection search">
    <ul class="srcsmall src1">
      <li>
        <div class="label">Skill Type:</div>
        <div class="fleft">
          <input type="text" value="" id="skill" />
        </div>
      </li>
     
    <li>
       <input type="button" value="Add Skill" id="add"/>
      </li>
    
    </ul>
   
  </div>
  <div class="subsection">
    <div class="datagrid">
      <table id="table">
        <thead>
          <tr>
            <th width="20%">Skill Type</th>
            
          </tr>
        </thead>
        <tbody>
       
          <tr>
            <td class="skilltype"><a href="#">Languages</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Web Technologies</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Markup Languages</a></td>
          
          </tr>
           <tr>
            <td class="skilltype"><a href="#">Scripting languages</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">XML Technologies</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">RDBMS</a></td>
          
          </tr>
           <tr>
            <td class="skilltype"><a href="#">Packages</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Operating Systems</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">IDE</a></td>
          
          </tr>
           <tr>
            <td class="skilltype"><a href="#">Database Technologies</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Web Servers</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Versioning Tools</a></td>
          
          </tr>
           </tr>
           <tr>
            <td class="skilltype"><a href="#">Advanced Concepts</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Reporting Tools</a></td>
            
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Open Sources</a></td>
          
          </tr>
          <tr>
            <td class="skilltype"><a href="#">Third Party S/W</a></td>
          
          </tr>
         </tbody>
      </table>
    </div>
   
  </div>
</div>
</body>
</html>
