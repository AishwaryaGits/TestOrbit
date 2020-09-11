/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.testorbit.mainsorbit.beans.HistoryBean;
import com.testorbit.mainsorbit.beans.TestCaseBean;
import com.testorbit.mainsorbit.dao.GenericDAO;
import com.testorbit.mainsorbit.dao.ProjectManagerDAO;
import com.testorbit.mainsorbit.dao.TesterDAO;
import com.testorbit.mainsorbit.dto.ResultSummaryDto;
import com.testorbit.mainsorbit.dto.TestCaseApprovalDto;
import com.testorbit.mainsorbit.dto.TestCaseDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.DateUtility;
import com.testorbit.mainsorbit.util.FrameworkConstants;


public class TesterServiceImpl implements TesterService {
	
	@Autowired
    private TesterDAO testerDAO;

	

	@Autowired
    private ProjectManagerDAO projectManagerDAO;
	
	@Autowired
    private GenericDAO genericDAO;
	

    public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	public ProjectManagerDAO getProjectManagerDAO() {
		return projectManagerDAO;
	}

	public void setProjectManagerDAO(ProjectManagerDAO projectManagerDAO) {
		this.projectManagerDAO = projectManagerDAO;
	}
	public TesterDAO getTesterDAO() {
		return testerDAO;
	}

	public void setTesterDAO(TesterDAO testerDAO) {
		this.testerDAO = testerDAO;
	}
	
	
	//Converts UserBean to ModuleDto
	private TestCaseDto convertTestCaseBeanToDto(TestCaseBean testCaseBean){
		TestCaseDto testCaseDto=new TestCaseDto();
		testCaseDto.setTestCaseId(testCaseBean.getTestCaseId());
		testCaseDto.setTestCaseInputs(testCaseBean.getTestCaseInputs());
		testCaseDto.setTestCaseOutputs(testCaseBean.getTestCaseOutputs());
		testCaseDto.setTestCaseStatus(testCaseBean.getTestCaseStatus());
		testCaseDto.setTaskId(testCaseBean.getTaskId());
		testCaseDto.setComments(testCaseBean.getComments());
		testCaseDto.setCreatDate(testCaseBean.getCreatDate());
		testCaseDto.setEndDate(testCaseBean.getEndDate());
		testCaseDto.setTesterId(testCaseBean.getTesterId());
		return testCaseDto;
	}
	
	//Converts ModuleBean to ModuleDto
	private TestCaseBean convertTestCaseDtoToBean(TestCaseDto testCaseDto){
		TestCaseBean testCaseBean=new TestCaseBean();
		testCaseBean.setTestCaseId(testCaseDto.getTestCaseId());
		testCaseBean.setTestCaseInputs(testCaseDto.getTestCaseInputs());
		testCaseBean.setTestCaseOutputs(testCaseDto.getTestCaseOutputs());
		testCaseBean.setTestCaseStatus(testCaseDto.getTestCaseStatus());
		testCaseBean.setTaskId(testCaseDto.getTaskId());
		testCaseBean.setComments(testCaseDto.getComments());
		testCaseBean.setCreatDate(testCaseDto.getCreatDate());
		testCaseBean.setEndDate(testCaseDto.getEndDate());
		testCaseBean.setTesterId(testCaseDto.getTesterId());
		return testCaseBean;
	}

	@Override
	public TestCaseDto createNewTestCase(TestCaseDto testCaseDto) throws TestOrbitException {
		TestCaseBean testCaseBean=null;
		TestCaseDto testCaseDtoService=null;
		long testCaseId=0;
		testCaseBean=convertTestCaseDtoToBean(testCaseDto);
		
		testCaseBean.setCreatDate(DateUtility.getCurrentDate());
		testCaseBean.setTestCaseStatus(FrameworkConstants.TESTCASE_STATUS_CREATED);
		
		testCaseId=testerDAO.createNewTestCase(testCaseBean);
		if(testCaseId !=0  ){
			
			testCaseBean=testerDAO.getTestCaseDetails(testCaseId);
		}
		
		
		testCaseDtoService=convertTestCaseBeanToDto(testCaseBean);
		return testCaseDtoService;
	}

	@Override
	public List<TestCaseDto> listTestCasebasedOnTask(long taskId) throws TestOrbitException {
		List<TestCaseDto> listTestCases=new ArrayList<TestCaseDto>();
		List<TestCaseBean> listTestCaseBean=null;
		TestCaseDto testCaseDto=null;
		listTestCaseBean=testerDAO.listTestCasebasedOnTask(taskId);
		if(listTestCaseBean!=null){
			for(TestCaseBean testCaseBean:listTestCaseBean){
				testCaseDto=convertTestCaseBeanToDto(testCaseBean);
				listTestCases.add(testCaseDto);
			}
			
		}
		return listTestCases;
	}

