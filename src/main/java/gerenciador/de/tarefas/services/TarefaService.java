package gerenciador.de.tarefas.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gerenciador.de.tarefas.dto.ResumoTarefaDto;
import gerenciador.de.tarefas.models.Categoria;
import gerenciador.de.tarefas.models.Tarefa;
import gerenciador.de.tarefas.models.Usuario;
import gerenciador.de.tarefas.repositories.CategoriaRepository;
import gerenciador.de.tarefas.repositories.TarefaRepository;
import gerenciador.de.tarefas.repositories.UsuarioRepository;
import gerenciador.de.tarefas.repositories.filters.TarefaFilter;
import gerenciador.de.tarefas.services.exceptions.UsuarioInativaException;



@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<ResumoTarefaDto> resumir(TarefaFilter tarefaFilter,
			Pageable pageable){
		return tarefaRepository.filtrar(tarefaFilter, pageable);
	}
	
	public Tarefa criar(Tarefa tarefa) {
		Usuario Usuario = UsuarioRepository.findById(
				tarefa.getUsuario().getCodigo()).orElseThrow();
		if(!Usuario.isAtivo()) {
			throw new UsuarioInativaException();
		}
		Categoria categoria = categoriaRepository.findById(
				tarefa.getCategoria().getCodigo()).orElseThrow();
		return tarefaRepository.save(tarefa);
	}
	
	public List<Tarefa> listar(){
		return tarefaRepository.findAll();
	}
	
	public Tarefa buscarPorCodigo(Long codigo) {
		Tarefa tarefa = tarefaRepository.findById(codigo).orElseThrow();
		return tarefa;
	}
	
	public void excluir(Long codigo) {
		tarefaRepository.deleteById(codigo);
	}
	
	public Tarefa atualizar(Long codigo, Tarefa tarefa) {
		Tarefa tarefaSalvo = tarefaRepository.
				findById(codigo).orElseThrow();
		Usuario Usuario = UsuarioRepository.findById(
				tarefa.getUsuario().getCodigo()).orElseThrow();
		if(!Usuario.isAtivo()) {
			throw new UsuarioInativaException();
		}
		Categoria categoria = categoriaRepository.findById(
				tarefa.getCategoria().getCodigo()).orElseThrow();
		BeanUtils.copyProperties(tarefa, tarefaSalvo, "codigo");
		return tarefaRepository.save(tarefaSalvo);
	}
}
