/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.testorbit.mainsorbit.beans.ModuleBean;
import com.testorbit.mainsorbit.beans.ModuleToTesterMappingBean;
import com.testorbit.mainsorbit.beans.UserBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.FrameworkConstants;


@SuppressWarnings("deprecation")
public class ProjectManagerDAOImpl extends HibernateDaoSupport  implements ProjectManagerDAO {

	@Override
	public long createNewModule(ModuleBean moduleBean) throws TestOrbitException {

        return (long) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	   
       		long moduleId=0;
       		moduleId= (long) session.save(moduleBean);
       		if(moduleId!=0 ){
       			return moduleId;
        		
    		}
       		return moduleId;
       		
       	}
       });
   
	}

	@Override
	public ModuleBean updateModuleToTester(List<UserBean> testerList,long moduleId) throws TestOrbitException {

        return (ModuleBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	ModuleToTesterMappingBean mappingBean=null;
        	ModuleBean moduleBeanDao=null;
        	for(UserBean userBean : testerList){
        		mappingBean=new ModuleToTesterMappingBean();
        		mappingBean.setTesterUserId(userBean.getUserId());
        		mappingBean.setModuleId(moduleId);
        		mappingBean.setProjectStatus(FrameworkConstants.STATUS_ACTIVE);
        		session.saveOrUpdate(mappingBean);
        	}
        	moduleBeanDao=(ModuleBean)session.get(ModuleBean.class, moduleId);
        	return moduleBeanDao;
       	}
       });
   
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleBean> listProjectsUnderProjManager(long projectManagerId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(ModuleBean.class);
	                criteria.add(Restrictions.eq("projectManagerId", projectManagerId));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}

	@Override
	public ModuleBean editModule(ModuleBean moduleBean) throws TestOrbitException {

        return (ModuleBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
       		
        	ModuleBean moduleBeanDao=null;
       		
       		session.saveOrUpdate(moduleBean);
       		moduleBeanDao=(ModuleBean)session.get(ModuleBean.class, moduleBean.getModuleId());
        	return moduleBeanDao;
       	
       	}
       });
   
	}

	@Override
	public ModuleBean getModuleDetails(long moduleId) throws TestOrbitException {

        return (ModuleBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	   ModuleBean moduleBeanDao=null;
        	if(moduleId!=0){
       		moduleBeanDao=(ModuleBean)session.get(ModuleBean.class,moduleId);
        	return moduleBeanDao;
        	}
        	return moduleBeanDao;
       	}
       });
   
	}

	@Override
	public UserBean updateTesterToNewManager(List<UserBean> testerList, long testManagerId)
			throws TestOrbitException {

        return (UserBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	   UserBean userBean=null;
        	for(UserBean userBeanLst : testerList){
        		userBean=(UserBean)session.get(UserBean.class,userBeanLst.getUserId());
        		userBean.setUserIdManager(testManagerId);
        		session.saveOrUpdate(userBean);
        	}
        	
        	return null;
       	}
       });
   
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleToTesterMappingBean> listModulesToTesters(long moduleId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(ModuleToTesterMappingBean.class);
	                criteria.add(Restrictions.eq("moduleId", moduleId));
	                criteria.add(Restrictions.eq("projectStatus", FrameworkConstants.STATUS_ACTIVE));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}

	@Override
	public ModuleBean updateModuleToTesterStatus(long moduleId) throws TestOrbitException {

        return (ModuleBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	   ModuleToTesterMappingBean moduleTesterBean=null;
        	   

               Criteria criteria = session.createCriteria(ModuleToTesterMappingBean.class);
               criteria.add(Restrictions.eq("moduleId", moduleId));
               List<ModuleToTesterMappingBean> projectTesterList = criteria.list();
               
               if (projectTesterList!=null && !projectTesterList.isEmpty()) {
            	   for(ModuleToTesterMappingBean projTesterBean: projectTesterList){
               		projTesterBean.setProjectStatus(FrameworkConstants.STATUS_INACTIVE);
               		session.saveOrUpdate(projTesterBean);
               	}
               	
               }
           
        	return null;
       	}
       });
   
	}
	//Project assigned to Test Manager under dashboard
	@SuppressWarnings("unchecked")
	@Override
	public ModuleBean listProjectsUnderTestManager(long testManagerId) throws TestOrbitException {
		  return (ModuleBean) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(ModuleBean.class);
	                criteria.add(Restrictions.eq("testManagerId", testManagerId));
	                criteria.add(Restrictions.eq("moduleStatus", FrameworkConstants.MODULE_STATUS_ACTIVE));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst.get(0);
	                }
	            }
	        });}

	@Override
	public UserBean assignTesterToTestManager(long testManagerId, long testerId) throws TestOrbitException {
	    return (UserBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
	        @Override
	        public Object doInHibernate(Session session) throws HibernateException {
	    		
	        	UserBean userBeanDao=null;
	    		
	    		if(testerId!=0 && testManagerId !=0){
	    			userBeanDao=(UserBean)session.get(UserBean.class, testerId);
	    			
	        		if(userBeanDao!=null){
	        			userBeanDao.setUserIdManager(testManagerId);
	        			session.saveOrUpdate(userBeanDao);
	        		}
	        		return userBeanDao;
	    		}else{
	    			return null;
	    		}
	    	}
	    });
}

	@Override
	public ModuleBean addModuleToTester(ModuleToTesterMappingBean projectTesterBean) throws TestOrbitException {


        return (ModuleBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	
        	ModuleBean moduleBeanDao=null;
        		session.saveOrUpdate(projectTesterBean);
        	return moduleBeanDao;
       	}
       });
   
	
	}

	


}