	@Override
	public List<TestCaseDto> listTestCaseTobeApproved(long taskId) throws TestOrbitException {
		List<TestCaseDto> listTestCases=new ArrayList<TestCaseDto>();
		List<TestCaseBean> listTestCaseBean=null;
		TestCaseDto testCaseDto=null;
		listTestCaseBean=testerDAO.listTestCaseTobeApproved(taskId);
		if(listTestCaseBean!=null){
			for(TestCaseBean testCaseBean:listTestCaseBean){
				testCaseDto=convertTestCaseBeanToDto(testCaseBean);
				listTestCases.add(testCaseDto);
			}
			
		}
		return listTestCases;
	}

	@Override
	public TestCaseDto modifyTestCaseStatus(TestCaseApprovalDto testCaseApprovalDto) throws TestOrbitException {
		TestCaseBean testcaseBean=null;
		TestCaseDto testcaseDtoService=null;
		char status=FrameworkConstants.TESTCASE_STATUS_CREATED;
		if(testCaseApprovalDto.getTestCaseId()!=0){
			
		if(testCaseApprovalDto.getTestCaseStatus() == 'Y' ){
			status=FrameworkConstants.STATUS_ACTIVE;
		}else if (testCaseApprovalDto.getTestCaseStatus() == 'R' ){
			status=FrameworkConstants.STATUS_REJECTED;
		}else if (testCaseApprovalDto.getTestCaseStatus() == 'N' ){
			status=FrameworkConstants.TESTCASE_STATUS_INVALID;
		}
		testcaseBean=testerDAO.modifyTestCaseStatus(testCaseApprovalDto.getTestCaseId(), status);
		testcaseDtoService=convertTestCaseBeanToDto(testcaseBean);
		}
		return testcaseDtoService;
	}

	@Override
	public TestCaseDto recordTestResult(TestCaseDto testCaseDto) throws TestOrbitException {
		HistoryBean historyBean=null;
		TestCaseDto testCaseDtoService=null;
		TestCaseBean testCaseBean=null;
		long historyId=0;
		if(testCaseDto.getModuleId()!=0 && testCaseDto.getTaskId()!=0 && testCaseDto.getTestCaseId()!=0  ){
		historyBean=new HistoryBean();
		historyBean.setExecutionDate(DateUtility.getCurrentDate());
		if(testCaseDto.getTestCaseResultStatus().equals(FrameworkConstants.RESULT_PASSED)){
			historyBean.setResultStatus(FrameworkConstants.RESULT_PASSED);
		}else if (testCaseDto.getTestCaseResultStatus().equals(FrameworkConstants.RESULT_FAILED)){
			historyBean.setResultStatus(FrameworkConstants.RESULT_FAILED);
		}
		historyBean.setModuleId(testCaseDto.getModuleId());
		historyBean.setTaskId(testCaseDto.getTaskId());
		historyBean.setTestCaseId(testCaseDto.getTestCaseId());
		historyBean.setTesterId(testCaseDto.getTesterId());
		historyId=testerDAO.recordTestResult(historyBean);
		}
	
		
			
		testCaseBean=testerDAO.getTestCaseDetails(testCaseDto.getTestCaseId());
		
		
		testCaseDtoService=convertTestCaseBeanToDto(testCaseBean);
		return testCaseDtoService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultSummaryDto getResultSummary(ResultSummaryDto resultSummaryDto) throws TestOrbitException {
	
		List<HistoryBean> listHistoryBean=null;
		ResultSummaryDto resultSummaryDtoRes=null;
		double totalTestCaseCount=0,passedCount=0,failedCount=0,perSummary=0;
		List testCaseIdsList=null;
		if(resultSummaryDto.getTaskId()!=0 && resultSummaryDto.getExecutionDate() !=null){
			listHistoryBean=testerDAO.getResultSummary(resultSummaryDto.getTaskId(),resultSummaryDto.getExecutionDate());
		}
		
		if(listHistoryBean!=null){
			resultSummaryDtoRes=new ResultSummaryDto();
			testCaseIdsList=new ArrayList();
			totalTestCaseCount=listHistoryBean.size();
			resultSummaryDtoRes.setTotaltestCaseCount(listHistoryBean.size());
			for(HistoryBean historyBean:listHistoryBean){
				if(historyBean.getResultStatus().equals(FrameworkConstants.RESULT_PASSED)){
					passedCount=passedCount+1;
				}else if(historyBean.getResultStatus().equals(FrameworkConstants.RESULT_FAILED)){
					failedCount=failedCount+1;
				}
				testCaseIdsList.add(historyBean.getTestCaseId());
			}
			resultSummaryDtoRes.setFailedCount((long)failedCount);
			resultSummaryDtoRes.setPassedCount((long)passedCount);
			perSummary=((passedCount/totalTestCaseCount) * FrameworkConstants.PER_100);
			resultSummaryDtoRes.setSummaryPercentage(perSummary);
			resultSummaryDtoRes.setTestCaseIdList(testCaseIdsList);
			resultSummaryDtoRes.setExecutionDate(resultSummaryDto.getExecutionDate());
		}
		return resultSummaryDtoRes;
	}

	

}
