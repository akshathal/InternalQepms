<%@page import="com.qepms.web.constants.QEPMSWebConstants"%>
<%@page import="com.qepms.infra.misc.wrapper.ResponseMessageWrapper"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="com.qepms.web.util.RESTUtil"%>
<%@page import="com.qepms.infra.dto.armg.EmployeeMasterDTO"%>
<%@page import="com.qepms.infra.dto.armg.EmployeeMasterDTOList"%>
<%@page import="com.qepms.infra.dto.armg.SkillDTO"%>
<%@page import="com.qepms.infra.dto.armg.SkillDTOList"%>
<%@page import="com.qepms.infra.dto.armg.EducationDTO"%>
<%@page import="com.qepms.infra.dto.armg.EducationDTOList"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTO"%>
<%@page import="com.qepms.infra.dto.armg.ProjectDTO"%>
<%@page import="com.qepms.infra.dto.armg.ResumeDTOList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.qepms.common.util.EmployeeSubmissionStatus"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@page import="com.qepms.common.util.SkillType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>Management System</title>


<link href="<c:url value="/resources/styles/styles.css"/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/scripts/common/distributormgr.js"/>"></script>
<script
	src="<c:url value="/resources/scripts/common/jquery-1.8.3.min.js"/>"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/scripts/common/simpletreemenu.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/common.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/common/datepicker.js"/>"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/scripts/common/SpryTabbedPanels.js"/>"
	type="text/javascript"></script>
