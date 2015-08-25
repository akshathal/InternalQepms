/**
 * 
 */
package com.qepms.web.restws;

/**
 * @author AshwiniK
 *
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qepms.business.service.ManagerService;
import com.qepms.infra.dto.armg.ResumeDTO;
import com.qepms.infra.exception.QEPMSApplicationException;
import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;

@Controller
public class ManagerRESTWS extends BaseRESTWS {

	private static final Logger LOG = LoggerFactory
			.getLogger(ManagerRESTWS.class);

	@Autowired
	private ManagerService managerService;

	// To get the list of Employees to be displayed in allresumes page

	@RequestMapping(value = "/manager/listofresumes", method = RequestMethod.GET)
	public @ResponseBody
	List<ResumeDTO> getListOfResumes(
			@RequestParam(value = "managerApprovalStatus", required = false) final String managerApprovalStatus,
			@RequestParam(value = "reportingManager", required = false) final String reportingManager)

	{

		List<ResumeDTO> resumeDTOList = null;

		LOG.debug("getListOfResumes() called with managerApprovalStatus "
				+ managerApprovalStatus + " and reportingManager "
				+ reportingManager);

		resumeDTOList = managerService.getListOfResumes(managerApprovalStatus,
				reportingManager);

		return resumeDTOList;
	}

	// To get the list of Employees to be displayed in allresumes page based on
	// manager email

	@RequestMapping(value = "/manager/listofresumesbasedonemail", method = RequestMethod.GET)
	public @ResponseBody
	List<ResumeDTO> getListOfResumesBasedOnManagerEmail(
			@RequestParam(value = "managerApprovalStatus", required = false) final String managerApprovalStatus,
			@RequestParam(value = "reportingManageremail", required = false) final String reportingManageremail)

	{

		List<ResumeDTO> resumeDTOList = null;

		LOG.debug("getListOfResumes() called with managerApprovalStatus "
				+ managerApprovalStatus + " and reportingManageremail "
				+ reportingManageremail);

		resumeDTOList = managerService.getListOfResumesBasedOnManagerEmail(managerApprovalStatus,
				reportingManageremail);

		return resumeDTOList;
	}

	// To get the single resume detail of each resume displayed
	@RequestMapping(value = "/manager/viewresume", method = RequestMethod.GET)
	public @ResponseBody
	ResumeDTO getResume(
			@RequestParam(value = "empId", required = false) final int empId) {
		ResumeDTO resumeDTO = null;
		LOG.debug("getResume() called with empId " + empId);
		resumeDTO = managerService.getResume(empId);
		return resumeDTO;
	}

	// To post the comments into database when the manager rejects the resume
	@RequestMapping(value = "/manager/rejectresume", method = RequestMethod.GET)
	public @ResponseBody
	ResponseMessageWrapper postComment(
			@RequestParam(value = "resumeid", required = false) final Integer resumeid,
			final String comments) {
		String responseMessage = null;
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		LOG.debug("postComment() called");
		responseMessage = managerService.postCommets(resumeid, comments);
		responseMessageWrapper.setResponseMessage(responseMessage);
		return responseMessageWrapper;
	}

	// To accept the resume
	@RequestMapping(value = "/manager/acceptresume", method = RequestMethod.GET)
	public @ResponseBody
	ResponseMessageWrapper acceptResume(
			@RequestParam(value = "resumeid", required = false) final Integer resumeid,
			final String comments) {
		String responseMessage = null;
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		LOG.debug("acceptResume() called");
		responseMessage = managerService.acceptResume(resumeid, comments);
		responseMessageWrapper.setResponseMessage(responseMessage);
		return responseMessageWrapper;
	}

}
