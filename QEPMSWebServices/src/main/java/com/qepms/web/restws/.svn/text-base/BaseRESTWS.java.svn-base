package com.qepms.web.restws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qepms.infra.exception.QEPMSApplicationException;
import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;

public abstract class BaseRESTWS {

	private Logger LOG = LoggerFactory.getLogger(BaseRESTWS.class);

	@ExceptionHandler(QEPMSApplicationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseMessageWrapper handleException(Exception exception) {
		LOG.error(exception.getMessage());
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		responseMessageWrapper.setResponseMessage(exception.getClass()
				.getName() + ": " + exception.getMessage());
		return responseMessageWrapper;
	}
}
