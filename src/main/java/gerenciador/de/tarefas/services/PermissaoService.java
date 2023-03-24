package gerenciador.de.tarefas.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gerenciador.de.tarefas.repositories.PermissaoRepository;

import gerenciador.de.tarefas.models.Permissao;



@Service
public class PermissaoService {
	
	@Autowired
	private PermissaoRepository PermissaoRepository;
	
	public Permissao criar(Permissao Permissao) {
		return PermissaoRepository.save(Permissao);
	}
	
	public List<Permissao> listar(){
		return PermissaoRepository.findAll();
	}
	
	public Permissao buscarPorCodigo(Long codigo) {
		Permissao Permissao = PermissaoRepository.findById(codigo).orElseThrow();
		return Permissao;
	}
	
	public void excluir(Long codigo) {
		PermissaoRepository.deleteById(codigo);
	}
	
	public Permissao atualizar(Long codigo, Permissao Permissao) {
		Permissao PermissaoSalva = PermissaoRepository.
				findById(codigo).orElseThrow();
		BeanUtils.copyProperties(Permissao, PermissaoSalva, "codigo");
		return PermissaoRepository.save(PermissaoSalva);
	}
	

}
