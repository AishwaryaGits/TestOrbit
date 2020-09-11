/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.testorbit.mainsorbit.beans.UserBean;
import com.testorbit.mainsorbit.beans.UserMappingBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.FrameworkConstants;

@SuppressWarnings("deprecation")
public class GenericDAOImpl extends HibernateDaoSupport  implements GenericDAO {
	

	@SuppressWarnings("unchecked")
	@Override
	public UserBean validateUser(UserBean userBean) throws TestOrbitException {
         return (UserBean) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
            	
            Criteria criteria=session.createCriteria(UserBean.class);
           
    		criteria.add(Restrictions.eq("email", userBean.getEmail()));
    		criteria.add(Restrictions.eq("userPassword", userBean.getUserPassword()));
    		criteria.add(Restrictions.eq("userStatus",FrameworkConstants.STATUS_ACTIVE));
    		UserBean userBeanRes=(UserBean)criteria.uniqueResult();
    		if(userBeanRes!=null){
    			return userBeanRes;
    		}
    		
    		return null;}
        });
    }



	
	@Override
	public UserBean registerNewUser(UserBean userBean) throws TestOrbitException {
         return (UserBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
        		
            	UserBean userBeanDao=null;
        		long userId= (long) session.save(userBean);
        		if(userId!=0 ){
        			userBeanDao=(UserBean)session.get(UserBean.class, userId);
        			UserMappingBean userMappingBean=null;
            		if(userBean.getRoleId()!=FrameworkConstants.ROLE_MANAGER){
            			userMappingBean=new UserMappingBean();
            			userMappingBean.setUserIdManager(userBean.getUserIdManager());
            			userMappingBean.setUserIdResource(userId);
            			userMappingBean.setApprovedStatus(FrameworkConstants.STATUS_INACTIVE);
            			session.saveOrUpdate(userMappingBean);
            		}
            		return userBeanDao;
        		}else{
        			return null;
        		}
        	}
        });
    }




	@SuppressWarnings("unchecked")
	@Override
	public List<UserBean> listTesterUnderTestManager(long testMgrId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(UserBean.class);
	                criteria.add(Restrictions.eq("userIdManager", testMgrId));
	                criteria.add(Restrictions.eq("roleId", FrameworkConstants.ROLE_TESTER));
	        		criteria.add(Restrictions.eq("userStatus",FrameworkConstants.STATUS_ACTIVE));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}




	@SuppressWarnings("unchecked")
	@Override
	public List<UserBean> listUnassignedTestManagers(long projectManagerId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	            	
	            	
	            	Query query=session.createQuery("SELECT user FROM UserBean user WHERE user.userId NOT IN "+
	            	"(SELECT module.testManagerId FROM ModuleBean module WHERE module.moduleStatus=:modStatus) AND "
	            	+ " user.roleId=:roleId  AND user.userStatus=:status  AND user.userIdManager=:projectManager");
	            	
	            	query.setParameter("modStatus", FrameworkConstants.MODULE_STATUS_ACTIVE);
	            	query.setParameter("roleId", FrameworkConstants.ROLE__TEST_MANAGER);
	            	query.setParameter("status", FrameworkConstants.STATUS_ACTIVE);
	            	query.setParameter("projectManager", projectManagerId);
	            	
	            	
	            	List<UserBean> testManagerList = query.list();
	                if (testManagerList.isEmpty()) {
	                    return null;
	                } else {
	                    return testManagerList;
	                }
	            }
	        });}




	@SuppressWarnings("unchecked")
	@Override
	public List<UserBean> listUsersToApprove(long projectManagerId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(UserBean.class);
	                criteria.add(Restrictions.eq("userIdManager", projectManagerId));
	        		criteria.add(Restrictions.eq("userStatus",FrameworkConstants.STATUS_INACTIVE));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}




	@Override
	public UserBean approveUsers( long userResourceId, char status) throws TestOrbitException{
    return (UserBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
        @Override
        public Object doInHibernate(Session session) throws HibernateException {
    		
        	UserBean userBeanDao=null;
    		
    		if(userResourceId!=0 ){
    			userBeanDao=(UserBean)session.get(UserBean.class, userResourceId);
    			
        		if(userBeanDao!=null){
        			userBeanDao.setUserStatus(status);
        			session.saveOrUpdate(userBeanDao);
        		}
        		return userBeanDao;
    		}else{
    			return null;
    		}
    	}
    });
}


  

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBean> listAllActiveProjectManagers() throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(UserBean.class);
	                criteria.add(Restrictions.eq("roleId", FrameworkConstants.ROLE_MANAGER));
	        		criteria.add(Restrictions.eq("userStatus",FrameworkConstants.STATUS_ACTIVE));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}


//unassigned testers for reassignment assignment module

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBean> listUnassignedTestersToTestManagers(long projectManagerId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	            	
	            	
	            	Query query=session.createQuery("SELECT user FROM UserBean user WHERE user.userId NOT IN "+
	            	"(SELECT moduleMap.testerUserId FROM ModuleToTesterMappingBean moduleMap WHERE moduleMap.projectStatus=:modStatus) AND "
	            	+ " user.roleId=:roleId  AND user.userStatus=:status  AND user.superManagerId=:projectManager");
	            	
	            	query.setParameter("modStatus", FrameworkConstants.STATUS_ACTIVE);
	            	query.setParameter("roleId", FrameworkConstants.ROLE_TESTER);
	            	query.setParameter("status", FrameworkConstants.STATUS_ACTIVE);
	            	query.setParameter("projectManager", projectManagerId);
	            	
	            	
	            	List<UserBean> testerList = query.list();
	                if (testerList.isEmpty()) {
	                    return null;
	                } else {
	                    return testerList;
	                }
	            }
	        });}




	@SuppressWarnings("unchecked")
	@Override
	public List<UserBean> listAllActiveTestManagersUnderProjectManager(long projectManagerId)
			throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(UserBean.class);
	                criteria.add(Restrictions.eq("roleId", FrameworkConstants.ROLE__TEST_MANAGER));
	        		criteria.add(Restrictions.eq("userStatus",FrameworkConstants.STATUS_ACTIVE));
	        		 criteria.add(Restrictions.eq("userIdManager", projectManagerId));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}

	
	


}
