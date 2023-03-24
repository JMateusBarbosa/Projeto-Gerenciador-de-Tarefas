package gerenciador.de.tarefas.repositories.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import gerenciador.de.tarefas.dto.ResumoUsuarioDto;
import gerenciador.de.tarefas.repositories.filters.UsuarioFilter;


public interface UsuarioRepositoryQuery {

	public Page<ResumoUsuarioDto> filtrar(
			UsuarioFilter usuarioFilter, Pageable pageable);
}
