/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.service;

import java.util.List;

import com.testorbit.mainsorbit.dto.HistoryDto;
import com.testorbit.mainsorbit.dto.TaskDto;

import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface TestManagerService {
	
	public TaskDto createNewTask(TaskDto taskDto) throws TestOrbitException;
	public List<TaskDto> listTasksUnderTestManager(long testManagerId) throws TestOrbitException;
	public List<TaskDto> listTasksAssignedToTester(long testerId) throws TestOrbitException;
	public List<TaskDto> listTasksForTestCaseApproval(long testManagerId) throws TestOrbitException;
	public TaskDto updateTaskStatusByTester(TaskDto taskDto) throws TestOrbitException ;
	public TaskDto editTask(TaskDto taskDto) throws TestOrbitException;
	public List<HistoryDto> listDatesTaskTestCasesExecuted(TaskDto taskDto) throws TestOrbitException;
}
