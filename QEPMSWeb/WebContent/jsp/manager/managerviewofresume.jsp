<%@page import="com.qepms.infra.dto.armg.SkillDTO"%>
<%@page import="com.qepms.infra.dto.armg.ProjectDTO"%>
<%@page import="com.qepms.infra.dto.armg.EducationDTO"%>
<%@page import="com.qepms.web.constants.QEPMSWebConstants"%>
<%@page import="com.qepms.infra.misc.wrapper.ResponseMessageWrapper"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTO" %>
<%@page import="com.qepms.infra.dto.armg.ResumeDTOList" %>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="com.qepms.web.util.RESTUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<%
    
    ApplicationContext context = RequestContextUtils.getWebApplicationContext(request); 
 	RESTUtil restUtil = (RESTUtil)context.getBean("restUtil");
 	String resumeid = request.getParameter("resumeid");
 	String status = request.getParameter("status");
 	System.out.println("Status in managerviewofresume "+status);
 	//ResumeDTO resume=(ResumeDTO)restUtil.getData(QEPMSWebConstants.Manager.MANAGER_VIEW_RESUME_WS_PAGE_PATH+"?empId="+empId, ResumeDTO.class);
 	ResumeDTO resume = (ResumeDTO) restUtil.getData(QEPMSWebConstants.Employee.EMPLOYEEVIEWRESUME_WS_PATH+"?resumeid="+resumeid,ResumeDTO.class); 
 	pageContext.setAttribute("resume", resume);
 	pageContext.setAttribute("status", status);
 	
    //String curentDate =(String)restUtil.getData(EFMSWebConstants.Issues.CURRENT_DATE, String.class);  
 	//pageContext.setAttribute("curentDate", curentDate);
 	
 	// To categorise the skills we are putting it into a List
 	
 	List<SkillDTO> skillList = resume.getSkills();
 	pageContext.setAttribute("skillList", skillList);
 	
 	// To print the project details we are putting it into List, its easy to ietrate and display
 	
 	List<ProjectDTO> projectList = resume.getProjects();
 	pageContext.setAttribute("projectList", projectList);
 	
 
 	List<EducationDTO> educationList =  resume.getEducations();
 	pageContext.setAttribute("educationList", educationList);
 	
 	

 	
 	
 	
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<title>Quinnox Employee Profile Management System</title>
<link href="<c:url value="/resources/styles/styles.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/common/distributormgr.js"/>"/>

<script src="<c:url value="/resources/scripts/common/common.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/simpletreemenu.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/SpryTabbedPanels.js"/>" type="text/javascript"></script>

<!--  This script is to diffrentiate the skills inorder to display -->
<script src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>" type="text/javascript"></script>
<script>

$(document).ready(function() 
		{
			

		//After rejecting

			$('.btn_reject').live("click",function(){

			   //	 $empId = $('#empId').text();
			     $reject=true;
			     $resumeid=<%=resumeid%>
				 $comments = $('.txt_comments').val();
			     if($comments==null || $comments=="")
				 {
				   //alert("Please Enter Comments");
				   $reject=false;
				 }
			     
			    if($reject)
				{
			    <%-- var url = '<%=RESTUtil.efmsWSBaseUrl + QEPMSWebConstants.Manager.MANAGER_REJECT_RESUME_WS_PAGE_PATH%>?empId='+$empId+'&comments='+ $comments; --%>
				var url = '<%=RESTUtil.efmsWSBaseUrl + QEPMSWebConstants.Manager.MANAGER_REJECT_RESUME_WS_PAGE_PATH%>?resumeid='+$resumeid+'&comments='+ $comments;
		        $.get(url, processResponse);
				}
			    
				function processResponse(data) {
					//alert(data.responseMessage);
					window.location.href = '/qepms/manager/allresume';

				}

				
			
		    });


		// After accepting

			$('.btn_accept').live("click",function(){
				 $accept=true;
				 $resumeid=<%=resumeid%>
				 $comments = $('.txt_comments').val();
				 if($comments==null || $comments=="")
				 {
				  // alert("Please Enter Comments");
				   $accept=false;
				 }
				 if($accept)
				 {
					var url = '<%=RESTUtil.efmsWSBaseUrl + QEPMSWebConstants.Manager.MANAGER_ACCEPT_RESUME_WS_PAGE_PATH%>?resumeid='+ $resumeid+'&comments='+ $comments;
					$.get(url, processResponse);
				 }
				
				function processResponse(data) {
					//alert(data.responseMessage);
					window.location.href = '/qepms/manager/allresume';
				}

				
			
		    });
		
			/* $(function(){
				  $csv = $("#professional_summary");
				  items = $csv.text().split(",");
				  $csv.replaceWith($("<li/>"));
				  items.forEach(function(item){
				      $("ul").append("<li>"+item+"</li>");
				  });
				}); */
		
		});


</script>


</head>

<body>
<div id="header"> 
  <%@include file="../common/header.jsp" %>
 <script>
		var role="<%=user.getRole()%>";
	  	distributormgr_header(role);
		selmenu('mn_manager');
	</script>
