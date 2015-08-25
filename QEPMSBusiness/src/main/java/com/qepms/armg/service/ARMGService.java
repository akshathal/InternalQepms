package com.qepms.armg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qepms.data.employee.EmployeeMaster;
import com.qepms.infra.dto.armg.EmployeeMasterDTO;
import com.qepms.infra.dto.armg.ResumeDTO;

public interface ARMGService {

	public List<ResumeDTO> getResumeList();
	public String uploadResume(String file, EmployeeMasterDTO employeeMasterDTO);
	public String exportResume(int resumeid,HttpServletRequest request,HttpServletResponse response);
	public  String insertEmployeeMaster(List<EmployeeMaster> employeeMasterList);
	

}
