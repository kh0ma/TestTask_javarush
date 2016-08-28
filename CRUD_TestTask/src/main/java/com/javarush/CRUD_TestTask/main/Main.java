package com.javarush.CRUD_TestTask.main;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javarush.CRUD_TestTask.DAO.UserDAO;
import com.javarush.CRUD_TestTask.model.User;



public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
    	
    	UserDAO userDAO =  context.getBean(UserDAO.class);

    	
    	
    	//System.out.println(userDAO.getNumberOfRows());
    	
    	
    	
    	
//    	User user = new User();
//    	
//    	user.setName("TESTTTTTT");
//    	user.setAge(12);
//    	user.setIsAdmin(true);
//    	user.setCreateDate(new Date());
    	
    	//userDAO.save(user);
    	
    	//userDAO.getList().forEach(e -> System.out.println(e));
    	
    	//userDAO.deleteById(1L);
//    	User userGetted = userDAO.getById(22L);
//    	System.out.println(userGetted);
//    	userGetted.setName("Getted");
//    	userDAO.update(userGetted);
//    	userGetted = userDAO.getById(22L);
//    	System.out.println(userGetted);
//    	userDAO.delete(userGetted);
//    	userGetted = userDAO.getById(22L);
//    	System.out.println(userGetted);
//    	
//    	userDAO.findByName("1").forEach(e -> System.out.println(e));
//    	
//    	System.out.println(userDAO.getNumberOfRows());
//    	
//    	userDAO.getListLazy(10, 5).forEach(e -> System.out.println(e));
	}

}

