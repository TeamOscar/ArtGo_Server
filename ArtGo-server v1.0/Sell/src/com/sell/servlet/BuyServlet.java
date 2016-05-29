package com.sell.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sell.dao.BuyDao;
import com.sell.dao.SellDao;
import com.sell.dao.UserDao;
import com.sell.domain.Buy;
import com.sell.domain.Order;
import com.sell.domain.Sell;
import com.sell.domain.User;

public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if(method.equals("add")){
			add(request, response);
		}else if(method.equals("findByUserId")){
			//findByUserId(request, response);
			order(request, response);
		}else if(method.equals("find")){
			find(request, response);
		}else if(method.equals("updateReceiveFalg")){
			updateReceiveFalg(request, response);
		}else if(method.equals("updateSendFalg")){
			updateSendFalg(request, response);
		}
	}
	
	private void order(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			List<Order> listOrder = new ArrayList<Order>();
			BuyDao mBuyDao = new BuyDao();
			String id = request.getParameter("id");
			ArrayList<Buy> list = mBuyDao.findByUserId(id);
			for(Buy item:list){
				Order mOrder = new Order();
				mOrder.setId(item.getId());
				mOrder.setBuyId(item.getBuyId());
				UserDao mUserDao = new UserDao();
				User mUser = mUserDao.findById(item.getBuyId());
				mOrder.setBuyName(mUser.getName());	
				mOrder.setSellId(item.getSellId());
			    mUser = mUserDao.findById(item.getSellId());
				mOrder.setSellName(mUser.getName());
				SellDao mSellDao = new SellDao();
				Sell mSell = mSellDao.find(item.getSellOrderId());
				mOrder.setPath(mSell.getPath());
				mOrder.setReceiveFalg(item.getReceiveFalg());
				mOrder.setSendFalg(item.getSendFalg());
				listOrder.add(mOrder);
			}
			response.getWriter().write(JSON.toJSONString(listOrder));
		}catch(Exception e){
			response.getWriter().write("没有查到和该用户关联的商品");
		}
	}
	
	private void add(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			BuyDao mBuyDao = new BuyDao();
			String buy = request.getParameter("buy");
			Buy mBuy = JSON.parseObject(buy, Buy.class);
			mBuyDao.add(mBuy);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}
	
	private void findByUserId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			BuyDao mBuyDao = new BuyDao();
			String id = request.getParameter("id");
			ArrayList<Buy> list = mBuyDao.findByUserId(id);
			response.getWriter().write(JSON.toJSONString(list));
		}catch(Exception e){
			response.getWriter().write("没有查到和该用户关联的商品");
		}
	}
	
	private void find(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			BuyDao mBuyDao = new BuyDao();
			String id = request.getParameter("id");
			Buy mBuy = mBuyDao.find(id);
			response.getWriter().write(JSON.toJSONString(mBuy));
		}catch(Exception e){
			response.getWriter().write("没有查到销售记录");
		}
	}
	
	private void updateReceiveFalg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			BuyDao mBuyDao = new BuyDao();
			String id = request.getParameter("id");
			mBuyDao.updateReceiveFalg(id);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}
	
	private void updateSendFalg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			BuyDao mBuyDao = new BuyDao();
			String id = request.getParameter("id");
			mBuyDao.updateSendFalg(id);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         doGet(request, response);
	}

}
