package com.telefonica.jee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.telefonica.jee.domain.Usuario;
import com.telefonica.jee.util.JPAUtil;

public class UserDAOImpl implements UserDAO{

	private static final String USER_COUNT = "SELECT COUNT(u) FROM Usuario u "
			+ "WHERE u.email = :email AND u.password = :password";

	private static final String FIND_BY_EMAIL = "SELECT u FROM Usuario u "
			+ "WHERE u.email = :email ";
	
	EntityManager manager;

	public void closeEntityManager() {
		manager.close();
	}

	public void openEntityManager() {
		manager = JPAUtil.getEntityManager();
	}


	@Override
	public boolean login(String email, String password) {
		openEntityManager();
		TypedQuery<Long> query = manager.createQuery(USER_COUNT, Long.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		Long numUsuario = query.getSingleResult();
		System.out.println("Numero de usuarios con email y password: " + numUsuario);
		closeEntityManager();
		return numUsuario > 0;
	}

	@Override
	public Usuario findById(Long id) {
		if (id == null) {
			return null;
		}
		openEntityManager();
		Usuario usuario = manager.find(Usuario.class, id);
		closeEntityManager();
		return usuario;
	}

	@Override
	public Usuario findByEmail(String email) {
		if (email == null || email.isEmpty()) {
			return null;
		}
		openEntityManager();
		TypedQuery<Usuario> namedQuery = manager.createQuery(FIND_BY_EMAIL, Usuario.class);
		namedQuery.setParameter("email", email);
		Usuario user = namedQuery.getSingleResult();
		 closeEntityManager();
		return user;
	}
	
	@Override
	public List<Usuario> findAll() {
		openEntityManager();
		TypedQuery<Usuario> namedQuery = manager.createNamedQuery("Usuario.findAll", Usuario.class);
		List<Usuario> usuarios = namedQuery.getResultList();
		closeEntityManager();
		return usuarios;
	}

	@Override
	public Usuario create(Usuario usuario) {
		if (usuario == null || usuario.getId() != null) {
			return usuario;
		}
		openEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(usuario);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		closeEntityManager();
		return usuario;
	}

	@Override
	public Usuario updatePassword(Long userId, String newPass) {
		if (userId == null) {
			return null;
		}
		openEntityManager();
		manager.getTransaction().begin();
		Usuario userDB = this.findById(userId);
		userDB.setPassword(newPass);
		manager.getTransaction().commit();
		closeEntityManager();
		return userDB;
	}

	@Override
	public Usuario update(Usuario usuario) {
		if (usuario.getId() == null) {
			return null;
		}
		openEntityManager();
		manager.getTransaction().begin();
		Usuario usuarioManaged = manager.merge(usuario);
		manager.getTransaction().commit();
		closeEntityManager();
		return usuarioManaged;
	}

	@Override
	public void delete(Long id) {

		// jpql
//		manager.getTransaction().begin();
//		String jpql = "DELETE FROM Usuario u WHERE u.id = :id ";
//		Query queryJpql = manager.createQuery(jpql);
//		queryJpql.setParameter("id", id);
//		int num = queryJpql.executeUpdate();
//		System.out.println("Numero entidades borradas " + num);
//		manager.getTransaction().commit();

		// native sql
//		manager.getTransaction().begin();
//		String sql = "DELETE FROM usuario WHERE id = " + id;
//		Query querySql = manager.createNativeQuery(sql);
//		int num = querySql.executeUpdate();
//		System.out.println("Numero entidades borradas " + num);
//		manager.getTransaction().commit();

		// manager remove
		openEntityManager();
		Usuario user = manager.find(Usuario.class, id);
		if (user != null) {
			manager.getTransaction().begin();
			manager.remove(user);
			manager.getTransaction().commit();
		}
		closeEntityManager();
	}




}
