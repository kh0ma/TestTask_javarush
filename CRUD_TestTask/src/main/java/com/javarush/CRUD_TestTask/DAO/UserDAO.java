package com.javarush.CRUD_TestTask.DAO;

import java.util.List;

import com.javarush.CRUD_TestTask.model.User;



public interface UserDAO {
	void save(User user);
	void update(User user);
	void delete(User user);
	void deleteById(Long id);
	User getById(Long id);
	List<User> findByName(String keyword);
	List<User> getList();
	Long getNumberOfRows();
	List<User> getListLazy(int firstResult, int maxResults);
}