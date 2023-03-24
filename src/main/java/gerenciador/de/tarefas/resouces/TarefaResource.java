package gerenciador.de.tarefas.resouces;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gerenciador.de.tarefas.dto.ResumoTarefaDto;
import gerenciador.de.tarefas.models.Tarefa;
import gerenciador.de.tarefas.repositories.filters.TarefaFilter;
import gerenciador.de.tarefas.services.TarefaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/Tarefas")
public class TarefaResource {
	
	@Autowired
	private TarefaService TarefaService;
	
	

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TAREFA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa Tarefa) {
		Tarefa TarefaSalva = TarefaService.criar(Tarefa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(TarefaSalva.getCodigo()).toUri();

		return ResponseEntity.created(uri).body(TarefaSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Page<ResumoTarefaDto>> resumir(TarefaFilter TarefaFilter,
			Pageable pageable) {
		Page<ResumoTarefaDto> resumos = TarefaService.resumir(TarefaFilter, pageable);
		return ResponseEntity.ok().body(resumos);
	}

	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TAREFA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Tarefa> buscarPorCodigo(@PathVariable Long codigo) {
		Tarefa Tarefa = TarefaService.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(Tarefa);
	}

	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TAREFA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
		TarefaService.excluir(codigo);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TAREFA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Tarefa Tarefa) {
		Tarefa TarefaSalva = TarefaService.atualizar(codigo, Tarefa);
		return ResponseEntity.ok().body(TarefaSalva);

	}

}
