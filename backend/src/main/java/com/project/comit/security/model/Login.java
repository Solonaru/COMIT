package com.project.comit.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.project.comit.entities.account.Account;
import com.project.comit.entities.account.admin.Admin;
import com.project.comit.entities.account.usr.Usr;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NaturalId
	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "eamil", nullable = false, unique = true)
	private String email;

	@NotBlank
	@Size(min = 3, max = 30)
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@NotBlank
	@Size(min = 6, max = 100)
	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "account")
	private Account account;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	/* ----- CONSTRUCTORS ----- */
	public Login() {
		super();
	}

	public Login(String name, String surname, String username, String email, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.account = new Usr(name, surname);
	}

	/* FOR TESTING PURPOSES: Temporary constructor to persist administrators */
	public Login(String name, String surname, String username, String email, String password, String adminWorkaround) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.account = new Admin(name, surname);
	}

	/* ----- GETTERS & SETTERS ----- */
	public Long getId() {
		return id;
	}

	public String getName() {
		return this.account.getName();
	}

	public void setName(String name) {
		this.account.setName(name);
	}

	public String getSurname() {
		return this.account.getSurname();
	}

	public void setSurname(String surname) {
		this.account.setSurname(surname);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
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

	/* ----- GETTERS & SETTERS ----- */
	public void addRole(Role role) {
		if (!this.roles.contains(role)) {
			this.roles.add(role);
		}
	}

	public void removeRole(Role role) {
		if (this.roles.contains(role)) {
			this.roles.remove(role);
		}
	}

}
