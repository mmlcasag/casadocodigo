package br.com.casadocodigo.loja.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SpringSecurityConfiguration;
import br.com.casadocodigo.loja.conf.WebAppConfigurator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={JPAConfiguration.class, WebAppConfigurator.class, DataSourceConfigurationTest.class, SpringSecurityConfiguration.class})
@ActiveProfiles("test")
public class ProdutosControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void deveRetornarParaHomeComProdutos() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.model()
					.attributeExists("produtos"))
			.andExpect(MockMvcResultMatchers
					.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception {
		mvc.perform(
			MockMvcRequestBuilders.get("/produtos/form")
			.with(SecurityMockMvcRequestPostProcessors
				.user("user@casadocodigo.com.br")
				.password("123456")
				.roles("USUARIO")))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
}