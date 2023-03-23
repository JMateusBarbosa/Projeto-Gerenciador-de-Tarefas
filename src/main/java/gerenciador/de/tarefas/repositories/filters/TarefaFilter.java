package gerenciador.de.tarefas.repositories.filters;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TarefaFilter {
	
private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFinalDaEntrega;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataEntrega;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataFinalDaEntrega() {
		return dataFinalDaEntrega;
	}

	public void setDataVencimentoDe(LocalDate dataFinalDaEntrega) {
		this.dataFinalDaEntrega = dataFinalDaEntrega;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
