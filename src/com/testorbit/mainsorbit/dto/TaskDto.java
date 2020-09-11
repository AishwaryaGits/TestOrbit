package com.testorbit.mainsorbit.dto;

import java.util.Date;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */

public class TaskDto {
	
	
	private long taskId;
	private  String taskDescription;
	private long testManagerId;
	private long testerId;
	private String taskStatus;
	private Date creatDate;
	private Date endDate;
	private Date startDate;
	private long moduleId;
	
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public long getTestManagerId() {
		return testManagerId;
	}
	public void setTestManagerId(long testManagerId) {
		this.testManagerId = testManagerId;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public long getTesterId() {
		return testerId;
	}
	public void setTesterId(long testerId) {
		this.testerId = testerId;
	}


}
