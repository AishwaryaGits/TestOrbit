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
@Table(name="TESTORBIT_USER_RESOURCE_MAPPING",catalog = "Testorbit")
public class UserMappingBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MAPPING_ID")
	private long mappingId;
	
	@Column(name="USER_ID_MANAGER")
	@JoinColumn(name="USER_ID")
	private long userIdManager;
	
	
	@Column(name="USER_ID_RESOURCE")
	@JoinColumn(name="USER_ID")
	private long userIdResource;
	
	@Column(name="APPROVED_STATUS")
	private char approvedStatus;

	public long getUserIdManager() {
		return userIdManager;
	}

	public void setUserIdManager(long userIdManager) {
		this.userIdManager = userIdManager;
	}

	public long getUserIdResource() {
		return userIdResource;
	}

	public void setUserIdResource(long userIdResource) {
		this.userIdResource = userIdResource;
	}

	public char getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(char approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public long getMappingId() {
		return mappingId;
	}

	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}

}
