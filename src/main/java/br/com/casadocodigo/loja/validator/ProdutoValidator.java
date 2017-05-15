package br.com.casadocodigo.loja.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.model.Produto;

public class ProdutoValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "titulo", "field.required.produto.titulo");
		ValidationUtils.rejectIfEmpty(e, "descricao", "field.required.produto.descricao");
		ValidationUtils.rejectIfEmpty(e, "paginas", "field.required.produto.paginas");
		
		Produto produto = (Produto) obj;
		if (produto.getPaginas() != null) {
			if (produto.getPaginas() < 0) {
				e.rejectValue("paginas", "negative.value");
			} else if (produto.getPaginas() > 9999) {
				e.rejectValue("paginas", "value.too.long");
			}
		}
	}
	
}
