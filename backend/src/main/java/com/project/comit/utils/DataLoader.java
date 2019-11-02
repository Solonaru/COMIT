package com.project.comit.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.comit.entities.account.Account;
import com.project.comit.entities.account.IAccountService;
import com.project.comit.entities.account.admin.Admin;
import com.project.comit.entities.account.usr.Usr;
import com.project.comit.entities.event.Event;
import com.project.comit.entities.event.IEventService;
import com.project.comit.entities.event.challenge.Challenge;
import com.project.comit.enums.EventType;
import com.project.comit.enums.Technology;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private DataLogger logger;

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IEventService eventService;

	/* ----- INITIALIZER ----- */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();
	}

	/* ----- METHODS ----- */
	private void loadData() {
		logger.printInfo("Starting data loading...");

		this.createAndPersistAccounts();
		this.createAndPersistEvents();

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
		this.accountService.insert(usr4);
	}

	private void createAndPersistEvents() {
		Challenge challenge1 = new Challenge("Code 2 + 2");
		challenge1.setTechnologies(Technology.Java, Technology.JavaScript);
		Challenge challenge2 = new Challenge("Code 2 - 2");
		challenge1.setTechnologies(Technology.JavaScript);
		Challenge challenge3 = new Challenge("Code 2 * 2");
		challenge1.setTechnologies(Technology.Java, Technology.Phyton);
		Challenge challenge4 = new Challenge("Code 2 / 2");
		challenge1.setTechnologies(Technology.JavaScript, Technology.CSharp, Technology.PHP);
		Challenge challenge5 = new Challenge("Code a simple calculator");
		challenge1.setTechnologies(Technology.Java, Technology.JavaScript, Technology.CSharp, Technology.CPlusPlus);
		Challenge challenge6 = new Challenge("Code a complex calculator");
		challenge1.setTechnologies(Technology.Java, Technology.JavaScript);
		Challenge challenge7 = new Challenge("Code somehting");
		challenge1.setTechnologies(Technology.Java);

		Event event1 = new Event("Event A", toDate("01-10-2019"), toDate("01-11-2019"), EventType.HACKATHON);
		event1.addChallenge(challenge1);
		event1.addChallenge(challenge2);
		Event event2 = new Event("Event B", toDate("15-10-2019"), toDate("01-11-2019"), EventType.PROGRAMMING);
		event2.addChallenge(challenge3);
		event2.addChallenge(challenge4);
		event2.addChallenge(challenge5);
		Event event3 = new Event("Event C", toDate("15-10-2019"), toDate("15-11-2019"), EventType.HACKATHON);
		event3.addChallenge(challenge6);
		Event event4 = new Event("Event D", toDate("01-10-2019"), toDate("01-11-2019"), EventType.WEB_CHALLENGE);
		event4.addChallenge(challenge7);

		this.eventService.insert(event1);
		this.eventService.insert(event2);
		this.eventService.insert(event3);
		this.eventService.insert(event4);
	}

	private LocalDate toDate(String stringDate) {
		DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(stringDate, frm);
	}

}
