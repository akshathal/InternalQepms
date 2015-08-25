package com.qepms.infra.dto.armg;

import java.util.ArrayList;
import java.util.List;

import com.qepms.data.employee.EmployeeMaster;

public class EmployeeMasterWrapperDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<EmployeeMaster> employeeMasterList = new ArrayList<EmployeeMaster>();

	public List<EmployeeMaster> getEmployeeMasterList() {
		return employeeMasterList;
	}

	public void setEmployeeMasterList(List<EmployeeMaster> employeeMasterList) {
		this.employeeMasterList = employeeMasterList;
	}

}
