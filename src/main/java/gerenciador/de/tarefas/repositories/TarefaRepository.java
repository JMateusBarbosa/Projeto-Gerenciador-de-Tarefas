package gerenciador.de.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gerenciador.de.tarefas.models.Tarefa;
import gerenciador.de.tarefas.repositories.queries.TarefaRepositoryQuery;

@Repository
public interface TarefaRepository extends   
	JpaRepository<Tarefa, Long>, TarefaRepositoryQuery{

}
