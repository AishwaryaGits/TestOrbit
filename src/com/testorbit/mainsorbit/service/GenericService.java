/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */

package com.testorbit.mainsorbit.service;

import java.util.List;

import com.testorbit.mainsorbit.dto.UserApprovalDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.dto.UserLoginDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface GenericService {
	//validate User Login
	public UserDto validateUser(UserLoginDto userLoginDto) throws TestOrbitException;
	
	public UserDto registerNewUser(UserDto userDto) throws TestOrbitException ;
	public List<UserDto> listUnassignedTestManagers(long projectManagerId) throws TestOrbitException;
	public List<UserDto> listUsersToApprove(long projectManagerId) throws TestOrbitException;
	public UserDto approveUsers(UserApprovalDto approveDto) throws TestOrbitException ;
	public List<UserDto> listAllActiveProjectManagers() throws TestOrbitException;
	public List<UserDto> listActiveTesterForTestManagers(long testManagerID) throws TestOrbitException ;
	public List<UserDto> listUnassignedTestersToTestManagers(long projectManagerId) throws TestOrbitException;
	public List<UserDto> listAllActiveTestManagersUnderProjectManager(long projectManagerId) throws TestOrbitException;
    
}
