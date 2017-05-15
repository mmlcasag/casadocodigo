package br.com.casadocodigo.loja.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.casadocodigo.loja.enums.TipoPreco;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	private String descricao;
	
	private Integer paginas;
	
	@DateTimeFormat
	private Calendar lancamento;
	
	@ElementCollection
	private List<Preco> precos = new ArrayList<>();
	
	private String sumarioPath;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getPaginas() {
		return this.paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	
	public Calendar getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Calendar lancamento) {
		this.lancamento = lancamento;
	}
	
	public List<Preco> getPrecos() {
		return this.precos;
	}
	
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	
	public String getSumarioPath() {
		return this.sumarioPath;
	}
	
	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + ", lancamento=" + lancamento + ", sumarioPath=" + sumarioPath + "]";
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + id;
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Produto other = (Produto) obj;
	    if (id != other.id)
	        return false;
	    return true;
	}
	
	public BigDecimal precoPara(TipoPreco tipoPreco) {
		return this.precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst().get().getValor();
	}
	
}