<script>
$(document).ready(function() 
		{
			//On click of Add of technical skills-----------------function1
			$('#technicaldetailserror').hide();
			$('#projectdetailsserror').hide();
			$('#educationdetailserror').hide();
			
			$( "#btn_skilltypeadd" ).click(function() {
				
				//getting the value of textbox
				$skillType=$('#skillid').val();
				$skillvalue=$('#skillvalue').val();
				$add = true;
				
				if($skillType==""||$skillType==null||$skillvalue==""||$skillvalue==null)
					{
					    $('#technicaldetailserror').show();
					    $('#technicaldetailserror').html('<font color="red">Please add both SkillType and SkillValue</font>');
					    $add = false;
					}
				
				if($add)
				{
				//appending table with value
				$("#technicaldetails table tbody:last").prepend(
						'<tr>' +
						'<td><div><span>'+$skillType+'</span> </div></td>'  +
						'<td>'+$skillvalue+'</td>' +
						'<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png"/>" alt="" class="deletetechnicaldetails" align="top" /></a></td>' +
						'</tr>' 
						);
				
				//refreshing the text field
				$('#skillid').val('');
				$skillvalue=$('#skillvalue').val('');
				$('#technicaldetailserror').hide();
				}
				
				
			
			ui_rows();
			});
			

		//on click of Add of project details-----------------------function2	
		$( "#btn_projectdetails" ).click(function() {
				
				//getting the value of textbox
				$clientname=$('#txt_clientname').val();
				$duration=$('#txt_duration').val();
				$projectname=$('#txt_projectname').val();
				$role=$('#txt_role').val();
				$projectbrief=$('#txt_projectbrief').val();
				$responsibility=$('#txt_responsibilities').val();
				$environment=$('#txt_environment').val();
				$add = true;
				
				if($clientname==""||$clientname==null||$duration==""||$duration==null||$projectname==""||$projectname==null||$role==""||$role==null||$projectbrief==""||$projectbrief==null||$responsibility==""||$responsibility==null||$environment==""||$environment==null)
				{
				    $('#projectdetailsserror').show();
				    $('#projectdetailsserror').html('<font color="red">Please fill all the below fields</font>');
				    $add = false;
				}
			
				//appending table with value
				if($add)
				{
				   	 $("#projectdetails table tbody:last").prepend(
										'<tr>' +
			            				'<td><div><span>'+$clientname+'</span> </div></td>'  +
			            				'<td>'+$duration+'</td>' +
										'<td>'+$projectname+'</td>' +
										'<td>'+$role+'</td>' +
										'<td>'+$projectbrief+'</td>' +
										'<td>'+$responsibility+'</td>' +
										'<td>'+$environment+'</td>' +
										'<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png"/>" alt="" align="top" class="deleteprojectdetails" /></a></td>' +
			        '</tr>'  );
					
					  //refreshing the text field
					    $('#txt_clientname').val('');
						$('#txt_duration').val('');
						$('#txt_projectname').val('');
						$('#txt_role').val('');
						$('#txt_projectbrief').val('');
						$('#txt_responsibilities').val('');
						$('#txt_environment').val('');
				$('#projectdetailsserror').hide();
				}
				
				
			
			ui_rows();
			});
			
		//on click of Add of education details----------------------------function3
		$( "#btn_educationdetails" ).click(function() {
				
				//getting the value of textbox
				$('#educationdetailserror').hide();
				/* $degree=$('#degree').val();
				$aggregate=$('#aggregate').val(); */
				$description=$('#description').val();
				/* $college=$('#textarea_college').val();
				$yearofpassing=$('#yearofpassing').val(); */
				$add = true;
				
				if($description==""||$description==null)
					{
					    $('#educationdetailserror').show();
					    $('#educationdetailserror').html('<font color="red">Please fill the below field</font>');
					    $add = false;
					}
				
				//appending table with value
				if($add)
				{
					   $("#educationdetails table tbody:last").prepend(
											'<tr>' +
				            				/* '<td><div><span>'+$degree+'</span> </div></td>'  +
				            				'<td>'+$aggregate+'</td>' + */
											'<td>'+$description+'</td>' +
											/* '<td>'+$college+'</td>' +
											'<td>'+$yearofpassing+'</td>' + */
											'<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png"/>" alt="" align="top" class="deleteeducationdetails" /></a></td>' +
				        '</tr>');
					
						
					  //refreshing the text field
					    /* $('#degree').val('');
						$('#aggregate').val(''); */
						$('#description').val('');
						$('#educationdetailserror').hide();
						/* $('#textarea_college').val('');
						$('#yearofpassing').val(''); */
						
				}
				
			
			ui_rows();
		});


		//Submit the resume

		$( ".submitform" ).click(function() {
			$submit =validate();
			if($submit)
			{
				$('#technicaldetailsError').hide();
				$('#projectdetailsserror').hide();
				$('#educationdetailsError').hide();
				$canSubmit=true;
			$title=$( "#title option:selected" ).text();
			$status="<%=EmployeeSubmissionStatus.SUBMITTED.name()%>";
			$('#txt_title').val($title);
			$skillPresent=createSkillList();
			if(!$skillPresent)
			{
			    $('#technicaldetailsError').html("<font color='red'>Technical Details cannot be empty</font>");
	            $('#technicaldetailsError').show();
	            $canSubmit =false;
			}
			
			$projectPresent=createProjectList();
			if(!$projectPresent)
			{
				$('#projectdetailsserror').html("<font color='red'>Project Details cannot be empty</font>");
	            $('#projectdetailsserror').show();
	            $canSubmit =false;
			}
			
			$educationPresent=createEducationList();
			if(!$educationPresent)
			{
			    $('#educationdetailsError').html("<font color='red'>Education Details cannot be empty</font>");
	            $('#educationdetailsError').show();
	            $canSubmit =false;
			}
			
			generateStatus($status);
			if($canSubmit)
			{
				$('#target').submit();
			}
			
			}
			
			
		});

		//Save resume as Draft

		$( ".draftform" ).click(function() {
			$title=$( "#title option:selected" ).text();
			$status="<%=EmployeeSubmissionStatus.PENDING.name()%>";
			$('#txt_title').val($title);
			createSkillList();
			createProjectList();
			createEducationList();
			generateStatus($status);
			$('#target').submit();
			
			
		});

		function generateStatus(status)
		{
			$str="<input type='hidden' name='employeeSumbissionStatus' value='" +status + "' />";
			$('#statusContainer').html($str);
		}

		function createSkillList()
		{
			$flag=false;
			
			$rows = $(".deletetechnicaldetails").closest("tr");
			if($rows.length>0)
			{
			    $flag=true;
			    
			}
			
			$str = "";
			var $i=0;
			
			for( $i=0; $i < $rows.length; $i++ )
				{
					$ele = $( $( $( ".deletetechnicaldetails" ).parent().parent().parent() )[ $i ] ).children() ; 
					
					$str = $str + "<input type='hidden' name='skills[" + $i + "].skillType' id='skillType" + $i + "' value='" + $ele.eq(0).text() + "' />"
					+"<input type='hidden' name='skills[" + $i + "].skill' id='skill" + $i + "' value='" + $ele.eq(1).text() + "' />"
					+"<input type='hidden' name='skills[" + $i + "].skillCatagory' id='skill" + $i + "' value='TECHNICAL' /><br>";
				
				}
			
			//creating primary skill
			$skill=$('#primaryskills').val();
			$skillcatagory="PRIMARY";
			$skillType=null;
			$primarystr= "<input type='hidden' name='skills[" + $i + "].skillType' id='skillType" + $i + "' value='" + $skillType + "' />"
			+"<input type='hidden' name='skills[" + $i + "].skill' id='skill" + $i + "' value='" + $skill + "' />"
			+"<input type='hidden' name='skills[" + $i + "].skillCatagory' id='skill" + $i + "' value='"+$skillcatagory+"' /><br>";
			
			//creating secondary skill
			$skill=$('#secondaryskills').val();
			$skillcatagory="SECONDARY";
			$skillType=null;
			$i++;
			$secondarystr= "<input type='hidden' name='skills[" + $i + "].skillType' id='skillType" + $i + "' value='" + $skillType + "' />"
			+"<input type='hidden' name='skills[" + $i + "].skill' id='skill" + $i + "' value='" + $skill + "' />"
			+"<input type='hidden' name='skills[" + $i + "].skillCatagory' id='skill" + $i + "' value='"+$skillcatagory+"' /><br>";
			
			$str = $str + $primarystr + $secondarystr;
		//	alert($str);
			$("#generatedSkillListContainer").html($str);
			return $flag;

		}

		function createProjectList()
		{
			$flag=false;	
			$rows = $(".deleteprojectdetails").closest("tr");
			if($rows.length>0)
			{
			    $flag=true;
			    
			}
			
			$str = "";
			var $i=0;
			
			for( $i=0; $i < $rows.length; $i++ )
				{
					$ele = $( $( $( ".deleteprojectdetails" ).parent().parent().parent() )[ $i ] ).children() ; 
					
		$str = $str +"<input type='hidden' name='projects[" + $i + "].clientName' id='clientName" + $i + "' value='" + $ele.eq(0).text() + "' />"
					+"<input type='hidden' name='projects[" + $i + "].duration' id='duration" + $i + "' value='" + $ele.eq(1).text() + "' />"
					+"<input type='hidden' name='projects[" + $i + "].projectName' id='projectName" + $i + "' value='" + $ele.eq(2).text() + "' />"
					+"<input type='hidden' name='projects[" + $i + "].role' id='role" + $i + "' value='" + $ele.eq(3).text() + "' />"
					+"<input type='hidden' name='projects[" + $i + "].projectDesc' id='projectDesc" + $i + "' value='" + $ele.eq(4).text() + "' />"
					+"<input type='hidden' name='projects[" + $i + "].responsibility' id='responsibility" + $i + "' value='" + $ele.eq(5).text() + "' />"
					+"<input type='hidden' name='projects[" + $i + "].environment' id='environment" + $i + "' value='" + $ele.eq(6).text()+"' /><br>";
				
				}
			//alert($str);
			$("#generatedProjectDetailsListContainer").html($str);
			return $flag;

		}

		function createEducationList()
		{
			$flag = false;
			$rows = $(".deleteeducationdetails").closest("tr");
			if($rows.length>0)
			{
			    $flag=true;
			    
			}
			
			
			
			
			
			$str = "";
			var $i=0;
			
			for( $i=0; $i < $rows.length; $i++ )
				{
					$ele = $( $( $( ".deleteeducationdetails" ).parent().parent().parent() )[ $i ] ).children() ; 
					
		$str = $str +"<input type='hidden' name='educations[" + $i + "].description' id='description" + $i + "' value='" + $ele.eq(0).text() + "' />";
					
				}
			//alert($str);
			$("#generatedEducatonDetailsListContainer").html($str);
             return $flag;
		}
		function validate()	
		{
		$flag =true;
		
        var primaryskills = $('#primaryskills').val();
        if (primaryskills == '' ||primaryskills==null ) {
           
            $('#primaryskillsError').html("<font color='red'>Primary Skills cannot be empty</font>");
            $('#primaryskillsError').show();
            $flag =false;
  	    }
        
        var secondaryskills = $('#secondaryskills').val();
        if (secondaryskills == '' ||secondaryskills==null ) {
           
            $('#secondaryskillsError').html("<font color='red'>Secondary Skills cannot be empty</font>");
            $('#secondaryskillsError').show();
            $flag =false;
        }
        
		var totalExperience = $('#totalExperience').val();
	    if (totalExperience == '' ||totalExperience==null ) {
	           
	            $('#totalExperienceError').html("<font color='red'>Total Experience cannot be empty</font>");
	            $('#totalExperienceError').show();
	            $flag =false;
	           
	        
        }
        var relevantExperience = $('#relevantExperience').val();
	    if (relevantExperience == '' ||relevantExperience==null ) {
	           
	            $('#relevantExperienceError').html("<font color='red'>Relevant Experience cannot be empty</font>");
	            $('#relevantExperienceError').show();
	            $flag =false;
	           
	        
        }
        var professionalSummary = $('#professionalsummary').val();
        if (professionalSummary == '' ||professionalSummary==null ) {
	         
	            $('#professionalSummaryError').html("<font color='red'>Professional Summary cannot be empty</font>");
	            $('#professionalSummaryError').show();
	            $flag =false;
	           
	        
	        
		}
        /*  var technicaldetails = $('#technicaldetails').val();
        if (technicaldetails == '' ||technicaldetails==null ) {
	         
	            $('#technicaldetailsError').html("<font color='red'>Technical Details cannot be empty</font>");
	            $('#technicaldetailsError').show();
	            $flag =false;
        }
        var projectdetails = $('#projectdetails').val();
        if (projectdetails == '' ||projectdetails==null ) {
	         
	            $('#projectdetailsError').html("<font color='red'>Project Details cannot be empty</font>");
	            $('#projectdetailsError').show();
	            $flag =false;
        } 
        var educationdetails = $('#educationdetails').val();
        if (educationdetails == '' ||educationdetails==null ) {
	         
	            $('#educationdetailsError').html("<font color='red'>Education Details cannot be empty</font>");
	            $('#educationdetailsError').show();
	            $flag =false;
        } */
		 
        return $flag;
		}

		  //Add blur function for validation
		  $("#primaryskills").blur(function(){
		    $('#primaryskillsError').hide();
		  });
		    
		  $("#secondaryskills").blur(function(){
			$('#secondaryskillsError').hide();
		  });
			    
		  $("#totalExperience").blur(function(){
			$('#totalExperienceError').hide();
		  });
				    
		  $("#relevantExperience").blur(function(){
			$('#relevantExperienceError').hide();
		  });
					    
		  $("#professionalsummary").blur(function(){
			$('#professionalSummaryError').hide();
		  });
		  
		 
		  $("#technicaldetails").blur(function(){
				$('#technicaldetailsError').hide();
		  });
		  $("#projectdetails").blur(function(){
				$('#projectdetailsError').hide();
		  });
		  $("#educationdetails").blur(function(){
				$('#educationdetailsError').hide();
		  });
		  
		//End blur for validation
		  
		  $("#primaryskills").attr('maxlength','255');
		  $("#secondayskills").attr('maxlength','255');
		  $("#totalExperience").attr('maxlength','20');
		  $("#relevantExperience").attr('maxlength','20');
		  $("#professionalSummary").attr('maxlength','10000');
		  $("#skillvalue").attr('maxlength','255');
		  $("#txt_clientname").attr('maxlength','50');
		  $("#txt_duration").attr('maxlength','20');
		  $("#txt_projectname").attr('maxlength','50');
		  $("#txt_role").attr('maxlength','255');
		  $("#txt_projectbrief").attr('maxlength','10000');
		  $("#txt_responsibilities").attr('maxlength','10000');
		  $("#txt_environment").attr('maxlength','10000');
		  /* $("#degree").attr('maxlength','50');
		  $("#aggregate").attr('maxlength','20'); */
		  $("#description").attr('maxlength','5000');
		  /* $("#textarea_college").attr('maxlength','50'); */
		  
		  
		  $("skillvalue").dblclick(function(){
			  $('#skillvalueError').hide();
			});
		  



				
		});
