package gerenciador.de.tarefas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gerenciador.de.tarefas.models.User;



public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);

}
