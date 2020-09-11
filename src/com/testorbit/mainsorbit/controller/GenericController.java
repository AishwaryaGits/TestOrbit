/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.testorbit.mainsorbit.dto.UserApprovalDto;
import com.testorbit.mainsorbit.dto.UserDto;
import com.testorbit.mainsorbit.dto.UserLoginDto;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.service.GenericService;
import com.testorbit.mainsorbit.util.FrameworkConstants;
import com.testorbit.mainsorbit.util.GenericJsonBean;

/**
 *
 * @author NiL
 */

@RestController
@EnableWebMvc
@RequestMapping("generic")
@SessionAttributes("userRegistration")
public class GenericController {

	@Autowired
	UserDto userRegistration;
	
	@Autowired
	GenericService genericService;
	
	@RequestMapping(value="loginUser",method=RequestMethod.GET)
	public ModelAndView showLoginpage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model=new ModelAndView(); 
		UserLoginDto userLoginDto=new UserLoginDto();
		model.addObject("userBean",userLoginDto);
		model.setViewName("testorbit.login.view");
        return  model;
    }

	@RequestMapping(value="registerUser",method=RequestMethod.GET)
	public ModelAndView showRegistrationpage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model=new ModelAndView(); 
		UserDto userDto=new UserDto();
		model.addObject("userDto",userDto);
		model.setViewName("testorbit.registration.view");
        return  model;
    }
	
	@RequestMapping(value="showModuleCreate",method=RequestMethod.GET)
	public ModelAndView showModuleCreationpage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model=new ModelAndView(); 
		model.addObject("userDto",userRegistration);
		model.setViewName("testorbit.moduleCreate.view");
        return  model;
    }

	@RequestMapping(value = "validateUser")
	public ModelAndView validateUser(@ModelAttribute("userBean")  UserLoginDto userLoginDto) {
		ModelAndView model=new ModelAndView(); 
		String infoMsg = "";
		
		
		try {
			
			if (userLoginDto==null) {
				throw new Exception();
			}
			
			
			 userRegistration = genericService.validateUser(userLoginDto);
			if(userRegistration==null){
				throw new Exception();
			}
			if(userRegistration.getRoleId() == FrameworkConstants.ROLE_MANAGER){
				model.setViewName("testorbit.managerDashBoard.view");
			}else if(userRegistration.getRoleId() == FrameworkConstants.ROLE__TEST_MANAGER){
				model.setViewName("testorbit.testManagerDashBoard.view");
			}else{
				model.setViewName("testorbit.testerDashBoard.view");
			}
			
			infoMsg="You can login post approval from manager.";
			model.addObject("userRegistration", userRegistration);
			
			model.addObject("infoMsg", infoMsg);
		}catch(TestOrbitException testOrbException){
			testOrbException.printStackTrace();
			infoMsg="Please Check Your Login Credentials";
			model.setViewName("testorbit.login.error");
			model.addObject("infoMsg", infoMsg);
		}
		
		catch (Exception e) {
			infoMsg="Please Check Your Login Credentials";
			e.printStackTrace();
			model.setViewName("testorbit.login.error");
			model.addObject("infoMsg", infoMsg);
			
			
		}
		return  model;
	}
	
	
	@RequestMapping(value = "addNewUser{userAs}/{managerId}", method = RequestMethod.POST)
	public ModelAndView addNewUser(@ModelAttribute("userDto")  UserDto userDto,
			@RequestParam int userAs,@RequestParam long managerId) {
		ModelAndView model=new ModelAndView(); 
		String infoMsg = "";
		
		UserDto userDtoRes=null;
		
		
		try {
			
			if (userDto==null) {
				throw new Exception();
			}
			if(userAs!=FrameworkConstants.ROLE_MANAGER){
				userDto.setUserIdManager(managerId);
				userDto.setSuperManagerId(managerId);
			}
			userDto.setRoleId(userAs);
			userDtoRes = genericService.registerNewUser(userDto);
			if(userDtoRes==null){
				throw new Exception();
			}
			if(userAs==FrameworkConstants.ROLE_MANAGER){
				model.setViewName("testorbit.loginManager.success");
			}else{
				infoMsg="You can login post approval from manager.";
				model.setViewName("testorbit.login.success");
			}
			
			model.addObject("infoMsg", infoMsg);
			
		}catch(TestOrbitException testOrbException){
			infoMsg="We are facing some issue!!";
			model.setViewName("testorbit.login.error");
			model.addObject("infoMsg", infoMsg);
		}
		
		catch (Exception e) {
			infoMsg="We are facing some issue!!";
			e.printStackTrace();
			model.setViewName("testorbit.login.error");
			model.addObject("infoMsg", infoMsg);
			
		
			
		}
		
		return  model;
	}
	//View Unassigned Test Managers
	@RequestMapping(value = "listUnassignedTestManagers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String unassignedTestManagers(@SessionAttribute("userRegistration") UserDto userRegistration) {
		
		GenericJsonBean genericJsonBean = null;
		List<UserDto> listTestmanager = null;
		try {

			listTestmanager=genericService.listUnassignedTestManagers(userRegistration.getUserId());
			
			if (null == listTestmanager) {
				throw new Exception();
			} else if (listTestmanager.isEmpty()) {
				throw new Exception();
			}
			genericJsonBean = new GenericJsonBean();
			genericJsonBean.setSTS("200");
			genericJsonBean.setMSG("success");
			genericJsonBean.setCONTENT(listTestmanager);

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
	
	public com.testorbit.mainsorbit.service.GenericService getGenericService() {
		return genericService;
	}

	public void setGenericService(com.testorbit.mainsorbit.service.GenericService genericService) {
		this.genericService = genericService;
	}

	
	
	//List Users to be approved Under Project Manager
	@RequestMapping(value = "listUsersToApprove", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String listUsersToApprove(@SessionAttribute("userRegistration") UserDto userRegistration) {
		
		GenericJsonBean genericJsonBean = null;
		List<UserDto> listUsersToApprove = null;
		try {

			listUsersToApprove=genericService.listUsersToApprove(userRegistration.getUserId());
			if (null == listUsersToApprove) {
				throw new Exception();
			} else if (listUsersToApprove.isEmpty()) {
				throw new Exception();
			}
			genericJsonBean = new GenericJsonBean();
			genericJsonBean.setSTS("200");
			genericJsonBean.setMSG("success");
			genericJsonBean.setCONTENT(listUsersToApprove);

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

	//List All Active  Project Managers
		@RequestMapping(value = "listActiveProjectManagers", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody String listAllActiveProjectManagers() {
			
			GenericJsonBean genericJsonBean = null;
			List<UserDto> listActiveProjectManagers = null;
			try {

				listActiveProjectManagers=genericService.listAllActiveProjectManagers();
				if (null == listActiveProjectManagers) {
					throw new Exception();
				} else if (listActiveProjectManagers.isEmpty()) {
					throw new Exception();
				}
				genericJsonBean = new GenericJsonBean();
				genericJsonBean.setSTS("200");
				genericJsonBean.setMSG("success");
				genericJsonBean.setCONTENT(listActiveProjectManagers);

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
		@RequestMapping(value = "approveUser", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody String approveUser(@SessionAttribute("userRegistration") UserDto userRegistration,@RequestBody  UserApprovalDto approveDto) {
			
			GenericJsonBean genericJsonBean = null;
			UserDto userDtoRes=null;
			
			
			try {
				
				if (approveDto==null) {
					throw new Exception();
				}
				
				userDtoRes = genericService.approveUsers(approveDto);
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

		
		//List  Active Testers under Test Managers for drop down for task assignment
				@RequestMapping(value = "listActiveTesterForTestManagers", method = RequestMethod.POST, consumes = "application/json")
				public @ResponseBody String listActiveTesterForTestManagers(@SessionAttribute("userRegistration") UserDto userRegistration) {
					
					GenericJsonBean genericJsonBean = null;
					List<UserDto> listActiveTesters = null;
					try {

						listActiveTesters=genericService.listActiveTesterForTestManagers(userRegistration.getUserId());
						if (null == listActiveTesters) {
							throw new Exception();
						} else if (listActiveTesters.isEmpty()) {
							throw new Exception();
						}
						genericJsonBean = new GenericJsonBean();
						genericJsonBean.setSTS("200");
						genericJsonBean.setMSG("success");
						genericJsonBean.setCONTENT(listActiveTesters);

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
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("testorbit.test.view");
    }
	
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("testorbit.test.add");
    }
	
	//View Unassigned Testers to TestManagers
		@RequestMapping(value = "listUnassignedTestersToTestManagers", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody String listUnassignedTestersToTestManagers(@SessionAttribute("userRegistration") UserDto userRegistration) {
			
			GenericJsonBean genericJsonBean = null;
			List<UserDto> listTesters = null;
			try {

				listTesters=genericService.listUnassignedTestersToTestManagers(userRegistration.getUserId());
				
				if (null == listTesters) {
					throw new Exception();
				} else if (listTesters.isEmpty()) {
					throw new Exception();
				}
				genericJsonBean = new GenericJsonBean();
				genericJsonBean.setSTS("200");
				genericJsonBean.setMSG("success");
				genericJsonBean.setCONTENT(listTesters);

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
		
		//View Unassigned Test Managers
		@RequestMapping(value = "listAllActiveTestManagersUnderProjectManager", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody String listAllActiveTestManagersUnderProjectManager(@SessionAttribute("userRegistration") UserDto userRegistration) {
			
			GenericJsonBean genericJsonBean = null;
			List<UserDto> listTestmanager = null;
			try {

				listTestmanager=genericService.listAllActiveTestManagersUnderProjectManager(userRegistration.getUserId());
				
				if (null == listTestmanager) {
					throw new Exception();
				} else if (listTestmanager.isEmpty()) {
					throw new Exception();
				}
				genericJsonBean = new GenericJsonBean();
				genericJsonBean.setSTS("200");
				genericJsonBean.setMSG("success");
				genericJsonBean.setCONTENT(listTestmanager);

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
