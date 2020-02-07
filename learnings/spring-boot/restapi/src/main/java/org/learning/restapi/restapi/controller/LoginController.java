package org.learning.restapi.restapi.controller;

import org.learning.restapi.restapi.dto.LoginDTO;
import org.learning.restapi.restapi.dto.TokenDTO;
import org.learning.restapi.restapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping(value = "/login")
	public TokenDTO createAuthenticationToken(@RequestBody LoginDTO loginDTO) {
		return loginService.createAuthenticationToken(loginDTO);

	}
	
	@PostMapping(value = "/register")
	public String registerUser(@RequestBody LoginDTO loginDTO) {
		return loginService.saveUser(loginDTO);

	}
	

}
