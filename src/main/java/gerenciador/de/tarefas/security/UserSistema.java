package gerenciador.de.tarefas.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonInclude(value = Include.NON_EMPTY)
public class UserSistema extends User {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("user")
	private User user;

	public UserSistema(gerenciador.de.tarefas.models.User user2,Collection<? extends GrantedAuthority> authorities) {
		super(user2.getEmail(), user2.getSenha(), authorities);
	}
	
	public User getUser() {
		return user;
	}
}