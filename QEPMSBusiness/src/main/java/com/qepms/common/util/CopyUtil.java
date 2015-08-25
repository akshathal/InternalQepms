package com.qepms.common.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.qepms.data.employee.Education;
import com.qepms.data.employee.EmployeeMaster;
import com.qepms.data.employee.Project;
import com.qepms.data.employee.Resume;
import com.qepms.data.employee.Skill;
import com.qepms.infra.dto.armg.EducationDTO;
import com.qepms.infra.dto.armg.EmployeeMasterDTO;
import com.qepms.infra.dto.armg.ProjectDTO;
import com.qepms.infra.dto.armg.ResumeDTO;
import com.qepms.infra.dto.armg.SkillDTO;

public class CopyUtil {
	
	public static Resume copyDTOtoPOJO(ResumeDTO resumeDTO,Resume resume) {
		// TODO Auto-generated method stub
		//setting simple elements
		
			resume.setEmployeeSumbissionStatus(resumeDTO.getEmployeeSumbissionStatus());
			resume.setManagerApprovalStatus(ManagerSubmissionStatus.PENDING.name());
			resume.setProfessionalSummary(resumeDTO.getProfessionalSummary());
			resume.setRelevantExperience(resumeDTO.getRelevantExperience());
			resume.setTotalExperience(resumeDTO.getTotalExperience());
			resume.setUpdatedDate(CustomDate.getCustomDate());
			
			
			
			
			//setting skill details
			Set skills=new HashSet(0);
			List<SkillDTO> skillDTOList = resumeDTO.getSkills();
			Skill skill = null;
			Iterator skillDTOItr = skillDTOList.iterator();
			while(skillDTOItr.hasNext())
			{
				SkillDTO skillDTO = (SkillDTO)skillDTOItr.next();
				skill=new Skill();
				skill.setCreatedDate(CustomDate.getCustomDate());
				skill.setResume(resume);
				skill.setSkillCatagory(skillDTO.getSkillCatagory());
				skill.setSkill(skillDTO.getSkill());
				skill.setSkillType(skillDTO.getSkillType());
				skill.setUpdatedDate(CustomDate.getCustomDate());
				skills.add(skill);
				
				
			}
			resume.setSkills(skills);
			
			
			//setting project details
			Set projects = new HashSet(0);
			List<ProjectDTO> projectsDTOList = resumeDTO.getProjects();
			Project project = null;
			Iterator projectDTOItr=projectsDTOList.iterator();
			while(projectDTOItr.hasNext())
			{
				ProjectDTO projectDTO =(ProjectDTO)projectDTOItr.next();
				project = new Project();
				project.setCreatedDate(CustomDate.getCustomDate());
				project.setEnvironment(projectDTO.getEnvironment());
				project.setProjectDesc(projectDTO.getProjectDesc());
				project.setProjectName(projectDTO.getProjectName());
				project.setResponsibility(projectDTO.getResponsibility());
				project.setResume(resume);
				project.setRole(projectDTO.getRole());
				project.setClientName(projectDTO.getClientName());
				project.setDuration(projectDTO.getDuration());
				project.setUpdatedDate(CustomDate.getCustomDate());
				projects.add(project);
				
				
			}
			resume.setProjects(projects);
			
			//setting education details
			List<EducationDTO> educationDTOList = resumeDTO.getEducations();
			Education education=null;
			Set educations = new HashSet(0);
			Iterator educationDTOListItr=educationDTOList.iterator();
			while(educationDTOListItr.hasNext())
			{
				EducationDTO educationDTO=(EducationDTO) educationDTOListItr.next();
				education = new Education();
				education.setAggregate(educationDTO.getAggregate());
				education.setCollege(educationDTO.getCollege());
				education.setCreatedDate(new Date());
				education.setDegree(educationDTO.getDegree());
				education.setDescription(educationDTO.getDescription());
				education.setResume(resume);
				education.setUpdatedDate(new Date());
				education.setYearOfPassing(educationDTO.getYearOfPassing());
				educations.add(education);
					
			}
			
			resume.setEducations(educations);
			
			//setting employee details
				Set resumes = new HashSet(0);
				EmployeeMasterDTO employeeMasterDTO = resumeDTO.getEmployeeMaster();
				EmployeeMaster employeeMaster = resume.getEmployeeMaster();
				if(employeeMaster==null)
				{
					employeeMaster = new EmployeeMaster();
				
				employeeMaster.setEmpId(employeeMasterDTO.getEmpId());
				employeeMaster.setName(employeeMasterDTO.getName());
				employeeMaster.setTitle(employeeMasterDTO.getTitle());
				employeeMaster.setEmployeeMail(employeeMasterDTO.getEmployeeMail());
				
				
				/*
				employeeMaster.setReportingManager(employeeMasterDTO.getReportingManager());
				*/
				employeeMaster.setCreatedDate(CustomDate.getCustomDate());
				employeeMaster.setUpdatedDate(CustomDate.getCustomDate());
				resume.setEmployeeMaster(employeeMaster);
				}
				resumes.add(resume);
				employeeMaster.setResumes(resumes);
				return resume;
	}

}
