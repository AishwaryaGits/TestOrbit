package com.testorbit.mainsorbit.dto;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
import javax.persistence.Entity;

@Entity
public class UserLoginDto {
	
	private long userId;
	private String employeeId;
	
	private String userEmailId;
	
	private String userPassword;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String email) {
		this.userEmailId = email;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	

}
