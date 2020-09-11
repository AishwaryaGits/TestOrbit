/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.testorbit.mainsorbit.beans.HistoryBean;
import com.testorbit.mainsorbit.beans.TaskBean;
import com.testorbit.mainsorbit.beans.TestCaseBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.FrameworkConstants;

@SuppressWarnings("deprecation")
public class TesterDAOImpl extends HibernateDaoSupport  implements TesterDAO {

	@Override
	public long createNewTestCase(TestCaseBean testCaseBean) throws TestOrbitException {

        return (long) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
       		
        	TestCaseBean taskCaseBeanDao=null;
       		long testCaseId=0;
       		testCaseId= (long) session.save(testCaseBean);
       		if(testCaseId!=0 ){
       			return testCaseId;
        		
    		}
       		return testCaseId;
       		
       	}
       });
   
	}

	@Override
	public TestCaseBean getTestCaseDetails(long testCaseId) throws TestOrbitException {

        return (TestCaseBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
        	   TestCaseBean taskCaseBeanDao=null;
        	if(testCaseId!=0){
       		taskCaseBeanDao=(TestCaseBean)session.get(TestCaseBean.class,testCaseId);
        	return taskCaseBeanDao;
        	}
        	return taskCaseBeanDao;
       	}
       });
   
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestCaseBean> listTestCasebasedOnTask(long taskId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(TestCaseBean.class);
	                criteria.add(Restrictions.eq("taskId", taskId));
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
	public List<TestCaseBean> listTestCaseTobeApproved(long taskId) throws TestOrbitException {
		  return (List) getHibernateTemplate().execute(new HibernateCallback() {
	            @Override
	            public Object doInHibernate(Session session) throws HibernateException {
	                Criteria criteria = session.createCriteria(TestCaseBean.class);
	                criteria.add(Restrictions.eq("taskId", taskId));
	                criteria.add(Restrictions.eq("testCaseStatus", FrameworkConstants.TESTCASE_STATUS_INVALID));
	                List lst = criteria.list();
	                if (lst.isEmpty()) {
	                    return null;
	                } else {
	                    return lst;
	                }
	            }
	        });}

	@Override
	public TestCaseBean modifyTestCaseStatus(long testCaseId, char status) throws TestOrbitException {
	    return (TestCaseBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
	        @Override
	        public Object doInHibernate(Session session) throws HibernateException {
	    		
	        	TestCaseBean testCaseBeanDao=null;
	    		
	    		if(testCaseId!=0 ){
	    			testCaseBeanDao=(TestCaseBean)session.get(TestCaseBean.class, testCaseId);
	    			
	        		if(testCaseBeanDao!=null){
	        			testCaseBeanDao.setTestCaseStatus(status);
	        			session.saveOrUpdate(testCaseBeanDao);
	        		}
	        		return testCaseBeanDao;
	    		}else{
	    			return null;
	    		}
	    	}
	    });
}

	@Override
	public long recordTestResult(HistoryBean historyBean) throws TestOrbitException {

        return (long) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
       		
        	HistoryBean historyBeanDao=null;
       		long historyId=0;
       	 Criteria criteria = session.createCriteria(HistoryBean.class);
         criteria.add(Restrictions.eq("testCaseId", historyBean.getTestCaseId()));
         criteria.add(Restrictions.eq("taskId", historyBean.getTaskId()));
         criteria.add(Restrictions.eq("executionDate", historyBean.getExecutionDate()));
         historyBeanDao = (HistoryBean) criteria.uniqueResult();
         if (historyBeanDao!=null) {
        	 historyBeanDao.setTesterId(historyBean.getTesterId());
        	 historyBeanDao.setResultStatus(historyBean.getResultStatus());
        	 session.saveOrUpdate(historyBeanDao);
         } else {
        	 historyId= (long) session.save(historyBean);
         }
         
       		if(historyId!=0 ){
       			return historyId;
        		
    		}
       		return historyId;
       		
       	}
       });
   
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryBean> getResultSummary(long taskId, Date executionDate) throws TestOrbitException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(HistoryBean.class);
				criteria.add(Restrictions.eq("taskId", taskId));
				criteria.add(Restrictions.eq("executionDate", executionDate));
				List lst = criteria.list();
				if (lst.isEmpty()) {
					return null;
				} else {
					return lst;
				}
			}
		});
	}
	
	
}
