package gerenciador.de.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gerenciador.de.tarefas.models.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
