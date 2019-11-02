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
import com.project.comit.entities.account.login.Login;
import com.project.comit.entities.account.personaldata.PersonalData;
import com.project.comit.entities.event.challenge.solution.Solution;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_generator")
	@SequenceGenerator(name = "usr_generator", sequenceName = "usr_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

	@JsonIgnoreProperties(value = "account")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Login login;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "personal_data")
	private PersonalData personalData;

	@JsonIgnoreProperties(value = "account")
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Solution> solutions;

	/* ----- CONSTRUCTORS ----- */
	protected Account() {
		super();
	}

	protected Account(String name, String surname, String username, String password) {
		super();
		this.login = new Login(username, password, this);
		this.personalData = new PersonalData(name, surname);
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getName() {
		return this.personalData.getName();
	}

	public void setName(String name) {
		this.personalData.setName(name);
	}

	public String getSurname() {
		return this.personalData.getSurname();
	}

	public void setSurname(String surname) {
		this.personalData.setSurname(surname);
	}

	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

}
