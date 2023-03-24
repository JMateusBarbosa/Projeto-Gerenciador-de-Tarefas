package gerenciador.de.tarefas.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gerenciador.de.tarefas.models.enums.Tipotarefa;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	@NotNull(message = "Data final da entrega é obrigatório")
	private LocalDate dataFinalDaEntrega;
	private LocalDate dataEntrega;
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo é obrigatório")
	private Tipotarefa tipo;
	
	@NotNull(message = "Categoria é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;

	
	@NotNull(message = "Usuario é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private Usuario usuario;

	public Tarefa() {
		super();
		
	}

	public Tarefa(Long codigo, @NotBlank(message = "Descrição é obrigatório") String descricao,
			@NotNull(message = "Data final da entrega é obrigatório") LocalDate dataFinalDaEntrega,
			LocalDate dataEntrega, String observacao, @NotNull(message = "Tipo é obrigatório") Tipotarefa tipo,
			@NotNull(message = "Categoria é obrigatório") Categoria categoria,
			@NotNull(message = "Usuario é obrigatório") Usuario usuario) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataFinalDaEntrega = dataFinalDaEntrega;
		this.dataEntrega = dataEntrega;
		this.observacao = observacao;
		this.tipo = tipo;
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

	public LocalDate getDataFinalDaEntrega() {
		return dataFinalDaEntrega;
	}

	public void setDataFinalDaEntrega(LocalDate dataFinalDaEntrega) {
		this.dataFinalDaEntrega = dataFinalDaEntrega;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Tipotarefa getTipo() {
		return tipo;
	}

	public void setTipo(Tipotarefa tipo) {
		this.tipo = tipo;
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
		return Objects.hash(codigo);
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
		return Objects.equals(codigo, other.codigo);
	}
	
	
}
