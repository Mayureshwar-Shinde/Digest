package net.masdigest.journalApp.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Getter
@Setter
public class JournalEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	@NonNull
	private String title;
	
	private String content;
	private LocalDateTime date;
	
	@ManyToOne
	@JsonIgnoreProperties("journalEntries")
	private User user;
	
	@PrePersist
	public void onCreate() {
		this.date = LocalDateTime.now();
	}
}
