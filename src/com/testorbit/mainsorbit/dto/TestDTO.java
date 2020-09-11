package com.testorbit.mainsorbit.dto;


/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
public class TestDTO {

    private String testCode;
    private String testName;
    private Integer testId;


    public TestDTO() {
    }

    public TestDTO(String testCode, String testName) {
        this.testCode = testCode;
        this.testName = testName;
    }

    
    @Override
	public String toString() {
		return "TestDTO [testCode=" + testCode + ", testName=" + testName + ", testId=" + testId + "]";
	}

	public TestDTO(String testCode, String testName, Integer testId) {
		super();
		this.testCode = testCode;
		this.testName = testName;
		this.testId = testId;
	}

	public String getTestCode() {
        return this.testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestName() {
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

}
