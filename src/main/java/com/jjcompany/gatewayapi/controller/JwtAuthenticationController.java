package com.jjcompany.gatewayapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjcompany.gatewayapi.config.JwtTokenUtil;
import com.jjcompany.gatewayapi.model.JwtRequest;
import com.jjcompany.gatewayapi.model.JwtResponse;
import com.jjcompany.gatewayapi.model.User;
import com.jjcompany.gatewayapi.model.UserRepository;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		User user = userRepository.findByEmailAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		if(user == null) {
			throw new RuntimeException("Usuario nao exise");
		}
		
		final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
		return ResponseEntity.ok(new JwtResponse(token));
	}
}