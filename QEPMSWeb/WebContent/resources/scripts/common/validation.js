// JavaScript Document --> Validations

// upload button validation for uploadresumetemplate.jsp
$(document).ready(function() 
{
	$( "#uploadButtonDoc" ).click(function() {
		
		var fileName = $('#id_file').val();
		var ext = $('#id_file').val().split('.').pop().toLowerCase();
		
		if((fileName == 'undefined') || (fileName == ''))
		{
			alert("Please select a file to be uploaded");
		}
		else if(($.inArray(ext, ['doc','docx']) == -1))
		{
			alert("Please upload a word document");
		}
		else
		{
			alert($('#id_file').val());
			$("#target").submit();
		}
		
	});
	
});

//upload button validation for uploadExcel.jsp
$(document).ready(function() 
{
	$( "#uploadButtonExcel" ).click(function() {
		
		var fileName = $('#id_file').val();
		var ext = $('#id_file').val().split('.').pop().toLowerCase();
		
		if((fileName == 'undefined') || (fileName == ''))
		{
			alert("Please select a file to be uploaded");
		}
		else if(($.inArray(ext, ['xls','xlsx']) == -1))
		{
			alert("Please upload a excel document");
		}
		else
		{
			alert($('#id_file').val());
			$("#target").submit();
		}
		
	});
	
});