
<%@page import="com.qepms.web.constants.QEPMSWebConstants"%>
<%@page import="com.qepms.infra.misc.wrapper.ResponseMessageWrapper"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="com.qepms.web.util.RESTUtil"%>
<%@page import="com.qepms.infra.dto.armg.EmployeeMasterDTO"%>
<%@page import="com.qepms.infra.dto.armg.EmployeeMasterDTOList"%>
<%@page import="com.qepms.infra.dto.armg.SkillDTO"%>
<%@page import="com.qepms.infra.dto.armg.SkillDTOList"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTO"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTOList"%>
<%@page import="com.qepms.infra.dto.armg.EducationDTO"%>
<%@page import="com.qepms.infra.dto.armg.EducationDTOList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/common/distributormgr.js"/>"></script>
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/simpletreemenu.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/common.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/datepicker.js"/>" type="text/javascript"></script>



 
 <script type="text/JavaScript">
 var asInitVals = new Array();
 jQuery.fn.dataTableExt.oSort['string-case-asc'] = function (x, y) {
     return ((x < y) ? -1 : ((x > y) ? 1 : 0));
 };

 jQuery.fn.dataTableExt.oSort['string-case-desc'] = function (x, y) {
     return ((x < y) ? 1 : ((x > y) ? -1 : 0));
 };


 $(document).ready(function () {
 	
 	 // Setup - add a text input to each footer cell
     $('.jqueryDataTable tfoot th').each( function () {
         var title = $('.jqueryDataTable thead th').eq( $(this).index() ).text();
         $(this).html( '<div style="float: left;text-align: left;"><input type="text" placeholder="Search '+title+'" />' );
     } );
  
     // DataTable
     var table = $('.jqueryDataTable').DataTable({
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
  
     // Apply the filter
     table.columns().eq( 0 ).each( function ( colIdx ) {
         $( 'input', table.column( colIdx ).footer() ).on( 'keyup change', function () {
             table
                 .column( colIdx )
                 .search( this.value )
                 .draw();
         } );
     } );
 	
 	
     
 });
 
$(document).ready(function() 
{
	$('.res').live("click",function(){

		$first=$(this).parent().children().first();
		$second=$first.next();
		$third=$second.next();
		window.opener.$("#empid").val($second.text());
		window.opener.$("#name").val($first.text());
		window.opener.$("#submiteddate").val($third.text());
		window.close();

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
 	Integer empId=user.getEmpId();
 	List<ResumeDTO> resume = (List<ResumeDTO>) restUtil.getData(QEPMSWebConstants.Employee.RESUME_MSTR_WS_PATH+"?empId="+empId+"&employeeSumbissionStatus=SUBMITTED&managerApprovalStatus=NA",ResumeDTOList.class);
 	pageContext.setAttribute("resume", resume);  
  %> 
  <script>
  var role="<%=user.getRole()%>";
  distributormgr_header(role);
  selmenu('mn_employee');
  </script> 
</div>
<!-- end of header !-->
<div id="content">
  <div id="leftNavigation"> 
    <script>distributormgr_employee_submenu('1','m_1_submitted');</script> 
  </div>
  <!-- end of left navigation !-->
  <div id="contenPan">
    <div id="contenPan_LeftNavToggle"><a href="javascript:" onclick="toggle_leftmenu();"><img id="img_leftmenu" src="<c:url value="/resources/images/menuarrowopen.png"/>" alt="Close Left menu" title="Close Left menu" /></a></div>
    <div id="contenPan_main">
      <h1>Search Submitted Resume
      </h1>
      <div class="subsection">
      <div class="datagrid">
      
       
      
      <table class="jqueryDataTable">
        <thead>
          <tr>
            <th>Emp Id</th>
            <th>Employee Name</th>
             <th>Submitted Date</th>
             <th>Approval Status</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${resume}" var="resume">
        <tr>
              <td class="res"><a href="/qepms/employee/viewresume?resumeid=${resume.resumeid}&status=${resume.employeeSumbissionStatus}"><c:out value="${resume.employeeMaster.empId}"/></a></td>   
              <td class="res"><c:out value="${resume.employeeMaster.name}"/></td>   
              
          	<td class="res"><c:out value="${resume.customUpdatedDate}"/></td>
           <td class="res"><c:out value="${resume.managerApprovalStatus}"/></td>
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

</body>
</html>
