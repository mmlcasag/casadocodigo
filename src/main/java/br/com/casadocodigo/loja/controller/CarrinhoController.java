package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.model.Carrinho;
import br.com.casadocodigo.loja.model.CarrinhoItem;
import br.com.casadocodigo.loja.model.Produto;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private Carrinho carrinho;
	
	@RequestMapping("/listas")
	public ModelAndView lista() {
		return new ModelAndView("carrinho/lista");
	}
	
	@RequestMapping("/adicionar")
	public ModelAndView adiciona(Integer produtoId, TipoPreco tipoPreco) {
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		return new ModelAndView("redirect:/carrinho/lista");
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ModelAndView remove(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho/lista");
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(produtoId);
		return new CarrinhoItem(produto, tipoPreco);
	}
	
}
