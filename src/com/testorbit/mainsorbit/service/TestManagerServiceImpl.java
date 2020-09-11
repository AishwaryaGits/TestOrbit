
package com.testorbit.mainsorbit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.testorbit.mainsorbit.beans.HistoryBean;
import com.testorbit.mainsorbit.beans.TaskBean;
import com.testorbit.mainsorbit.dao.GenericDAO;
import com.testorbit.mainsorbit.dao.ProjectManagerDAO;
import com.testorbit.mainsorbit.dao.TestManagerDAO;
import com.testorbit.mainsorbit.dto.HistoryDto;
import com.testorbit.mainsorbit.dto.TaskDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.DateUtility;
import com.testorbit.mainsorbit.util.FrameworkConstants;

public class TestManagerServiceImpl implements TestManagerService {

	@Autowired
	private TestManagerDAO testManagerDAO;

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

	public TestManagerDAO getTestManagerDAO() {
		return testManagerDAO;
	}

	public void setTestManagerDAO(TestManagerDAO testManagerDAO) {
		this.testManagerDAO = testManagerDAO;
	}

	// Converts TaskBean to TaskDto
	private TaskDto convertTaskBeanToDto(TaskBean taskBean) {
		TaskDto taskDto = new TaskDto();
		taskDto.setTaskId(taskBean.getTaskId());
		taskDto.setTaskDescription(taskBean.getTaskDescription());
		taskDto.setTaskStatus(taskBean.getTaskStatus());
		taskDto.setTestManagerId(taskBean.getTestManagerId());
		taskDto.setModuleId(taskBean.getModuleId());
		taskDto.setCreatDate(taskBean.getCreatDate());
		taskDto.setEndDate(taskBean.getEndDate());
		taskDto.setStartDate(taskBean.getStartDate());
		return taskDto;
	}

	// Converts TaskBean to TaskDto
	private TaskBean convertTaskDtoToBean(TaskDto taskDto) {
		TaskBean taskBean = new TaskBean();
		taskBean.setTaskId(taskDto.getTaskId());
		taskBean.setTaskDescription(taskDto.getTaskDescription());
		taskBean.setTaskStatus(taskDto.getTaskStatus());
		taskBean.setTestManagerId(taskDto.getTestManagerId());
		taskBean.setModuleId(taskDto.getModuleId());
		taskBean.setCreatDate(taskDto.getCreatDate());
		taskBean.setEndDate(taskDto.getEndDate());
		taskBean.setStartDate(taskDto.getStartDate());
		return taskBean;
	}

	@Override
	public TaskDto createNewTask(TaskDto taskDto) throws TestOrbitException {
		TaskBean taskBean = null;
		TaskDto taskDtoService = null;
		long taskId = 0;
		taskBean = convertTaskDtoToBean(taskDto);
		Date startDateMapped = null;
		taskBean.setCreatDate(DateUtility.getCurrentDate());

		if (taskBean.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_INPROGRESS)) {
			taskBean.setStartDate(DateUtility.getCurrentDate());
		}

		taskId = testManagerDAO.createNewTask(taskBean);
		if (taskId != 0 && taskDto.getTesterId() != 0) {
			if (taskBean.getStartDate() != null) {
				startDateMapped = taskBean.getStartDate();
			}
			taskBean = testManagerDAO.updateTaskToTester(taskId, taskDto.getTesterId(), startDateMapped);
		}

