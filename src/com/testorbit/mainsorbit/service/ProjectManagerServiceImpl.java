/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.testorbit.mainsorbit.beans.ModuleBean;
import com.testorbit.mainsorbit.beans.ModuleToTesterMappingBean;
import com.testorbit.mainsorbit.beans.UserBean;
import com.testorbit.mainsorbit.dao.GenericDAO;
import com.testorbit.mainsorbit.dao.ProjectManagerDAO;
import com.testorbit.mainsorbit.dto.ModuleDto;
import com.testorbit.mainsorbit.dto.TesterAssignmentDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.DateUtility;
import com.testorbit.mainsorbit.util.FrameworkConstants;


public class ProjectManagerServiceImpl implements ProjectManagerService {

	@Autowired
	private ProjectManagerDAO projectManagerDAO;

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	public ProjectManagerDAO getProjectManagerDAO() {
		return projectManagerDAO;
	}

	public void setProjectManagerDAO(ProjectManagerDAO projectManagerDAO) {
		this.projectManagerDAO = projectManagerDAO;
	}

	// Converts UserBean to ModuleDto
	private ModuleDto convertModuleBeanToDto(ModuleBean moduleBean) {
		ModuleDto moduleDto = new ModuleDto();
		moduleDto.setModuleId(moduleBean.getModuleId());
		moduleDto.setModuleName(moduleBean.getModuleName());
		moduleDto.setModuleDescription(moduleBean.getModuleDescription());
		moduleDto.setProjectManagerId(moduleBean.getProjectManagerId());
		moduleDto.setTestManagerId(moduleBean.getTestManagerId());
		moduleDto.setModuleStatus(moduleBean.getModuleStatus());
		moduleDto.setCreatDate(moduleBean.getCreatDate());
		moduleDto.setEndDate(moduleBean.getEndDate());
		return moduleDto;
	}

	// Converts ModuleBean to ModuleDto
	private ModuleBean convertModuleDtoToBean(ModuleDto moduleDto) {
		ModuleBean moduleBean = new ModuleBean();
		moduleBean.setModuleId(moduleDto.getModuleId());
		moduleBean.setModuleName(moduleDto.getModuleName());
		moduleBean.setModuleDescription(moduleDto.getModuleDescription());
		moduleBean.setProjectManagerId(moduleDto.getProjectManagerId());
		moduleBean.setTestManagerId(moduleDto.getTestManagerId());
		moduleBean.setModuleStatus(moduleDto.getModuleStatus());
		moduleBean.setCreatDate(moduleDto.getCreatDate());
		moduleBean.setEndDate(moduleDto.getEndDate());
		return moduleBean;
	}

