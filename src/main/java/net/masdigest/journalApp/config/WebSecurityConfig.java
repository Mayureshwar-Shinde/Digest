package net.masdigest.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import net.masdigest.journalApp.security.JwtAuthFilter;


@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	// private final PasswordEncoder passwordEncoder;
	private final JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf(csrfConfig -> csrfConfig.disable())
			.sessionManagement(sessionConfig -> 
					sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/journal/**", "/auth/**").permitAll()
					//.requestMatchers("/admin/**").authenticated()
					//.requestMatchers("/employee/**").hasRole("EMP")
					//.requestMatchers("/users/**").hasAnyRole("user", "visitor")
					.anyRequest().authenticated()
			)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
			// .formLogin(Customizer.withDefaults());
		return httpSecurity.build();
	}
	
}



