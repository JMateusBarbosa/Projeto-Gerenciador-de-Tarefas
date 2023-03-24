package gerenciador.de.tarefas.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gerenciador.de.tarefas.dto.ResumoUsuarioDto;
import gerenciador.de.tarefas.models.Usuario;
import gerenciador.de.tarefas.repositories.CategoriaRepository;
import gerenciador.de.tarefas.repositories.TarefaRepository;
import gerenciador.de.tarefas.repositories.UsuarioRepository;
import gerenciador.de.tarefas.repositories.filters.UsuarioFilter;



@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TarefaRepository TarefaRepository;
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<ResumoUsuarioDto> resumir(UsuarioFilter usuarioFilter,
			Pageable pageable){
		return usuarioRepository.filtrar(usuarioFilter, pageable);
	}
	public Usuario criar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorCodigo(Long codigo) {
		Usuario usuario = usuarioRepository.findById(codigo).orElseThrow();
		return usuario;
	}
	
	public void excluir(Long codigo) {
		usuarioRepository.deleteById(codigo);
	}
	
	public Usuario atualizarPropriedadeAtivo(Long codigo,
			Boolean ativo) {
		Usuario usuarioSalva = usuarioRepository.findById(codigo).
				orElseThrow();
		usuarioSalva.setAtivo(ativo);
		return usuarioRepository.save(usuarioSalva);
	}
	
	public Usuario atualizar(Long codigo, Usuario usuario) {
		Usuario usuarioSalva = usuarioRepository.
				findById(codigo).orElseThrow();
		BeanUtils.copyProperties(usuario, usuarioSalva, "codigo");
		return usuarioRepository.save(usuarioSalva);
	}

}
