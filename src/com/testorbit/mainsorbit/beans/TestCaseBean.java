package com.testorbit.mainsorbit.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
/**
*
* @author Aishwarya Pauskar
*/

@Entity
@Table(name="TESTORBIT_TEST_CASES",catalog = "Testorbit")
public class TestCaseBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TESTCASE_ID")
	private long testCaseId;
	
	@Column(name="TESTCASE_INPUT")
	private String testCaseInputs;
	
	@Column(name="TESTCASE_OUTPUT")
	private  String testCaseOutputs;
	
	@Column(name="TESTCASE_COMMENTS")
	private String comments;
	
	@Column(name="TASK_ID")
	@JoinColumn(name="TASK_ID")
	private long taskId;
	
	@Column(name="TESTER_ID")
	@JoinColumn(name="USER_ID")
	private long testerId;
	
	@Column(name="TESTCASE_STATUS")
	private char testCaseStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="CREAT_DATE")
	private Date creatDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;
	
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
