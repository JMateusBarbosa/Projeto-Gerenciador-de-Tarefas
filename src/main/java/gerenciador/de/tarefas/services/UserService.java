package gerenciador.de.tarefas.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gerenciador.de.tarefas.models.User;
import gerenciador.de.tarefas.models.Usuario;
import gerenciador.de.tarefas.repositories.UserRepository;
import jakarta.validation.Valid;



public class UserService {
	@Autowired
	private UserRepository UserRepository;
	private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
	
	public User criar(@Valid User user) {
		user.setSenha("{bcrypt}"+encoder.encode(user.getSenha()));
		return UserRepository.save(user);
	}
	
	public List<User> listar(){
		return UserRepository.findAll();
	}
	
	public User buscarPorCodigo(Long codigo) {
		User User = UserRepository.findById(codigo).orElseThrow();
		return User;
	}
	
	public void excluir(Long codigo) {
		UserRepository.deleteById(codigo);
	}
	
	public User atualizar(Long codigo, User user) {
		User userSalva = UserRepository.
				findById(codigo).orElseThrow();
		if (user.getSenha()!=null) {
			user.setSenha("{bcrypt}"+encoder.encode(user.getSenha()));
			BeanUtils.copyProperties(user, userSalva, "codigo");
			return UserRepository.save(userSalva);
		}
		BeanUtils.copyProperties(user, userSalva,new String[] {"senha", "codigo"});
		return UserRepository.save(userSalva);
	}

}
