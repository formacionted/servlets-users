package com.telefonica.jee.dao;

import java.util.List;

import com.telefonica.jee.domain.Todo;

public interface TodoDAO {

	public Todo findById(Long id);
	
	public List<Todo> findAll();
	
	public List<Todo> findTodosByUserId(Long userId);
	
	public Todo create(Todo todo);
	
	public Todo update(Todo todo);
		
	public void delete(Long id);
	
}
