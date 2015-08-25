package com.qepms.data.dao.employee;
// default package
// Generated Mar 13, 2014 3:13:20 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qepms.data.employee.Skill;

/**
 * Home object for domain model class Skill.
 * @see .Skill
 * @author Hibernate Tools
 */
@Repository
public class SkillDAO {

	private static final Log log = LogFactory.getLog(SkillDAO .class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Skill transientInstance) {
		log.debug("persisting Skill instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Skill instance) {
		log.debug("attaching dirty Skill instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Skill instance) {
		log.debug("attaching clean Skill instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Skill persistentInstance) {
		log.debug("deleting Skill instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Skill merge(Skill detachedInstance) {
		log.debug("merging Skill instance");
		try {
			Skill result = (Skill) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Skill findById(java.lang.Integer id) {
		log.debug("getting Skill instance with id: " + id);
		try {
			Skill instance = (Skill) sessionFactory.getCurrentSession().get(
					"Skill", id);
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

	public List findByExample(Skill instance) {
		log.debug("finding Skill instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Skill").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
