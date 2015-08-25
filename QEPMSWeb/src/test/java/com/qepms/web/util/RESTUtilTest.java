package com.qepms.web.util;

import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qepms.infra.dto.sample.DepartmentDTO;
import com.qepms.infra.dto.sample.DepartmentDTOList;
import com.qepms.junit.QEPMSWebTestCase;
import com.qepms.web.util.RESTUtil;

public class RESTUtilTest extends QEPMSWebTestCase {

	private static final Logger LOG = LoggerFactory
			.getLogger(RESTUtilTest.class);

	@Autowired
	RESTUtil restUtil;

	@Test
	public void testGetData() {
		try {
			DepartmentDTO response = (DepartmentDTO) restUtil.getData(
					"/sample/dept/1", DepartmentDTO.class);
			LOG.debug(response.getDeptname());
			assertNotNull(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchData() {
		try {
			List<DepartmentDTO> response = (List<DepartmentDTO>) restUtil
					.getData("/sample/dept?deptName=", DepartmentDTOList.class);
			LOG.debug("response: " + response);
			for (DepartmentDTO departmentDTO : response) {
				LOG.debug(departmentDTO.getDeptid() + ": "
						+ departmentDTO.getDeptname());
			}
			assertNotNull(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testPutData() {

		DepartmentDTO newDepartmentDTO = new DepartmentDTO();
		newDepartmentDTO.setDeptname("New-Dept5");
		newDepartmentDTO.setCreatedby("test");
		newDepartmentDTO.setModifiedby("test");

		String expectedPartialResponse = "Department added successfully";

		try {

			String response = restUtil
					.putData(newDepartmentDTO, "/sample/dept")
					.getResponseMessage();

			LOG.debug(response);

			assertNotNull(response);

			boolean flag = response.contains(expectedPartialResponse);

			assertTrue(flag);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testPostData() {

		DepartmentDTO modificationDepartmentDTO = new DepartmentDTO();
		modificationDepartmentDTO.setDeptid(126);
		modificationDepartmentDTO.setDeptname("New Dept 11");
		modificationDepartmentDTO.setModifiedby("test");

		String expectedPartialResponse = "Department modified successfully";

		try {

			String response = restUtil.postData(modificationDepartmentDTO,
					"/sample/dept").getResponseMessage();

			LOG.debug(response);

			assertNotNull(response);

			boolean flag = response.contains(expectedPartialResponse);

			assertTrue(flag);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}
}
