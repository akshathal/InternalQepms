package com.qepms.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qepms.infra.dto.armg.ResumeDTO;
import com.qepms.web.constants.QEPMSWebConstants;
import com.qepms.web.util.RESTUtil;
import javax.validation.Valid;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;

@Controller
public class EmployeeController {
	
		@Autowired
		RESTUtil restUtil;

		private Validator validator;

		public Validator getValidator() {
			return validator;
		}

		@Autowired
		public void setValidator(Validator validator) {
			this.validator = validator;
		}
	
	
	private static final Logger LOG = LoggerFactory
			.getLogger(EmployeeController.class);
	
	@RequestMapping(value = "/employee/landingpage", method = RequestMethod.GET)
	public String showEmployeeLandingPage() {
		LOG.debug("showEmployeeLandingPage() called");
		return QEPMSWebConstants.Employee.LANDING_PAGE_PATH;
	}
	
	
	@RequestMapping(value = "/employee/submittedresume", method = RequestMethod.GET)
	public String showEmployeeSubmittedResume() {
		LOG.debug("showEmployeeSubmittedResume() called");
		return QEPMSWebConstants.Employee.SUBMITTEDRESUME_PAGE_PATH;
	}
	
	
	
	@RequestMapping(value = "/employee/draftresume", method = RequestMethod.GET)
	public String showEmployeeDraftResume() {
		LOG.debug("showEmployeeDraftResume() called");
		return QEPMSWebConstants.Employee.DRAFTRESUME_PAGE_PATH;
	}
	
	@RequestMapping(value = "/employee/rejectedresume", method = RequestMethod.GET)
	public String showEmployeeRejectedResume() {
		LOG.debug("showEmployeeRejectedResume() called");
		return QEPMSWebConstants.Employee.REJECTEDRESUME_PAGE_PATH;
	}
	
	

	@RequestMapping(value = "/employee/submitresume", method = RequestMethod.GET)
	public String showEmployeeSubmitResume() {
		LOG.debug("showEmployeeSubmitResume() called");
		return QEPMSWebConstants.Employee.SUBMITRESUME_PAGE_PATH;
	}
	
	@RequestMapping(value = "/employee/viewresume", method = RequestMethod.GET)
	public String showEmployeeViewResume() {
		LOG.debug("showEmployeeViewResume() called");
		return QEPMSWebConstants.Employee.VIEWRESUME_PAGE_PATH;
	}
	
	@RequestMapping(value = "/employee/editresume", method = RequestMethod.GET)
	public String showEmployeeEditResume() {
		LOG.debug("showEmployeeEditResume() called");
		return QEPMSWebConstants.Employee.EDITRESUME_PAGE_PATH;
	}
	
	

	@RequestMapping(value = "/common/addskillpopup", method = RequestMethod.GET)
	public String addskillpopup() {
		LOG.debug("addskillpopup() called");
		return QEPMSWebConstants.Employee.ADD_SKILL_POPUP_PAGE_PATH;
	}
	
	
	
	//submitting the resume process
	
	@RequestMapping(value = "/employee/submitsresume", method = RequestMethod.POST)
	public ModelAndView submitResume(
			@ModelAttribute @Valid ResumeDTO resumeDTO,
			BindingResult result) {
		LOG.debug("submitResume() called from controller");

		ResponseMessageWrapper responseMessageWrapper = null;
		try {
			responseMessageWrapper = restUtil.putData(resumeDTO,
					QEPMSWebConstants.Employee.SUBMITRESUME_MSTR_WS_PATH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView(
				QEPMSWebConstants.Employee.SUBMITRESUME_PAGE_PATH,
				"responseMessageWrapper", responseMessageWrapper);
	}
	
}
