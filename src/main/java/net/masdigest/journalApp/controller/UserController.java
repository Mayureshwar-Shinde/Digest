package net.masdigest.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.masdigest.journalApp.entity.User;
import net.masdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userService.getAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		User user = userService.get(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		User newUser = userService.create(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable long id) {
		User updatedUser = userService.update(user, id);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getByUsername(@PathVariable String username) {
		User user = userService.getByUsername(username);
		return ResponseEntity.ok(user);
	}
}
