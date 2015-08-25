package com.qepms.common;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xhtmlrenderer.pdf.ITextRenderer;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.lowagie.text.*;//DocumentException;
import com.lowagie.*;
import com.qepms.infra.constants.CommonConstants;
import com.qepms.infra.dto.armg.ResumeDTO;

import freemarker.template.Configuration;   
import freemarker.template.Template;   
import freemarker.template.TemplateException; 
import com.qepms.infra.dto.armg.SkillDTO;
import com.qepms.infra.dto.armg.ProjectDTO;
import com.qepms.infra.dto.armg.EducationDTO;

public class pdfConversion {
	public int topValue = 10;  
    public int leftValue = 20;  
    public int rightValue = 10;  
    public int bottomValue = 10;  
    public int userSpaceWidth = 1300;
    private static final int BUFFER_SIZE = 4096;
    
    		
	public void convert(ResumeDTO resume, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Configuration cfg = new Configuration(); 
		OutputStream os = null;
		cfg.setDirectoryForTemplateLoading(new File(CommonConstants.UPLOAD_DIRECTORY));
		Template template = cfg.getTemplate("resume.ftl");
		String htmlName = CommonConstants.UPLOAD_DIRECTORY+"//"+resume.getEmployeeMaster().getEmpId()+"_"+resume.getEmployeeMaster().getName()+".html";
		String pdfName = CommonConstants.UPLOAD_DIRECTORY+"//"+resume.getEmployeeMaster().getEmpId()+"_"+resume.getEmployeeMaster().getName()+".pdf";
		   System.out.println(htmlName);
		   System.out.println(pdfName);
		   //System.out.println(CommonConstants.UPLOAD_DIRECTORY+"//"+htmlName);
		   Map<String, Object> resumeMap = new HashMap<String, Object>();
		   List<SkillDTO> skillList = resume.getSkills();
		   List<ProjectDTO> projectList = resume.getProjects();
		   List<EducationDTO> educationList =  resume.getEducations();
		   resumeMap.put("skillList", skillList);
		   resumeMap.put("projectList", projectList);
		   resumeMap.put("educationList", educationList);
		   resumeMap.put("resume",resume);
		   
	       Writer write = new FileWriter(new File(htmlName));   
	        try {
	        	
	        	template.process(resumeMap, write);
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createpdf(htmlName,pdfName,request,response);
			write.close();
	}
			 public void createpdf(String htmlName, String pdfName, HttpServletRequest request, HttpServletResponse response) {  
			        try {  
			            pdfConversion jt = new pdfConversion();  
			            String html = readFile(htmlName, "UTF-8");  
			            jt.doConversion2(html,pdfName);
			            //doDownload(pdfName,request,response);
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
			    }  
			  
			    public void doConversion2( String html, String pdfName)   
			                throws InvalidParameterException, MalformedURLException, IOException {  
			  
			    	PD4ML pd4ml = new PD4ML();  
		              
			        pd4ml.setHtmlWidth(userSpaceWidth); // set frame width of "virtual web browser"   
			              
			        // choose target paper format  
			        pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));   
			              
			        // define PDF page margins  
			        pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));   
			  
			        // source HTML document also may have margins, could be suppressed this way   
			        // (PD4ML *Pro* feature):  
			        pd4ml.addStyle("BODY {margin: 0}", true);  
			              
			        // If built-in basic PDF fonts are not sufficient or   
			        // if you need to output non-Latin texts, TTF embedding feature should help   
			        // (PD4ML *Pro*)  
			        pd4ml.useTTF("c:/windows/fonts", true);  
			  
			        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			        // actual document conversion from HTML string to byte array  
			        pd4ml.render(new StringReader(html), baos);   
			        // if the HTML has relative references to images etc,   
			        // use render() method with baseDirectory parameter instead  
			        baos.close();  
			          
			        System.out.println( "resulting PDF size: " + baos.size() + " bytes" );  
			        // in Web scenarios it is a good idea to send the size with   
			        // "Content-length" HTTP header  
			  
			        File output = new File(pdfName);  
			        java.io.FileOutputStream fos = new java.io.FileOutputStream(output);  
			        fos.write( baos.toByteArray() );  
			        fos.close();  
			        baos.close();  
			        System.out.println( pdfName + "\nResume is ready." );  
			    }  
			      
			    private final static String readFile( String path, String encoding ) throws IOException {  
			  
			        File f = new File( path );  
			        FileInputStream is = new FileInputStream(f);  
			        BufferedInputStream bis = new BufferedInputStream(is);  
			          
			        ByteArrayOutputStream fos = new ByteArrayOutputStream();  
			        byte buffer[] = new byte[2048];  
			  
			        int read;  
			        do {  
			            read = is.read(buffer, 0, buffer.length);  
			            if (read > 0) {   
			                fos.write(buffer, 0, read);   
			            }  
			        } while (read > -1);  
			  
			        fos.close();  
			        bis.close();  
			        is.close();  
			  
			        return fos.toString(encoding);  
			    }
			
			    
			    
	//code added by Mukunda for download functionality
			    
/*public void doDownload(String filePath, HttpServletRequest request,HttpServletResponse response) throws IOException {

					// get absolute path of the application
					ServletContext context = request.getServletContext();
					String appPath = context.getRealPath("");
					System.out.println("appPath = " + appPath);

					// construct the complete absolute path of the file
					System.out.println(filePath);					
					File downloadFile = new File(filePath);
					FileInputStream inputStream = new FileInputStream(downloadFile);
					
					// get MIME type of the file
					String mimeType = context.getMimeType(filePath);
					if (mimeType == null) {
						// set to binary type if MIME mapping not found
						mimeType = "application/octet-stream";
					}
					System.out.println("MIME type: " + mimeType);

					// set content attributes for the response
					response.setContentType(mimeType);
					response.setContentLength((int) downloadFile.length());

					// set headers for the response
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"",
							downloadFile.getName());
					response.setHeader(headerKey, headerValue);

					// get output stream of the response
					OutputStream outStream = response.getOutputStream();

					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;

					// write bytes read from the input stream into the output stream
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}

					inputStream.close();
					outStream.close();

				}
*/

	
}


			    