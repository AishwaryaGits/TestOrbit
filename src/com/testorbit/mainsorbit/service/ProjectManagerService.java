/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.service;

import java.util.List;
import com.testorbit.mainsorbit.dto.ModuleDto;
import com.testorbit.mainsorbit.dto.TesterAssignmentDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface ProjectManagerService {
	
	public ModuleDto createNewModule(ModuleDto moduleDto) throws TestOrbitException;
	public ModuleDto editModule(ModuleDto moduleDto) throws TestOrbitException;
	public List<ModuleDto> listProjectsUnderProjManager(long projectManagerId) throws TestOrbitException;
	public ModuleDto listProjectsUnderTestManager(long testManagerId) throws TestOrbitException;
	public UserDto assignTesterToTestManager(TesterAssignmentDto testerAssignmentDto) throws TestOrbitException ;
	
}
