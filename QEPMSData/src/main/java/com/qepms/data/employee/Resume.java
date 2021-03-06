package com.qepms.data.employee;
// default package
// Generated Mar 13, 2014 3:13:19 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Resume generated by hbm2java
 */
public class Resume implements java.io.Serializable {

	@Override
	public String toString() {
		return "Resume [resumeid=" + resumeid + ", employeeMaster="
				+ employeeMaster + ", totalExperience=" + totalExperience
				+ ", relevantExperience=" + relevantExperience
				+ ", professionalSummary=" + professionalSummary
				+ ", employeeSumbissionStatus=" + employeeSumbissionStatus
				+ ", managerApprovalStatus=" + managerApprovalStatus
				+ ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", comments=" + comments + ", projects="
				+ projects + ", educations=" + educations + ", skills="
				+ skills + "]";
	}

	private Integer resumeid;
	private EmployeeMaster employeeMaster;
	private String totalExperience;
	private String relevantExperience;
	private String professionalSummary;
	private String employeeSumbissionStatus;
	private String managerApprovalStatus;
	private Date createdDate;
	private Date updatedDate;
	private String comments;
	private Set projects = new HashSet(0);
	private Set educations = new HashSet(0);
		private Set skills=new HashSet(0);

	public Resume() {
	}

	public Resume(Date createdDate, Date updatedDate) {
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Resume(Integer resumeid, EmployeeMaster employeeMaster,
			String totalExperience, String relevantExperience,
			String professionalSummary, String employeeSumbissionStatus,
			String managerApprovalStatus, Date createdDate, Date updatedDate,
			Set projects, Set educations, Set skills) {
		super();
		this.resumeid = resumeid;
		this.employeeMaster = employeeMaster;
		this.setTotalExperience(totalExperience);
		this.setRelevantExperience(relevantExperience);
		this.professionalSummary = professionalSummary;
		this.employeeSumbissionStatus = employeeSumbissionStatus;
		this.managerApprovalStatus = managerApprovalStatus;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.projects = projects;
		this.educations = educations;
		this.skills = skills;
	}



	public Integer getResumeid() {
		return this.resumeid;
	}

	public void setResumeid(Integer resumeid) {
		this.resumeid = resumeid;
	}

	public EmployeeMaster getEmployeeMaster() {
		return this.employeeMaster;
	}

	public void setEmployeeMaster(EmployeeMaster employeeMaster) {
		this.employeeMaster = employeeMaster;
	}

	
	public String getProfessionalSummary() {
		return this.professionalSummary;
	}

	public void setProfessionalSummary(String professionalSummary) {
		this.professionalSummary = professionalSummary;
	}

	public String getEmployeeSumbissionStatus() {
		return this.employeeSumbissionStatus;
	}

	public void setEmployeeSumbissionStatus(String employeeSumbissionStatus) {
		this.employeeSumbissionStatus = employeeSumbissionStatus;
	}

	public String getManagerApprovalStatus() {
		return this.managerApprovalStatus;
	}

	public void setManagerApprovalStatus(String managerApprovalStatus) {
		this.managerApprovalStatus = managerApprovalStatus;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set getProjects() {
		return this.projects;
	}

	public void setProjects(Set projects) {
		this.projects = projects;
	}

	public Set getEducations() {
		return this.educations;
	}

	public void setEducations(Set educations) {
		this.educations = educations;
	}

	public Set getSkills() {
		return skills;
	}

	public void setSkills(Set skills) {
		this.skills = skills;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(String relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

}
