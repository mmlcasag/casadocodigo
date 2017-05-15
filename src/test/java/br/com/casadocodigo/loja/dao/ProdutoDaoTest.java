package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.builder.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.model.Produto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, ProdutoDao.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDaoTest {
	
	@Autowired
	private ProdutoDao dao;
	
	@Test
	@Transactional
	public void somaPrecosPorTipoTest() {
		List<Produto> impressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
		List<Produto> ebooks = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();
		
		impressos.stream().forEach(dao::grava);
		ebooks.stream().forEach(dao::grava);
		
		BigDecimal valor = dao.somaPrecosPorTipo(TipoPreco.EBOOK);
		Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
	
}