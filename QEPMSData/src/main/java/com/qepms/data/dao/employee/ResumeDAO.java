package com.qepms.data.dao.employee;
// default package
// Generated Mar 13, 2014 3:13:20 PM by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qepms.data.employee.EmployeeMaster;
import com.qepms.data.employee.Resume;


/**
 * Home object for domain model class Resume.
 * @see .Resume
 * @author Hibernate Tools
 */
@Repository
public class ResumeDAO {

	private static final Log log = LogFactory.getLog(ResumeDAO .class);

	@Autowired
	private SessionFactory sessionFactory;



	public void persist(Resume transientInstance) {
		log.debug("persisting Resume instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Resume instance) {
		log.debug("attaching dirty Resume instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Resume instance) {
		log.debug("attaching clean Resume instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Resume persistentInstance) {
		log.debug("deleting Resume instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Resume merge(Resume detachedInstance) {
		log.debug("merging Resume instance");
		try {
			Resume result = (Resume) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Resume findById(java.lang.Integer id) {
		log.debug("getting Resume instance with id: " + id);
		try {
			com.qepms.data.employee.Resume instance = (com.qepms.data.employee.Resume) sessionFactory.getCurrentSession().get(
					"com.qepms.data.employee.Resume", id);
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

	public List findByExample(Resume instance) {
		log.debug("finding Resume instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Resume").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Resume> getResumeList() {
		log.debug("getting ResumeList");
		try {
			String queryName="getResumeList";
			Query getResumeList = sessionFactory.getCurrentSession().getNamedQuery(queryName);
			
			List<Resume> resume=(List<Resume>)getResumeList.list();
			return resume;
		} catch (RuntimeException re) {
			log.error("getting ResumeList", re);
			throw re;
		}
		
	}
	
	//get latest resume for armg team
	public List<Resume> getLatestResumeList(List<EmployeeMaster> employees,  String managerApprovalStatus) {
		log.debug("getting LatestResumeList");
		try {
			
			
			
			/*String queryName= from Resume r where */
			
			
			
			//String queryName="select r1.* from Resume r1 left outer join Resume r2 on r1.employeeMaster.empId = r2.employeeMaster.empId and (r1.UpdatedDate < r2.UpdatedDate or (r1.UpdatedDate = r2.UpdatedDate and r1.Resumeid < r2.Resumeid)) where r2.employeeMaster.empId is null";
			
			/*DetachedCriteria recentDate = DetachedCriteria.forClass(Resume.class).add(Restrictions.eq("employeeMaster.empId", 4495))
				    .setProjection(Projections.max("updatedDate"));*/
		/*	Date d = (Date) sessionFactory.getCurrentSession().createCriteria(Resume.class).add(Restrictions.eq("employeeMaster.empId", 4495)).setProjection(Projections.max("updatedDate")).uniqueResult();
			Date d = (Date) sessionFactory.getCurrentSession().createCriteria(Resume.class).add(Restrictions.eq("employeeMaster.empId", 4495)).setProjection(Projections.max("updatedDate")).uniqueResult();
			System.out.println("date="+d);*/
			/*String queryName="select distinct resume from Resume resume where resume.employeeMaster.empId IN (4495) order by updatedDate";*/
			String queryName="getResumeForListOfEmployee";
			Query getResumeList = sessionFactory.getCurrentSession().getNamedQuery(queryName);
			getResumeList.setParameterList("employees", employees);
			getResumeList.setString("managerApprovalStatus", managerApprovalStatus);
			List<Resume> resumes=(List<Resume>)getResumeList.list();
			return resumes;
		} catch (RuntimeException re) {
			log.error("getting ResumeList", re);
			throw re;
		}
		
	}

	// To get list of Resumes from DAO

		public List<Resume> getListOfResumes(String managerApprovalStatus, String reportingManager,String employeeSubmissionStatus) {
			// TODO Auto-generated method stub
			
			log.debug("getting ResumeList");
			try {
				String queryName="getListOfResumes";
				Query getResumeList = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				getResumeList.setString("managerApprovalStatus",managerApprovalStatus);
				getResumeList.setString("reportingManager",reportingManager);
				getResumeList.setString("employeeSubmissionStatus",employeeSubmissionStatus);
				log.debug("Qeuery " + getResumeList);
				List<Resume> resumeList=(List<Resume>)getResumeList.list();
				System.out.println("Reporting Manager in ResumeDAO"+reportingManager);
				System.out.println(resumeList);
				return resumeList;
			} catch (RuntimeException re) {
				log.error("getting ResumeList", re);
				throw re;
			}
		}
		
		public List<Resume> getListOfResumesBasedOnManagerEmail(
				String managerApprovalStatus, String reportingManageremail,
				String employeeSubmissionStatus) {
			log.debug("getting ResumeList");
			try {
				String queryName="getListOfResumesBasedOnManagerEmail";
				Query getResumeList = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				getResumeList.setString("managerApprovalStatus",managerApprovalStatus);
				getResumeList.setString("reportingManageremail",reportingManageremail);
				getResumeList.setString("employeeSubmissionStatus",employeeSubmissionStatus);
				log.debug("Query in ResumeDAO" + getResumeList);
				List<Resume> resumeList=(List<Resume>)getResumeList.list();
				System.out.println("Reporting Manager in ResumeDAO::::"+reportingManageremail);
				System.out.println(resumeList);
				return resumeList;
			} catch (RuntimeException re) {
				log.error("getting ResumeList", re);
				throw re;
			}
		}
		
		
		
		public Resume getResume(int empId) {
			// TODO Auto-generated method stub
			log.debug("getting Resume");
			try {
				String queryName="getResumeOfEmployee";
				Query getResumeOfEmployee = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				getResumeOfEmployee.setInteger("empId",empId);
				log.debug("Qeuery " + getResumeOfEmployee);
				Resume resume=(Resume) getResumeOfEmployee.uniqueResult();
			
				return resume;
			} catch (RuntimeException re) {
				log.error("getting Resume", re);
				throw re;
			}
		}

		public int postComments(int resumeid, String comments,String managerStatus, String employeeStatus, Date date ) {
			// TODO Auto-generated method stub
			
			log.debug("posting Comments for rejected Resume");
			try {
				String queryName="postCommentToRejectedResume";
				Query postCommentToRejectedResume = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				postCommentToRejectedResume.setInteger("resumeid",resumeid);
				postCommentToRejectedResume.setString("comments", comments);
				postCommentToRejectedResume.setString("managerStatus", managerStatus);
				postCommentToRejectedResume.setString("employeeStatus", employeeStatus);
				postCommentToRejectedResume.setTimestamp("date", date);
				log.debug("Qeuery " + postCommentToRejectedResume);
				int rowsAffected=postCommentToRejectedResume.executeUpdate();
			
				return rowsAffected;
			} catch (RuntimeException re) {
				log.error("posting Comments", re);
				throw re;
			}
		}

		public int acceptResume(int resumeid,String managerStatus,String comments) {
			// TODO Auto-generated method stub
			log.debug("updating the status of resume to accepted");
			try {
				String queryName="acceptResume";
				Query acceptResume = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				acceptResume.setInteger("resumeid",resumeid);
				acceptResume.setString("managerStatus", managerStatus);
				acceptResume.setString("comments", comments);
				log.debug("Qeuery " + acceptResume);
				int rowsAffected=acceptResume.executeUpdate();
			
				return rowsAffected;
			} catch (RuntimeException re) {
				log.error("posting Comments", re);
				throw re;
			}
			
		}
		public List<Resume> getResumeListByEmployeeStatus(Integer empId,String employeeSumbissionStatus) {
			
			log.debug("getting ResumeList");
			try {
				String queryName="getResumeListByEmployeeStatus";
				Query getResumeList =sessionFactory.getCurrentSession().getNamedQuery(queryName);
				getResumeList.setInteger("empId",empId);
				getResumeList.setString("employeeSumbissionStatus", employeeSumbissionStatus);
				List<Resume> resumeList=(List<Resume>)getResumeList.list();
				return resumeList;
			} catch (RuntimeException re) {
				log.error("getting ResumeList", re);
				throw re;
			}
		}
		
		/*public List<Resume> getResumeListByManagerStatus(Integer empId,
				String managerApprovalStatus) {
			log.debug("getting ResumeList");
			try {
				String queryName="getResumeListByManagerStatus";
				Query getResumeList =sessionFactory.getCurrentSession().getNamedQuery(queryName);
				getResumeList.setInteger("empId",empId);
				getResumeList.setString("managerApprovalStatus", managerApprovalStatus);
				List<Resume> resumeList=(List<Resume>)getResumeList.list();
				return resumeList;
			} catch (RuntimeException re) {
				log.error("getting ResumeList", re);
				throw re;
			}
		}*/
		
		public List<Resume> getResumeListByManagerStatus(Integer empId,
				String employeeSumbissionStatus,String managerApprovalStatus) {
			log.debug("getting ResumeList");
			String queryName=null;
			List<Resume> resumeList=null;
			try {
				
				if(managerApprovalStatus.trim().equalsIgnoreCase("NA"))
				{
					System.out.println("queryName=getViewResume");
					queryName="getResumeListByEmployeeSubmitted";
					Query getResumeList =sessionFactory.getCurrentSession().getNamedQuery(queryName);
					getResumeList.setInteger("empId",empId);
					getResumeList.setString("employeeSumbissionStatus", employeeSumbissionStatus);
					resumeList=(List<Resume>)getResumeList.list();
				}
				else
				{
					System.out.println("queryName=getResumeListByStatus");
					queryName="getResumeListByStatus";
					Query getResumeList =sessionFactory.getCurrentSession().getNamedQuery(queryName);
					getResumeList.setInteger("empId",empId);
					getResumeList.setString("employeeSumbissionStatus", employeeSumbissionStatus);
					getResumeList.setString("managerApprovalStatus", managerApprovalStatus);
					resumeList=(List<Resume>)getResumeList.list();
				}
				
				return resumeList;
			} catch (RuntimeException re) {
				log.error("getting ResumeList", re);
				throw re;
			}
		}

		
		public Resume getResume(Integer empId, String employeeSubmissionStatus) {
			// TODO Auto-generated method stub
			
			
			 try {
				   String queryName="getViewResume";
				   Query getViewResume = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				   getViewResume.setInteger("empId",empId);
				   getViewResume.setString("employeeSubmissionStatus", employeeSubmissionStatus);
				   log.debug("Query getViewResume executed");
				  List<Resume> resumeList=(List<Resume>)getViewResume.list();
			      if(resumeList.size()>0)
			      {
			    	 return resumeList.get(0);
			      }
			      {
			    	  throw new RuntimeException();
			      }
				  
				  } catch (RuntimeException re) {
				   log.error("getting Resume", re);
				   throw re;
				  }
			
			
		}

		
		public Resume getSubmitResume(Integer empId ) {

			// TODO Auto-generated method stub
			
			try{
				  String queryName="getSubmitResume";
				  Query getSubmitResume = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				  getSubmitResume.executeUpdate(); 
				 
				Object title = null;
				getSubmitResume.setParameter("title",title); 
				
				  getSubmitResume.setInteger("empId",empId);
				  log.debug("Query " + getSubmitResume);
				
				  return null;
			} catch (RuntimeException re) {
				log.error("attach failed", re);
				throw re;
		}

		
}

		public Resume getEditResume(Integer empId) {
			// TODO Auto-generated method stub

			 try {
				   String queryName="getEditResume";
				   Query getEditResume = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				   getEditResume.setInteger("empId",empId);
				   //log.debug("Query " + getViewResume);
				   Resume resume=(Resume)getEditResume.uniqueResult();
				   log.debug("resume skills " + resume.getSkills());
				  
				   return resume  ;
				  } catch (RuntimeException re) {
				   log.error("getting Resume", re);
				   throw re;
				  }
			
			
		}

		public Resume findResumeById(Integer resumeid) {
			// TODO Auto-generated method stub
			 try {
				   String queryName="findResumeById";
				   Query findResumeById = sessionFactory.getCurrentSession().getNamedQuery(queryName);
				   findResumeById.setInteger("resumeid",resumeid);
				   Resume resume=(Resume)findResumeById.uniqueResult();
				   log.debug("getting resume with id" + resume.getResumeid());
				   return resume  ;
				  } catch (RuntimeException re) {
				   log.error("getting Resume", re);
				   throw re;
				  }
		}

		public Resume getDraftResumeForEmployee(Integer empId,
				String employeeSumbissionStatus, String managerApprovalStatus) {
			log.debug("getting ResumeList");
			try {
				String queryName="getResumeListByStatus";
				Query getResumeList =sessionFactory.getCurrentSession().getNamedQuery(queryName);
				getResumeList.setInteger("empId",empId);
				getResumeList.setString("employeeSumbissionStatus", employeeSumbissionStatus);
				getResumeList.setString("managerApprovalStatus", managerApprovalStatus);
				Resume resumeList=(Resume)getResumeList.uniqueResult();
				return resumeList;
			} catch (RuntimeException re) {
				log.error("getting ResumeList", re);
				throw re;
			}
		}

}
