package gerenciador.de.tarefas.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;	
	private String observacao;
	
	
	
	@NotNull(message = "Categoria é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;

	@JsonIgnoreProperties({"usuario","ativo"})
	@NotNull(message = "Usuario é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_pessoa")
	private Usuario usuario;

	public Tarefa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tarefa(Long codigo, @NotBlank(message = "Descrição é obrigatório") String descricao, String observacao,
			@NotNull(message = "Categoria é obrigatório") Categoria categoria,
			@NotNull(message = "Usuario é obrigatório") Usuario usuario) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.observacao = observacao;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, codigo, descricao, observacao, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(observacao, other.observacao)
				&& Objects.equals(usuario, other.usuario);
	} 

	
}
