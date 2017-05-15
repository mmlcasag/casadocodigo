package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.dao.UsuarioDao;

@EnableWebMvcSecurity
public class SpringSecurityProperties extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/pagamento/finaliza").hasRole("ADMIN")
			.antMatchers("/produtos/lista").hasRole("ADMIN")
			.antMatchers("/produtos/form").hasRole("ADMIN")
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/produtos/**").permitAll()
			.antMatchers("/carrinho/**").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
