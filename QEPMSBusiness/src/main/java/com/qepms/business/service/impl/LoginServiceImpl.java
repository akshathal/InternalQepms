package com.qepms.business.service.impl;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.qepms.business.service.LoginService;
import com.qepms.data.dao.employee.EmployeeMasterDAO;
import com.qepms.data.employee.EmployeeMaster;
import com.qepms.infra.dto.login.UserDTO;
import com.qepms.infra.ldap.Authentication;

@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private EmployeeMasterDAO employeeMasterDAO;

	@Override
	public UserDTO authenticate(String uName,String pwd) throws Exception {
		UserDTO userDTO=new UserDTO();
		 
		Map<String, String> auth;
		try {
			
			 auth = Authentication.authenticateAD(uName, pwd);
			 if(auth==null)
			 {
				 return null;
			 }
			 else
			 {
				 String mail=auth.get("mail");
				 String managerDetail=auth.get("manager");
				 String empId=auth.get("employeeNumber");
				 String[]managerDetails=managerDetail.split(",");
				 String managerNames[]=managerDetails[0].split("=");
				 String manager=managerNames[1];
				 String userName=auth.get("sAMAccountName");
				 String name=auth.get("displayName");
				 String title=auth.get("title");
				 
				
				 EmployeeMaster employeeMaster = employeeMasterDAO.findById(Integer.parseInt(empId));
				
				 
				 userDTO.setUserName(userName);
				 userDTO.setEmployeEmail(mail);
				 userDTO.setEmployeeManager(manager);
				 userDTO.setEmployeeName(name);
				 userDTO.setEmpId(Integer.parseInt(empId));
				 userDTO.setJobTitle(title);
				 
				 if(employeeMaster!=null)
				 {
					 userDTO.setRole(employeeMaster.getGroup());
				 }
				
					 
				 
				 
				 
				 return userDTO;
			 }
		   } catch (Exception e) {
			
			throw e;
		}
		
		   
		
	}

}
