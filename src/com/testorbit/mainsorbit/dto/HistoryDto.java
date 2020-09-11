package com.testorbit.mainsorbit.dto;

import java.util.Date;

/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */

public class HistoryDto {
	
	
	private long historyID;
	
	
	private long testerId;
	
	
	private long taskId;
	
	
	private long testCaseId;
	
	
	
	private long moduleId;
	
	
	private String resultStatus;
	
	private Date executionDate;

	public long getHistoryID() {
		return historyID;
	}

	public void setHistoryID(long historyID) {
		this.historyID = historyID;
	}

	public long getTesterId() {
		return testerId;
	}

	public void setTesterId(long testerId) {
		this.testerId = testerId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
	
	
	
}
