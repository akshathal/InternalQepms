package com.qepms.infra.constants;



public interface CommonConstants {
	
	public static final String LOGIN_SUCCESS_STATUS = "success";
	public static final String LOGIN_FAILURE_STATUS = "failure";
	public static final String LOGIN_CUSTOM_MESSAGE="User is not registered in QEPMS Please contact ARMG Team";
	public static final String LOGIN_FAILURE_ERROR="Username/Password is invalid";
	public static final String UPLOAD_DIRECTORY = "/Upload";
	public static final String UPLOAD_SUCCESS_STATUS = "Successfully Uploaded";
	public static final String UPLOAD_FAILURE_STATUS = "Upload Failed";
	public static final String Export_SUCCESS_STATUS = "Successfully Exported Click Ok to download the pdf";
	public static final String Export_FAILURE_STATUS = "Export Failed";
	public static final int THRESHOLD_SIZE 	= 1024 * 1024 * 3; 	// 3MB
	public static final int MAX_FILE_SIZE 		= 1024 * 1024 * 40; // 40MB
	public static final int MAX_REQUEST_SIZE 	= 1024 * 1024 * 50; // 50MB
	public static final String RESUME_SUBMISSION_FAILURE_STATUS = "Some Problem In Submitting Resume.Please try again.";
	public static final String RESUME_SUBMISSION_SUCCESS_STATUS = "Resume Submitted Successfully!";
	

}
