package com.project.comit.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.comit.entities.account.admin.Admin;
import com.project.comit.entities.account.admin.IAdminService;
import com.project.comit.entities.account.usr.IUsrService;
import com.project.comit.entities.account.usr.Usr;
import com.project.comit.entities.event.Event;
import com.project.comit.entities.event.IEventService;
import com.project.comit.entities.event.challenge.Challenge;
import com.project.comit.entities.event.challenge.IChallengeService;
import com.project.comit.entities.event.challenge.solution.ISolutionService;
import com.project.comit.entities.event.challenge.solution.Solution;
import com.project.comit.enums.EventType;
import com.project.comit.enums.Technology;
import com.project.comit.security.model.ILoginService;
import com.project.comit.security.model.IRoleService;
import com.project.comit.security.model.Login;
import com.project.comit.security.model.Role;
import com.project.comit.security.model.RoleName;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private DataLogger logger;

	@Autowired
	private ILoginService loginService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IUsrService usrService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IEventService eventService;
	@Autowired
	private IChallengeService challengeService;
	@Autowired
	private ISolutionService solutionService;

	/* ----- INITIALIZER ----- */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadData();
	}

	/* ----- METHODS ----- */
	private void loadData() {
		logger.printInfo("Starting data loading...");

		this.createAndPersistRoles();
		this.createAndPersistAccounts();
		this.createAndPersistEventsAndChallenges();
		this.createAndPersistSolutions();

		logger.printInfo("Data successfully loaded.");
	}

	private void createAndPersistRoles() {
		Role role1 = new Role(RoleName.ROLE_ADMIN);
		Role role2 = new Role(RoleName.ROLE_PM);
		Role role3 = new Role(RoleName.ROLE_USER);

		this.roleService.insert(role1);
		this.roleService.insert(role2);
		this.roleService.insert(role3);
	}

	private void createAndPersistAccounts() {
		Role userRole = roleService.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not find."));

		Login usr1 = new Login("Agape", "Solonaru", "Agape", "agape.solonaru@gmail.com",
				"$2a$12$a/2HNAcTzG5HG/D40RitxOmUWdgzQ5FtgwOrhKDLB.2hutMo5T5N2");
		usr1.addRole(userRole);

		Login usr2 = new Login("Lilian", "Solonaru", "Lilian", "lilian.solonaru@gmail.com",
				"$2a$12$S/qh3ASyIr9TTAxXDVg92.AaVjmuYJe1IajwIS1FBLdUromVUwceO");
		usr2.addRole(userRole);

		Login usr3 = new Login("Viorel", "Solonaru", "Viorel", "viorel.solonaru@gmail.com",
				"$2a$12$r8zH66p/IXowTF9ijteW8e0ETJCn.QaAAruTunXFwMyVR2TTVwivu");
		usr3.addRole(userRole);

		/* TODO: Replace workaround with proper solution */
		Login usr4 = new Login("Anastasia", "Solonaru", "Anastasia", "anastasia.solonaru@gmail.com",
				"$2a$12$MglDew76JD1lpkyKspdk9e63LgseAE20tbez.qbmpKM0a8Jx6qPmy", "Admin");
		usr4.addRole(userRole);
		usr4.addRole(adminRole);

		this.loginService.insert(usr1);
		this.loginService.insert(usr2);
		this.loginService.insert(usr3);
		this.loginService.insert(usr4);
	}

	private void createAndPersistEventsAndChallenges() {
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

	private void createAndPersistSolutions() {
		List<Challenge> challenges = this.challengeService.findAll();
		List<Admin> admins = this.adminService.findAll();
		List<Usr> usrs = this.usrService.findAll();

		Solution admSol1 = new Solution("Sol1", challenges.get(0), admins.get(0));
		Solution admSol2 = new Solution("Sol2", challenges.get(1), admins.get(0));
		Solution admSol3 = new Solution("Sol3", challenges.get(2), admins.get(0));
		Solution admSol4 = new Solution("Sol4", challenges.get(3), admins.get(0));
		Solution admSol5 = new Solution("Sol5", challenges.get(4), admins.get(0));
		Solution admSol6 = new Solution("Sol6", challenges.get(4), admins.get(0));
		Solution admSol7 = new Solution("Sol7", challenges.get(5), admins.get(0));
		Solution admSol8 = new Solution("Sol1", challenges.get(6), admins.get(0));

		solutionService.insert(admSol1);
		solutionService.insert(admSol2);
		solutionService.insert(admSol3);
		solutionService.insert(admSol4);
		solutionService.insert(admSol5);
		solutionService.insert(admSol6);
		solutionService.insert(admSol7);
		solutionService.insert(admSol8);

		Solution usrSol1 = new Solution("UsrSol1", challenges.get(0), usrs.get(0));
		Solution usrSol2 = new Solution("UsrSol2", challenges.get(1), usrs.get(1));
		Solution usrSol3 = new Solution("UsrSol3", challenges.get(2), usrs.get(0));
		Solution usrSol4 = new Solution("UsrSol4", challenges.get(1), usrs.get(2));
		Solution usrSol5 = new Solution("UsrSol5", challenges.get(1), usrs.get(2));
		Solution usrSol6 = new Solution("UsrSol6", challenges.get(2), usrs.get(0));
		Solution usrSol7 = new Solution("UsrSol7", challenges.get(3), usrs.get(1));
		Solution usrSol8 = new Solution("UsrSol8", challenges.get(4), usrs.get(0));
		Solution usrSol9 = new Solution("UsrSol9", challenges.get(5), usrs.get(1));
		Solution usrSol10 = new Solution("UsrSol10", challenges.get(6), usrs.get(1));
		Solution usrSol11 = new Solution("UsrSol10", challenges.get(6), usrs.get(0));
		Solution usrSol12 = new Solution("UsrSol11", challenges.get(1), usrs.get(0));
		Solution usrSol13 = new Solution("UsrSol12", challenges.get(1), usrs.get(2));
		Solution usrSol14 = new Solution("UsrSol13", challenges.get(2), usrs.get(1));
		Solution usrSol15 = new Solution("UsrSol14", challenges.get(2), usrs.get(2));
		Solution usrSol16 = new Solution("UsrSol15", challenges.get(5), usrs.get(1));
		Solution usrSol17 = new Solution("UsrSol16", challenges.get(6), usrs.get(0));
		Solution usrSol18 = new Solution("UsrSol17", challenges.get(6), usrs.get(1));
		Solution usrSol19 = new Solution("UsrSol18", challenges.get(0), usrs.get(2));
		Solution usrSol20 = new Solution("UsrSol19", challenges.get(1), usrs.get(1));

		solutionService.insert(usrSol1);
		solutionService.insert(usrSol2);
		solutionService.insert(usrSol3);
		solutionService.insert(usrSol4);
		solutionService.insert(usrSol5);
		solutionService.insert(usrSol6);
		solutionService.insert(usrSol7);
		solutionService.insert(usrSol8);
		solutionService.insert(usrSol9);
		solutionService.insert(usrSol10);
		solutionService.insert(usrSol11);
		solutionService.insert(usrSol12);
		solutionService.insert(usrSol13);
		solutionService.insert(usrSol14);
		solutionService.insert(usrSol15);
		solutionService.insert(usrSol16);
		solutionService.insert(usrSol17);
		solutionService.insert(usrSol18);
		solutionService.insert(usrSol19);
		solutionService.insert(usrSol20);
	}

	private LocalDate toDate(String stringDate) {
		DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(stringDate, frm);
	}

}
