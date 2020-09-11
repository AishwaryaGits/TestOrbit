package com.testorbit.mainsorbit.dto;

import java.util.Date;
import java.util.List;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */

public class ResultSummaryDto {
	
	
	private long taskId;
	private long totaltestCaseCount;
	private long passedCount;
	private Date executionDate;
	private long failedCount;
	private double summaryPercentage;
	private List testCaseIdList;
	
	public List getTestCaseIdList() {
		return testCaseIdList;
	}
	public void setTestCaseIdList(List testCaseIdList) {
		this.testCaseIdList = testCaseIdList;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public long getTotaltestCaseCount() {
		return totaltestCaseCount;
	}
	public void setTotaltestCaseCount(long totaltestCaseCount) {
		this.totaltestCaseCount = totaltestCaseCount;
	}
	public long getPassedCount() {
		return passedCount;
	}
	public void setPassedCount(long passedCount) {
		this.passedCount = passedCount;
	}
	public Date getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	public long getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}
	public double getSummaryPercentage() {
		return summaryPercentage;
	}
	public void setSummaryPercentage(double summaryPercentage) {
		this.summaryPercentage = summaryPercentage;
	}
	
	
	

}
