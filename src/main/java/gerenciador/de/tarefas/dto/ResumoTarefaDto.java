package gerenciador.de.tarefas.dto;

import java.time.LocalDate;

import gerenciador.de.tarefas.models.enums.Tipotarefa;


public class ResumoTarefaDto {

	private Long codigo;
	private String descricao;
	private LocalDate dataFinalDaEntrega;
	private LocalDate dataEntrega;
	private Tipotarefa tipo;
	private String categoria;
	private String usuario;
	public ResumoTarefaDto() {
		super();
		
	}
	public ResumoTarefaDto(Long codigo, String descricao, LocalDate dataFinalDaEntrega, LocalDate dataEntrega,
			Tipotarefa tipo, String categoria, String usuario) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataFinalDaEntrega = dataFinalDaEntrega;
		this.dataEntrega = dataEntrega;
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
	public Tipotarefa getTipo() {
		return tipo;
	}
	public void setTipo(Tipotarefa tipo) {
		this.tipo = tipo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
