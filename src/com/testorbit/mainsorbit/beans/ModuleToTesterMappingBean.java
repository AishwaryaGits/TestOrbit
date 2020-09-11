package com.testorbit.mainsorbit.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
/**
*
* @author Aishwarya Pauskar
*/

@Entity
@Table(name="MODULES_TO_TESTERS",catalog = "Testorbit")
public class ModuleToTesterMappingBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MODULE_TESTER_ID")
	private long moduleTesterID;
	
	@Column(name="TESTER_ID")
	@JoinColumn(name="USER_ID")
	private long testerUserId;
	
	@Column(name="MODULE_ID")
	@JoinColumn(name="MODULE_ID")
	private long moduleId;
	
	@Column(name="MOD_TESTER_STATUS")
	private char projectStatus;
	
	public long getTesterUserId() {
		return testerUserId;
	}
	public void setTesterUserId(long testerUserId) {
		this.testerUserId = testerUserId;
	}
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	public long getModuleTesterID() {
		return moduleTesterID;
	}
	public void setModuleTesterID(long moduleTesterID) {
		this.moduleTesterID = moduleTesterID;
	}
	public char getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(char projectStatus) {
		this.projectStatus = projectStatus;
	}

	
	
}
