package com.qepms.data.employee;
// default package
// Generated Mar 13, 2014 3:13:19 PM by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Skill generated by hbm2java
 */
public class Skill implements java.io.Serializable {

	private Integer skillId;
	private String skillType;
	private String skillCatagory;
	private String skill;
	private Resume resume;
	public Skill(Integer skillId, String skillType, String skillCatagory,
			String skill, Resume resume, Date createdDate, Date updatedDate) {
		super();
		this.skillId = skillId;
		this.skillType = skillType;
		this.skillCatagory = skillCatagory;
		this.skill = skill;
		this.resume = resume;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	private Date createdDate;
	private Date updatedDate;

	public Skill() {
	}

	public Skill(Date createdDate, Date updatedDate) {
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	

	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillType() {
		return this.skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getSkillCatagory() {
		return this.skillCatagory;
	}

	public void setSkillCatagory(String skillCatagory) {
		this.skillCatagory = skillCatagory;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

}
