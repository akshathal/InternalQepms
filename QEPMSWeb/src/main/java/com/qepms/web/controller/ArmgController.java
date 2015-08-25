package com.qepms.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import net.sf.ehcache.writer.writebehind.operations.DeleteAllOperation;
import net.sf.ehcache.writer.writebehind.operations.DeleteOperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qepms.common.util.CustomDate;
import com.qepms.data.employee.EmployeeMaster;
import com.qepms.infra.constants.CommonConstants;
import com.qepms.infra.dto.armg.EmployeeMasterDTO;
import com.qepms.infra.dto.armg.EmployeeMasterWrapperDTO;
import com.qepms.infra.dto.armg.ResumeDTO;
import com.qepms.infra.dto.armg.UploadDTO;
import com.qepms.infra.misc.wrapper.ResponseMessageWrapper;
import com.qepms.web.constants.QEPMSWebConstants;
import com.qepms.web.util.RESTUtil;

@Controller
public class ArmgController {
	@Autowired
	RESTUtil restUtil;

	private Validator validator;

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	private static final Logger LOG = LoggerFactory
			.getLogger(ArmgController.class);

	@RequestMapping(value = "/armg/armgresume", method = RequestMethod.GET)
	public String showArmgarmgresumePage() {
		LOG.debug("showArmgarmgresumePage() called");

		return QEPMSWebConstants.Armg.ARMGRESUME_PAGE_PATH;
	}

	@RequestMapping(value = "/armg/landing", method = RequestMethod.GET)
	public String showArmglandingPage() {
		LOG.debug("showArmglandingPage() called");
		return QEPMSWebConstants.Armg.LANDING_PAGE_PATH;
	}

	@RequestMapping(value = "/armg/armgviewofresume", method = RequestMethod.GET)
	public String showArmgarmgviewofresumePage() {
		LOG.debug("showArmgarmgviewofresumePage() called");
		return QEPMSWebConstants.Armg.ARMGVIEWOFRESUME_PAGE_PATH;

	}

	@RequestMapping(value = "/armg/importresume", method = RequestMethod.GET)
	public String showArmgimportresumePage() {
		LOG.debug("showArmgimportresumePage() called");
		return QEPMSWebConstants.Armg.IMPORTRESUME_PAGE_PATH;

	}

	/*
	 * @RequestMapping(value = "/armg/armgconfiguration", method =
	 * RequestMethod.GET) public String showConfigurationPage() {
	 * LOG.debug("showConfigurationPage() called"); return
	 * QEPMSWebConstants.Armg.CONFIGURATION_PAGE_PATH; }
	 */

	@RequestMapping(value = "/armg/uploadresumetemplate", method = RequestMethod.GET)
	public String showArmguploadresumetemplatePage() {
		LOG.debug("showArmguploadresumetemplatePage() called");
		return QEPMSWebConstants.Armg.UPLOADRESUMETEMPLATE_PAGE_PATH;
	}

