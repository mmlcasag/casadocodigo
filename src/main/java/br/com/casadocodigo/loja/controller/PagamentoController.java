package br.com.casadocodigo.loja.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.model.Carrinho;
import br.com.casadocodigo.loja.model.HerokuappJson;
import br.com.casadocodigo.loja.model.Usuario;

@Controller
@RequestMapping("/pagamento")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private MailSender sender;
	
	@RequestMapping(value="/finaliza", method=RequestMethod.POST)
	public Callable<ModelAndView> finaliza(@AuthenticationPrincipal Usuario usuario, RedirectAttributes redirect) {
		return() -> {
			String retorno = this.enviaPagamentoHerokuapp();
			enviaEmailPedidoFinalizado(usuario);
			redirect.addFlashAttribute("mensagem", retorno);
			carrinho.limpar();
			return new ModelAndView("redirect:/");
		};
	}
	
	private String enviaPagamentoHerokuapp() {
		try {
			String uri = "http://book-payment.herokuapp.com/payment";
			return rest.postForObject(uri, new HerokuappJson(carrinho.getTotal()), String.class);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return "Valor maior que o permitido";
		}
	}
	
	private void enviaEmailPedidoFinalizado(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("compras@casadocodigo.com.br");
		//email.setTo(usuario.getUsername());
		email.setTo("fabibr@gmail.com");
		email.setSubject("Pedido Finalizado");
		email.setText("Compra aprovada com sucesso no valor de R$ " + carrinho.getTotal());
		sender.send(email);
	}
	
}
