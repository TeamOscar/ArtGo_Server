package com.sell.dao;

import java.awt.List;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sell.domain.Sell;
import com.sell.domain.User;
import com.sell.util.JdbcUtils;

public class SellDao {
	
	public void add(Sell mSell){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into sell (id,path,userId,time,price,description,address) values (?,?,?,?,?,?,?)";
			Object params[] = {mSell.getId(),mSell.getPath(),mSell.getUserId(),mSell.getTime(),mSell.getPrice(),mSell.getDescription(),mSell.getAddress()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	 public Sell find(String id){
	    	try{
	    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "select * from sell where id=?";
				return  (Sell) runner.query(sql,id, new BeanHandler(Sell.class));
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	 
    public ArrayList<Sell> findSell(String sellId){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from sell where userId=?";
			return  (ArrayList<Sell>) runner.query(sql,sellId, new BeanListHandler(Sell.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
    public ArrayList<Sell> findSell(){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from sell";
			return  (ArrayList<Sell>) runner.query(sql, new BeanListHandler(Sell.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
    public boolean update(String id,String path){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update sell set path=? where id=?";
			Object params[] = {path,id};
			runner.update(sql,params);
			return true;
		}catch (Exception e) {
	    	return false;
		}
    }
}
