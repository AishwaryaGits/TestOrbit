package com.testorbit.mainsorbit.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
*
* @author Aishwarya Pauskar
*/
@Entity
@Table(name="TESTORBIT_USER_REGISTRATION",catalog = "Testorbit")
public class UserBean {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private  String lastName;
	

	
	@Column(name="E_MAIL")
	private String email;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="USER_PASSWORD")
	private String userPassword;
	
	@Column(name="ROLE_ID")
	private int roleId;
	
	@Column(name="MANAGER_ID")
	private long userIdManager;
	
	@Column(name="SUPER_MANAGER_ID")
	private long superManagerId;
	
	public long getSuperManagerId() {
		return superManagerId;
	}
	public void setSuperManagerId(long superManagerId) {
		this.superManagerId = superManagerId;
	}
	@Column(name="USER_STATUS")
	private char userStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="CREAT_DATE")
	private Date creatDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="MODIFY_DATE")
	private Date ModifyDate;
	
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
