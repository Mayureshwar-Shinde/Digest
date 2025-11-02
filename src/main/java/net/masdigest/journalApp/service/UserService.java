package net.masdigest.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.masdigest.journalApp.entity.User;
import net.masdigest.journalApp.repository.UserRepository;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User get(long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException());
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public User update(User user, long id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		if(user.getUsername() != null && !user.getUsername().equals("")) {
			existingUser.setUsername(user.getUsername());
		}
		if(user.getPassword() != null && !user.getPassword().equals("")) {
			existingUser.setPassword(user.getPassword());
		}
		return userRepository.save(existingUser);
	}
	
	public void delete(long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		userRepository.delete(user);
	}
	
	// Custom methods
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
