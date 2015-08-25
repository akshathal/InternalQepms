package com.qepms.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;


public class RESTUtil {

	private static final Logger LOG = LoggerFactory.getLogger(RESTUtil.class);

	public static String efmsWSBaseUrl;
	
	public static String efmsAppBaseUrl;

	private RestTemplate restTemplate;

	public static void setEfmsAppBaseUrl(String efmsAppBaseUrl) {
		RESTUtil.efmsAppBaseUrl = efmsAppBaseUrl;
	}
	
	public void setefmsWSBaseUrl(String efmsWSBaseURL) {
		RESTUtil.efmsWSBaseUrl = efmsWSBaseURL;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Object getData(String contextPath, Class<?> clazz) throws Exception {

		String efmsWSURL;

		efmsWSURL = efmsWSBaseUrl + contextPath;

		LOG.debug("efmsWSURL: " + efmsWSURL);

		Object response;

		response = restTemplate.getForObject(efmsWSURL, clazz);

		return response;
	}

	public ResponseMessageWrapper putData(Object putRequest, String contextPath)
			throws Exception {

		String efmsURL;
		String responseData = "";

		efmsURL = efmsWSBaseUrl + contextPath;

		LOG.debug("efmsURL: " + efmsURL);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(putRequest,
				headers);
		ResponseEntity<ResponseMessageWrapper> responseEntity = restTemplate
				.exchange(efmsURL, HttpMethod.PUT, requestEntity,
						ResponseMessageWrapper.class);

		ResponseMessageWrapper responseMessageWrapper = responseEntity
				.getBody();

		responseData = responseMessageWrapper.getResponseMessage();

		LOG.debug(responseData);

		return responseMessageWrapper;
	}

	public ResponseMessageWrapper postData(Object postRequest, String contextPath)
			throws Exception {

		String efmsURL;

		String responseData = "";

		efmsURL = efmsWSBaseUrl + contextPath;

		LOG.debug("efmsURL: " + efmsURL);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(postRequest,
				headers);
		ResponseEntity<ResponseMessageWrapper> responseEntity = restTemplate
				.exchange(efmsURL, HttpMethod.POST, requestEntity,
						ResponseMessageWrapper.class);

		ResponseMessageWrapper responseMessageWrapper = responseEntity
				.getBody();

		responseData = responseMessageWrapper.getResponseMessage();

		LOG.debug(responseData);

		return responseMessageWrapper;
	}
}
