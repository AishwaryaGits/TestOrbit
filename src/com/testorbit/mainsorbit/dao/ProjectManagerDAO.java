/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.List;

import com.testorbit.mainsorbit.beans.ModuleBean;
import com.testorbit.mainsorbit.beans.ModuleToTesterMappingBean;
import com.testorbit.mainsorbit.beans.UserBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface ProjectManagerDAO {
	
	public long createNewModule(ModuleBean moduleBean) throws TestOrbitException;
	
	public ModuleBean editModule(ModuleBean moduleBean) throws TestOrbitException;
	
	public ModuleBean getModuleDetails(long moduleId) throws TestOrbitException;
	
	public ModuleBean updateModuleToTester(List<UserBean> testerList,long moduleId) throws TestOrbitException;
	
	public ModuleBean updateModuleToTesterStatus(long moduleId) throws TestOrbitException;
	
	public List<ModuleBean> listProjectsUnderProjManager(long projectManagerId) throws TestOrbitException;
	
	public List<ModuleToTesterMappingBean> listModulesToTesters(long moduleId) throws TestOrbitException;
	
	public UserBean updateTesterToNewManager(List<UserBean> testerList,long testManagerId) throws TestOrbitException;
	
	public ModuleBean listProjectsUnderTestManager(long testManagerId) throws TestOrbitException;
	
	public UserBean assignTesterToTestManager(long testManagerId,long testerId) throws TestOrbitException ;
	
	public ModuleBean addModuleToTester(ModuleToTesterMappingBean projectTesterBean) throws TestOrbitException;
}
