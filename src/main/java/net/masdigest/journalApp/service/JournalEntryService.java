package net.masdigest.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.masdigest.journalApp.controller.JournalEntryController;
import net.masdigest.journalApp.entity.JournalEntry;
import net.masdigest.journalApp.entity.User;
import net.masdigest.journalApp.repository.JournalEntryRepository;
import net.masdigest.journalApp.repository.UserRepository;


@Service
@Slf4j
public class JournalEntryService {
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public JournalEntry saveEntry(JournalEntry journalEntry) {
		log.info("CREATE - New JournalEntry");
		return journalEntryRepository.save(journalEntry);
	}
	
	public List<JournalEntry> getAll() {
		return journalEntryRepository.findAll();
	}
	
	public JournalEntry get(Long id) {
		return journalEntryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException());
	}
	
	public void delete(long id) {
		JournalEntry journalEntry = journalEntryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		journalEntryRepository.delete(journalEntry);
	}
	
	public JournalEntry update(JournalEntry journalEntry, long id) {
		JournalEntry existingJournalEntry = journalEntryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		if(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("")) {
			existingJournalEntry.setTitle(journalEntry.getTitle());
		}
		if(journalEntry.getContent() != null && !journalEntry.getContent().equals("")) {
			existingJournalEntry.setContent(journalEntry.getContent());
		}
		return journalEntryRepository.save(existingJournalEntry);
	}
	
	// as per user
	public JournalEntry saveEntryByUser(JournalEntry journalEntry, String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		journalEntry.setUser(user);
		return journalEntryRepository.save(journalEntry);
	}
	
	public List<JournalEntry> getAllByUser(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		return user.getJournalEntries();
	}
	
	// update, get-by-id, delete : can be done by others
}
