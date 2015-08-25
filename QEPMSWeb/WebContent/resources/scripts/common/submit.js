$(document).ready(function() 
{
	//On click of Add of technical skills-----------------function1
	$( "#btn_skilltypeadd" ).click(function() {
		
		//getting the value of textbox
		$skillType=$('#skillid').val();
		$skillvalue=$('#skillvalue').val();
		
		//appending table with value
		$("#technicaldetails table tbody:last").prepend(
				'<tr>' +
				'<td><div><span>'+$skillType+'</span> </div></td>'  +
				'<td>'+$skillvalue+'</td>' +
				'<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png/>" alt="" class="deletetechnicaldetails" align="top" /></a></td>' +
				'</tr>' 
				);
		
		//refreshing the text field
		$('#skillid').val('');
		$skillvalue=$('#skillvalue').val('');
		
		
	
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
	
		//appending table with value
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
		
		
		
	
	ui_rows();
	});
	
//on click of Add of education details----------------------------function3
$( "#btn_educationdetails" ).click(function() {
		
		//getting the value of textbox
		$degree=$('#degree').val();
		$aggregate=$('#aggregate').val();
		$description=$('#description').val();
		$college=$('#textarea_college').val();
		$yearofpassing=$('#yearofpassing').val();
		
		//appending table with value
	   $("#educationdetails table tbody:last").prepend(
							'<tr>' +
            				'<td><div><span>'+$degree+'</span> </div></td>'  +
            				'<td>'+$aggregate+'</td>' +
							'<td>'+$description+'</td>' +
							'<td>'+$college+'</td>' +
							'<td>'+$yearofpassing+'</td>' +
							'<td align="center"><a href="javascript:" onclick="$(this).parent().parent().remove(); ui_rows(); return false;"><img src="<c:url value="/resources/images/delete.png"/>" alt="" align="top" class="deleteeducationdetails" /></a></td>' +
        '</tr>');
	
		
	  //refreshing the text field
	    $('#degree').val('');
		$('#aggregate').val('');
		$('#description').val('');
		$('#textarea_college').val('');
		$('#yearofpassing').val('');
		
		
		
	
	ui_rows();
});


//Submit the resume

$( ".submitform" ).click(function() {
	$title=$( "#title option:selected" ).text();
	$status="<%=EmployeeSubmissionStatus.SUBMITTED.name()%>";
	$('#txt_title').val($title);
	createSkillList();
	createProjectList();
	createEducationList();
	generateStatus($status);
	$('#target').submit();
	
	
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
	$str="<input type='hidden' name='submissionStatus' value='" +status + "' />";
	$('#statusContainer').html($str);
}

function createSkillList()
{
	
	$rows = $(".deletetechnicaldetails").closest("tr");
	
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
	//alert($str);
	$("#generatedSkillListContainer").html($str);

}

function createProjectList()
{
	
	$rows = $(".deleteprojectdetails").closest("tr");
	
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

}

function createEducationList()
{
	
	$rows = $(".deleteeducationdetails").closest("tr");
	
	$str = "";
	var $i=0;
	
	for( $i=0; $i < $rows.length; $i++ )
		{
			$ele = $( $( $( ".deleteeducationdetails" ).parent().parent().parent() )[ $i ] ).children() ; 
			
$str = $str +"<input type='hidden' name='educations[" + $i + "].degree' id='degree" + $i + "' value='" + $ele.eq(0).text() + "' />"
			+"<input type='hidden' name='educations[" + $i + "].aggregate' id='aggregate" + $i + "' value='" + $ele.eq(1).text() + "' />"
			+"<input type='hidden' name='educations[" + $i + "].description' id='description" + $i + "' value='" + $ele.eq(2).text() + "' />"
			+"<input type='hidden' name='educations[" + $i + "].college' id='college" + $i + "' value='" + $ele.eq(3).text() + "' />"
			+"<input type='hidden' name='educations[" + $i + "].yearOfPassing' id='yearOfPassing" + $i + "' value='" + $ele.eq(4).text() + "' /><br>";
		}
	//alert($str);
	$("#generatedEducatonDetailsListContainer").html($str);

}



		
});