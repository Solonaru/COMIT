package com.project.comit.entities.event.challenge.solution;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.comit.entities.account.Account;
import com.project.comit.entities.event.challenge.Challenge;
import com.project.comit.enums.SolutionType;

@Entity
public class Solution {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solution_generator")
	@SequenceGenerator(name = "solution_generator", sequenceName = "solution_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String content;
	@JsonIgnoreProperties(value = "solutions")
	@ManyToOne
	@JoinColumn(name = "challenge", updatable = false, nullable = false)
	private Challenge challenge;
	@JsonIgnoreProperties(value = "solutions")
	@ManyToOne
	@JoinColumn(name = "account", updatable = false, nullable = false)
	private Account account;
	private SolutionType solutionType;

	/* ----- CONSTRUCTORS ----- */
	public Solution() {
		super();
	}

	public Solution(Long id, String content, Challenge challenge, Account account) {
		super();
		this.id = id;
		this.content = content;
		this.challenge = challenge;
		this.account = account;
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public SolutionType getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(SolutionType solutionType) {
		this.solutionType = solutionType;
	}

}
