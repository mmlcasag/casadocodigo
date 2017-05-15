package br.com.casadocodigo.loja.model;

import java.math.BigDecimal;

public class HerokuappJson {

	private BigDecimal value;
	
	public HerokuappJson() {
	}
	
	public HerokuappJson(BigDecimal value) {
		this.setValue(value);
	}
	
	public BigDecimal getValue() {
		return this.value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