</script>
</head>

<body>
	<div id="header">
		<%@include file="../common/header.jsp"%>
		<%
	ApplicationContext context = RequestContextUtils.getWebApplicationContext(request); 
 	RESTUtil restUtil = (RESTUtil)context.getBean("restUtil");
 	String resumeid = request.getParameter("resumeid");
 
    ResumeDTO resume = (ResumeDTO) restUtil.getData(QEPMSWebConstants.Employee.EMPLOYEEVIEWRESUME_WS_PATH+"?resumeid="+resumeid,ResumeDTO.class);
 	pageContext.setAttribute("resume", resume);  	 
 	List<EducationDTO> educations = resume.getEducations();
 	pageContext.setAttribute("educations", educations); 
    List<SkillDTO> skillList = resume.getSkills();
 	pageContext.setAttribute("skillList", skillList); 
 	List<ProjectDTO> projectList = resume.getProjects();
 	pageContext.setAttribute("projectList", projectList); 
 	
 	    //Skill type (primary/secondary)
 	 	String primary=SkillType.PRIMARY.name();
 	 	String secondary=SkillType.SECONDARY.name();
 	 	String check="null";
 	 	pageContext.setAttribute("primary", primary); 
 	 	pageContext.setAttribute("secondary", secondary); 
 	 	pageContext.setAttribute("check", check);
 	 	
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
			<script>distributormgr_employee_submenu('2','m_1_draft');</script>
		</div>
		<!-- end of left navigation !-->
		<div id="contenPan">
			<div id="contenPan_LeftNavToggle">
				<a href="javascript:" onclick="toggle_leftmenu();"><img
					id="img_leftmenu"
					src="<c:url value="/resources/images/menuarrowopen.png"/>"
					alt="Close Left menu" title="Close Left menu" /></a>
			</div>
			<div id="contenPan_main">
				<form:form modelAttribute="resumeDTO" method="post" id="target"
					action="/qepms/employee/submitsresume" commandName="resumeDTO">
					<h1>Edit Resume</h1>
					<div id="TabbedPanels1" class="TabbedPanels">
						<ul class="TabbedPanelsTabGroup">
							<li class="TabbedPanelsTab" tabindex="0">Edit Resume</li>
						</ul>
						<div class="TabbedPanelsContentGroup">
							<div class="TabbedPanelsContent">

								<div class="subsection" id="forms">
									<ul>
										<li>
											<div class="label">Title:</div>
											<div class="fleft">
												<select id="title">
													<option></option>
													<option selected>
														<c:out value="${resume.employeeMaster.title}" />
													</option>
													<option>Mr</option>
													<option>Miss</option>
												</select>
											</div>
											<div class="div_tittlehidden">
												<input id="txt_title" type="hidden"
													name="employeeMaster.title"
													value="${resume.employeeMaster.title}"></input>
											</div>
										</li>
										<li>
											<div class="label">Emp Id:</div>
											<div class="fleft">${resume.employeeMaster.empId}</div> <input
											type="hidden" name="employeeMaster.empId"
											value="${resume.employeeMaster.empId}"></input> <input
											type="hidden" name="employeeMaster.employeeMail"
											value="${resume.employeeMaster.employeeMail}"></input> <input
											type="hidden" name="employeeMaster.reportingManager"
											value="${resume.employeeMaster.reportingManager}"></input>

										</li>
										<li>
											<div class="label">Full Name:</div>
											<div class="fleft">
												<c:out value="${resume.employeeMaster.name}" />
											</div>
											<div>
												<input type="hidden" name="employeeMaster.name"
													value="${resume.employeeMaster.name}"></input>
											</div>
										</li>
										<c:forEach items="${skillList}" var="skills">
											<c:choose>
												<c:when test="${skills.skillCatagory == 'PRIMARY'}">
													<li><span class="mandatory">*</span>
														<div class="label">Primary Skills:</div>
														<div class="fleft"><textarea rows="2" cols="5" id="primaryskills"><c:out value="${skills.skill}" /></textarea></div>
								<div id="primaryskillsError"></div>
								</li>
								</c:when>
								<c:when test="${skills.skillCatagory == 'SECONDARY'}">
									<li><span class="mandatory">*</span>
										<div class="label">Secondary Skills:</div>
										<div class="fleft"><textarea rows="2" cols="5" id="secondaryskills"><c:out value="${skills.skill}" /></textarea></div>
										<div id="secondaryskillsError"></div>
									</li>
							</c:when>
							</c:choose>
							</c:forEach>
							<li><span class="mandatory">*</span>
								<div class="label">Total Experience:</div>
								<div class="fleft">
									<input type="text" value="${resume.totalExperience}" name="totalExperience" id="totalExperience"/>&nbsp;
									 <font style="" class="label"></font>
								</div>
						<div id="totalExperienceError"></div></li>
						<li><span class="mandatory">*</span>
							<div class="label">Relevant Experience:</div>
							<div class="fleft">
								<input type="text" value="${resume.relevantExperience}" name="relevantExperience" id="relevantExperience"/>&nbsp;
								 <font style="" class="label"></font>
					</div>
					<div id="relevantExperienceError"></div></li>
					
					<li><span class="mandatory">*</span>
						<div class="label">Professional Summary:</div>
						<div class="fleft">
							<textarea rows="2" cols="5" name="professionalSummary" id="professionalsummary">${resume.professionalSummary}</textarea>
						</div>
			<div id="professionalSummaryError"></div></li>
			</ul>
		</div>
		<!-- Start :: Technical Details Section -->
		<div class="subsection">
		<div id="technicaldetailserror"></div>
			<h2>Technical Skills</h2>
			<div class="datagrid" id="technicaldetails">
			<div id="technicaldetailsError"></div>
				<table>
					<thead>

						<tr>
							<th width="15%">Skill Type</th>
							<th>Skill Value</th>
							<th width="5%">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td nowrap="nowrap"><input type="text" value="" name=""
								id="skillid" class="normal" size="19" readonly="readonly" /> <a
								href="javascript:"
								onclick="PopupCenter('/qepms/common/addskillpopup','win','500','250');"><img
									src="<c:url value="/resources/images/arrowBlue.png"/>" alt=""
									align="absmiddle" /></a></td>
							<td><input type="text" id="skillvalue" value="" /></td>
							<td align="center"><input type="button" value="Add"
								id="btn_skilltypeadd" /></td>
						</tr>
						<c:forEach items="${skillList}" var="skills">
							<c:if test="${skills.skillType != check}">
								<tr>
									<td><div>
											<span><c:out value="${skills.skillType}" /></span>
										</div></td>
									<td><c:out value="${skills.skill}" /></td>
									<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png"/>" alt=""
											class="deletetechnicaldetails" /></a></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
					<tbody>
					</tbody>
					<tfoot>
						<tr class="totalsec">
							<td colspan="3">&nbsp;</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<div id="generatedSkillListContainer"></div>
		<!-- End :: Tecnical Details Section -->


		<!-- Start :: Project Details Section -->
		<div class="subsection">
		<div id="projectdetailsserror"></div>
			<h2>Professional Experience</h2>
			<div class="datagrid" id="projectdetails">
			<div id="projectdetailsError"></div>
				<table>
					<thead>
						<tr>
							<th width="9%">Client Name</th>
							<th width="1%">Duration</th>
							<th>Project Name</th>
							<th>Role</th>
							<th width="25%">Project Brief</th>
							<th width="15%">Responsibilities</th>
							<th width="15%">Environment</th>
							<th width="5%">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td nowrap="nowrap"><input type="text" id="txt_clientname"
								value="" name="" class="normal" size="19" /></td>
							<td><input type="text" id="txt_duration" value="" /></td>
							<td><input type="text" id="txt_projectname" value="" /></td>
							<td><input type="text" id="txt_role" value="" /></td>
							<td><textarea rows="5" id="txt_projectbrief" cols="50"><c:out value="" /></textarea></td>
							<td><textarea rows="5" id="txt_responsibilities" cols="30"><c:out value="" /></textarea></td>
							<td><textarea rows="5" id="txt_environment" cols="30"><c:out value="" /></textarea></td>
							<td align="center"><input type="button" value="Add"
								id="btn_projectdetails" /></td>
						</tr>
						<c:forEach items="${projectList}" var="projects">
							<tr>
								<td><c:out value="${projects.clientName}" /></td>
								<td><c:out value="${projects.duration}" /></td>
								<td><c:out value="${projects.projectName}" /></td>
								<td><c:out value="${projects.role}" /></td>
								<td><c:out value="${projects.projectDesc}" /></td>
								<td><c:out value="${projects.responsibility}" /></td>
								<td><c:out value="${projects.environment}" /></td>
								<td align="center"><a href="javascript:"onclick="$(this).parent().parent().remove(); ui_rows(); return false;">
									<img src="<c:url value="/resources/images/delete.png"/>" alt=""
										class="deleteprojectdetails" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
					<tbody>
					</tbody>
					<tfoot>
						<tr class="totalsec">
							<td colspan="9">&nbsp;</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<div id="generatedProjectDetailsListContainer"></div>
		<!-- End :: Project Details Section -->


		<!-- Start :: Education Details Section -->
		<div class="subsection">
		<div id="educationdetailserror"></div>
			<h2>Education Details</h2>
			<div class="datagrid" id="educationdetails">
			<div id="educationdetailsError"></div>
				<table>
					<thead>
						<tr>
							<!-- <th>Degree</th>
                      <th>Aggregate(%)</th> -->
							<th>Description</th>
							<!-- <th>College</th>
					  <th>YearOfPassing</th> -->
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<%-- <td><input type="text" value="${education.degree}"/></td>
                        <td><input type="text" value="${education.aggregate}" /></td> --%>
							<td><textarea rows="2" cols="160" id="description"> <c:out value="${education.description}" /></textarea></td>
							
							<%-- <td><input type="text" value="${education.college}"/></td>
						<td><input id='yearofpassing' type='text' value="${education.yearOfPassing}" readonly='readonly' class="date" /> --%>
							<%-- <img src="<c:url value="/resources/images/iccal.png"/>" onclick="scwShow(scwID('yearofpassing'),event);" align="absmiddle" />
							</td> --%>
							
							
							<td align="center"><input type="button" value="Add" id="btn_educationdetails" /></td>
						</tr>
						<c:forEach items="${educations}" var="education">
							<tr>
								<%-- <td><c:out value="${education.degree}"/></td>
                      <td><c:out value="${education.aggregate}"/></td> --%>
								<td><c:out value="${education.description}" /></td>
								<%-- <td><c:out value="${education.college}"/></td>
					  <td><c:out value="${education.yearOfPassing}"/></td> --%>
								<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png"/>" alt=""
										class="deleteeducationdetails" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
					<tbody>

					</tbody>
					<tfoot>
						<tr class="totalsec">
							<td colspan="9">&nbsp;</td>
						</tr>
					</tfoot>
				</table>
			</div>

		</div>
		<div id="generatedEducatonDetailsListContainer"></div>
		<!-- End :: Education Details Section -->




		<div class="bottombtn">
			<input class="draftform" type="button" value="Save As Draft" /> <input
				class="submitform" type="button" value="Submit" /> <input
				type="button" value="Cancel" onclick="history.back();" />
		</div>
		<div id="statusContainer"></div>
		<div id="resumeIdContainer">
			<input type="hidden" name="resumeid" value="${resume.resumeid}"></input>
		</div>
	</div>
	<!-- end of TabbedPanelsContent !-->


	</div>
	</div>
	</form:form>
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
