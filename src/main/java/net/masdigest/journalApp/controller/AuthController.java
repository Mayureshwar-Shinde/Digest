package net.masdigest.journalApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.masdigest.journalApp.dto.LoginRequestDTO;
import net.masdigest.journalApp.dto.LoginResponseDTO;
import net.masdigest.journalApp.dto.SignupResponseDTO;
import net.masdigest.journalApp.security.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(authService.login(loginRequestDTO));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDTO> signup(@RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(authService.signup(loginRequestDTO));
	}
}
