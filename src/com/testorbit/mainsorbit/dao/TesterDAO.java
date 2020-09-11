/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.Date;
import java.util.List;
import com.testorbit.mainsorbit.beans.HistoryBean;
import com.testorbit.mainsorbit.beans.TestCaseBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface TesterDAO {
	
	public long createNewTestCase(TestCaseBean testCaseBean) throws TestOrbitException;
	public TestCaseBean getTestCaseDetails(long testCaseId) throws TestOrbitException;
	
	public List<TestCaseBean> listTestCasebasedOnTask(long taskId) throws TestOrbitException;
	public List<TestCaseBean> listTestCaseTobeApproved(long taskId) throws TestOrbitException;
	public TestCaseBean modifyTestCaseStatus(long testCaseId,char status) throws TestOrbitException ;
	public long recordTestResult(HistoryBean historyBean) throws TestOrbitException;
	public List<HistoryBean> getResultSummary(long taskId,Date executionDate) throws TestOrbitException;
	
	
}
