package com.xadmin.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.xadmin.usermanagement.bean.User;
import com.xadmin.usermanagement.dao.UserDao;

import jakarta.servlet.annotation.WebServlet;






@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao UserDao;
	
	public void init() {
		UserDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			/*
			 * case "/insert": insertUser(request, response); break;
			 */
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;/*
						 * case "/update": updateUser(request, response); break;
						 */
			case "/find":
				findUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = UserDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void findUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String CIN = request.getParameter("CIN");
		User user = UserDao.selectUser(CIN);
		if (user!= null) {
		List<User> listUser =  new ArrayList<>();
		listUser.add(user);
		request.setAttribute("listUser", listUser);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String CIN = request.getParameter("CIN");
		User existingUser = UserDao.selectUser(CIN);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		request.setAttribute("edit", true);
		dispatcher.forward(request, response);

	}

	
	/*
	 * private void insertUser(HttpServletRequest request, HttpServletResponse
	 * response) throws SQLException, IOException { String CIN =
	 * request.getParameter("CIN"); String nom = request.getParameter("nom"); String
	 * prenom = request.getParameter("prenom"); String job =
	 * request.getParameter("job"); String skills = request.getParameter("skills");
	 * User newUser = new User(CIN, nom, prenom, job, skills);
	 * UserDao.insertUser(newUser); response.sendRedirect("list"); }
	 * 
	 * private void updateUser(HttpServletRequest request, HttpServletResponse
	 * response) throws SQLException, IOException { String CIN =
	 * request.getParameter("CIN"); String nom = request.getParameter("nom"); String
	 * prenom = request.getParameter("prenom"); String job =
	 * request.getParameter("job"); String skills = request.getParameter("skills");
	 * User book = new User(CIN, nom, prenom, job, skills);
	 * UserDao.updateUser(book); response.sendRedirect("list"); }
	 */
	 

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String id = request.getParameter("CIN");
		UserDao.deleteUser(id);
		response.sendRedirect("list");

	}

}