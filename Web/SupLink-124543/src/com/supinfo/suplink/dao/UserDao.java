package com.supinfo.suplink.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.supinfo.suplink.entity.User;
import com.supinfo.suplink.function.MD5;

public class UserDao {
	
	private static EntityManagerFactory emf;

	public UserDao() {}
		
	public static User getUserById(Long id) {
		try {
			emf = Persistence.createEntityManagerFactory("PU");
			EntityManager em = emf.createEntityManager();
			
			Query querySelect = em.createQuery("SELECT u FROM User AS u WHERE u.id = :id");
			querySelect.setParameter("id", id);
			User user = new User();
			user = (User) querySelect.getSingleResult();
			
			em.close();
			emf.close();

			return user;
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public static User getUserByAuth(User user){
		emf = Persistence.createEntityManagerFactory("PU");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT u FROM User AS u WHERE email = :email");
		query.setParameter("email", user.getEmail());
		User thisUser = (User) query.getSingleResult();
		
		em.close();
		emf.close();

		if (thisUser.getPassword().equals(MD5.getHash(user.getPassword()))) {
			return thisUser;
		}
		return null;				
	}
}
