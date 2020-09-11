/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testorbit.mainsorbit.dto.HistoryDto;
import com.testorbit.mainsorbit.dto.TaskDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.service.TestManagerService;
import com.testorbit.mainsorbit.util.FrameworkConstants;
import com.testorbit.mainsorbit.util.GenericJsonBean;

/**
 *
 * @author NiL
 */

@RestController
@EnableWebMvc
@RequestMapping("testManager")
public class TestManagerController {

	@Autowired
	TestManagerService testManagerService;
	

	public TestManagerService getTestManagerService() {
		return testManagerService;
	}


	public void setTestManagerService(TestManagerService testManagerService) {
		this.testManagerService = testManagerService;
	}


	@RequestMapping(value="showTaskCreate",method=RequestMethod.GET)
	public ModelAndView showTaskCreationpage(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userRegistration") UserDto userRegistration ) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.taskCreate.view");
        return  model;
    }
	
	@RequestMapping(value="showApproveTestCasePage",method=RequestMethod.GET)
	public ModelAndView showApproveTestCasePage(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userRegistration") UserDto userRegistration ) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.approveTestCases.view");
        return  model;
    }
	@RequestMapping(value = "createTask", method = RequestMethod.POST)
	public @ResponseBody String createTask(@RequestBody TaskDto taskDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
		GenericJsonBean genericJsonBean = null;
		TaskDto taskDtoRes=null;
		try {
			
			if (taskDto==null) {
				throw new Exception();
			}
			taskDto.setTestManagerId(userRegistration.getUserId());
			taskDtoRes = testManagerService.createNewTask(taskDto);
			if(taskDtoRes==null){
				throw new Exception();
			}
			genericJsonBean = new GenericJsonBean();
			
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
			genericJsonBean.setMSG("Module Added Successfully");
			genericJsonBean.setCONTENT(taskDtoRes);
			
		}catch(TestOrbitException testOrbException){
			genericJsonBean = new GenericJsonBean();
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_ERROR);
			genericJsonBean.setMSG("Error");
			genericJsonBean.setCONTENT("Something going wrong");
		}
		
		catch (Exception e) {
			genericJsonBean = new GenericJsonBean();
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_ERROR);
			genericJsonBean.setMSG("Error");
			genericJsonBean.setCONTENT("Something going wrong");
				
		}
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(genericJsonBean);
	}
	
	//View Tasks UnderTestManager
			@RequestMapping(value = "listTasksUnderTestManager", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String listTasksUnderTestManager(@SessionAttribute("userRegistration") UserDto userRegistration) {
				
				GenericJsonBean genericJsonBean = null;
				List<TaskDto> listTasks = null;
				try {

					listTasks=testManagerService.listTasksUnderTestManager(userRegistration.getUserId());
					
					if (null == listTasks) {
						throw new Exception();
					} else if (listTasks.isEmpty()) {
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(listTasks);

				}catch(TestOrbitException testOrbException){
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				catch (Exception ex) { // Catch block for unhandle exception
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();

				return gson.toJson(genericJsonBean);
			}
			
		
			//View TestCases as per Task  Under Tester
			@RequestMapping(value = "listTasksAssignedToTester", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String listTasksAssignedToTester(@SessionAttribute("userRegistration") UserDto userRegistration) {
				
				GenericJsonBean genericJsonBean = null;
				List<TaskDto> listTaskToTesters = null;
				try {
					
					listTaskToTesters=testManagerService.listTasksAssignedToTester(userRegistration.getUserId());
					
					if (null == listTaskToTesters) {
						throw new Exception();
					} else if (listTaskToTesters.isEmpty()) {
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(listTaskToTesters);

				}catch(TestOrbitException testOrbException){
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				catch (Exception ex) { // Catch block for unhandle exception
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();

				return gson.toJson(genericJsonBean);
			}
		
			
			//View Tasks UnderTestManager for test case approval
			@RequestMapping(value = "listTasksForTestCaseApproval", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String listTasksForTestCaseApproval(@SessionAttribute("userRegistration") UserDto userRegistration) {
				
				GenericJsonBean genericJsonBean = null;
				List<TaskDto> listTasks = null;
				try {

					listTasks=testManagerService.listTasksForTestCaseApproval(userRegistration.getUserId());
					
					if (null == listTasks) {
						throw new Exception();
					} else if (listTasks.isEmpty()) {
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(listTasks);

				}catch(TestOrbitException testOrbException){
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				catch (Exception ex) { // Catch block for unhandle exception
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();

				return gson.toJson(genericJsonBean);
			}
			
			//List Users to be approved Under Project Manager and to send for approval
			@RequestMapping(value = "updateTaskStatusByTester", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String updateTaskStatusByTester(@RequestBody TaskDto taskDto) {
				
				GenericJsonBean genericJsonBean = null;
				TaskDto taskDtoRes=null;
				
				
				try {
					
					if (taskDto==null) {
						throw new Exception();
					}
					
					taskDtoRes = testManagerService.updateTaskStatusByTester(taskDto);
					if(taskDtoRes==null){
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(taskDtoRes);

					
				}catch(TestOrbitException testOrbException){
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				catch (Exception ex) { // Catch block for unhandle exception
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();

				return gson.toJson(genericJsonBean);
			}
			
			//edit task by testmanager
			
			@RequestMapping(value = "editTask", method = RequestMethod.POST)
			public @ResponseBody String editTask(@RequestBody TaskDto taskDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
				GenericJsonBean genericJsonBean = null;
				
				TaskDto taskDtoRes=null;
				try {
					
					if (taskDto==null) {
						throw new Exception();
					}
					taskDto.setTestManagerId(userRegistration.getUserId());
					taskDtoRes = testManagerService.editTask(taskDto);
					if(taskDtoRes==null){
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					
					genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
					genericJsonBean.setMSG("Module Added Successfully");
					genericJsonBean.setCONTENT(taskDtoRes);
					
				}catch(TestOrbitException testOrbException){
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_ERROR);
					genericJsonBean.setMSG("Error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				
				catch (Exception e) {
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_ERROR);
					genericJsonBean.setMSG("Error");
					genericJsonBean.setCONTENT("Something going wrong");
						
				}
				
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				return gson.toJson(genericJsonBean);
			}

			//List Dates Task Testcases Executed
			@RequestMapping(value = "listDatesTaskTestCasesExecuted", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String listDatesTaskTestCasesExecuted(@RequestBody TaskDto taskDto) {
				
				GenericJsonBean genericJsonBean = null;
				List<HistoryDto> listHistoryDates = null;
				try {
					
					if (taskDto==null) {
						throw new Exception();
					}

					listHistoryDates=testManagerService.listDatesTaskTestCasesExecuted(taskDto);
					
					if (null == listHistoryDates) {
						throw new Exception();
					} else if (listHistoryDates.isEmpty()) {
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(listHistoryDates);

				}catch(TestOrbitException testOrbException){
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				catch (Exception ex) { // Catch block for unhandle exception
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("500");
					genericJsonBean.setMSG("error");
					genericJsonBean.setCONTENT("Something going wrong");
				}
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();

				return gson.toJson(genericJsonBean);
			}
	
}
