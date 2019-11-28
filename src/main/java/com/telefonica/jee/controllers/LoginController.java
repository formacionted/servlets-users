package com.telefonica.jee.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.telefonica.jee.dao.UserDAOImpl;
import com.telefonica.jee.domain.Usuario;
import com.telefonica.jee.util.AppConstants;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/", "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAOImpl usuarioDAO = new UserDAOImpl();
	public LoginController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (doesValidSessionExist(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(AppConstants.REDIRECT_HOME_PAGE);
			dispatcher.forward(request, response);
			return;
		}

		request.getRequestDispatcher(AppConstants.LOGIN_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (!usuarioDAO.login(email, password)) {
			request.setAttribute("error",AppConstants.LOGIN_ERROR);
			response.sendRedirect(AppConstants.LOGIN_PAGE);
			return;
		}

		HttpSession session = request.getSession(true);// create session
		Usuario user = usuarioDAO.findByEmail(email);
		session.setAttribute(AppConstants.SESSION_USER, user);
		response.sendRedirect("todos");
	}
	


	private boolean doesValidSessionExist(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session != null) {

			Usuario user = (Usuario) session.getAttribute(AppConstants.SESSION_USER);

			if (user != null) {
				return true;
			}

		}
		return false;
	}
	

	
	
}
