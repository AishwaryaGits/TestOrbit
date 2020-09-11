/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.service;

import java.util.List;

import com.testorbit.mainsorbit.dto.ResultSummaryDto;
import com.testorbit.mainsorbit.dto.TestCaseApprovalDto;
import com.testorbit.mainsorbit.dto.TestCaseDto;

import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface TesterService {
	
	public TestCaseDto createNewTestCase(TestCaseDto testCaseDto) throws TestOrbitException;
	public List<TestCaseDto> listTestCasebasedOnTask(long taskId) throws TestOrbitException;
	public List<TestCaseDto> listTestCaseTobeApproved(long taskId) throws TestOrbitException;
	public TestCaseDto modifyTestCaseStatus(TestCaseApprovalDto testCaseApprovalDto) throws TestOrbitException ;
	public TestCaseDto recordTestResult(TestCaseDto testCaseDto) throws TestOrbitException;
	public ResultSummaryDto getResultSummary(ResultSummaryDto resultSummaryDto) throws TestOrbitException;
	
}
