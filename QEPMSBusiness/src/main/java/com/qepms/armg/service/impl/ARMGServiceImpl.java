package com.qepms.armg.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.aspose.words.Document;
import com.qepms.armg.service.ARMGService;
import com.qepms.business.service.EmployeeViewService;
import com.qepms.business.service.impl.EmployeeViewServiceImpl;
import com.qepms.common.pdfConversion;
import com.qepms.common.util.CopyUtil;
import com.qepms.common.util.CustomDate;
import com.qepms.common.util.EmployeeSubmissionStatus;
import com.qepms.common.util.ManagerSubmissionStatus;
import com.qepms.common.util.SkillType;
import com.qepms.data.dao.employee.EmployeeMasterDAO;
import com.qepms.data.dao.employee.ResumeDAO;
import com.qepms.data.employee.Education;
import com.qepms.data.employee.EmployeeMaster;
import com.qepms.data.employee.Project;
import com.qepms.data.employee.Resume;
import com.qepms.data.employee.Skill;
import com.qepms.infra.constants.CommonConstants;
import com.qepms.infra.dto.armg.EducationDTO;
import com.qepms.infra.dto.armg.EmployeeMasterDTO;
import com.qepms.infra.dto.armg.ProjectDTO;
import com.qepms.infra.dto.armg.ResumeDTO;
import com.qepms.infra.dto.armg.SkillDTO;



