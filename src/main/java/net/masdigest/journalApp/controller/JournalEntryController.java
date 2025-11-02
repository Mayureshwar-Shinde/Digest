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

import net.masdigest.journalApp.entity.JournalEntry;
import net.masdigest.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@GetMapping
	public ResponseEntity<List<JournalEntry>> getAll() {
		List<JournalEntry> journalEntries = journalEntryService.getAll();
		return new ResponseEntity<>(journalEntries, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {
		JournalEntry savedJournalEntry = journalEntryService.saveEntry(journalEntry);
		return new ResponseEntity<>(savedJournalEntry, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JournalEntry> findById(@PathVariable Long id) {
		JournalEntry journalEntry = journalEntryService.get(id);
		return new ResponseEntity<>(journalEntry, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJournalEntry(@PathVariable Long id) {
		journalEntryService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<JournalEntry> updateJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable Long id) {
		JournalEntry updatedJournalEntry = journalEntryService.update(journalEntry, id);
		return ResponseEntity.ok(updatedJournalEntry);
	}
	
	// as per user
	@PostMapping("/user/{username}")
	public ResponseEntity<JournalEntry> createEntryByUser(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
		JournalEntry savedJournalEntry = journalEntryService.saveEntryByUser(journalEntry, username);
		return new ResponseEntity<>(savedJournalEntry, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<List<JournalEntry>> getAllByser(@PathVariable String username) {
		List<JournalEntry> journalEntries = journalEntryService.getAllByUser(username);
		return ResponseEntity.ok(journalEntries);
	}
}