</div>
<!-- end of header !-->
<div id="content">
  <div id="leftNavigation"> 
  
    <script>distributormgr_manager_submenu('0','0');</script> 
  </div>
  <!-- end of left navigation !-->
  <div id="contenPan">
    <div id="contenPan_LeftNavToggle"><a href="javascript:" onclick="toggle_leftmenu();"><img id="img_leftmenu" src="<c:url value="/resources/images/menuarrowopen.png"/>" alt="Close Left menu" title="Close Left menu" /></a></div>
    <div id="contenPan_main">
      <h1>View Resume
        
      </h1>
      <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li class="TabbedPanelsTab" tabindex="0">View Resume</li>
         
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
            
           <div class="subsection" id="forms">
              <ul>
                <li>
                  <div class="label">Title:</div>
                  <div class="fleft">${resume.employeeMaster.title}</div>
				</li>
				<li>
                  <div class="label">Employee Id</div>
                  <div class="fleft" id="empId">${resume.employeeMaster.empId}</div>
				 </li>
				 <li>
                  <div class="label">Full Name</div>
                  <div class="fleft">${resume.employeeMaster.name}</div>
                </li>
                <c:forEach items="${skillList}" var="skills">
				<c:choose>
				<c:when test="${skills.skillCatagory=='PRIMARY'}">
				<li><div class="label">Primary Skills:</div>
					<div class="fcenter">&nbsp;<c:out value="${skills.skill}" /></div>
				</li>
				</c:when>
				<c:when test="${skills.skillCatagory=='SECONDARY'}">
				<li><div class="label">Secondary Skills:</div>
					<div class="fcenter">&nbsp;<c:out value="${skills.skill}" /></div> 
				</li>
				</c:when>
			   </c:choose>
               </c:forEach>
                <li>
                  <div class="label">Total Experience:</div>
                  <div class="fleft">${resume.totalExperience} &nbsp;<font  style="bold" class="label"></font></div>
                </li>
				 <li>
                  <div class="label">Relevant Experience:</div>
                  <div class="fleft">${resume.relevantExperience} &nbsp;<font  style="bold" class="label"></font></div>
                </li>
                <li>
                <table>
                  <tr>
                  <td><div class="label">Professional Summary:</div></td>
                  <td><div class="fcenter" id="professional_summary">${resume.professionalSummary}</div></td>
                  </tr>
                  </table>
                </li>
              </ul>
            </div>
			<!-- start of TechnicalSkills !-->
			
			<div class="subsection">
              <h2>Technical Skills</h2>
              <div class="datagrid">
                <table> 
                  <thead>
                  <tr>
                      <th width="15%">Skill Category </th>
                      <th>Skill Value </th>
                    </tr>
                  </thead>
                       <tbody>
						<c:forEach items="${skillList}" var="skills">
						<c:choose>
						<c:when test="${skills.skillCatagory=='TECHNICAL'}">
		              <tr>
                      <td><div><span></span>${skills.skillType}</div></td>
                      <td>${skills.skill}</td>
                    </tr>
                     </c:when></c:choose></c:forEach>
                     </tbody>
                     <tfoot>
                    <tr class="totalsec">
                      <td colspan="2">&nbsp;</td>
                      
                    </tr>
                  </tfoot>
                </table>
              </div>
             </div>
            <!-- end of TechnicalSkills !-->
			
			
		   <!-- Start :: Project Details Section -->
            <div class="subsection">
              <h2>Professional Experience</h2>
              <div class="datagrid">
               
                
                 <table> 
                  <thead>
                    <tr>
                      <th >Client Name </th>
                      <th >Duration(Yrs) </th>
					  <th>Project Name </th>
					  <th>Role </th>
					  <th>Project Brief </th>
					  <th>Responsibilities</th>
					  <th> Environment</th>
					 
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${projectList}" var="projects">
                  
                   <tr>
                      
					    <td><c:out value="${projects.clientName}"></c:out></td>
					    <td><c:out value="${projects.duration}"></c:out></td>
					    <td><c:out value="${projects.projectName}"></c:out></td>
					    <td><c:out value="${projects.role}"></c:out></td>
					    <td><c:out value="${projects.projectDesc}"></c:out></td>
					    <td><c:out value="${projects.responsibility}"></c:out></td>
					    <td><c:out value="${projects.environment}"></c:out></td>
                   </tr>
                    </c:forEach>
                    
                  </tbody> 
                  <tfoot>
                    <tr class="totalsec">
                      <td colspan="7">&nbsp;</td>
                      
                    </tr>
                  
                  </tfoot>
                </table>
                
                
                
                
                
                
              </div>
             </div>
            <!-- End :: Project Details Section -->
			
			<!-- Start :: Education Details Section -->
                     
            <div class="subsection">
              <h2>Education Details</h2>
              <div class="datagrid">
                <table> 
                  <thead>
                    <tr>
                      <!-- <th>Degree</th>
                      <th>Aggregate(%)</th> -->
					  <th>Description</th>
					  <!-- <th>College</th>
					  <th>YearOfPassing</th> -->
                    </tr>
                  </thead>
                  <tbody>
         
                  <c:forEach items="${educationList}" var="education">
                    <tr>
              
                      <%-- <td><div>${education.degree}</div></td>
                      <td><div>${education.aggregate}</div></td> --%>
                      <td><div>${education.description}</div></td>
                      <%-- <td><div>${education.college}</div></td>
                      <td><div>${education.yearOfPassing}</div></td> --%>
                      
                    </tr>
                     </c:forEach> 
                   
                  </tbody>
                  <tfoot>
                    <tr class="totalsec">
                      <td colspan="5">&nbsp;</td>
                      
                    </tr>
                  
                  </tfoot>
                </table>
              </div>
             </div>
            <!-- end of  Education Details Section !-->
            <c:if test="${status=='PENDING'}">
            <ul>
                <li>
                 <div class="label">Comments</div>
             <div class="fleft"><textarea rows="4" cols="40" class="txt_comments"></textarea></div>
				</li>
			</ul>
             
       
            <div class="bottombtn">
              <input type="button" value="Approve" class="btn_accept" />
              <input type="button" value="Reject" class="btn_reject" />
			  <input type="button" value="Back"  onclick="history.back();" />
            </div>
					  
            </c:if>
			
          </div>
          
          
        
        </div>
      </div>
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
