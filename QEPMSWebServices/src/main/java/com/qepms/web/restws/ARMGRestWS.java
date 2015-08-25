package com.qepms.web.restws;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qepms.armg.service.ARMGService;
import com.qepms.data.employee.EmployeeMaster;
import com.qepms.infra.dto.armg.EmployeeMasterDTO;
import com.qepms.infra.dto.armg.EmployeeMasterWrapperDTO;
import com.qepms.infra.dto.armg.ResumeDTO;
import com.qepms.infra.dto.armg.UploadDTO;
import com.qepms.infra.exception.QEPMSApplicationException;
import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;


@Controller
public class ARMGRestWS {
	
	@Autowired 
	private ARMGService armgService;
	
	private static final Logger LOG = LoggerFactory.getLogger(ARMGRestWS.class);
	
	
	//getting all the resume to armg which is been uploaded
	@RequestMapping(value = "/armg/resume", method = RequestMethod.GET)
	public @ResponseBody List<ResumeDTO> getResumeList() {
		LOG.debug("authenticate() called from restWS ");
		 List<ResumeDTO> resumeDTOList=null;
		 
		try {
			resumeDTOList = armgService.getResumeList();
		
		    } catch (Exception e) {
			throw new QEPMSApplicationException(
					"Error while trying to authenticate user Error details: " + e.getMessage());
		  }
		return resumeDTOList;
	}
	/*
	 * upload file
	 */
	@RequestMapping(value = "/armg/upload",method = RequestMethod.POST)
	public @ResponseBody ResponseMessageWrapper submitResume(@RequestBody UploadDTO UploadDTO)
	{
		String status=null;
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		EmployeeMasterDTO employeeMasterDTO=UploadDTO.getEmployeeMasterDTO();
		String file=UploadDTO.getFilePath();
		LOG.debug("submitResume() called ");

		try {
		
			status = armgService.uploadResume(file,employeeMasterDTO);
			responseMessageWrapper.setResponseMessage(status);
			
		} catch (Exception e) {
			throw new QEPMSApplicationException(
					"Error while trying to upload resume Error details: "+
					" | Error details: "  + e.getMessage());
		}

		return responseMessageWrapper;
	}
	
	
	//------------ Code added by Mukunda for armg export functionality------
	@RequestMapping(value = "/armg/exportresume",method = RequestMethod.GET)
	public @ResponseBody ResponseMessageWrapper exportResume(@RequestParam(value = "resumeid", required = false) final Integer resumeid, HttpServletRequest request, HttpServletResponse response)
	{
		ResumeDTO resumeDTO = new ResumeDTO();
		String status=null;
		System.out.println("ARMG Export Resume");
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		LOG.debug("exportResume() called ");

		try {
			status = armgService.exportResume(resumeid,request,response);
			responseMessageWrapper.setResponseMessage(status);
			
		} catch (Exception e) {
			throw new QEPMSApplicationException(
					"Error while trying to export resume Error details: "+
					" | Error details: "  + e.getMessage());
		}
		return responseMessageWrapper;

//--------Code end----------------------------
		
	}
	@RequestMapping(value = "/armg/uploademployeedetails",method = RequestMethod.POST)
	public @ResponseBody ResponseMessageWrapper submitEployeeDetails(@RequestBody  EmployeeMasterWrapperDTO employMasterWrapper)
	{
		String status=null;
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		LOG.debug("submitResume() called ");

		try {
            List<EmployeeMaster> employeeMasterList = employMasterWrapper.getEmployeeMasterList();
			status = armgService.insertEmployeeMaster(employeeMasterList);
			responseMessageWrapper.setResponseMessage(status);

		} catch (Exception e) {
			throw new QEPMSApplicationException(

					"Error while trying to upload resume Error details: "+
					" | Error details: "  + e.getMessage());
		}

		return responseMessageWrapper;
	}

}
