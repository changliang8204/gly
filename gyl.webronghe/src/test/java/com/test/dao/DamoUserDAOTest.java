package com.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qiankang.dao.DamoUserDAO;
import com.qiankang.model.User;
import com.test.base.TestBase;

public class DamoUserDAOTest extends TestBase {
	@Autowired
	private DamoUserDAO userDAO;
	
	@Ignore
	public void addUserTest(){
		User user = new User();
		user.setUsername("常亮");
		user.setPasswd("1111111");
		userDAO.addByHibernate(user);
	}
	
	@Test
	public void searchAll(){
		List<User> users = userDAO.searchAll();
		Assert.assertNotNull(users);
		Assert.assertEquals(2, users.size());
	}
	
}
