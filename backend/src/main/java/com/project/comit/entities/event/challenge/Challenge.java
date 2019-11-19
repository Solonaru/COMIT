package com.project.comit.entities.event.challenge;

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
import com.project.comit.entities.event.Event;
import com.project.comit.entities.event.challenge.resource.Resource;
import com.project.comit.entities.event.challenge.skilllevel.SkillLevel;
import com.project.comit.entities.event.challenge.solution.Solution;
import com.project.comit.entities.event.challenge.technology.Technology;

@Entity
public class Challenge {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_generator")
	@SequenceGenerator(name = "challenge_generator", sequenceName = "challenge_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String title;
	private String content;
	@ElementCollection
	private List<String> restrictions;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "challenge_technology", joinColumns = @JoinColumn(name = "challenge_id"), inverseJoinColumns = @JoinColumn(name = "tehchnology_id"))
	private Set<Technology> technologies;
	@ManyToOne
	private SkillLevel skillLevel;
	@JsonIgnoreProperties(value = "challenges")
	@ManyToOne
	@JoinColumn(name = "building", updatable = false, nullable = false)
	private Event event;
	@JsonIgnoreProperties(value = "challenge")
	@OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Solution> solutions;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "challenge_resource", joinColumns = @JoinColumn(name = "challenge_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
	private Set<Resource> resources;
	@ElementCollection
	private Set<String> tags;

	/* ----- CONSTRUCTORS ----- */
	public Challenge() {
		super();
		this.restrictions = new ArrayList<String>();
		this.technologies = new HashSet<Technology>();
		this.solutions = new ArrayList<Solution>();
		this.tags = new HashSet<String>();
	}

	public Challenge(String title, String content, SkillLevel skillLevel) {
		super();
		this.title = title;
		this.content = content;
		this.restrictions = new ArrayList<String>();
		this.technologies = new HashSet<Technology>();
		this.skillLevel = skillLevel;
		this.solutions = new ArrayList<Solution>();
		this.tags = new HashSet<String>();
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<String> restrictions) {
		this.restrictions = restrictions;
	}

	public Set<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(Technology... technologies) {
		this.technologies = new HashSet<Technology>(Arrays.asList(technologies));
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Resource... resources) {
		this.resources = new HashSet<Resource>(Arrays.asList(resources));
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(String... tags) {
		this.tags = new HashSet<String>(Arrays.asList(tags));
	}

}
