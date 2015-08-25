/**
 * 
 */
package com.qepms.business.service;

import java.util.List;

import com.qepms.infra.dto.armg.ResumeDTO;

/**
 * @author AshwiniK
 *
 *
 * This class gives the definitions for the actions perofrmed by Manager
 * 
 */
public interface ManagerService {

	// To list the resumes
	
	public List<ResumeDTO> getListOfResumes(String managerApprovalStatus, String reportingManager);

	public ResumeDTO getResume(int empId);

	public String postCommets(int resumeid, String comments);

	public String acceptResume(int resumeid,String comments);

	public List<ResumeDTO> getListOfResumesBasedOnManagerEmail(
			String managerApprovalStatus, String reportingManageremail);

	

}
