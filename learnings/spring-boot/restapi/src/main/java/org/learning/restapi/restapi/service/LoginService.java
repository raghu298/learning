package org.learning.restapi.restapi.service;

import org.learning.restapi.restapi.dto.LoginDTO;
import org.learning.restapi.restapi.dto.TokenDTO;
import org.learning.restapi.restapi.entities.Login;
import org.learning.restapi.restapi.exception.custom.ValidationException;
import org.learning.restapi.restapi.filter.JwtUserDetailsService;
import org.learning.restapi.restapi.repository.ILoginRepository;
import org.learning.restapi.restapi.repository.IUserRepository;
import org.learning.restapi.restapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class LoginService {

	@Autowired
	private ILoginRepository repository;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private JwtUserDetailsService service;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;


	public TokenDTO createAuthenticationToken(LoginDTO loginDTO) {
		UserDetails userDetail = loadUserDetails(loginDTO.getUsername());
		authenticate(loginDTO.getUsername(), loginDTO.getPassword());

		final String token = tokenUtil.generateToken(userDetail);
		return new TokenDTO(token);

	}
	
	public String saveUser(LoginDTO user)  {
		Login login=new Login();
		login.setUsername(user.getUsername());
		login.setPassword(bcryptEncoder.encode(user.getPassword()));
		repository.save(login); 
		return "Success";
	}
	
	public UserDetails loadUserDetails(String name) {
		return service.loadUserByUsername(name);
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new ValidationException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new ValidationException("INVALID_CREDENTIALS");
		}
	}
}
