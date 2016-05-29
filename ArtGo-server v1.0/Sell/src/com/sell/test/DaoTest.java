package com.sell.test;

import org.junit.Test;

import com.sell.dao.UserDao;
import com.sell.domain.User;

public class DaoTest {
	@Test
	public void addUser(){
		UserDao mUserDao = new UserDao();
		User mUser = new User();
		mUser.setId("12345678");
		mUser.setName("j1");
		mUser.setAddress("上海");
		mUser.setEmail("j1@seuic.com");
		mUser.setPhone("1234556677");
		mUser.setPassword("123456");
		mUser.setSign("花落花开");
		mUserDao.add(mUser);
	}
	
	@Test
	public void find(){
		UserDao mUserDao = new UserDao();
		User mUser = new User();
		mUser.setId("123456");
		mUser.setName("j1");
		mUser.setAddress("上海");
		mUser.setEmail("j1@seuic.com");
		mUser.setPhone("1234556677");
		mUser.setPassword("123456");
		mUser.setSign("花落花开");
		mUserDao.find("j1@seuic.com","123456");
	}
	@Test
	public void updateName(){
        UserDao mUserDao = new UserDao();
        User mUser = new User();
		mUser.setId("123456");
		mUser.setName("j1");
		mUser.setAddress("上海");
		mUser.setEmail("j1@seuic.com");
		mUser.setPhone("1234556677");
		mUser.setPassword("123456");
		mUser.setSign("花落花开");
		mUserDao.updateName(mUser);   
	}
}
