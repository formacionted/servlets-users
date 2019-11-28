package com.telefonica.jee.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.telefonica.jee.dao.TodoDAOImpl;
import com.telefonica.jee.domain.Todo;
import com.telefonica.jee.domain.Usuario;
import com.telefonica.jee.util.AppConstants;

/**
 * Servlet implementation class TareaServlet
 */
@WebServlet(name = "TodoServlet", urlPatterns = { "/todos" })
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDAOImpl todoDAO = new TodoDAOImpl();

		
    public TodoServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null || action.length() == 0) 
			action = AppConstants.ACTION_RETRIEVE;
		
		switch (action) {
			case AppConstants.ACTION_RETRIEVE:
				retrieve(request, response);
				break;
			case AppConstants.ACTION_UPDATE:
				update(request, response);
				break;
			case AppConstants.ACTION_DELETE:
				delete(request, response);
				break;
			default:
				retrieve(request, response);
		}
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		
		todoDAO.delete(Long.valueOf(id));
		
		retrieve(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		Todo todo = todoDAO.findById(Long.valueOf(id));
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/views/todo-create.jsp").forward(request, response);
	}

	private void retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute(AppConstants.SESSION_USER);
		List<Todo> todoList = todoDAO.findTodosByUserId(user.getId());

		// todo list is request scoped to avoid storing and synchronizing it in
		// session for each CRUD operation
		request.setAttribute("todoList", todoList);

		request.getRequestDispatcher(AppConstants.HOME_PAGE).forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Todo todo = new Todo();
		todo.setDescription(request.getParameter("description"));
		if (request.getParameter("done") != null && request.getParameter("done").equals("on")) {
			todo.setDone(true);
		}
		todo.setUsuario((Usuario)request.getSession().getAttribute(AppConstants.SESSION_USER));
		
		if (id != null && !id.isEmpty()) {
			// update
			todo.setId(Long.valueOf(id));
			todoDAO.update(todo);
		} else {
			// create
			todoDAO.create(todo);

		}
		retrieve(request, response);
		
	}

}
