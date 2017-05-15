package br.com.casadocodigo.loja.controller;

import javax.persistence.NoResultException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView trataException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("mensagem", "O sistema disparou uma exceção não tratada. Contate o administrador do sistema.");
		modelAndView.addObject("detalhes", exception);
		return modelAndView;
	}
	
	@ExceptionHandler(NoResultException.class)
	public ModelAndView trataNoResultException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("mensagem", "O produto requisitado não foi encontrado no sistema");
		modelAndView.addObject("detalhes", exception);
		return modelAndView;
	}
	
}