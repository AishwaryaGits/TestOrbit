/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.Date;
import java.util.List;

import com.testorbit.mainsorbit.beans.HistoryBean;
import com.testorbit.mainsorbit.beans.TaskBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;


public interface TestManagerDAO {
	
	public long createNewTask(TaskBean taskBean) throws TestOrbitException;
	public TaskBean getTaskDetails(long taskId) throws TestOrbitException;
	public TaskBean updateTaskToTester(long taskId,long testerId,Date startDate) throws TestOrbitException;
	public List<TaskBean> listTasksUnderTestManager(long testManagerId) throws TestOrbitException;
	public List<TaskBean> listTasksAssignedToTester(long testerId) throws TestOrbitException;
	public List<TaskBean> listTasksForTestCaseApproval(long testManagerId) throws TestOrbitException;
	public TaskBean updateTaskStatusByTester(TaskBean taskBean) throws TestOrbitException ;
	public TaskBean editTask(TaskBean taskBean) throws TestOrbitException;
	public TaskBean editTaskStatustoTester(TaskBean taskBean,long testerId) throws TestOrbitException ;
	public List<HistoryBean> listDatesTaskTestCasesExecuted(long taskId) throws TestOrbitException;
	
}
