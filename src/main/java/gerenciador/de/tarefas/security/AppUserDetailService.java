package gerenciador.de.tarefas.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gerenciador.de.tarefas.models.User;
import gerenciador.de.tarefas.repositories.UserRepository;


@Service
public class AppUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository UserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> UserOptional = UserRepository.findByEmail(email);
		User User = UserOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuário " + email + " não encontrado"));
		return new UserSistema(User, getPermissoes(User));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(User User) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		User.getPermissoes()
				.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));

		return authorities;
	}

}
