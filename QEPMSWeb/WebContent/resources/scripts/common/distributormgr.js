// JavaScript Document
function distributormgr_header(role){

var userrole=role.toLowerCase();
document.write('<div class="navigation">');
document.write('<ul>');

document.write('<li id="mn_employee"><a href="/qepms/employee/landingpage">Employee</a></li>');

if(userrole=="manager")
	{
		document.write('<li id="mn_manager"><a href="/qepms/manager/landingpage">Manager</a></li>');
	}
if(userrole=="armg")
	{
		document.write('<li id="mn_armg"><a href="/qepms/armg/landing">Armg</a></li>');
	}
document.write('<li id="mn_armg"><a href="/qepms/login/logout">Logout</a></li>');
document.write('</ul>');
document.write('</div>');
}



function distributormgr_employee_submenu(i,j){
	var t, k;
	if (i == '1')
	{t = "open"; k = "closed";}
	else if (i == '2')
	{t = "closed"; k = "open";}
	 else
	 {t = "closed"; k = "closed";}
	 
	document.write('<ul id="tree_invoice" class="treeview">');
	document.write('<li id="m_submitresume"><a href="/qepms/employee/submitresume">Submit Resume</a></li>');
	document.write('<li><a href="#">Search Resume(s)</a>');
	document.write('<ul id="m_1" id="fl1" rel='+t+'>');
	document.write('<li id="m_1_submitted"><a href="/qepms/employee/submittedresume">Submitted</a></li>');
	//document.write('<li id="m_1_draft"><a href="/qepms/employee/draftresume">Draft</a></li>');
	document.write('<li id="m_1_rejected"><a href="/qepms/employee/rejectedresume">Rejected</a></li>');
	
	document.write('</ul>');
	document.write('</li>');
	document.write('</ul>');
	
	ddtreemenu.createTree("tree_invoice", true);
	$("#"+j+">a").addClass("selected");
}

function distributormgr_armg_submenu(i,j){
	var t, k;
	if (i == '1')
	{t = "open"; k = "closed";}
	else if (i == '2')
	{t = "closed"; k = "open";}
	 else
	 {t = "closed"; k = "closed";}
	 
	document.write('<ul id="tree_invoice" class="treeview">');
	document.write('<li id="m_armgresume"><a href="/qepms/armg/armgresume">Search Resume</a></li>');
	document.write('<li id="m_2_uploadresumetemplate"><a href="/qepms/armg/uploadresumetemplate">Upload Resume</a></li>');
	document.write('<li id="m_2_uploadExceltemplate"><a href="/qepms/armg/uploadeExcel">Upload Employee Details</a></li>');
	//document.write('<li id="m_2_uploadresumetemplate"><a href="/qepms/armg/armgconfiguration">Configuration</a></li>');
	
	document.write('</ul>');
	ddtreemenu.createTree("tree_invoice", true);
	$("#"+j+">a").addClass("selected");
}
function distributormgr_manager_submenu(i,j){
	var t, k;
	if (i == '1')
	{t = "open"; k = "closed";}
	else if (i == '2')
	{t = "closed"; k = "open";}
	else
	{t = "closed"; k = "closed";}
	document.write('<ul id="tree_invoice" class="treeview">');
	//document.write('<li><a href="#">Search Resume(s)</a>');
	//document.write('<ul id="m_1" id="fl1" rel='+t+'>');
	document.write('<li id="m_allresume"><a href="/qepms/manager/allresume">Pending Resumes</a></li>');
	document.write('<li id="m_2_approvedresume"><a href="/qepms/manager/approvedresume">Approved Resumes</a></li>');
	document.write('<li id="m_2_rejectedresume"><a href="/qepms/manager/rejectedresume">Rejected Resume</a></li>');
	document.write('</ul>');
	/*document.write('</li>');
	document.write('</ul>');*/
	ddtreemenu.createTree("tree_invoice", true);
	$("#"+j+">a").addClass("selected");
	}




