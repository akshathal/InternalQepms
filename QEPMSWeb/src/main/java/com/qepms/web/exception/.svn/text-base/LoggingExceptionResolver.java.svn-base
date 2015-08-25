package com.qepms.web.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class LoggingExceptionResolver extends SimpleMappingExceptionResolver {
	private Logger logger = LoggerFactory
			.getLogger(LoggingExceptionResolver.class);

	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		this.logger.warn(buildLogMessage(ex, request), ex);
		this.logger.debug(buildLogMessage(ex, request), ex);
	}

}
