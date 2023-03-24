package gerenciador.de.tarefas.resouces;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import gerenciador.de.tarefas.models.User;
import gerenciador.de.tarefas.services.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/Users")
public class UserResouce {
	
	@Autowired
	private UserService UserService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_User') and hasAuthority('SCOPE_write')")
	public ResponseEntity<User> criar(@Valid @RequestBody User User) {
		User UserSalva = UserService.criar(User);
		
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{codigo}").
				buildAndExpand(UserSalva.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(UserSalva);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_User') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<List<User>> listar() {
		List<User> Users = UserService.listar();
		return ResponseEntity.ok().body(Users);
	}
	
	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_User') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<User> buscarPorCodigo(@PathVariable 
			Long codigo){
		User User = UserService.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(User);
	}
	
	@DeleteMapping(value="/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_User') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Void> excluir(@PathVariable Long codigo){
		UserService.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_User') and hasAuthority('SCOPE_write')")
	public ResponseEntity<User> atualizar(@PathVariable Long codigo,
			@Valid @RequestBody User User){
		User UserSalva = UserService.atualizar(codigo,
				User);
		return ResponseEntity.ok().body(UserSalva);
		
	}

}
