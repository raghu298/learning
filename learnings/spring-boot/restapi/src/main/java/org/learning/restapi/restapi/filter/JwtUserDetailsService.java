package org.learning.restapi.restapi.filter;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.learning.restapi.restapi.dto.LoginDTO;
import org.learning.restapi.restapi.entities.Login;
import org.learning.restapi.restapi.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private ILoginRepository repositroy;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*if ("javainuse".equals(username)) {
			return new User("javainuse", "$2a$10$K4gYuv4JIRCPBf8bMD1kGOOl29FGv9z2GfGX7cqKxVR5Joj8FsqwO",
					new ArrayList<>());
		} else {
			
			throw new UsernameNotFoundException("User not found with username: " + username);
		}*/
		
		Login login = repositroy.findByUsername(username);
		if (login == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(login.getUsername(), login.getPassword(),
				new ArrayList<>());
		
	}
	
	public Login save(LoginDTO loginDTO) {
		Login nlogin=new Login();
		nlogin.setUsername(loginDTO.getUsername());
		nlogin.setPassword(bcryptEncoder.encode(loginDTO.getPassword()));
		return nlogin;
	}

}