	@Override
	public ModuleDto createNewModule(ModuleDto moduleDto) throws TestOrbitException {
		ModuleBean moduleBean = null;
		long moduleId = 0;
		List<UserBean> testerList = null;
		ModuleDto moduleDtoService = null;
		moduleBean = convertModuleDtoToBean(moduleDto);
		moduleBean.setCreatDate(DateUtility.getCurrentDate());

		if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_NOTSTARTED)) {
			moduleBean.setEndDate(null);
			moduleBean.setStartDate(null);
		} else if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_ACTIVE)) {
			moduleBean.setStartDate(DateUtility.getCurrentDate());
			if (moduleDto.getEndDate() != null) {
				moduleBean.setEndDate(moduleDto.getEndDate());
			} else {
				moduleBean.setEndDate(null);
			}
		} else {
			moduleBean.setEndDate(DateUtility.getCurrentDate());
		}

		moduleId = projectManagerDAO.createNewModule(moduleBean);
		if (moduleId != 0 && moduleDto.getTestManagerId() != 0) {
			testerList = genericDAO.listTesterUnderTestManager(moduleDto.getTestManagerId());
		}
		if (testerList != null && (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_ACTIVE))) {
			moduleBean = projectManagerDAO.updateModuleToTester(testerList, moduleId);
		}
		moduleDtoService = convertModuleBeanToDto(moduleBean);
		return moduleDtoService;
	}

	@Override
	public List<ModuleDto> listProjectsUnderProjManager(long projectManagerId) throws TestOrbitException {
		List<ModuleDto> listProjects = new ArrayList<ModuleDto>();
		List<ModuleBean> listProjectsBean = null;
		ModuleDto moduleDto = null;
		listProjectsBean = projectManagerDAO.listProjectsUnderProjManager(projectManagerId);
		if (listProjectsBean != null) {
			for (ModuleBean moduleBean : listProjectsBean) {
				moduleDto = convertModuleBeanToDto(moduleBean);
				listProjects.add(moduleDto);
			}

		}
		return listProjects;
	}

	@Override
	public ModuleDto editModule(ModuleDto moduleDto) throws TestOrbitException {
		ModuleBean moduleBean = null;
		ModuleBean moduleBeanToCheckProj = null;
		ModuleBean existingModuleBean = null;
		long moduleId = 0;
		boolean testManagerChanged = false;
		boolean modulerHasTesters = false;
		List<UserBean> oldManagertesterList = null;
		List<UserBean> newManagertesterList = null;
		List<ModuleToTesterMappingBean> modulerHasTestersList = null;
		ModuleDto moduleDtoService = null;
		moduleBean = convertModuleDtoToBean(moduleDto);
		if (moduleBean.getModuleId() != 0) {
			existingModuleBean = projectManagerDAO.getModuleDetails(moduleBean.getModuleId());
			if (existingModuleBean != null) {
				moduleBean.setCreatDate(existingModuleBean.getCreatDate());
				if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_NOTSTARTED)) {
					moduleBean.setEndDate(null);
					moduleBean.setStartDate(null);
				} else if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_ACTIVE)) {
					// set start date
					if (existingModuleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_ACTIVE)) {
						moduleBean.setStartDate(existingModuleBean.getStartDate());
					} else {
						moduleBean.setStartDate(DateUtility.getCurrentDate());
						moduleBeanToCheckProj = projectManagerDAO.listProjectsUnderTestManager(moduleBean.getTestManagerId());
						if(moduleBeanToCheckProj!=null){
							throw new TestOrbitException("Test Manager has active projects");
						}
					}
					// set end date
					if (moduleDto.getEndDate() != null) {
						moduleBean.setEndDate(moduleDto.getEndDate());
					} else {
						moduleBean.setEndDate(null);
					}
				} else if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_INACTIVE)) {
					// set start date
					moduleBean.setStartDate(existingModuleBean.getStartDate());
					moduleBean.setEndDate(DateUtility.getCurrentDate());
					// set end date

				}
				projectManagerDAO.editModule(moduleBean);

				// scenario if test manager is changed
				if ((moduleBean.getTestManagerId() != 0)
						&& (moduleBean.getTestManagerId() != existingModuleBean.getTestManagerId())) {
					testManagerChanged = true;
				}

				if (!(moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_INACTIVE))) {

					modulerHasTestersList = projectManagerDAO.listModulesToTesters(moduleBean.getModuleId());
					if ((modulerHasTestersList != null && !modulerHasTestersList.isEmpty())) {
						modulerHasTesters = true;
					}
					// Update the user_table with new manager ID.
					if (testManagerChanged && existingModuleBean.getTestManagerId() != 0) {
						oldManagertesterList = genericDAO
								.listTesterUnderTestManager(existingModuleBean.getTestManagerId());

					}
					if (testManagerChanged && moduleBean.getTestManagerId() != 0) {
						newManagertesterList = genericDAO.listTesterUnderTestManager(moduleBean.getTestManagerId());

					}

					if (oldManagertesterList != null) {
						projectManagerDAO.updateTesterToNewManager(oldManagertesterList, moduleBean.getTestManagerId());
					}
					// existing testers of new manager assigned to project
					// manager.

					// If module has already testers then
					if (modulerHasTesters) {
						if (newManagertesterList != null && !newManagertesterList.isEmpty()) {
							projectManagerDAO.updateTesterToNewManager(newManagertesterList,
									moduleBean.getProjectManagerId());
						}
					} else {

						if (newManagertesterList != null
								&& (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_ACTIVE))) {
							moduleBean = projectManagerDAO.updateModuleToTester(newManagertesterList, moduleId);
						}
					}
				} else if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_INACTIVE)) {
					if (moduleBean.getModuleId() != 0) {
						projectManagerDAO.updateModuleToTesterStatus(moduleBean.getModuleId());
					}

				}
			}

		}

		moduleDtoService = convertModuleBeanToDto(moduleBean);
		return moduleDtoService;
	}

	@Override
	public ModuleDto listProjectsUnderTestManager(long testManagerId) throws TestOrbitException {
		ModuleDto moduleDto = null;
		ModuleBean moduleBean = null;
		moduleBean = projectManagerDAO.listProjectsUnderTestManager(testManagerId);
		if (moduleBean != null) {

			moduleDto = convertModuleBeanToDto(moduleBean);

		}

		return moduleDto;
	}

	@Override
	public UserDto assignTesterToTestManager(TesterAssignmentDto testerAssignmentDto) throws TestOrbitException {
		UserDto userDto = null;
		ModuleBean moduleBean = null;
		UserBean userBean = null;
		ModuleToTesterMappingBean mappingBean = null;
		userBean = projectManagerDAO.assignTesterToTestManager(testerAssignmentDto.getUserIdTestManager(),
				testerAssignmentDto.getUserIdTester());
		if (testerAssignmentDto.getUserIdTestManager() != 0) {
			moduleBean = projectManagerDAO.listProjectsUnderTestManager(testerAssignmentDto.getUserIdTestManager());
			if (moduleBean != null) {
				mappingBean = new ModuleToTesterMappingBean();
				mappingBean.setModuleId(moduleBean.getModuleId());
				mappingBean.setTesterUserId(testerAssignmentDto.getUserIdTester());
				if (moduleBean.getModuleStatus().equals(FrameworkConstants.MODULE_STATUS_ACTIVE)) {
					mappingBean.setProjectStatus(FrameworkConstants.STATUS_ACTIVE);
				} else {
					mappingBean.setProjectStatus(FrameworkConstants.STATUS_INACTIVE);
				}
				projectManagerDAO.addModuleToTester(mappingBean);
			}
		}
		userDto = new UserDto();
		userDto.setUserId(userBean.getUserId());
		userDto.setFirstName(userBean.getFirstName());
		userDto.setLastName(userBean.getLastName());
		return userDto;
	}

}
