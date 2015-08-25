package com.qepms.web.restws;



import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qepms.business.service.LoginService;
import com.qepms.infra.constants.CommonConstants;
import com.qepms.infra.dto.login.UserDTO;
import com.qepms.infra.exception.QEPMSApplicationException;
import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;


@Controller
public class LoginRestWS {
	
	@Autowired 
	private LoginService loginService;
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginRestWS.class);
	
	
	/*
	 * authenticate the userdetails and return the status
	 */
	@RequestMapping(value = "/login/userdetails", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseMessageWrapper authenticate(@RequestBody UserDTO userDTO) {
		UserDTO returnedUserDTO = null;
		LOG.debug("authenticate() called from restWS ");
		ResponseMessageWrapper responseWrapper = new ResponseMessageWrapper();
		try {
			returnedUserDTO = loginService.authenticate(userDTO.getUserName(),userDTO.getPassword());
			if(returnedUserDTO==null)
			{
				responseWrapper.setResponseMessage(CommonConstants.LOGIN_FAILURE_STATUS);
			}
			else if(returnedUserDTO.getRole()==null)
			{
				responseWrapper.setResponseMessage(CommonConstants.LOGIN_CUSTOM_MESSAGE);
			}
			else
			{
				responseWrapper.setResponseMessage(CommonConstants.LOGIN_SUCCESS_STATUS);
			}
		    } catch (Exception e) {
			throw new QEPMSApplicationException(
					"Error while trying to authenticate user Error details: " + e.getMessage());
		  }
		return responseWrapper;
	}
	@RequestMapping(value = "/login/userdetails", method = RequestMethod.GET)
	public @ResponseBody UserDTO getUserDetails(
			@RequestParam(value = "userName", required = false) final String userName,
			@RequestParam(value = "password", required = false) final String password
			) {
		UserDTO returnedUserDTO = null;
		LOG.debug("authenticate() called from restWS ");
		
		try {
			returnedUserDTO = loginService.authenticate(userName,password);
			
		    } catch (Exception e) {
			throw new QEPMSApplicationException(
					"Error while trying to authenticate user Error details: " + e.getMessage());
		  }
		return returnedUserDTO;
	}

}
