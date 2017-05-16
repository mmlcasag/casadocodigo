package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.model.Usuario;

@Repository
public class UsuarioDao implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuarios = manager.createQuery(" select u from Usuario u where u.username = :username ", Usuario.class)
				.setParameter("username", username)
				.getResultList();
		
		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado");
		}
		
		return usuarios.get(0);
	}

	public void grava(Usuario usuario) {
		manager.persist(usuario);
	}
	
}