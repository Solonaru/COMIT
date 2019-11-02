package com.project.comit.entities.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.comit.entities.event.challenge.Challenge;
import com.project.comit.enums.EventType;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
	@SequenceGenerator(name = "event_generator", sequenceName = "event_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private EventType eventType;
	@JsonIgnoreProperties(value = "event")
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Challenge> challenges;

	/* ----- CONSTRUCTORS ----- */
	public Event() {
		super();
		this.challenges = new ArrayList<Challenge>();
	}

	public Event(String name, LocalDate startDate, LocalDate endDate, EventType eventType) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventType = eventType;
		this.challenges = new ArrayList<Challenge>();
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public List<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	/* ----- METHODS ----- */
	public void addChallenge(Challenge challenge) {
		this.challenges.add(challenge);
		challenge.setEvent(this);
	}

	public void removeChallenge(Challenge challenge) {
		this.challenges.remove(challenge);
		challenge.setEvent(null);
	}

}
