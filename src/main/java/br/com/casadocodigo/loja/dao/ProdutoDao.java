package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.model.Produto;

@Repository
@Transactional
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void grava(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> lista() {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos preco", Produto.class).getResultList();
	}
	
	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos preco where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}
	
	public BigDecimal somaPrecosPorTipo (TipoPreco tipo) {
		TypedQuery<BigDecimal> query = manager.createQuery(" select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipo ", BigDecimal.class);
		
		query.setParameter("tipo", tipo);
		
		return query.getSingleResult();
	}
	
}