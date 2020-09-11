package com.testorbit.mainsorbit.dto;

import java.util.Date;

/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
public class TestCaseDto {

	private long testCaseId;
	private String testCaseInputs;
	private  String testCaseOutputs;
	private String comments;
	private long taskId;
	private long testerId;
	private long moduleId;
	private char testCaseStatus;
	private Date creatDate;
	private Date endDate;
	private String testCaseResultStatus;
	
	public String getTestCaseResultStatus() {
		return testCaseResultStatus;
	}
	public void setTestCaseResultStatus(String testCaseResultStatus) {
		this.testCaseResultStatus = testCaseResultStatus;
	}
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	
	public long getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(long testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getTestCaseInputs() {
		return testCaseInputs;
	}
	public void setTestCaseInputs(String testCaseInputs) {
		this.testCaseInputs = testCaseInputs;
	}
	public String getTestCaseOutputs() {
		return testCaseOutputs;
	}
	public void setTestCaseOutputs(String testCaseOutputs) {
		this.testCaseOutputs = testCaseOutputs;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public long getTesterId() {
		return testerId;
	}
	public void setTesterId(long testerId) {
		this.testerId = testerId;
	}
	public char getTestCaseStatus() {
		return testCaseStatus;
	}
	public void setTestCaseStatus(char testCaseStatus) {
		this.testCaseStatus = testCaseStatus;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
