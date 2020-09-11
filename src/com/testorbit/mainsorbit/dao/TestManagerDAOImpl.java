/*
 *  @author Aishwarya Pauskar
 *  @version 1.1
 */
package com.testorbit.mainsorbit.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.testorbit.mainsorbit.beans.HistoryBean;
import com.testorbit.mainsorbit.beans.TaskBean;
import com.testorbit.mainsorbit.beans.TaskToTesterMappingBean;
import com.testorbit.mainsorbit.exception.TestOrbitException;
import com.testorbit.mainsorbit.util.FrameworkConstants;


@SuppressWarnings("deprecation")
public class TestManagerDAOImpl extends HibernateDaoSupport implements TestManagerDAO {

	@Override
	public long createNewTask(TaskBean taskBean) throws TestOrbitException {

		return (long) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {

				long taskId = 0;
				taskId = (long) session.save(taskBean);
				if (taskId != 0) {
					return taskId;

				}
				return taskId;

			}
		});

	}

	@Override
	public TaskBean getTaskDetails(long taskId) throws TestOrbitException {

		return (TaskBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				TaskBean taskBeanDao = null;
				if (taskId != 0) {
					taskBeanDao = (TaskBean) session.get(TaskBean.class, taskId);
					return taskBeanDao;
				}
				return taskBeanDao;
			}
		});

	}

	@Override
	public TaskBean updateTaskToTester(long taskId, long testerId, Date startDate) throws TestOrbitException {

		return (TaskBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				TaskToTesterMappingBean mappingBean = null;

				TaskBean taskBeanDao = null;
				mappingBean = new TaskToTesterMappingBean();
				mappingBean.setTaskId(taskId);
				mappingBean.setTesterId(testerId);
				mappingBean.setStartDate(startDate);
				session.saveOrUpdate(mappingBean);

				taskBeanDao = (TaskBean) session.get(TaskBean.class, taskId);
				return taskBeanDao;
			}
		});

	}

	// List all Tasks under test manager
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBean> listTasksUnderTestManager(long testManagerId) throws TestOrbitException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(TaskBean.class);
				criteria.add(Restrictions.eq("testManagerId", testManagerId));
				List lst = criteria.list();
				if (lst.isEmpty()) {
					return null;
				} else {
					return lst;
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBean> listTasksAssignedToTester(long testerId) throws TestOrbitException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {

				Query query = session.createQuery("SELECT task FROM TaskBean task WHERE task.taskId IN "
						+ "(SELECT taskTesterMap.taskId FROM TaskToTesterMappingBean taskTesterMap WHERE taskTesterMap.testerId=:taskTesterId) AND "
						+ "task.taskStatus !=:status");

				query.setParameter("taskTesterId", testerId);
				query.setParameter("status", FrameworkConstants.TASK_STATUS_COMPLETE);

				List<TaskBean> taskToTesters = query.list();
				if (taskToTesters.isEmpty()) {
					return null;
				} else {
					return taskToTesters;
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBean> listTasksForTestCaseApproval(long testManagerId) throws TestOrbitException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {

				Query query = session.createQuery(
						"SELECT task FROM TaskBean task WHERE (task.taskStatus=:statusStart OR task.taskStatus=:statusBlocked) AND task.testManagerId=:taskManagerId ");

				query.setParameter("taskManagerId", testManagerId);
				query.setParameter("statusStart", FrameworkConstants.TASK_STATUS_INPROGRESS);
				query.setParameter("statusBlocked", FrameworkConstants.TASK_STATUS_BLOCKED);

				List<TaskBean> taskToTesters = query.list();
				if (taskToTesters.isEmpty()) {
					return null;
				} else {
					return taskToTesters;
				}
			}
		});
	}

	@Override
	public TaskBean updateTaskStatusByTester(TaskBean taskBean) throws TestOrbitException {

		return (TaskBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				
				session.saveOrUpdate(taskBean);
				TaskToTesterMappingBean mappingBean = null;

				TaskBean taskBeanDao = null;

				Criteria criteria = session.createCriteria(TaskToTesterMappingBean.class);
				criteria.add(Restrictions.eq("taskId", taskBean.getTaskId()));
				mappingBean= (TaskToTesterMappingBean) criteria.uniqueResult();
				if (mappingBean!=null) {
					mappingBean.setStartDate(taskBean.getStartDate());
					session.saveOrUpdate(mappingBean);
				}
				taskBeanDao = (TaskBean) session.get(TaskBean.class, taskBean.getTaskId());
				return taskBeanDao;
			}
		});

	}

	@Override
	public TaskBean editTask(TaskBean taskBean) throws TestOrbitException {

        return (TaskBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
           @Override
           public Object doInHibernate(Session session) throws HibernateException {
       		
        	   TaskBean taskBeanDao=null;
       		
       		session.saveOrUpdate(taskBean);
       		taskBeanDao=(TaskBean)session.get(TaskBean.class, taskBean.getTaskId());
        	return taskBeanDao;
       	
       	}
       });
   
	}

	@Override
	public TaskBean editTaskStatustoTester(TaskBean taskBean,long testerId) throws TestOrbitException {

		return (TaskBean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				
				TaskToTesterMappingBean mappingBean = null;

				TaskBean taskBeanDao = null;

				Criteria criteria = session.createCriteria(TaskToTesterMappingBean.class);
				criteria.add(Restrictions.eq("taskId", taskBean.getTaskId()));
				mappingBean= (TaskToTesterMappingBean) criteria.uniqueResult();
				
				if (mappingBean!=null) {
					if(mappingBean.getTesterId() != testerId ){
					mappingBean.setTesterId(testerId);
					mappingBean.setStartDate(taskBean.getStartDate());
					session.saveOrUpdate(mappingBean);
					}
				} else {
					mappingBean = new TaskToTesterMappingBean();
					mappingBean.setTaskId(taskBean.getTaskId());
					mappingBean.setTesterId(testerId);
					mappingBean.setStartDate(taskBean.getStartDate());
					session.saveOrUpdate(mappingBean);
		         }
		         
				taskBeanDao = (TaskBean) session.get(TaskBean.class, taskBean.getTaskId());
				return taskBeanDao;
			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryBean> listDatesTaskTestCasesExecuted(long taskId) throws TestOrbitException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {

				Query query = session.createQuery(
						"SELECT  historyBean FROM HistoryBean historyBean WHERE historyBean.taskId=:historyTaskId");

				query.setParameter("historyTaskId", taskId);
				
				List<HistoryBean> historyBeans = query.list();
				if (historyBeans.isEmpty()) {
					return null;
				} else {
					return historyBeans;
				}
			}
		});
	}

}
