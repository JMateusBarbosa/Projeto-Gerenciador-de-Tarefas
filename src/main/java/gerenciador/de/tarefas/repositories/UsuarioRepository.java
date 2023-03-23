package gerenciador.de.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gerenciador.de.tarefas.models.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
