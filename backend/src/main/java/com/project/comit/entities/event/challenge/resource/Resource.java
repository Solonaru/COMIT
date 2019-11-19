package com.project.comit.entities.event.challenge.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_generator")
	@SequenceGenerator(name = "resource_generator", sequenceName = "resource_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String name;
	private String urlShort;
	private String urlFull;
	private String content;

	/* ----- CONSTRUCTORS ----- */
	public Resource() {
		super();
	}

	public Resource(String name, String urlShort, String urlFull, String content) {
		super();
		this.name = name;
		this.urlShort = urlShort;
		this.urlFull = urlFull;
		this.content = content;
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

	public String getUrlShort() {
		return urlShort;
	}

	public void setUrlShort(String urlShort) {
		this.urlShort = urlShort;
	}

	public String getUrlFull() {
		return urlFull;
	}

	public void setUrlFull(String urlFull) {
		this.urlFull = urlFull;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
