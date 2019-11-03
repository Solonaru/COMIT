package com.project.comit.entities.account.admin;

import javax.persistence.Entity;

import com.project.comit.entities.account.Account;

@Entity
public class Admin extends Account {

	/* ----- CONSTRUCTORS ----- */
	public Admin() {
		super();
	}

	public Admin(String name, String surname, String username, String email, String password) {
		super(name, surname, username, email, password);
	}

}
