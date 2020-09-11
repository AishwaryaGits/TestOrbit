package com.testorbit.mainsorbit.dao;

import java.util.List;
import com.testorbit.mainsorbit.beans.UserBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;

/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
public interface GenericDAO {
	
	//validate User Login
	
	public UserBean validateUser(UserBean userBean) throws TestOrbitException;
	
	//List of testes under testmanager based on test manager Id
	public List<UserBean> listTesterUnderTestManager(long testMgrId) throws TestOrbitException;

	public UserBean registerNewUser(UserBean userBean) throws TestOrbitException;
	
	public List<UserBean> listUnassignedTestManagers(long projectManagerId) throws TestOrbitException;
	public List<UserBean> listUsersToApprove(long projectManagerId) throws TestOrbitException;
	
	public List<UserBean> listAllActiveProjectManagers() throws TestOrbitException;
	
	public UserBean approveUsers(long userResourceId,char status) throws TestOrbitException ;
	
	public List<UserBean> listUnassignedTestersToTestManagers(long projectManagerId) throws TestOrbitException;
	
	public List<UserBean> listAllActiveTestManagersUnderProjectManager(long projectManagerId) throws TestOrbitException;
	
}
