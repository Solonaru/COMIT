package com.project.comit.entities.account.admin;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.comit.entities.account.Account;

@Entity
@JsonDeserialize(as = Admin.class)
public class Admin extends Account {

	/* ----- CONSTRUCTORS ----- */
	public Admin() {
		super();
	}

	public Admin(String name, String surname) {
		super(name, surname, "admin");
	}

}
