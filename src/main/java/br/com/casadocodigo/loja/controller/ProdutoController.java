package br.com.casadocodigo.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.utils.FileSaver;
import br.com.casadocodigo.loja.validator.ProdutoValidator;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoDao dao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidator());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping("/grava")
	@CacheEvict(value="listaProdutosHome", allEntries=true)
	public ModelAndView grava(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return form(produto);
		}
		
		if (sumario != null) {
			String arquivo = fileSaver.write("uploads", sumario);
			produto.setSumarioPath(arquivo);
		}
		
		dao.grava(produto);
		
		redirect.addFlashAttribute("mensagem","Operação realizada com sucesso!");
		return new ModelAndView("redirect:lista");
	}
	
	@RequestMapping("/lista")
	public ModelAndView lista() {
		List<Produto> produtos = dao.lista();
		
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		Produto produto = dao.find(id);
		
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
}