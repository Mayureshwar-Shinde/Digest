package net.masdigest.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.masdigest.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	
	private Map<Long, JournalEntry> journalEntries = new HashMap<>();
	
	@GetMapping
	public List<JournalEntry> getAll() {
		return new ArrayList<>(journalEntries.values());
	}
	
	@PostMapping
	public String createEntry(@RequestBody JournalEntry journalEntry) {
		journalEntries.put(journalEntry.getId(), journalEntry);
		return journalEntry.getTitle();
	}
	
	@GetMapping("/{id}")
	public JournalEntry findById(@PathVariable Long id) {
		return journalEntries.get(id);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteJournalEntry(@PathVariable Long id) {
		journalEntries.remove(id);
		return true;
	}
	
	@PutMapping("/{id}")
	public JournalEntry updateJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable Long id) {
		journalEntries.put(id, journalEntry);
		return journalEntries.get(id);
	}
}
