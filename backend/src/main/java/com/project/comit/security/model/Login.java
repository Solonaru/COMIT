package com.project.comit.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.comit.entities.account.Account;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "name")
	private String name;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@NaturalId
	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "eamil", nullable = false, unique = true)
	private String email;

	@NotBlank
	@Size(min = 6, max = 100)
	@Column(name = "password", nullable = false)
	private String password;

	@JsonIgnoreProperties(value = "login")
	@OneToOne
	@JoinColumn(name = "account")
	private Account account;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	/* ----- CONSTRUCTORS ----- */
	public Login() {
		super();
	}

	public Login(String name, String username, String email, String password, Account account) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.account = account;
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Account getAccount() {
		return account;
	}

}
