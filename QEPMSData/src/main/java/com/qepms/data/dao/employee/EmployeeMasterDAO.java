package com.qepms.data.dao.employee;
// default package
// Generated Mar 13, 2014 3:13:20 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qepms.data.employee.EmployeeMaster;
import com.qepms.data.employee.Resume;

/**
 * Home object for domain model class EmployeeMaster.
 * @see .EmployeeMaster
 * @author Hibernate Tools
 */
@Repository
public class EmployeeMasterDAO {

	private static final Log log = LogFactory.getLog(EmployeeMasterDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(EmployeeMaster transientInstance) {
		log.debug("persisting EmployeeMaster instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(EmployeeMaster instance) {
		log.debug("attaching dirty EmployeeMaster instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EmployeeMaster instance) {
		log.debug("attaching clean EmployeeMaster instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(EmployeeMaster persistentInstance) {
		log.debug("deleting EmployeeMaster instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EmployeeMaster merge(EmployeeMaster  detachedInstance) {
		log.debug("merging EmployeeMaster instance");
		try {
			EmployeeMaster  result = (EmployeeMaster ) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EmployeeMaster  findById(java.lang.Integer id) {
		log.debug("getting EmployeeMaster instance with id: " + id);
		try {
			EmployeeMaster  instance = (EmployeeMaster) sessionFactory
					.getCurrentSession().get("com.qepms.data.employee.EmployeeMaster", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EmployeeMaster instance) {
		log.debug("finding EmployeeMaster instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("EmployeeMaster")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<EmployeeMaster> getAllEmployees() {
		// TODO Auto-generated method stub
		 try {
			   String queryName="getAllEmployees";
			   Query getAllEmployees = sessionFactory.getCurrentSession().getNamedQuery(queryName);
			   List<EmployeeMaster> employees=(List<EmployeeMaster>)getAllEmployees.list();
			   log.debug("getAllEmployees() called from DAO");
			   return employees  ;
			  }catch (RuntimeException re) {
			   log.error("getting Employees failed", re);
			   throw re;
			  }
	}

	public EmployeeMaster findByName(String name) {
		// TODO Auto-generated method stub
		 try {
			   String queryName="getEmployeeDetail";
			   Query getEmployeeDetail = sessionFactory.getCurrentSession().getNamedQuery(queryName);
			   getEmployeeDetail.setString("name", "%"+name.trim()+"%");
			   EmployeeMaster employees=(EmployeeMaster)getEmployeeDetail.uniqueResult();
			   log.debug("findByName() called from DAO");
			   return employees  ;
			  }catch (RuntimeException re) {
			   log.error("findByName failed", re);
			   throw re;
			  }
	}
}
