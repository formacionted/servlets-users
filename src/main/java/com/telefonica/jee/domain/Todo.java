package com.telefonica.jee.domain;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * It represents a task
 */
@Entity
@Table(name="todo")
@NamedQuery(name="Todo.findAll", query="SELECT t FROM Todo t")
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private boolean done;
	
	@ManyToOne()
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Todo() {
	}


	public Todo(String description, boolean done, Usuario usuario) {
		this.description = description;
		this.done = done;
		this.usuario = usuario;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", done=" + done + ", usuario=" + usuario.getId() + "]";
	}


}
