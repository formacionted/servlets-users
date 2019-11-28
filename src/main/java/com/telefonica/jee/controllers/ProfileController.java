package com.telefonica.jee.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.telefonica.jee.dao.TodoDAO;
import com.telefonica.jee.dao.TodoDAOImpl;
import com.telefonica.jee.dao.UserDAO;
import com.telefonica.jee.dao.UserDAOImpl;
import com.telefonica.jee.domain.Todo;
import com.telefonica.jee.domain.Usuario;
import com.telefonica.jee.util.AppConstants;

/**
 * Servlet implementation class TareaServlet
 */
@WebServlet(name = "ProfileServlet", urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO usuarioDAO = new UserDAOImpl();
	private TodoDAO todoDAO = new TodoDAOImpl();

		
    public ProfileController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null || action.length() == 0) 
			action = AppConstants.ACTION_RETRIEVE;
		
		switch (action) {
			case AppConstants.ACTION_RETRIEVE:
				retrieve(request, response);
				break;
			default:
				retrieve(request, response);
		}
	}



	private void retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("user");
		Usuario user = usuarioDAO.findById(Long.valueOf(usuario.getId()));
		request.setAttribute("user", user);
		request.getRequestDispatcher("/views/user-create.jsp").forward(request, response);		
	}

	// solo actualiza, no crea
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = new Usuario();
		user.setId(Long.valueOf(request.getParameter("id")));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		usuarioDAO.update(user);
		response.sendRedirect("todos");
		
	}

}