	// uploading the resume to database
	@RequestMapping(value = "/armg/uploadfile", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView submitResume(@RequestParam("file") MultipartFile file,
			@RequestParam("empId") int empId,
			@RequestParam("employeeMail") String employeeMail,
			@RequestParam("reportingManager") String reportingManager) {
		UploadDTO uploadDTO = new UploadDTO();
		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		EmployeeMasterDTO employeeMasterDTO = new EmployeeMasterDTO();
		employeeMasterDTO.setEmpId(empId);
		employeeMasterDTO.setEmployeeMail(employeeMail);
		employeeMasterDTO.setReportingManager(reportingManager);
		employeeMasterDTO.setCreatedDate(CustomDate.getCustomDate());
		employeeMasterDTO.setUpdatedDate(CustomDate.getCustomDate());
		uploadDTO.setEmployeeMasterDTO(employeeMasterDTO);
		LOG.debug("submitResume() called ");
		try {
			if (!file.isEmpty()) {

				byte[] bytes = file.getBytes();
				System.out.println("fileNmae=" + file.getOriginalFilename());
				String rootPath = CommonConstants.UPLOAD_DIRECTORY;
				File dir = new File(rootPath);
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				LOG.info("Server File Location=" + serverFile.getAbsolutePath());

				uploadDTO.setFilePath(serverFile.getAbsolutePath());
				responseMessageWrapper = restUtil.postData(uploadDTO,
						QEPMSWebConstants.Armg.UPLOADRESUME_MSTR_WS_PATH);
			} else {
				responseMessageWrapper
						.setResponseMessage("Upload Failed!!File is empty");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView(
				QEPMSWebConstants.Armg.UPLOADRESUMETEMPLATE_PAGE_PATH,
				"responseMessageWrapper", responseMessageWrapper);
	}

	// uploading the resume to database
	@RequestMapping(value = "/armg/exportresume", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView exportResumeAsDoc(HttpServletRequest request) {
		ResponseMessageWrapper responseMessageWrapper = null;
		ResumeDTO resumeDTO = (ResumeDTO) request.getAttribute("resume");
		try {
			responseMessageWrapper = restUtil.putData(resumeDTO,
					QEPMSWebConstants.Armg.EXPORT_RESUME_WS_PATH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView(
				QEPMSWebConstants.Armg.ARMGVIEWOFRESUME_PAGE_PATH
						+ "?resumeid=" + resumeDTO.getResumeid(),
				"responseMessageWrapper", responseMessageWrapper);
	}

	// added by sampad
	@RequestMapping(value = "/armg/uploadeExcel", method = RequestMethod.GET)
	public String showArmguploadExcel() {
		LOG.debug("showArmguploadExcel() called");
		return QEPMSWebConstants.Armg.UPLOADEXCEL_PAGE_PATH;
	}

	/**
	 * @param file
	 * @param empId
	 * @param employeeMail
	 * @param reportingManager
	 * @return
	 */
	@RequestMapping(value = "/armg/uploadExcelFile", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView uploadExcelFileHandler(
			@RequestParam("file") MultipartFile file,
			@RequestParam("empId") int empId,
			@RequestParam("employeeMail") String employeeMail,
			@RequestParam("reportingManager") String reportingManager) {

		ResponseMessageWrapper responseMessageWrapper = new ResponseMessageWrapper();
		EmployeeMaster employeemaster = null;
		List<EmployeeMaster> employeeMasterList = new ArrayList<EmployeeMaster>();
		EmployeeMasterWrapperDTO employMasterWrapper = new EmployeeMasterWrapperDTO();
		LOG.debug("submitResume() called ");
		String nameOFFile = file.getOriginalFilename();
		try {
			if (!file.isEmpty()) {

				byte[] bytes = file.getBytes();

				System.out.println("fileNmae=" + file.getOriginalFilename());
				String rootPath = CommonConstants.UPLOAD_DIRECTORY;
				File dir = new File(rootPath);
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				File filePath = serverFile;
				String fileName = file.getOriginalFilename();
				stream.write(bytes);

				LOG.info("Server File Location=" + serverFile.getAbsolutePath());

				int i = 0;
				DateFormat df = new SimpleDateFormat("mm-dd-yyyy");
				Date createDate = null;
				Date updateDate = null;

				File filePathtoDir = new File(CommonConstants.UPLOAD_DIRECTORY);

				System.out.println("chethana: File Path based on file path "
						+ filePath);
				System.out.println("chethana :File name based on file name"
						+ fileName);

				InputStream inputStream = new FileInputStream(serverFile);
				System.out.println("input stream has" + inputStream);

				System.out.println("filenameeeeeeeeeeeee" + fileName);				
				if (fileName != null) {

					// XSSFWorkbook workbook = new XSSFWorkbook(fileName);

					// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
					org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory
							.create(filePath);
					System.out.println("workbook:" + workbook);

					// XSSFSheet sheet = workbook.getSheetAt(0);

					// Iterate start from the first sheet of the uploaded excel
					// file
					org.apache.poi.ss.usermodel.Sheet sheet = workbook
							.getSheetAt(0);
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						employeemaster = new EmployeeMaster();

						if (row.getRowNum() == 0) {
							continue;// skip to read the first row of file
						}

						// For each row, iterate through each coulumns
						Iterator<Cell> cellIterator = row.cellIterator();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							System.out.println("ColumnIndex:"
									+ cell.getColumnIndex());
							if (cell.getColumnIndex() == 0) {

								System.out.print("col1:"
										+ cell.getNumericCellValue() + "\t\t");
								// 1
								employeemaster.setEmpId((int) cell
										.getNumericCellValue());
							} else if (cell.getColumnIndex() == 1) {

								System.out.print("col2:"
										+ cell.getStringCellValue() + "\t\t");
								// 2
								employeemaster.setName(cell
										.getStringCellValue().trim());
							} else if (cell.getColumnIndex() == 2) {

								System.out.print("col3:"
										+ cell.getStringCellValue().trim()
										+ "\t\t");
								// 3
								employeemaster.setTitle(cell
										.getStringCellValue());
							}

							else if (cell.getColumnIndex() == 3) {

								System.out.print("col4:"
										+ cell.getStringCellValue().trim()
										+ "\t\t");
								// 4
								employeemaster.setCurrentProject(cell
										.getStringCellValue());
							}

							else if (cell.getColumnIndex() == 4) {

								System.out.print("col5:"
										+ cell.getStringCellValue().trim()
										+ "\t\t");
								// 5
								employeemaster.setReportingManager(cell
										.getStringCellValue());
							}

							else if (cell.getColumnIndex() == 5) {

								System.out.print("col6:"
										+ cell.getStringCellValue().trim()
										+ "\t\t");
								employeemaster.setEmployeeMail(cell
										.getStringCellValue());
							}

							else if (cell.getColumnIndex() == 6) {

								System.out.print("col7:"
										+ cell.getStringCellValue().trim()
										+ "\t\t");
								// 6
								employeemaster.setManagerMail(cell
										.getStringCellValue());
							}

							/*
							 * else if (cell.getColumnIndex() == 7 ) {
							 * 
							 * System.out.print("col8:"+cell.getStringCellValue()
							 * + "\t\t");
							 * 
							 * //7 employeemaster.setCreatedDate(df.parse(cell.
							 * getStringCellValue())); }
							 * 
							 * else if (cell.getColumnIndex() == 8 ) {
							 * System.out
							 * .print("col9:"+cell.getStringCellValue() +
							 * "\t\t");
							 * employeemaster.setUpdatedDate(df.parse(cell
							 * .getStringCellValue())); }
							 */
							else if (cell.getColumnIndex() == 9) {
								System.out.print("col10:"
										+ cell.getStringCellValue() + "\t\t");
								employeemaster.setGroup(cell
										.getStringCellValue().trim());
							}

							/*
							 * else if (cell.getColumnIndex() == 10 ) {
							 * System.out
							 * .print("col11:"+cell.getStringCellValue() +
							 * "\t\t"); //10
							 * employeemaster.setGroup(cell.getStringCellValue
							 * ()); }
							 */

						}
						
						System.out.println("Testing employeemaster");
						// armgService.insertEmployeeMaster(employeemaster);
						employeeMasterList.add(employeemaster);

					}

					// file1.close();
				} else {
					System.out.println(" File is not Found");
				}
				employMasterWrapper.setEmployeeMasterList(employeeMasterList);
				responseMessageWrapper = restUtil.postData(employMasterWrapper,
						QEPMSWebConstants.Armg.UPLOAD_EMPLOYEE_MSTR_WS_PATH);

				// UPLOAD

			} else {
				responseMessageWrapper
						.setResponseMessage(CommonConstants.UPLOAD_FAILURE_STATUS
								+ "File is empty");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			File filePathtoDir = new File(CommonConstants.UPLOAD_DIRECTORY);
			File[] listFiles = filePathtoDir.listFiles();
			for (File file2 : listFiles) {
				String filename = file2.getName();
				String ext = filename.substring(filename.lastIndexOf(".") + 1);
				if (ext.contains("xls")) {
					file2.delete();
					System.out.println("the excel file is deleted !!!!");
					break;
				}
			}

		}

		return new ModelAndView(QEPMSWebConstants.Armg.UPLOADEXCEL_PAGE_PATH,
				"responseMessageWrapper", responseMessageWrapper);
	}

	// code added by Mukunda for download functionality

	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(value = "/armg/downloadresume", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("empId") int empId,
			@RequestParam("employeeName") String employeeName)
			throws IOException {

		// get absolute path of the application
		String filePath = CommonConstants.UPLOAD_DIRECTORY + "//" + empId + "_"
				+ employeeName + ".pdf";
		String htmlFilePath = CommonConstants.UPLOAD_DIRECTORY + "//" + empId
				+ "_" + employeeName + ".html";
		File htmlFile = new File(htmlFilePath);
		System.out.println(htmlFilePath);
		System.out.println(filePath);
		ServletContext context = request.getServletContext();
		/*
		 * String appPath = context.getRealPath("");
		 * System.out.println("appPath = " + appPath);
		 */

		// construct the complete absolute path of the file
		/* String fullPath = appPath + filePath; */
		File downloadFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(filePath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);
	//	System.out.println(response.getHeader(headerValue));
		//System.out.println(response.getHeader(headerKey));

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
		downloadFile.delete();
		htmlFile.delete();

	}

}