package com.project.comit.entities.account.usr;

import javax.persistence.Entity;

import com.project.comit.entities.account.Account;

@Entity
public class Usr extends Account {

	/* ----- CONSTRUCTORS ----- */
	public Usr() {
		super();
	}

	public Usr(String name, String surname) {
		super(name, surname);
	}

}
