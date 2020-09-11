package com.testorbit.mainsorbit.dto;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
public class UserApprovalDto {
	private long userIdProjectManager;
	

	private long userIdResource;
	
	private char userStatus;
	
	public long getUserIdProjectManager() {
		return userIdProjectManager;
	}

	public void setUserIdProjectManager(long userIdProjectManager) {
		this.userIdProjectManager = userIdProjectManager;
	}

	public long getUserIdResource() {
		return userIdResource;
	}

	public void setUserIdResource(long userIdResource) {
		this.userIdResource = userIdResource;
	}

	public char getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(char userStatus) {
		this.userStatus = userStatus;
	}

	
}
