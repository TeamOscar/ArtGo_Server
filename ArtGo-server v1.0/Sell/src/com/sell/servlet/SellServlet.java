package com.sell.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sell.dao.SellDao;
import com.sell.dao.UserDao;
import com.sell.domain.Sell;
import com.sell.domain.User;

public class SellServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if(method.equals("add")){
			add(request, response);
		}else if(method.equals("find")){
			find(request, response);
		}else if(method.equals("findAll")){
			findAll(request, response);
		}
		
	}
	
	private void add(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			SellDao mSellDao = new SellDao();
			String sell = request.getParameter("sell");
			Sell mSell = JSON.parseObject(sell, Sell.class);
			mSellDao.add(mSell);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}
	
	private void find(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			SellDao mSellDao = new SellDao();
			String sellId = request.getParameter("sellId");
			ArrayList<Sell> list = mSellDao.findSell(sellId);
			response.getWriter().write(JSON.toJSONString(list));
		}catch(Exception e){
			response.getWriter().write("该用户没有销售商品");
		}
	}
	
	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			SellDao mSellDao = new SellDao();
			ArrayList<Sell> list = mSellDao.findSell();
			response.getWriter().write(JSON.toJSONString(list));
		}catch(Exception e){
			response.getWriter().write("没有任何用户销售商品");
		}
	}


	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doGet(request, response);
	}

}
