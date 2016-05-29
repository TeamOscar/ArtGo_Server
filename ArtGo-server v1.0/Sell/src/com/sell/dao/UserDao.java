package com.sell.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.sell.domain.User;
import com.sell.util.JdbcUtils;

public class UserDao {
	
    public void add(User mUser){
    	try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user1 (id,name,address,sign,email,password,phone) values (?,?,?,?,?,?,?)";
			Object params[] = {mUser.getId(),mUser.getName(),mUser.getAddress(),mUser.getSign(),mUser.getEmail(),mUser.getPassword(),mUser.getPhone()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public User find(String email,String password){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user1 where email=? and password=?";
			Object params[] = {email,password};
			return  (User) runner.query(sql,params, new BeanHandler(User.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public User find(String email){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user1 where email=?";
			return  (User) runner.query(sql,email, new BeanHandler(User.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public User findById(String id){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user1 where id=?";
			return  (User) runner.query(sql,id, new BeanHandler(User.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public void updateName(User mUser){
    	try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update user1 set name=? where id=?";

			Object params[] = {mUser.getName(),mUser.getId()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public void updateAddress(User mUser){
    	try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update user1 set address=? where id=?";

			Object params[] = {mUser.getAddress(),mUser.getId()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public void updateSign(User mUser){
    	try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update user1 set sign=? where id=?";

			Object params[] = {mUser.getSign(),mUser.getId()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