		taskDtoService = convertTaskBeanToDto(taskBean);
		return taskDtoService;
	}

	@Override
	public List<TaskDto> listTasksUnderTestManager(long testManagerId) throws TestOrbitException {
		List<TaskDto> listTasks = new ArrayList<TaskDto>();
		List<TaskBean> listTasksBean = null;
		TaskDto taskDto = null;
		listTasksBean = testManagerDAO.listTasksUnderTestManager(testManagerId);
		if (listTasksBean != null) {
			for (TaskBean taskBean : listTasksBean) {
				taskDto = convertTaskBeanToDto(taskBean);
				listTasks.add(taskDto);
			}

		}
		return listTasks;
	}

	@Override
	public List<TaskDto> listTasksAssignedToTester(long testerId) throws TestOrbitException {
		List<TaskDto> listTasks = new ArrayList<TaskDto>();
		List<TaskBean> listTasksBean = null;
		TaskDto taskDto = null;
		listTasksBean = testManagerDAO.listTasksAssignedToTester(testerId);
		if (listTasksBean != null) {
			for (TaskBean taskBean : listTasksBean) {
				taskDto = convertTaskBeanToDto(taskBean);
				listTasks.add(taskDto);
			}

		}
		return listTasks;
	}

	@Override
	public List<TaskDto> listTasksForTestCaseApproval(long testManagerId) throws TestOrbitException {
		List<TaskDto> listTasks = new ArrayList<TaskDto>();
		List<TaskBean> listTasksBean = null;
		TaskDto taskDto = null;
		listTasksBean = testManagerDAO.listTasksForTestCaseApproval(testManagerId);
		if (listTasksBean != null) {
			for (TaskBean taskBean : listTasksBean) {
				taskDto = convertTaskBeanToDto(taskBean);
				listTasks.add(taskDto);
			}

		}
		return listTasks;
	}

	@Override
	public TaskDto updateTaskStatusByTester(TaskDto taskDto) throws TestOrbitException {
		TaskBean taskBean = null;
		TaskBean existingTaskBean = null;
		TaskDto taskDtoService = null;
		if (taskDto.getTaskId() != 0) {
			existingTaskBean = testManagerDAO.getTaskDetails(taskDto.getTaskId());
			if (existingTaskBean != null) {
				if (!(taskDto.getTaskStatus().equals(existingTaskBean.getTaskStatus()))) {
					if ((taskDto.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_INPROGRESS))
							&& (existingTaskBean.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_TODO))) {

						if (existingTaskBean.getStartDate() == null) {
							existingTaskBean.setStartDate(DateUtility.getCurrentDate());
						}

					} else if ((taskDto.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_COMPLETE))) {

						existingTaskBean.setEndDate(DateUtility.getCurrentDate());
					}
					existingTaskBean.setTaskStatus(taskDto.getTaskStatus());
					taskBean = testManagerDAO.updateTaskStatusByTester(existingTaskBean);
				}

			}
		}
		taskDtoService = convertTaskBeanToDto(taskBean);
		return taskDtoService;

	}
	// Task edit by the test manager

	@Override
	public TaskDto editTask(TaskDto taskDto) throws TestOrbitException {
		TaskBean taskBean = null;
		TaskDto taskDtoService = null;
		TaskBean existingTaskBean = null;
		if (taskDto.getTaskId() != 0) {
			existingTaskBean = testManagerDAO.getTaskDetails(taskDto.getTaskId());
			if (existingTaskBean != null) {
				if (!(taskDto.getTaskStatus().equals(existingTaskBean.getTaskStatus()))) {

					if (taskDto.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_TODO)) {
						if (taskDto.getStartDate() != null) {
							existingTaskBean.setStartDate(taskDto.getStartDate());
						}
						if (taskDto.getEndDate() != null) {
							existingTaskBean.setEndDate(taskDto.getEndDate());
						}
					} else if (taskDto.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_INPROGRESS)) {
						if ((existingTaskBean.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_TODO))) {

							if (existingTaskBean.getStartDate() == null) {
								existingTaskBean.setStartDate(DateUtility.getCurrentDate());
							}

						}
						if (taskDto.getEndDate() != null) {
							existingTaskBean.setEndDate(taskDto.getEndDate());
						}
					} else if ((taskDto.getTaskStatus().equals(FrameworkConstants.TASK_STATUS_COMPLETE))) {

						existingTaskBean.setEndDate(DateUtility.getCurrentDate());
					}
					existingTaskBean.setTaskStatus(taskDto.getTaskStatus());

				}
				if (taskDto.getTaskDescription() != "") {
					existingTaskBean.setTaskDescription(taskDto.getTaskDescription());
				}
				taskBean = testManagerDAO.editTask(existingTaskBean);
				if (taskDto.getTesterId() != 0) {

					testManagerDAO.editTaskStatustoTester(existingTaskBean, taskDto.getTesterId());
				}
			}
		}

		taskDtoService = convertTaskBeanToDto(taskBean);
		return taskDtoService;
	}

	@Override
	public List<HistoryDto> listDatesTaskTestCasesExecuted(TaskDto taskDto) throws TestOrbitException {
		List<HistoryDto> listHistory = null;
		List<HistoryBean> listHistoryBean = null;
		HistoryDto historyDto = null;

		if (taskDto.getTaskId() != 0) {
			listHistoryBean = testManagerDAO.listDatesTaskTestCasesExecuted(taskDto.getTaskId());
			if (listHistoryBean != null) {
				listHistory = new ArrayList<HistoryDto>();
				for (HistoryBean historyBeans : listHistoryBean) {
					historyDto = convertHistoryBeanToDto(historyBeans);
					int count = 0;
					if (!listHistory.isEmpty()) {
						for (HistoryDto listhistoryDtos : listHistory) {
							if (listhistoryDtos.getExecutionDate().equals(historyDto.getExecutionDate())) {
								count++;
							}
						}

					}
					if (count == 0) {
						listHistory.add(historyDto);
					}

				}

			}
		}
		return listHistory;
	}

	// Converts HistoryBean to HistoryDto
	private HistoryDto convertHistoryBeanToDto(HistoryBean historyBean) {
		HistoryDto historyDto = new HistoryDto();
		historyDto.setHistoryID(historyBean.getHistoryID());
		historyDto.setModuleId(historyBean.getModuleId());
		historyDto.setResultStatus(historyBean.getResultStatus());
		historyDto.setTaskId(historyBean.getTaskId());
		historyDto.setTestCaseId(historyBean.getTestCaseId());
		historyDto.setTesterId(historyBean.getTesterId());
		historyDto.setExecutionDate(historyBean.getExecutionDate());

		return historyDto;
	}

}
