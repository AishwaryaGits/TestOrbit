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
import com.testorbit.mainsorbit.dto.ResultSummaryDto;
import com.testorbit.mainsorbit.dto.TaskDto;
import com.testorbit.mainsorbit.dto.TestCaseApprovalDto;
import com.testorbit.mainsorbit.dto.TestCaseDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.service.TesterService;
import com.testorbit.mainsorbit.util.FrameworkConstants;
import com.testorbit.mainsorbit.util.GenericJsonBean;

/**
 *
 * @author NiL
 */

@RestController
@EnableWebMvc
@RequestMapping("tester")
public class TesterController {

	@Autowired
	TesterService testerService;
	
	

	public TesterService getTesterService() {
		return testerService;
	}



	public void setTesterService(TesterService testerService) {
		this.testerService = testerService;
	}



	@RequestMapping(value="showTestCaseCreate",method=RequestMethod.GET)
	public ModelAndView showTestCaseCreatePage(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userRegistration") UserDto userRegistration ) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.testCaseCreate.view");
        return  model;
    }
	

	
	@RequestMapping(value = "createTestCase", method = RequestMethod.POST)
	public @ResponseBody String createTestCase(@RequestBody TestCaseDto testCaseDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
		GenericJsonBean genericJsonBean = null;
		TestCaseDto testCaseDtoRes=null;
		try {
			
			if (testCaseDto==null) {
				throw new Exception();
			}
			testCaseDto.setTesterId(userRegistration.getUserId());
			testCaseDtoRes = testerService.createNewTestCase(testCaseDto);
			if(testCaseDtoRes==null){
				throw new Exception();
			}
			genericJsonBean = new GenericJsonBean();
			
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
			genericJsonBean.setMSG("Module Added Successfully");
			genericJsonBean.setCONTENT(testCaseDtoRes);
			
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
	
	//View TestCases as per Task  Under Tester
			@RequestMapping(value = "listTestCasebasedOnTask", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String listTestCasebasedOnTask(@RequestBody TaskDto taskDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
				
				GenericJsonBean genericJsonBean = null;
				List<TestCaseDto> listTestCases = null;
				try {
					if(taskDto!=null && taskDto.getTaskId() !=0){
					listTestCases=testerService.listTestCasebasedOnTask(taskDto.getTaskId());
					}
					if (null == listTestCases) {
						throw new Exception();
					} else if (listTestCases.isEmpty()) {
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(listTestCases);

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
			
			//View TestCases to be approved
			@RequestMapping(value = "listTestCaseTobeApproved", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String listTestCaseTobeApproved(@RequestBody TaskDto taskDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
				
				GenericJsonBean genericJsonBean = null;
				List<TestCaseDto> listTestCases = null;
				try {
					if(taskDto!=null && taskDto.getTaskId() !=0){
					listTestCases=testerService.listTestCaseTobeApproved(taskDto.getTaskId());
					}
					if (null == listTestCases) {
						throw new Exception();
					} else if (listTestCases.isEmpty()) {
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(listTestCases);

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
			@RequestMapping(value = "modifyTestCaseStatus", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String modifyTestCaseStatus(@RequestBody  TestCaseApprovalDto testCaseApprovalDto) {
				
				GenericJsonBean genericJsonBean = null;
				TestCaseDto testCaseDtoRes=null;
				
				
				try {
					
					if (testCaseApprovalDto==null) {
						throw new Exception();
					}
					
					testCaseDtoRes = testerService.modifyTestCaseStatus(testCaseApprovalDto);
					if(testCaseDtoRes==null){
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					genericJsonBean.setSTS("200");
					genericJsonBean.setMSG("success");
					genericJsonBean.setCONTENT(testCaseDtoRes);

					
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

			
			@RequestMapping(value = "recordTestResult", method = RequestMethod.POST)
			public @ResponseBody String recordTestResult(@RequestBody TestCaseDto testCaseDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
				GenericJsonBean genericJsonBean = null;
				TestCaseDto testCaseDtoRes=null;
				try {
					
					if (testCaseDto==null) {
						throw new Exception();
					}
					testCaseDto.setTesterId(userRegistration.getUserId());
					testCaseDtoRes = testerService.recordTestResult(testCaseDto);
					if(testCaseDtoRes==null){
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					
					genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
					genericJsonBean.setMSG("Module Added Successfully");
					genericJsonBean.setCONTENT(testCaseDtoRes);
					
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
			
			//View Result Summary on Execution Date for a task 
			@RequestMapping(value = "getResultSummary", method = RequestMethod.POST, consumes = "application/json")
			public @ResponseBody String getResultSummary(@RequestBody ResultSummaryDto resSummary) {
				
				GenericJsonBean genericJsonBean = null;
				ResultSummaryDto resSummaryDtoRes=null;
				try {
					
					if (resSummary==null) {
						throw new Exception();
					}
				
					resSummaryDtoRes = testerService.getResultSummary(resSummary);
					if(resSummaryDtoRes==null){
						throw new Exception();
					}
					genericJsonBean = new GenericJsonBean();
					
					genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
					genericJsonBean.setMSG("Module Added Successfully");
					genericJsonBean.setCONTENT(resSummaryDtoRes);
					
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
