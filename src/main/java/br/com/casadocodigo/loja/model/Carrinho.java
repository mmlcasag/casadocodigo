package br.com.casadocodigo.loja.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.enums.TipoPreco;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class Carrinho implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
	
	public void setItens(Map<CarrinhoItem, Integer> itens) {
		this.itens = itens;
	}
	
	public Integer getQuantidade() {
	    return itens.values().stream().reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}
	
	public Integer getQuantidade(CarrinhoItem carrinhoItem) {
		if (!itens.containsKey(carrinhoItem)) {
	        itens.put(carrinhoItem, 0);
	    }
		return itens.get(carrinhoItem);
	}
	
	public void add(CarrinhoItem carrinhoItem) {
		itens.put(carrinhoItem, this.getQuantidade(carrinhoItem) + 1);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(this.getTotal(item));
		}
		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		this.itens.remove(new CarrinhoItem(produto, tipoPreco));
	}
	
	public void limpar() {
		for (CarrinhoItem item : this.getItens()) {
			this.remover(item.getProduto().getId(), item.getTipoPreco());
		}
	}
	
}