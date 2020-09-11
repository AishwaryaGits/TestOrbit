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
import com.testorbit.mainsorbit.dto.ModuleDto;
import com.testorbit.mainsorbit.dto.TesterAssignmentDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.service.ProjectManagerService;
import com.testorbit.mainsorbit.util.FrameworkConstants;
import com.testorbit.mainsorbit.util.GenericJsonBean;

/**
 *
 * @author NiL
 */

@RestController
@EnableWebMvc
@RequestMapping("projectManager")
public class ProjectManagerController {

	@Autowired
	ProjectManagerService projectManagerService;
	
	public ProjectManagerService getProjectManagerService() {
		return projectManagerService;
	}

	public void setProjectManagerService(ProjectManagerService projectManagerService) {
		this.projectManagerService = projectManagerService;
	}

	@RequestMapping(value="showModuleCreate",method=RequestMethod.GET)
	public ModelAndView showModuleCreationpage(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userRegistration") UserDto userRegistration ) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.moduleCreate.view");
        return  model;
    }
	
	@RequestMapping(value="showApproveUsers",method=RequestMethod.GET)
	public ModelAndView showApproveUsersPage(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userRegistration") UserDto userRegistration ) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.approveCreate.view");
        return  model;
    }
	
	@RequestMapping(value="showAssignTesters",method=RequestMethod.GET)
	public ModelAndView showAssignTesters(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userRegistration") UserDto userRegistration ) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.assignTesters.view");
        return  model;
    }
	@RequestMapping(value = "createModule", method = RequestMethod.POST)
	public @ResponseBody String createModule(@RequestBody ModuleDto moduleDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
		GenericJsonBean genericJsonBean = null;
		ModuleDto moduleDtoRes=null;
		try {
			
			if (moduleDto==null) {
				throw new Exception();
			}
			moduleDto.setProjectManagerId(userRegistration.getUserId());
			
			moduleDtoRes = projectManagerService.createNewModule(moduleDto);
			if(moduleDtoRes==null){
				throw new Exception();
			}
			genericJsonBean = new GenericJsonBean();
			
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
			genericJsonBean.setMSG("Module Added Successfully");
			genericJsonBean.setCONTENT(moduleDtoRes);
			
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
	
	
	@RequestMapping(value = "editModule", method = RequestMethod.POST)
	public @ResponseBody String editModule(@RequestBody ModuleDto moduleDto,@SessionAttribute("userRegistration") UserDto userRegistration) {
		GenericJsonBean genericJsonBean = null;
		ModuleDto moduleDtoRes=null;
		try {
			
			if (moduleDto==null) {
				throw new Exception();
			}
			moduleDto.setProjectManagerId(userRegistration.getUserId());
			if(moduleDto.getCurrentTestManagerId()!=0 && moduleDto.getTestManagerId()==0){
				moduleDto.setTestManagerId(moduleDto.getCurrentTestManagerId());
			}
			moduleDtoRes = projectManagerService.editModule(moduleDto);
			if(moduleDtoRes==null){
				throw new Exception();
			}
			genericJsonBean = new GenericJsonBean();
			
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_OK);
			genericJsonBean.setMSG("Module Added Successfully");
			genericJsonBean.setCONTENT(moduleDtoRes);
			
		}catch(TestOrbitException testOrbException){
			genericJsonBean = new GenericJsonBean();
			genericJsonBean.setSTS(FrameworkConstants.RES_STATUS_ERROR);
			genericJsonBean.setMSG(testOrbException.getMessage());
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
	
	
	
	//View Projects Under Project Manager
		@RequestMapping(value = "listProjectsUnderProjManager", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody String listProjectsUnderProjManager(@SessionAttribute("userRegistration") UserDto userRegistration) {
			
			GenericJsonBean genericJsonBean = null;
			List<ModuleDto> listprojects = null;
			try {

				listprojects=projectManagerService.listProjectsUnderProjManager(userRegistration.getUserId());
				
				if (null == listprojects) {
					throw new Exception();
				} else if (listprojects.isEmpty()) {
					throw new Exception();
				}
				genericJsonBean = new GenericJsonBean();
				genericJsonBean.setSTS("200");
				genericJsonBean.setMSG("success");
				genericJsonBean.setCONTENT(listprojects);

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
		
		//View Active Project Under Test Manager
				@RequestMapping(value = "listProjectUnderTestManager", method = RequestMethod.POST, consumes = "application/json")
				public @ResponseBody String listProjectUnderTestManager(@SessionAttribute("userRegistration") UserDto userRegistration) {
					
					GenericJsonBean genericJsonBean = null;
					ModuleDto testModule = null;
					try {

						testModule=projectManagerService.listProjectsUnderTestManager(userRegistration.getUserId());
						
						if (null == testModule) {
							throw new Exception();
						}
						genericJsonBean = new GenericJsonBean();
						genericJsonBean.setSTS("200");
						genericJsonBean.setMSG("success");
						genericJsonBean.setCONTENT(testModule);

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
				
				//List Users to be approved Under Project Manager
				@RequestMapping(value = "assignTesterToTestManager", method = RequestMethod.POST, consumes = "application/json")
				public @ResponseBody String assignTesterToTestManager(@SessionAttribute("userRegistration") UserDto userRegistration,@RequestBody  TesterAssignmentDto testerAssignmentDto) {
					
					GenericJsonBean genericJsonBean = null;
					UserDto userDtoRes=null;
					
					
					try {
						
						if (testerAssignmentDto==null) {
							throw new Exception();
						}
						
						userDtoRes = projectManagerService.assignTesterToTestManager(testerAssignmentDto);
						if(userDtoRes==null){
							throw new Exception();
						}
						genericJsonBean = new GenericJsonBean();
						genericJsonBean.setSTS("200");
						genericJsonBean.setMSG("success");
						genericJsonBean.setCONTENT(userDtoRes);

						
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
