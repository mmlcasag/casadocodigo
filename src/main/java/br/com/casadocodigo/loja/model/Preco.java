package br.com.casadocodigo.loja.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import br.com.casadocodigo.loja.enums.TipoPreco;

@Embeddable
public class Preco {
	
	private TipoPreco tipo;
	private BigDecimal valor;
	
	public TipoPreco getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.tipo.name() + " - " + this.valor;
	}
	
}
