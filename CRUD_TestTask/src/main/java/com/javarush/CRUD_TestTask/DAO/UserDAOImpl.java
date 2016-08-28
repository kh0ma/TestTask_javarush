package com.javarush.CRUD_TestTask.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.javarush.CRUD_TestTask.model.User;



public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	
	@Override
	public List<User> getList() {
		Session session = this.sessionFactory.openSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List list = query.list();
		session.close();
		
		return list;
	}


	@Override
	public void save(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}


	@Override
	public void update(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}


	@Override
	public void delete(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String hql = "DELETE FROM User "  + 
	             "WHERE id = :id";
		Query query = session.createQuery(hql);
		Transaction tx = session.beginTransaction();
		query.setParameter("id", id);
		tx.commit();
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		session.close();
	}


	@Override
	public User getById(Long id) {
		Session session = this.sessionFactory.openSession();
		User user = (User) session.get(User.class, id);
		return user;
	}


	@Override
	public List<User> findByName(String keyword) {
		Session session = this.sessionFactory.openSession();
		String hql = "from User where name like :keyword";
		
		Query query = session.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		List<User> list = query.list();
		session.close();
		return list;
	}


	@Override
	public Long getNumberOfRows() {
		Session session = this.sessionFactory.openSession();
		String hql = "select count(*) from User";
		Query query = session.createQuery(hql);
		Long count = (Long)query.uniqueResult();
		session.close();
		return count;
	}


	@Override
	public List<User> getListLazy(int firstResult, int maxResults) {
		Session session = this.sessionFactory.openSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List results = query.list();
		session.close();
		return results;
	}

	

}

