package com.testorbit.mainsorbit.dto;
/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
import java.util.Date;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
public class UserDto implements java.io.Serializable{
	
	private long userId;
	private String employeeId;
	private String firstName;
	private  String lastName;
	private String email;
	private String contactNo;
	private String userPassword;
	private int roleId;
	private char userStatus;
	private Date creatDate;
	private Date ModifyDate;
	private long userIdManager;
	private long superManagerId;
	
	public long getSuperManagerId() {
		return superManagerId;
	}
	public void setSuperManagerId(long superManagerId) {
		this.superManagerId = superManagerId;
	}
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public char getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(char userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public Date getModifyDate() {
		return ModifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		ModifyDate = modifyDate;
	}
	public long getUserIdManager() {
		return userIdManager;
	}
	public void setUserIdManager(long userIdManager) {
		this.userIdManager = userIdManager;
	}
	
	

}
