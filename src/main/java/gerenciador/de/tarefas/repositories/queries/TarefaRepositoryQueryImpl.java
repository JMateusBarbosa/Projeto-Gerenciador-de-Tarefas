package gerenciador.de.tarefas.repositories.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import gerenciador.de.tarefas.dto.ResumoTarefaDto;
import gerenciador.de.tarefas.models.Tarefa;
import gerenciador.de.tarefas.repositories.filters.TarefaFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TarefaRepositoryQueryImpl implements TarefaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ResumoTarefaDto> filtrar(TarefaFilter TarefaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<ResumoTarefaDto> criteria = builder.createQuery(ResumoTarefaDto.class);
		Root<Tarefa> root = criteria.from(Tarefa.class);
		
		criteria.select(builder.construct(ResumoTarefaDto.class, root.get("codigo"), root.get("descricao"),
				root.get("dataVencimento"), root.get("dataPagamento"), root.get("valor"), root.get("tipo"),
				root.get("categoria").get("nome"), root.get("pessoa").get("nome")));
		
		Predicate[] predicates = criarRestricoes(TarefaFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}
		
		TypedQuery<ResumoTarefaDto> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable,
				total(TarefaFilter));
	}
	
	private Long total(TarefaFilter TarefaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Tarefa> root = criteria.from(Tarefa.class);

		Predicate[] predicates = criarRestricoes(TarefaFilter, builder, root);
		if (predicates.length > 0) {
			criteria.where(predicates);
		}

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<ResumoTarefaDto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistroPorPagina);
	}

	private Predicate[] criarRestricoes(TarefaFilter TarefaFilter, CriteriaBuilder builder,
			Root<Tarefa> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(TarefaFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get("descricao")),
					"%" + TarefaFilter.getDescricao().toLowerCase() + "%"));
		}

		if (TarefaFilter.getDataFinalDaEntrega() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataFinalDaEntrega"), TarefaFilter.getDataFinalDaEntrega()));
		}

		if (TarefaFilter.getDataEntrega() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataEntrega"), TarefaFilter.getDataEntrega()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
