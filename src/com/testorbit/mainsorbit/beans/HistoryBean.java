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
@Table(name="TESTORBIT_HISTORY",catalog = "Testorbit")
public class HistoryBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="HISTORY_ID")
	private long historyID;
	
	@Column(name="TESTER_ID")
	@JoinColumn(name="USER_ID")
	private long testerId;
	
	@Column(name="TASK_ID")
	@JoinColumn(name="TASK_ID")
	private long taskId;
	
	@Column(name="TESTCASE_ID")
	@JoinColumn(name="TESTCASE_ID")
	private long testCaseId;
	
	
	@Column(name="MODULE_ID")
	@JoinColumn(name="MODULE_ID")
	private long moduleId;
	
	@Column(name="RESULT_STATUS")
	private String resultStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="EXECUTION_DATE")
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
