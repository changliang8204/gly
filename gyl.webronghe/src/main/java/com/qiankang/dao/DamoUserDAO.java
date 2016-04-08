package com.qiankang.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qiankang.model.User;

@Repository
public class DamoUserDAO {
	@Autowired
	private JdbcTemplate templet;
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUser(String name, String passwd){
		String sql = "insert into user values('"+name+"','"+passwd+"')";
		templet.execute(sql);
	}
	
	@Transactional
	public List<User> searchAll(){
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select u from User as u");
		return q.list();
	}
	
	@Transactional
	public void addByHibernate(User user){
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
}
