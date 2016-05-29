package com.sell.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sell.dao.UserDao;
import com.sell.domain.User;

public class UserServlet extends HttpServlet {

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
		if(method.equals("addUser")){
			addUser(request, response);
		}else if(method.equals("modifyName")){
			modifyName(request, response);
		}else if(method.equals("modifyAddress")){
			modifyAddress(request, response);
		}else if(method.equals("modifySign")){
			modifySign(request, response);
		}else if(method.equals("findUser")){
			findUser(request, response);
		}else if(method.equals("findUserById")){
			findUserById(request, response);
		}
		
	}
	
	private void findUserById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			UserDao mUserDao = new UserDao();
			String id = request.getParameter("id");
			User user = mUserDao.findById(id);
			response.getWriter().write(JSON.toJSONString(user));
		}catch(Exception e){
			response.getWriter().write("用户不存在或用户名、密码错！");
		}
	}

	private void findUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			UserDao mUserDao = new UserDao();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User user = mUserDao.find(email, password);
			response.getWriter().write(JSON.toJSONString(user));
		}catch(Exception e){
			response.getWriter().write("用户不存在或用户名、密码错！");
		}
	}

	private void modifySign(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			UserDao mUserDao = new UserDao();
			String user = request.getParameter("user");
			User user2 = JSON.parseObject(user, User.class);
			mUserDao.updateSign(user2);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}

	private void modifyAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			UserDao mUserDao = new UserDao();
			String user = request.getParameter("user");
			User user2 = JSON.parseObject(user, User.class);
			mUserDao.updateAddress(user2);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}

	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			UserDao mUserDao = new UserDao();
			String user = request.getParameter("user");
			User user2 = JSON.parseObject(user, User.class);
			mUserDao.add(user2);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
		}
	}

	
	private void modifyName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			UserDao mUserDao = new UserDao();
			String user = request.getParameter("user");
			User user2 = JSON.parseObject(user, User.class);
			mUserDao.updateName(user2);
			response.getWriter().write("true");
		}catch(Exception e){
			response.getWriter().write("false");
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
