package com.sell.dao;

import java.awt.List;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sell.domain.Buy;
import com.sell.domain.Sell;
import com.sell.util.JdbcUtils;

public class BuyDao {
    public void add(Buy mBuy){
    	try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into buy (id,sellOrderId,sellId,buyId,receiveFalg,sendFalg) values (?,?,?,?,?,?)";
			Object params[] = {mBuy.getId(),mBuy.getSellOrderId(),mBuy.getSellId(),mBuy.getBuyId(),mBuy.getReceiveFalg(),mBuy.getSendFalg()};
			runner.update(sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public ArrayList<Buy> findByUserId(String id){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from buy where sellId=? or buyId=?";
			Object params[] = {id,id};
			return  (ArrayList<Buy>) runner.query(sql,params, new BeanListHandler(Buy.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public Buy find(String id){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from buy where id=?";
			return  (Buy) runner.query(sql,id, new BeanHandler(Buy.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    public boolean updateReceiveFalg(String id){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update buy set receiveFalg=1 where id=?";
			Object params[] = {id};
			runner.update(sql,params);
			return true;
		}catch (Exception e) {
			return false;
		}
    }
    
    public boolean updateSendFalg(String id){
    	try{
    		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update buy set sendFalg=1 where id=?";
			Object params[] = {id};
			runner.update(sql,params);
			return true;
		}catch (Exception e) {
			return false;
		}
    }
    
    
}