@Transactional
public class ARMGServiceImpl implements ARMGService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ARMGServiceImpl.class);
	
	@Autowired
	private ResumeDAO resumeDAO;
	
	@Autowired
	private EmployeeMasterDAO employeeMasterDAO;
	
	@Autowired
	private EmployeeViewService employeeViewService;
	

	@Override
	public List<ResumeDTO> getResumeList() {
		
		List<ResumeDTO> resumeDTOList=new ArrayList<ResumeDTO>();
		ResumeDTO resumeDTO=null;
		EmployeeMasterDTO employeeMasterDTO=null;
		//get all the employees from DB
		List<EmployeeMaster> employees = employeeMasterDAO.getAllEmployees();
		
		//get all the resume ordered by date and desc
		List<Resume> resumeListWithDuplicates=resumeDAO.getLatestResumeList(employees,ManagerSubmissionStatus.APPROVED.name());
		
		//Only the latest resume eliminating duplicates
		boolean present = false;
		List<Resume> resumeList= new ArrayList<Resume>();
		
		for (Resume resumeDuplicate : resumeListWithDuplicates) {
			
			for (Resume resume : resumeList) 
			{
				if(resumeDuplicate.getEmployeeMaster().equals(resume.getEmployeeMaster()))
				{
					present=true;
				}
			       	
			}
			if(!present)
			{
				resumeList.add(resumeDuplicate);
				
			}
			present=false;
			
		}
		
		//List<Resume> resumeList=resumeDAO.getResumeList();
		Iterator resumeitr=resumeList.iterator();
		while(resumeitr.hasNext())
		{
			Resume resume=(Resume)resumeitr.next();
			resumeDTO=new ResumeDTO();
			
			/*
			 * setting employee details
			 */
			employeeMasterDTO=new EmployeeMasterDTO();
			employeeMasterDTO.setName(resume.getEmployeeMaster().getName());
			employeeMasterDTO.setEmpId(resume.getEmployeeMaster().getEmpId());
			resumeDTO.setEmployeeMaster(employeeMasterDTO);
			resumeDTO.setResumeid(resume.getResumeid());
			
			List<SkillDTO> skillDTOList =new ArrayList<SkillDTO>();
			SkillDTO sillDTO=null;
			Set<Skill> skillList = resume.getSkills();
			Iterator skillItr=skillList.iterator();
			while(skillItr.hasNext())
			{
				Skill skill=(Skill)skillItr.next();
				if(skill.getSkillCatagory().equals(SkillType.PRIMARY.name())||skill.getSkillCatagory().equals(SkillType.SECONDARY.name()))
				{
					sillDTO = new SkillDTO();
					sillDTO.setSkillType(skill.getSkillType());
					sillDTO.setSkillCatagory(skill.getSkillCatagory());
					sillDTO.setSkill(skill.getSkill());
					skillDTOList.add(sillDTO);
				}
			}
			resumeDTO.setSkills(skillDTOList);
			
			resumeDTOList.add(resumeDTO);
			 
		}
		
		return resumeDTOList;
	}

	@Override
	public String uploadResume(String filePath,EmployeeMasterDTO employeeMasterDTO) {
		// TODO Auto-generated method stub
		
		
		//creating persistence object
		Resume resumePOJO = new Resume();
		resumePOJO.setCreatedDate(CustomDate.getCustomDate());
		
		//upload the file 
		//String filePath=uploadToServerFolder(file);
		
		//convert file to html
		if(!filePath.isEmpty())
		{
			convertDocxToHTML(filePath);
		}
		
		//parse html
		ResumeDTO resumeDTO = readHTMLAndParseData(employeeMasterDTO);
		
		
		if(resumeDTO==null)
		{
			return CommonConstants.UPLOAD_FAILURE_STATUS + "\t" +"Invalid file";
		}
		System.out.println("File Path is:::::::::::::::::"+filePath);
		String fileName=filePath.substring(filePath.lastIndexOf("\\")+1);
		System.out.println("File Name is:::::::::::::::::"+fileName);
		String []fileNameParts =fileName.split("_");
		int empId = Integer.parseInt(fileNameParts[0]);
		System.out.println("Emp Id is:::::::::::::::::::::::"+empId);
		EmployeeMaster employeeDetail = employeeMasterDAO.findById(empId);
		if(employeeDetail==null)
		{
			return CommonConstants.UPLOAD_FAILURE_STATUS + "\t" +"User Not Registered";
		}
		resumePOJO.setEmployeeMaster(employeeDetail);
		//create the persistence object 
		Resume resume=CopyUtil.copyDTOtoPOJO(resumeDTO,resumePOJO);
		resume.setManagerApprovalStatus(ManagerSubmissionStatus.APPROVED.name());
		resume.setCreatedDate(CustomDate.getCustomDate());
		resume.setUpdatedDate(CustomDate.getCustomDate());
		EmployeeMaster employee = resume.getEmployeeMaster();
		/*EmployeeMaster employeeDTO= employeeMasterDAO.findByName(resumeDTO.getEmployeeMaster().getName()); 
		employee.setEmpId(employeeDTO.getEmpId());
		employee.setEmployeeMail(employeeDTO.getEmployeeMail());*/
		
		
		//persist object
		try
		{
			employeeMasterDAO.attachDirty(employee);
			return CommonConstants.UPLOAD_SUCCESS_STATUS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return CommonConstants.UPLOAD_FAILURE_STATUS;
			
		}
		
	}

	

    /*
     * sub methods used for uploading resume	
     */
   // upload the docx file from UI to server folder
	/*private String uploadToServerFolder(MultipartFile file) {
	
		 if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                System.out.println("fileNmae="+file.getOriginalFilename());
	                // Creating the directory to store file
	               // String rootPath = System.getProperty("catalina.home");
	                String rootPath = CommonConstants.UPLOAD_DIRECTORY;
	                File dir = new File(rootPath);
	                if (!dir.exists())
	                    dir.mkdirs();
	 
	                // Create the file on server
	                File serverFile = new File(dir.getAbsolutePath()
	                        + File.separator + file.getOriginalFilename());
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	 
	                LOG.info("Server File Location="
	                        + serverFile.getAbsolutePath());
	 
	                return serverFile.getAbsolutePath();
	            } catch (Exception e) {
	                return null;
	            }
	        } else {
	            return null;
	        }
		

		
	}*/
	
	//convert the docx file uploaded to html
	private void convertDocxToHTML(String filePath) {
			// TODO Auto-generated method stub
			try {
				Document doc = new Document(filePath);
				doc.save(CommonConstants.UPLOAD_DIRECTORY+"\\Generatedresume.html");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	//read the converted html and parse data
	
	private ResumeDTO readHTMLAndParseData(EmployeeMasterDTO employeeMasterDTO) {
			
		    
			//getting header from header xml
			Map<String,String> headerMap=obtainHeaderFromXML();
			String htmlToParse=obtainHtmlFromSource();
			System.out.println(htmlToParse);
			ResumeDTO resumeDTO=parseResumeDTOFromHTMLString(htmlToParse,headerMap,employeeMasterDTO);
			
			return resumeDTO;
		}
		
	

		/*
	     * end sub methods used for uploading resume	
	     */
	
	private Map<String, String> obtainHeaderFromXML() {
		// TODO Auto-generated method stub
	   Map<String,String> headerList= new HashMap<String,String>();
	   //Get the DOM Builder Factory
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	   //Get the DOM Builder
	   try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Load and Parse the XML document
		    //document contains the complete XML as a Tree.
			org.w3c.dom.Document document = builder.parse(new File(CommonConstants.UPLOAD_DIRECTORY+"//Headers.xml"));
		    //Iterating through the nodes and extracting the data.
		    NodeList nodeList = document.getDocumentElement().getChildNodes();
		    for (int i = 0; i < nodeList.getLength(); i++) {
		        //We have encountered an <header> tag.
		        Node node = nodeList.item(i);
		        if (node instanceof org.w3c.dom.Element) {
		        
		        	headerList.put(node.getTextContent(), node.getNodeName());
		        }
		    }
		 
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return headerList;
	}
	
	private String obtainHtmlFromSource() {
		// TODO Auto-generated method stub
	     BufferedReader br = null;
	     String htmlToParse=null;
	     try {
	            String sCurrentLine;
	            br = new BufferedReader( new FileReader(CommonConstants.UPLOAD_DIRECTORY+"\\Generatedresume.html"));
	            while ((sCurrentLine = br.readLine()) != null) {
	               htmlToParse = htmlToParse + sCurrentLine;
	            }
   	        } catch (IOException e) 
   	        {
	            e.printStackTrace();
	        }finally {
	            try {
	                if (br!= null)
	                    br.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    
		return htmlToParse;
	}


	
	private ResumeDTO parseResumeDTOFromHTMLString(String htmlToParse,Map<String, String> headerMap,EmployeeMasterDTO employeeMasterDTO) 
	   {
	// TODO Auto-generated method stub
		System.out.println(htmlToParse);
	   org.jsoup.nodes.Document doc = Jsoup.parse(htmlToParse);
	   org.jsoup.select.Elements elements = doc.body().select("*");
	   
	   ResumeDTO resumeDTO= new ResumeDTO();
	// EmployeeMasterDTO employeeMasterDTO=new EmployeeMasterDTO();
	   List<SkillDTO> skillDTOList=new ArrayList<SkillDTO>();
	   List<EducationDTO> educationDTOList= new ArrayList<EducationDTO>();
	   List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
	   DecimalFormat dec = new DecimalFormat();
	   dec.setParseBigDecimal(true);
	   SkillDTO skillDTO= null;
	   EducationDTO educationDTO = new EducationDTO();
	   ProjectDTO projectDTO = null;
	 
	   
	   
	   List<org.jsoup.nodes.Element> parts = new ArrayList<org.jsoup.nodes.Element>();
	   for (org.jsoup.nodes.Element e : elements)
	   {
	            parts.add(e);
	            
	   }
	   for(int i=0;i<parts.size();i++)
	   {
	        if(parts.get(i).tagName().equalsIgnoreCase("p"))
	        {
	        	System.out.println(parts.get(i).text());
	       
	        for (Map.Entry<String, String> entry : headerMap.entrySet())
	        {
	        	if(parts.get(i).text().contains(entry.getKey())) 
	        	{
	        		switch(entry.getValue())
	        		{
	   
	        		case "Title":
	        			//System.out.println(parts.get(i).text());
	        			employeeMasterDTO.setTitle(replaceSpecialChar(parts.get(i).text().split(":")[1]));
	        			//System.out.println(parts.get(i).text().split(":")[1]);
	        			break;
	    		
	        		case "FullName":
	        			//System.out.println(parts.get(i).text());
	        			employeeMasterDTO.setName(replaceSpecialChar(parts.get(i).text().split(":")[1]));
	        			break;
	    		
	        		case "TotalExperience":
	        			//System.out.println(parts.get(i).text());
	        			resumeDTO.setTotalExperience(replaceSpecialChar(parts.get(i).text().split(":")[1]));
	        			break;
	    		
	        		case "RelevantExperience":
	        			// System.out.println(parts.get(i).text());
	        			resumeDTO.setRelevantExperience(replaceSpecialChar(parts.get(i).text().split(":")[1]));
	        			break;
	       		
	        		case "PrimarySkills":
	    	
	        			// System.out.println(parts.get(i).text());
			    		skillDTO= new SkillDTO();
			    		skillDTO.setSkillCatagory(SkillType.PRIMARY.name());
			    		skillDTO.setCreatedDate(CustomDate.getCustomDate());
			    		skillDTO.setSkill(replaceSpecialChar(parts.get(i).text().split(":")[1]));
			    		skillDTO.setUpdatedDate(CustomDate.getCustomDate());
			    		skillDTOList.add(skillDTO);
			    		break;
			    		
	        		case "SecondarySkills":
			    		//System.out.println(parts.get(i).text());
			    		skillDTO= new SkillDTO();
			    		skillDTO.setSkillCatagory(SkillType.SECONDARY.name());
			    		skillDTO.setCreatedDate(CustomDate.getCustomDate());
			    		skillDTO.setSkill(replaceSpecialChar(parts.get(i).text().split(":")[1]));
			    		skillDTO.setUpdatedDate(CustomDate.getCustomDate());
			    		skillDTOList.add(skillDTO);
			    		break;
	    	
	        		case "ProfessionalSummary":
			    	   //System.out.println("hello");
			    	   //System.out.println(parts.get(i+2).text());
			    	   
			    	   //break;
	        			i++;
						String professionalSummary="start";
						while(!parts.get(i).text().contains("Technical Skills"))
						    {
			   			 if(parts.get(i).tagName().equalsIgnoreCase("p"))
			   			 {
			   				 if(professionalSummary.trim().equalsIgnoreCase("start"))
			   				 {
			   					professionalSummary = parts.get(i).text().trim();
			   				 }
			   				 else
			   				 {
			   					StringBuilder res = new StringBuilder();
			   					res.append(professionalSummary.toString()+"\r\n ");
			   					professionalSummary=professionalSummary.trim()+parts.get(i).text().trim();
			   				 }
							 }
							 i++;
						     }
						i--;
						System.out.println(professionalSummary);
						resumeDTO.setProfessionalSummary(replaceSpecialChar(professionalSummary));
			//code end.................... 
						
						break;

	    	   
	        	    case "ClientName":
			    	   projectDTO = new ProjectDTO();
			    	   projectDTO.setClientName(replaceSpecialChar(parts.get(i).text().split(":")[1]));
			    	   // System.out.println("ClientName="+parts.get(i).text());
			    	   break;
	    	   
	        	    case "Duration":
			    	   projectDTO.setDuration(replaceSpecialChar(parts.get(i).text().split(":")[1]));
			    	   //System.out.println("Duration="+parts.get(i).text());
			    	   break;
	    	   
	        	    case "ProjectName":
			    	   projectDTO.setProjectName(replaceSpecialChar(parts.get(i).text().split(":")[1]));
			    	   //System.out.println("ProjectName="+parts.get(i).text());
			    	   break;
	    	   
	        	    case "Role":
			    	   projectDTO.setRole(replaceSpecialChar(parts.get(i).text().split(":")[1]));
			    	   //System.out.println("Role="+parts.get(i).text());
			    	   break;
	    	   
	        	    case "ProjectBrief":
				    	//System.out.println("Case start--------------------------------------------------------------------->");
						i++;
						String brief="start";
						 while(!parts.get(i).text().contains("Responsibilities:"))
						    {
			   			 if(parts.get(i).tagName().equalsIgnoreCase("p"))
			   			 {
			   				 if(brief.trim().equalsIgnoreCase("start"))
			   				 {
			   					 brief = parts.get(i).text().trim();
			   					 //System.out.println(brief);
			   				 }
			   				 else
			   				 {
			   					brief=brief.trim()+parts.get(i).text().trim();
			   				 }
							 // System.out.println(parts.get(i).text());
			   			 }
							 i++;
						     }
						 i--;
						projectDTO.setProjectDesc(replaceSpecialChar(brief));
						//System.out.println("Case end--------------------------------------------------------------------->");
						   break;
	        	 case "Responsibilities":
			    	//-- code added by Mukunda to get all the data in responsibilites	
					i++;
					String responsibilities="start";
					while(!parts.get(i).text().contains("Environment:"))
					    {
		   			 if(parts.get(i).tagName().equalsIgnoreCase("p"))
		   			 {
		   				 if(responsibilities.trim().equalsIgnoreCase("start"))
		   				 {
		   					responsibilities = parts.get(i).text().trim();
		   				 }
		   				 else
		   				 {
		   					StringBuilder res = new StringBuilder();
		   					res.append(responsibilities.toString()+"\r\n ");
		   					responsibilities=responsibilities.trim()+parts.get(i).text().trim();
		   				 }
						 }
						 i++;
					     }
					i--;
					//System.out.println(responsibilities);
		//code end.................... 
					projectDTO.setResponsibility(replaceSpecialChar(responsibilities));
					break;

	        	 case "Environment":
			    	//System.out.println("Environment="+parts.get(i).text());
			    	projectDTO.setEnvironment(parts.get(i).text().split(":")[1]);
			    	projectDTO.setCreatedDate(CustomDate.getCustomDate());
			    	projectDTO.setUpdatedDate(CustomDate.getCustomDate());
			    	projectDTOList.add(projectDTO);
			    	break;
			   
			     case "TechnicalSkills":
			    	String html =parts.get(i+2).html();
			    	org.jsoup.nodes.Document docTable = Jsoup.parse(html);
			    	org.jsoup.select.Elements elementsByTag = docTable.select("table");
			    	for (org.jsoup.nodes.Element e : elementsByTag)
			    	{
			   
			    		org.jsoup.select.Elements rows = e.getElementsByTag("tr");
			    		for(org.jsoup.nodes.Element row : rows) {
			    		String Test = row.getElementsByTag("td").get(0).text();
			    		String Result = row.getElementsByTag("td").get(1).text();
			    		skillDTO= new SkillDTO();
			    		skillDTO.setSkillType(Test);
			    		skillDTO.setCreatedDate(CustomDate.getCustomDate());
			    		skillDTO.setUpdatedDate(CustomDate.getCustomDate());
			    		skillDTO.setSkillCatagory(SkillType.TECHNICAL.name());
			    		skillDTO.setSkill(Result);
			    		skillDTOList.add(skillDTO);
			        //   System.out.print(Test+"\t");
			        //   System.out.println(Result);
			           
			    		}
			    	}
			    	break;
	    
			    case "Education":
			    	
			    	System.out.println("Education Details Start");
					
					//-- code added by Mukunda to get all the data in education	
					i++;
					for(int j=i; j<parts.size();j++)
					{
						if(parts.get(j).tagName().equalsIgnoreCase("p") )
						{
							if(!replaceSpecialChar(parts.get(j).text()).trim().isEmpty())
							{	
								//System.out.println(parts.get(j).text().trim());
								educationDTO = new EducationDTO();
								educationDTO.setDescription(replaceSpecialChar(parts.get(j).text()).trim());
								educationDTOList.add(educationDTO);
								//System.out.println(educationDTOList);
							}
							
							//System.out.println(educationDTOList);
						}
						
					}
					
		//code end....................
					/*educationDTO.setDescription(replaceSpecialChar(parts.get(i+2).text()));
					educationDTOList.add(educationDTO);*/
			    	
	        		}//END OF SWITCH
	        	}//END OF CONTAINS KEY
	        }//END OF FOR HEADER MAP
	     }//END OF IF PARAGRAPH
	}//END OF FOR EACH PARTS

	 
	   if(employeeMasterDTO==null || skillDTOList.size()==0||projectDTOList.size()==0)
	   {
		   resumeDTO=null;
	   }
	   else
	   {
		resumeDTO.setEmployeeMaster(employeeMasterDTO);
		resumeDTO.setSkills(skillDTOList);
		resumeDTO.setProjects(projectDTOList);
		resumeDTO.setEducations(educationDTOList);
		resumeDTO.setEmployeeSumbissionStatus(EmployeeSubmissionStatus.SUBMITTED.name());
	   }
		return resumeDTO;
	}

//--- Added by Mukunda to replace special characters in word document parsing
private String replaceSpecialChar(String strReplace) {

//	strReplace = strReplace.replaceAll("\n"," ");
	strReplace = strReplace.replaceAll("[^\\w\\s\\-_()?:!.,;&]", "");
	return strReplace;
}
//--------------------------------------------------------------------------

@Override
public String exportResume(int resumeid, HttpServletRequest request, HttpServletResponse response) 
{
	System.out.println("Pdf Conversion for resume started");
	ResumeDTO resumeDTO = new ResumeDTO();
	//EmployeeViewServiceImpl exportResume = new EmployeeViewServiceImpl();
	resumeDTO = employeeViewService.getResume(resumeid);
	System.out.println("PDF conversion resumeDTO for resume id :  "+resumeDTO.getResumeid());
	if(resumeDTO == null)
	{
		return CommonConstants.Export_FAILURE_STATUS + "\t" +"Resume not available";
	}
	try
	{
		System.out.println("PDF Conversion for resume");
		pdfConversion convertToPdf = new pdfConversion();
		convertToPdf.convert(resumeDTO,request,response);
		return CommonConstants.Export_SUCCESS_STATUS;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return CommonConstants.UPLOAD_FAILURE_STATUS;
		
	}
	
}



/*
 * testing
 */

/*public static void main(String[] args)
{
	Resume resumePOJO = new Resume();
	ARMGServiceImpl impl = new ARMGServiceImpl();
	//uploadToServerFolder(uploadDTO);
	impl.convertDocxToHTML();
	ResumeDTO resumeDTO = impl.readHTMLAndParseData();
	Resume resume=CopyUtil.copyDTOtoPOJO(resumeDTO,resumePOJO);
	
	
	
	
}*/


/*
 * end of testing
 */

//added by sampad
@Override
public  String insertEmployeeMaster(List<EmployeeMaster> employeeMasterList){
	System.out.println("entering insertEmployeeMaster");
  String status = CommonConstants.UPLOAD_SUCCESS_STATUS;
  
  try
  {
  	for (EmployeeMaster employeeMaster : employeeMasterList) {
  		employeeMaster.setCreatedDate(CustomDate.getCustomDate());
      	employeeMaster.setUpdatedDate(CustomDate.getCustomDate());
      	if(employeeMaster.getEmpId()!=null)
      	{
      		//check id of employee is already in DB
      		EmployeeMaster employeeMasterInDB =  employeeMasterDAO.findById(employeeMaster.getEmpId());
      		if(employeeMasterInDB==null)
      		{
      			employeeMasterDAO.persist(employeeMaster);
      		}
      		else
      		{
      			employeeMasterInDB.setEmployeeMail(employeeMaster.getEmployeeMail());
      			employeeMasterInDB.setCurrentProject(employeeMaster.getCurrentProject());
      			employeeMasterInDB.setGroup(employeeMaster.getGroup());
      			employeeMasterInDB.setManagerMail(employeeMaster.getManagerMail());
      			employeeMasterInDB.setName(employeeMaster.getName());
      			employeeMasterInDB.setReportingManager(employeeMaster.getReportingManager());
      			employeeMasterInDB.setTitle(employeeMaster.getTitle());
      			employeeMasterInDB.setUpdatedDate(CustomDate.getCustomDate());
      			employeeMasterDAO.attachDirty(employeeMasterInDB);
      		}
      	}
      	else
      	{
      		break;
      	}
      	
			
		}
  	
  }
  catch(Exception e )
  {
  	status=CommonConstants.UPLOAD_FAILURE_STATUS;
  	e.printStackTrace();
  }
  return status;

}



}
