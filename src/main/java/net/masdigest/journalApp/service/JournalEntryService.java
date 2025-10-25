package net.masdigest.journalApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.masdigest.journalApp.entity.JournalEntry;
import net.masdigest.journalApp.repository.JournalEntryRepository;


@Service
@Slf4j
public class JournalEntryService {
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
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
}
