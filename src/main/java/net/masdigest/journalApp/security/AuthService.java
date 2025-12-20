package net.masdigest.journalApp.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.masdigest.journalApp.dto.LoginRequestDTO;
import net.masdigest.journalApp.dto.LoginResponseDTO;
import net.masdigest.journalApp.dto.SignupResponseDTO;
import net.masdigest.journalApp.entity.User;
import net.masdigest.journalApp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final AuthUtil authUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
		);
		
		User user = (User) authentication.getPrincipal();
		String token = authUtil.generateAccessToken(user);
		
		return new LoginResponseDTO(token, user.getId());
	}
	
	
	public SignupResponseDTO signup(LoginRequestDTO loginRequestDTO) {
		User user = userRepository.findByUsername(loginRequestDTO.getUsername()).orElse(null);
		if(user != null) {
			throw new IllegalArgumentException("User already exist");
		}
		
		user = userRepository.save(User.builder()
				.username(loginRequestDTO.getUsername())
				.password(passwordEncoder.encode(loginRequestDTO.getPassword()))
				.build()
		);
		
		return new SignupResponseDTO(user.getId(), user.getUsername());
	}
}
