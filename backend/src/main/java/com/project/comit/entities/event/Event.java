package com.project.comit.entities.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.comit.entities.event.challenge.Challenge;
import com.project.comit.entities.event.challenge.technology.Technology;
import com.project.comit.entities.event.eventtype.EventType;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
	@SequenceGenerator(name = "event_generator", sequenceName = "event_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "name", unique = true)
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	@ManyToOne
	private EventType eventType;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_technology", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "tehchnology_id"))
	private Set<Technology> technologies;
	@JsonIgnoreProperties(value = "event")
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Challenge> challenges;
	@ElementCollection
	private Set<String> tags;

	/* ----- CONSTRUCTORS ----- */
	public Event() {
		super();
		this.technologies = new HashSet<Technology>();
		this.challenges = new ArrayList<Challenge>();
		this.tags = new HashSet<String>();
	}

	public Event(String name, String description, LocalDate startDate, LocalDate endDate, EventType eventType) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventType = eventType;
		this.technologies = new HashSet<Technology>();
		this.challenges = new ArrayList<Challenge>();
		this.tags = new HashSet<String>();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(Technology... technologies) {
		this.technologies = new HashSet<Technology>(Arrays.asList(technologies));
	}

	public List<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(String... tags) {
		this.tags = new HashSet<String>(Arrays.asList(tags));
	}

	/* ----- METHODS ----- */
	public void addChallenge(Challenge challenge) {
		this.challenges.add(challenge);
		
		challenge.getTechnologies().forEach(technology -> {
			this.addTechnology(technology);
		});
		
		challenge.setEvent(this);
	}

	public void removeChallenge(Challenge challenge) {
		this.challenges.remove(challenge);
		challenge.setEvent(null);
	}
	
	public void addTechnology(Technology technology) {
		this.technologies.add(technology);
	}

}
