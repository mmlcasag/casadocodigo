package br.com.casadocodigo.loja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.dao.UsuarioDao;
import br.com.casadocodigo.loja.model.Authority;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.Usuario;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDao dao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping("/")
	@Cacheable(value="listaProdutosHome")
	public ModelAndView index() {
		List<Produto> produtos = dao.lista();
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/hfoa8hvv9h3fpwesfjp93gpf9ehvholsiefh")
	@Transactional
	@ResponseBody
	public String urlMagicaMaluca() {
		Authority role = new Authority();
		role.setAuthority("ADMIN");
		
		ArrayList<Authority> arrayRoles = new ArrayList<Authority>();
		arrayRoles.add(role);
		
		Usuario admin = new Usuario();
		admin.setUsername("admin@casadocodigo.com.br");
		admin.setPassword("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		admin.setAuthorities(arrayRoles);
		
		usuarioDao.grava(admin);
		
		return "Url Mágica executada";
	}
	
}