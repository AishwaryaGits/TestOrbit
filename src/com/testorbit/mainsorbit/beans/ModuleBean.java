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
@Table(name="TESTORBIT_MODULES",catalog = "Testorbit")
public class ModuleBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MODULE_ID")
	private long moduleId;
	
	@Column(name="MODULE_NAME")
	private String moduleName;
	
	@Column(name="MODULE_DESC")
	private  String moduleDescription;
	
	@Column(name="PROJECT_MANAGER_ID")
	@JoinColumn(name="USER_ID")
	private long projectManagerId;
	
	@Column(name="TEST_MANAGER_ID")
	private long testManagerId;
	
	@Column(name="MODULE_STATUS")
	private String moduleStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="CREAT_DATE")
	private Date creatDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;
	
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
