package com.project.comit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.comit.entities.account.Account;
import com.project.comit.entities.account.IAccountService;
import com.project.comit.entities.account.admin.Admin;
import com.project.comit.entities.account.usr.Usr;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private DataLogger logger;

	@Autowired
	private IAccountService accountService;

	/* ----- INITIALIZER ----- */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();
	}

	/* ----- METHODS ----- */
	private void loadData() {
		logger.printInfo("Starting data loading...");

		createAndPersistAccounts();

		logger.printInfo("Data successfully loaded.");
	}

	private void createAndPersistAccounts() {
		Account usr1 = new Usr("Agape", "Solonaru", "Agape", "08102004");
		Account usr2 = new Usr("Lilian", "Solonaru", "Lilian", "12061998");
		Account usr3 = new Usr("Viorel", "Solonaru", "Viorel", "17041996");
		Account usr4 = new Admin("Anastasia", "Solonaru", "Anastasia", "02022006");

		this.accountService.insert(usr1);
		this.accountService.insert(usr2);
		this.accountService.insert(usr3);

		this.accountService.deleteById(1L);

		this.accountService.insert(usr4);
	}

}
