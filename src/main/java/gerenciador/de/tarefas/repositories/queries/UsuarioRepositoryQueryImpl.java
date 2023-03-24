package gerenciador.de.tarefas.repositories.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import gerenciador.de.tarefas.dto.ResumoUsuarioDto;
import gerenciador.de.tarefas.models.Usuario;
import gerenciador.de.tarefas.repositories.filters.UsuarioFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class UsuarioRepositoryQueryImpl implements UsuarioRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ResumoUsuarioDto> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<ResumoUsuarioDto> criteria = builder.createQuery(ResumoUsuarioDto.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		criteria.select(builder.construct(ResumoUsuarioDto.class, root.get("codigo"), root.get("nome"),
				root.get("ativo")));
		
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}
		
		TypedQuery<ResumoUsuarioDto> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable,
				total(usuarioFilter));
	}
	
	

	private Long total(UsuarioFilter usuarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<ResumoUsuarioDto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistroPorPagina);
	}
	private Predicate[] criarRestricoes(UsuarioFilter usuarioFilter, CriteriaBuilder builder,
			Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
