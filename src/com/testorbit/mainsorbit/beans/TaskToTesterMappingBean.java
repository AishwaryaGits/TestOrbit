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
@Table(name="TESTORBIT_TASKS_TO_TESTERS",catalog = "Testorbit")
public class TaskToTesterMappingBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TASK_TESTER_ID")
	
	private long taskTesterID;
	
	@Column(name="TASK_ID")
	@JoinColumn(name="TASK_ID")
	private long taskId;
	
	@Column(name="TESTER_ID")
	@JoinColumn(name="USER_ID")
	private long testerId;
	

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;



	public long getTaskTesterID() {
		return taskTesterID;
	}



	public void setTaskTesterID(long taskTesterID) {
		this.taskTesterID = taskTesterID;
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



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
}
