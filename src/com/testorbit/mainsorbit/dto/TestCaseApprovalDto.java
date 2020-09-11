package com.testorbit.mainsorbit.dto;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
public class TestCaseApprovalDto {

	private long testCaseId;

	private char testCaseStatus;

	public long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public char getTestCaseStatus() {
		return testCaseStatus;
	}

	public void setTestCaseStatus(char testCaseStatus) {
		this.testCaseStatus = testCaseStatus;
	}

}
