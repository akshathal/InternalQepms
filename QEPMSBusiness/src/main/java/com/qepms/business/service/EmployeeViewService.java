package com.qepms.business.service;

import java.util.List;

import com.qepms.infra.dto.armg.ResumeDTO;

public interface EmployeeViewService {
	

	public List<ResumeDTO> getResume(Integer empId, String managerApprovalStatus, String employeeSumbissionStatus);
	public ResumeDTO getSubmitResume(Integer empId);
	public ResumeDTO getEditResume(Integer empId);
	public List<String> getSkillTypeNames();
	public String submitResume(ResumeDTO resumeDTO);
	
	
	/*
	 * for viewing each resume
	 */
	public ResumeDTO getResume(int resumeid);
	public ResumeDTO getDraftResume(Integer empId,
			String managerApprovalStatus, String employeeSumbissionStatus);

	
	
	
}
