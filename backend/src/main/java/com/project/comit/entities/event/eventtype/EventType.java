package com.project.comit.entities.event.eventtype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class EventType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	@Column(length = 30)
	private String name;

	/* ----- CONSTRUCTORS ----- */
	public EventType() {
		super();
	}

	public EventType(String name) {
		super();
		this.name = name;
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String type) {
		this.name = type;
	}

}
