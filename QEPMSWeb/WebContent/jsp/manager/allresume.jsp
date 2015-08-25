<%@page import="com.qepms.web.constants.QEPMSWebConstants"%>
<%@page import="com.qepms.infra.misc.wrapper.ResponseMessageWrapper"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTO"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTOList"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="com.qepms.web.util.RESTUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/styles/jquery.dataTables.css" />" rel="stylesheet"  type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/scripts/common/distributormgr.js"/>"></script>
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/jquery.dataTables.js" />"type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/simpletreemenu.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/common.js"/>" type="text/javascript"></script>


<script type="text/JavaScript">
var asInitVals = new Array();
jQuery.fn.dataTableExt.oSort['string-case-asc'] = function (x, y) {
    return ((x < y) ? -1 : ((x > y) ? 1 : 0));
};

jQuery.fn.dataTableExt.oSort['string-case-desc'] = function (x, y) {
    return ((x < y) ? 1 : ((x > y) ? -1 : 0));
};

   
    
    
$(document).ready(function() 
{
	 $('.jqueryDataTable').dataTable({
	    	
	    	"oLanguage": {

	            'sSearch': "Search",
	            'sLengthMenu': '<strong>Display</strong> <select>'+
	            '<option value="10">10</option>'+
	            '<option value="20">20</option>'+
	            '<option value="30">30</option>'+
	            '<option value="40">40</option>'+
	            '<option value="50">50</option>'+
	            '<option value="-1">All</option>'+
	            '</select>'

	        },
	    	 'bSort':true,
	    	 'sPaginationType':'full_numbers',
	    	 'bFilter': true,
	    	 'bSortClasses':false,
	    	 'iDisplayLength':10
	    	 
	    	 
	    
	    });
	
	
	$('.employee').live("click",function(){

		$first=$(this).parent().children().first();
		$second=$first.next();
		$third=$second.next();
		window.opener.$("#empId").val($second.text());
		window.opener.$("#name").val($first.text());
		window.close();

		
	
    });

//After rejecting

	$('.btn_reject').live("click",function(){

		 $reject=true;
		 $comments = $(this).parent().next().children('.txt_comments').val();
		 if($comments==null || $comments=="")
			 {
			   //alert("Please Enter Comments");
			   $reject=false;
			 }
		
			// $resumeid = $(this).parent().parent().children().first().text();
			if($reject)
			{
				$resumeid = $(this).parent().children('.resumeid').val();
     			var url = '<%=RESTUtil.efmsWSBaseUrl + QEPMSWebConstants.Manager.MANAGER_REJECT_RESUME_WS_PAGE_PATH%>?resumeid='+$resumeid+'&comments='+ $comments;
        		$.get(url, processResponse);
				
			}
			function processResponse(data) {
				//alert(data.responseMessage);
				location.reload();
		
			 	}
		
        

		
	
    });


// After accepting

	$('.btn_accept').live("click",function(){

		//$empId = $(this).parent().parent().children().first().text();
		 $accept=true;
		 $resumeid = $(this).parent().children('.resumeid').val();
		 $comments = $(this).parent().next().children('.txt_comments').val();
		 if($comments==null || $comments=="")
		 {
		   //alert("Please Enter Comments");
		   $accept=false;
		 }
		 
		 if($accept)
		 {
				var url = '<%=RESTUtil.efmsWSBaseUrl + QEPMSWebConstants.Manager.MANAGER_ACCEPT_RESUME_WS_PAGE_PATH%>?resumeid='+ $resumeid+'&comments='+ $comments;
				$.get(url, processResponse);
		 }
		
		function processResponse(data) {
			//alert(data.responseMessage);
			location.reload();
		}

		
	
    });



	
	
});

	
</script>
</head>

<body>
<div id="header">
    <%@include file="../common/header.jsp" %>
<%
 	ApplicationContext context = RequestContextUtils.getWebApplicationContext(request); 
 	RESTUtil restUtil = (RESTUtil)context.getBean("restUtil");
 	/* String reportingManager = user.getEmployeeName();
 	
 	// Load the Member grid (first and every page reload)
 	List<ResumeDTO> resumelist = (List<ResumeDTO>)restUtil.getData(QEPMSWebConstants.Manager.EMPLOYEE_MASTER_WS_PAGE_PATH+"?managerApprovalStatus=PENDING&reportingManager="+reportingManager, ResumeDTOList.class); */

 	
 	//Added By Chethana
 	String reportingManagerEmail = user.getEmployeEmail();
 	System.out.println("reporting manager"+reportingManagerEmail);
 	
 	// Load the Member grid (first and every page reload)
 	List<ResumeDTO> resumelist = (List<ResumeDTO>)restUtil.getData(QEPMSWebConstants.Manager.EMPLOYEE_BASED_ON_MANAGER_EMAIL_WS_PAGE_PATH+"?managerApprovalStatus=PENDING&reportingManageremail="+reportingManagerEmail, ResumeDTOList.class); 
 	pageContext.setAttribute("resumelist", resumelist);
 	//[End] Added by Chethana
%> 

	<script>
		var role="<%=user.getRole()%>";
	  	distributormgr_header(role);
		selmenu('mn_manager');
	</script>	
</div>
<!-- end of header !-->
<div id="content">
	<div id="leftNavigation">
     <script>distributormgr_manager_submenu('0','m_allresume');</script>
    </div>
    <!-- end of left navigation !-->
    <div id="contenPan"><div id="contenPan_LeftNavToggle"><a href="javascript:" onclick="toggle_leftmenu();"><img id="img_leftmenu" src="<c:url value="/resources/images/menuarrowopen.png"/>" alt="Close Left menu" title="Close Left menu" /></a></div>
    <div id="contenPan_main">
    	<h1>Approval Pending Resume(s) 
        </h1>
        <div class="subsection">
    	 <div class="datagrid">
    <table class="jqueryDataTable">
      <thead>
        <tr>
        	<th>EmpID</th>
            <th>Employee Name</th>
            <th>Action</th>
            <th>Comments</th>
            <th>Submitted Date</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${resumelist}" var="resume">
        <tr>
            <td class="employee"><a href="/qepms/manager/managerviewofresume?resumeid=${resume.resumeid}&status=PENDING"><c:out value="${resume.employeeMaster.empId}" /></a></td>
            <td class="employee"><c:out value="${resume.employeeMaster.name}" /></td>
           <td width='20%'>
				<input type="button" value="Approve" class="btn_accept"/>
				<input type="button" value="Reject" class="btn_reject" />
				<input type="hidden" class="resumeid" value="${resume.resumeid}"/>
			 </td>
			 <td>
			   <textarea rows="4" cols="40" class="txt_comments"></textarea>
			  </td>
			  <td>
			  ${resume.customUpdatedDate}
			  </td>
          </tr>
        </c:forEach>
             
              
           
        </tbody>
     </table>	
     
    </div>
    <div id='tablePagination'>
	
  	
  </div>
  </div>
    </div>
    <!-- end of content Panel !-->
</div>
</div>
<!-- end of content !-->
<div id="footer">
	<script>footer_01();</script>
</div>
<!-- end of footer !-->

<!--  datatable scripts -->

<script src="<c:url value="/resources/scripts/common/jquery.dataTables.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="http://code.jquery.com/ui/1.9.2/jquery-ui.js"/>" type="text/javascript"></script>


</body>
</html>
