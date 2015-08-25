package com.qepms.infra.dto.login;
public class UserDTO {
	
	private String userName;
	private String password;
	private String employeEmail;
	private String employeeManager;
	private String employeeName;
	private String role;
	private int empId;
	private String jobTitle;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeEmail() {
		return employeEmail;
	}
	public void setEmployeEmail(String employeEmail) {
		this.employeEmail = employeEmail;
	}
	public String getEmployeeManager() {
		return employeeManager;
	}
	public void setEmployeeManager(String employeeManager) {
		this.employeeManager = employeeManager;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public UserDTO(String userName, String password, String employeEmail,
			String employeeManager, String employeeName) {
		super();
		this.userName = userName;
		this.password = password;
		this.employeEmail = employeEmail;
		this.employeeManager = employeeManager;
		this.employeeName = employeeName;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

	
}
