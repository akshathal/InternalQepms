package com.qepms.common;

import java.awt.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import com.qepms.infra.dto.armg.ResumeDTO;

public class ObjectToHashMap {

	public HashMap convertToHash(ResumeDTO resume)
	{
		HashMap resumeMap = new HashMap();
		resumeMap.put("title", resume.getEmployeeMaster().getTitle());
		resumeMap.put("empId", resume.getEmployeeMaster().getEmpId());
		resumeMap.put("name", resume.getEmployeeMaster().getName());
		List skillList = new List();
		Iterator i = resume.getSkills().iterator();
		while(i.hasNext())
		{
			int j=0;
			//String temp = resume.getSkills().get(j);
			
		}
		
		
		
		return resumeMap;
		
	}
}
