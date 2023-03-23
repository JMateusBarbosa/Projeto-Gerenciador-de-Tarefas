package gerenciador.de.tarefas.repositories.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import gerenciador.de.tarefas.dto.ResumoTarefaDto;
import gerenciador.de.tarefas.repositories.filters.TarefaFilter;



public interface TarefaRepositoryQuery {
	
	public Page<ResumoTarefaDto> filtrar(
			TarefaFilter tarefaFilter, Pageable pageable);

}
