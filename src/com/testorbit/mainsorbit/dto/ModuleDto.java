package com.testorbit.mainsorbit.dto;

import java.util.Date;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */

public class ModuleDto {

	private long moduleId;
	private String moduleName;
	private  String moduleDescription;
	private long projectManagerId;
	private long testManagerId;
	private String moduleStatus;
	private Date creatDate;
	private Date endDate;
	private Date startDate;
	private long currentTestManagerId;
	public long getCurrentTestManagerId() {
		return currentTestManagerId;
	}
	public void setCurrentTestManagerId(long currentTestManagerId) {
		this.currentTestManagerId = currentTestManagerId;
	}
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleDescription() {
		return moduleDescription;
	}
	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}
	public long getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(long projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	public long getTestManagerId() {
		return testManagerId;
	}
	public void setTestManagerId(long testManagerId) {
		this.testManagerId = testManagerId;
	}
	public String getModuleStatus() {
		return moduleStatus;
	}
	public void setModuleStatus(String moduleStatus) {
		this.moduleStatus = moduleStatus;
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
	


}
