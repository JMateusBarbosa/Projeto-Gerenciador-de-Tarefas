package gerenciador.de.tarefas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gerenciador.de.tarefas.models.Permissao;


@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Long> {

	

	

	

}
