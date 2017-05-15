package br.com.casadocodigo.loja.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String password;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<Authority>();
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<Authority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
