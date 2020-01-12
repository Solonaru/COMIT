package com.project.comit.entities.account;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.comit.entities.account.personaldata.PersonalData;
import com.project.comit.entities.event.challenge.solution.Solution;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonDeserialize(using = AccountDeserializer.class)
public abstract class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_generator")
	@SequenceGenerator(name = "usr_generator", sequenceName = "usr_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "personal_data")
	protected PersonalData personalData;

	@JsonIgnoreProperties(value = "account")
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Solution> solutions;

	protected String type;

	/* ----- CONSTRUCTORS ----- */
	protected Account() {
		super();
	}

	protected Account(String name, String surname, String type) {
		super();
		this.personalData = new PersonalData(name, surname);
		this.type = type;
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}
	
	public PersonalData getPersonalData() {
		return personalData;
	}

	public String getName() {
		return this.getPersonalData().getName();
	}

	public void setName(String name) {
		this.getPersonalData().setName(name);
	}

	public String getSurname() {
		return this.getPersonalData().getSurname();
	}

	public void setSurname(String surname) {
		this.getPersonalData().setSurname(surname);
	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
