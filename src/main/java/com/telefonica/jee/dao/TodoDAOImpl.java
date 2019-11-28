package com.telefonica.jee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.telefonica.jee.domain.Todo;
import com.telefonica.jee.util.JPAUtil;

public class TodoDAOImpl implements TodoDAO{
	private static final String FIND_BY_USERID = "SELECT t FROM Todo t WHERE t.usuario.id = :userId";
	public TodoDAOImpl() {
	}

	EntityManager manager;

	public void closeEntityManager() {
		manager.close();
	}

	public void openEntityManager() {
		manager = JPAUtil.getEntityManager();
	}

	@Override
	public List<Todo> findTodosByUserId(Long userId) {
		manager = JPAUtil.getEntityManager();
		TypedQuery<Todo> query = manager.createQuery(FIND_BY_USERID, Todo.class);
		query.setParameter("userId", userId);
		List<Todo> results = query.getResultList();
		manager.close();
		return results;
	}

	@Override
	public Todo create(Todo todo) {
		if (todo == null || todo.getId() != null) {
			return todo;
		}
		openEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(todo);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		closeEntityManager();
		return todo;
	}

	@Override
	public Todo update(Todo todo) {
		if (todo.getId() == null) {
			return null;
		}
		openEntityManager();
		manager.getTransaction().begin();
		Todo todoUpdated = manager.merge(todo);
		manager.getTransaction().commit();
		closeEntityManager();
		return todoUpdated;
	}

	@Override
	public void delete(Long id) {

		// manager remove
		openEntityManager();

		Todo todo = manager.find(Todo.class, id);
		if (todo != null) {
			manager.getTransaction().begin();
			manager.remove(todo);
			manager.getTransaction().commit();
		}

		closeEntityManager();
	}

	@Override
	public Todo findById(Long id) {
		if (id == null) {
			return null;
		}
		openEntityManager();
		Todo todo = manager.find(Todo.class, id);
		closeEntityManager();
		return todo;
	}

	@Override
	public List<Todo> findAll() {
		openEntityManager();
		TypedQuery<Todo> namedQuery = manager.createNamedQuery("Todo.findAll", Todo.class);
		List<Todo> todos = namedQuery.getResultList();
		closeEntityManager();
		return todos;
	}

}
