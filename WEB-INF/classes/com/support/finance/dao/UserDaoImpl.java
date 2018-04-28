package com.support.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.support.finance.beans.CustomUser;
import com.support.finance.security.utils.SecurityUtils;




@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*@Override
	public String saveUser(UserRegister userRegister) {
				Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String uniqueid="ERR";
		try {
			UserMaster userMaster=SignupUtility.convertUserBeanintoMaster(userRegister);
			uniqueid = (String) session.save(userMaster);
			UserRoleMaster userRoleMaster=SignupUtility.convertUserBeanintoUserRoleMaster(userRegister);
			userRoleMaster.setUserMaster(userMaster);
			session.save(userRoleMaster);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return uniqueid;
	}*/
	
	@Override
	public boolean checkusername(String username) {
		String queryString = "SELECT userid FROM UserMaster WHERE username = :username";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameter("username", username);
		if(query.list().size() > 0)
			return false;
		else
			return true;
	}

	/*@Override
	public UserRegister getUserSerialNo(String username) {
		String userserialno="";
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
				Criteria sqlcriteria=session.createCriteria(UserMaster.class);
				sqlcriteria.add(Restrictions.eq("username", username));;
				UserMaster userMaster = (UserMaster)sqlcriteria.list().get(0);
			session.getTransaction().commit();
			return SignupUtility.convertUserMasterintoUserRegister(userMaster);		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}*/
	
	@Override
	public boolean checkmobilenumber(String mobilenumber) {
		String queryString = "SELECT userid FROM UserMaster WHERE mobilenumber = :mobilenumber";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameter("mobilenumber", mobilenumber);
		if(query.list().size() > 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean checkregistrationno(String registrationno) {
		String queryString = "SELECT registrationno FROM UgAdmitCardModel WHERE registrationno = :registrationno or rollno = :rollno";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameter("registrationno", registrationno);
		query.setParameter("rollno",registrationno);
		if(query.list().size() > 0)
			return true;
		else
			return false;
	}

	
	@Override
	public CustomUser loadUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String queryString = "SELECT u.fullname,u.username,u.password,ur.role FROM UserMaster u , UserRoleMaster ur   WHERE ur.userMaster.username=u.username and u.username=:username AND u.enabled=:enabled";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setParameter("username", username);
		query.setParameter("enabled",Integer.parseInt("1"));
		@SuppressWarnings("rawtypes")
		List userMaster = query.list();
		tx.commit();
		session.close();
		return SecurityUtils.convertUserMasterintoBean(userMaster);
	
	}

	

}
