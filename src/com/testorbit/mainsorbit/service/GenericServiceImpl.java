/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.testorbit.mainsorbit.beans.UserBean;
import com.testorbit.mainsorbit.dao.GenericDAO;
import com.testorbit.mainsorbit.dto.UserApprovalDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.dto.UserLoginDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.DateUtility;
import com.testorbit.mainsorbit.util.FrameworkConstants;


public class GenericServiceImpl implements GenericService {

	@Autowired
    private GenericDAO genericDAO;
	
	


    public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	

   
	
	//Converts UserBean to UserDto
	private UserDto convertUserBeanToDto(UserBean userBean){
		UserDto userDto=new UserDto();
		userDto.setUserId(userBean.getUserId());
		userDto.setFirstName(userBean.getFirstName());
		userDto.setLastName(userBean.getLastName());
		userDto.setContactNo(userBean.getContactNo());
		userDto.setEmail(userBean.getEmail());
		userDto.setRoleId(userBean.getRoleId());
		userDto.setUserStatus(userBean.getUserStatus());
		userDto.setUserIdManager(userBean.getUserIdManager());
		userDto.setSuperManagerId(userBean.getSuperManagerId());
		userDto.setUserPassword(userBean.getUserPassword());
		userDto.setEmployeeId(userBean.getEmployeeId());
		return userDto;
	}
	
	//Converts UserBean to UserDto
	private UserBean convertUserDtoToBean(UserDto userDto){
		UserBean userBean=new UserBean();
		userBean.setUserId(userDto.getUserId());
		userBean.setFirstName(userDto.getFirstName());
		userBean.setLastName(userDto.getLastName());
		userBean.setContactNo(userDto.getContactNo());
		userBean.setEmail(userDto.getEmail());
		userBean.setRoleId(userDto.getRoleId());
		userBean.setUserStatus(userDto.getUserStatus());
		userBean.setUserIdManager(userDto.getUserIdManager());
		userBean.setSuperManagerId(userDto.getSuperManagerId());
		userBean.setUserPassword(userDto.getUserPassword());
		userBean.setEmployeeId(userDto.getEmployeeId());
		return userBean;
	}
	
	@Override
	public UserDto validateUser(UserLoginDto userLoginDto) throws TestOrbitException {
		UserBean userBean=new UserBean();
		UserDto userDto=null;
		userBean.setEmail(userLoginDto.getUserEmailId());
		userBean.setUserPassword(userLoginDto.getUserPassword());
		userBean=genericDAO.validateUser(userBean);
		if(userBean!=null){
		userDto=convertUserBeanToDto(genericDAO.validateUser(userBean));
		}
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) throws TestOrbitException {
		UserBean userBean=null;
		UserDto userDtoService=null;
		userBean=convertUserDtoToBean(userDto);
		
		if(userBean.getRoleId()!=FrameworkConstants.ROLE_MANAGER){
			userBean.setUserStatus(FrameworkConstants.STATUS_INACTIVE);
		}else{
			userBean.setUserStatus(FrameworkConstants.STATUS_ACTIVE);
		}
		
		
		userBean.setCreatDate(DateUtility.getCurrentDate());
		userBean.setModifyDate(DateUtility.getCurrentDate());
		userBean=genericDAO.registerNewUser(userBean);
		userDtoService=convertUserBeanToDto(userBean);
		return userDtoService;
	}

	@Override
	public List<UserDto> listUnassignedTestManagers(long projectManagerId) throws TestOrbitException {
		List<UserDto> listTestManagers=new ArrayList<UserDto>();
		List<UserBean> listTestManagersBean=null;
		UserDto userDto=null;
		listTestManagersBean=genericDAO.listUnassignedTestManagers(projectManagerId);
		if(listTestManagersBean!=null){
			for(UserBean userBean:listTestManagersBean){
				userDto=convertUserBeanToDto(userBean);
				listTestManagers.add(userDto);
			}
			
		}
		return listTestManagers;
	}
	
	@Override
	public List<UserDto> listUsersToApprove(long projectManagerId) throws TestOrbitException {
		List<UserDto> listUserToApprove=new ArrayList<UserDto>();
		List<UserBean> listUserBean=null;
		UserDto userDto=null;
		listUserBean=genericDAO.listUsersToApprove(projectManagerId);
		if(listUserBean!=null){
			for(UserBean userBean:listUserBean){
				userDto=convertUserBeanToDto(userBean);
				listUserToApprove.add(userDto);
			}
			
		}
		return listUserToApprove;
	}

	

	@Override
	public UserDto approveUsers(UserApprovalDto approveDto) throws TestOrbitException {
		UserBean userBean=null;
		UserDto userDtoService=null;
		char status=FrameworkConstants.STATUS_INACTIVE;
		if(approveDto.getUserIdResource()!=0){
			
		if(approveDto.getUserStatus() == 'Y' ){
			status=FrameworkConstants.STATUS_ACTIVE;
		}else if (approveDto.getUserStatus() == 'R' ){
			status=FrameworkConstants.STATUS_REJECTED;
		}
		userBean=genericDAO.approveUsers(approveDto.getUserIdResource(), status);
		userDtoService=convertUserBeanToDto(userBean);
		}
		return userDtoService;
	}

	@Override
	public List<UserDto> listAllActiveProjectManagers() throws TestOrbitException {
		List<UserDto> listActivePojectManagers=new ArrayList<UserDto>();
		List<UserBean> listUserBean=null;
		UserDto userDto=null;
		listUserBean=genericDAO.listAllActiveProjectManagers();
		if(listUserBean!=null){
			for(UserBean userBean:listUserBean){
				userDto=convertUserBeanToDto(userBean);
				listActivePojectManagers.add(userDto);
			}
			
		}
		return listActivePojectManagers;
	}
	
	@Override
	public List<UserDto> listActiveTesterForTestManagers(long testManagerID) throws TestOrbitException {
		List<UserDto> listActiveTesters=new ArrayList<UserDto>();
		List<UserBean> listUserBean=null;
		UserDto userDto=null;
		listUserBean=genericDAO.listTesterUnderTestManager(testManagerID);
		if(listUserBean!=null){
			for(UserBean userBean:listUserBean){
				userDto=convertUserBeanToDto(userBean);
				listActiveTesters.add(userDto);
			}
			
		}
		return listActiveTesters;
	}

	@Override
	public List<UserDto> listUnassignedTestersToTestManagers(long projectManagerId) throws TestOrbitException {
		List<UserDto> listTesters=new ArrayList<UserDto>();
		List<UserBean> listTestersBean=null;
		UserDto userDto=null;
		listTestersBean=genericDAO.listUnassignedTestersToTestManagers(projectManagerId);
		if(listTestersBean!=null){
			for(UserBean userBean:listTestersBean){
				userDto=convertUserBeanToDto(userBean);
				listTesters.add(userDto);
			}
			
		}
		return listTesters;
	}

	@Override
	public List<UserDto> listAllActiveTestManagersUnderProjectManager(long projectManagerId) throws TestOrbitException {
		List<UserDto> listTestManagers=new ArrayList<UserDto>();
		List<UserBean> listTestManagersBean=null;
		UserDto userDto=null;
		listTestManagersBean=genericDAO.listAllActiveTestManagersUnderProjectManager(projectManagerId);
		if(listTestManagersBean!=null){
			for(UserBean userBean:listTestManagersBean){
				userDto=convertUserBeanToDto(userBean);
				listTestManagers.add(userDto);
			}
			
		}
		return listTestManagers;
	}

}
