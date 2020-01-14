package com.project.comit.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

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
import com.project.comit.entities.event.challenge.resource.IResourceService;
import com.project.comit.entities.event.challenge.resource.Resource;
import com.project.comit.entities.event.challenge.skilllevel.ISkillLevelService;
import com.project.comit.entities.event.challenge.skilllevel.SkillLevel;
import com.project.comit.entities.event.challenge.solution.ISolutionService;
import com.project.comit.entities.event.challenge.solution.Solution;
import com.project.comit.entities.event.challenge.technology.ITechnologyService;
import com.project.comit.entities.event.challenge.technology.Technology;
import com.project.comit.entities.event.eventstatus.EventStatus;
import com.project.comit.entities.event.eventstatus.IEventStatusService;
import com.project.comit.entities.event.eventtype.EventType;
import com.project.comit.entities.event.eventtype.IEventTypeService;
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
	private IEventTypeService eventTypeService;
	@Autowired
	private ITechnologyService technologyService;
	@Autowired
	private ISkillLevelService skillLevelService;
	@Autowired
	private IEventStatusService eventStatusService;
	@Autowired
	private IResourceService resourceService;

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

		this.createAndPersistEventTypes();
		this.createAndPersistTechnologies();
		this.createAndPersistSkillLevels();
		this.createAndPersistEventStatuses();
		this.createAndPersistResources();

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

		Login usr4 = new Login("Anastasia", "Solonaru", "Anastasia", "anastasia.solonaru@gmail.com",
				"$2a$12$MglDew76JD1lpkyKspdk9e63LgseAE20tbez.qbmpKM0a8Jx6qPmy", "Admin");
		usr4.addRole(userRole);
		usr4.addRole(adminRole);

		this.loginService.insert(usr1);
		this.loginService.insert(usr2);
		this.loginService.insert(usr3);
		this.loginService.insert(usr4);
	}

	private void createAndPersistEventTypes() {
		EventType eventType1 = new EventType("IT Competition");
		EventType eventType2 = new EventType("Hackaton");
		EventType eventType3 = new EventType("Web challenge");

		this.eventTypeService.insert(eventType1);
		this.eventTypeService.insert(eventType2);
		this.eventTypeService.insert(eventType3);
	}

	private void createAndPersistTechnologies() {
		Technology tech1 = new Technology("Java");
		Technology tech2 = new Technology("JavaScript");
		Technology tech3 = new Technology("Python");

		this.technologyService.insert(tech1);
		this.technologyService.insert(tech2);
		this.technologyService.insert(tech3);
	}

	private void createAndPersistSkillLevels() {
		SkillLevel skillLevel1 = new SkillLevel("Junior");
		SkillLevel skillLevel2 = new SkillLevel("Medior");
		SkillLevel skillLevel3 = new SkillLevel("Senior");

		this.skillLevelService.insert(skillLevel1);
		this.skillLevelService.insert(skillLevel2);
		this.skillLevelService.insert(skillLevel3);
	}

	private void createAndPersistEventStatuses() {
		EventStatus eventStatus1 = new EventStatus("Completed");
		EventStatus eventStatus2 = new EventStatus("Ongoing");

		this.eventStatusService.insert(eventStatus1);
		this.eventStatusService.insert(eventStatus2);
	}

	private void createAndPersistResources() {
		Resource resource1 = new Resource("MDN", "developer.mozilla.org", "https://developer.mozilla.org/en-US/",
				"MDN Web Docs, previously Mozilla Developer Network and formerly Mozilla Developer Center, is the official Mozilla website for development documentation of web standards and Mozilla projects.");
		Resource resource2 = new Resource("FreeCodeCamp", "freecodecamp.org", "https://www.freecodecamp.org/",
				"Learn to code with free online courses, programming projects, and interview preparation for developer jobs.");
		Resource resource3 = new Resource("TutorialsPoint", "tutorialspoint.com",
				"https://www.tutorialspoint.com/index.htm",
				"Tutorialspoint.com is a dedicated website to provide quality online education in the domains of Computer Science, Information Technology, Programming Languages, and other Engineering as well as Management subjects.");
		Resource resource4 = new Resource("EggHead", "egghead.io", "https://egghead.io/",
				"EggHead is a group of working web development professionals and open source contributors that provide you with concise, information dense video courses on the best tools in the industry.");
		Resource resource5 = new Resource("DevDocs", "devdocs.io", "https://devdocs.io/",
				"Fast, offline, and free documentation browser for developers. Search 100+ docs in one web app.");

		this.resourceService.insert(resource1);
		this.resourceService.insert(resource2);
		this.resourceService.insert(resource3);
		this.resourceService.insert(resource4);
		this.resourceService.insert(resource5);
	}

	private void createAndPersistEventsAndChallenges() {
		List<EventType> eventTypes = this.eventTypeService.findAll();
		List<Technology> technologies = this.technologyService.findAll();
		List<SkillLevel> skillLevels = this.skillLevelService.findAll();
		List<Resource> resources = this.resourceService.findAll();

		Challenge challenge1 = new Challenge("Addition",
				"Create a function in a language of your choice which takes as a parameter two variables and returns their sum.",
				this.getRnFromList(skillLevels));
		challenge1.setTechnologies(this.getRnFromList(technologies), this.getRnFromList(technologies));
		challenge1.setResources(this.getRnFromList(resources), this.getRnFromList(resources),
				this.getRnFromList(resources));
		challenge1.setTips("Add the two numbers", "Use plus sign");
		Challenge challenge2 = new Challenge("Substraction",
				"Create a function that takes two parameters and returns the value of the first minus the value of the second parameter.",
				this.getRnFromList(skillLevels));
		challenge2.setTechnologies(this.getRnFromList(technologies));
		challenge2.setResources(this.getRnFromList(resources), this.getRnFromList(resources));
		challenge2.setTips("Substract the two numbers", "Use minus sign");
		Challenge challenge3 = new Challenge("Multiply",
				"Create a function that takes two parameters and multiplies them. As a challenge you can create a function that takes multiple parameters and multiplies all of them.",
				this.getRnFromList(skillLevels));
		challenge3.setTechnologies(this.getRnFromList(technologies), this.getRnFromList(technologies));
		challenge3.setResources(this.getRnFromList(resources), this.getRnFromList(resources),
				this.getRnFromList(resources));
		challenge3.setTips("Multiply the two numbers", "Use multiplication sign");
		Challenge challenge4 = new Challenge("Divide",
				"Create a function that takes two parameters and divides them.",
				this.getRnFromList(skillLevels));
		challenge4.setTechnologies(this.getRnFromList(technologies), this.getRnFromList(technologies),
				this.getRnFromList(technologies));
		challenge4.setResources(this.getRnFromList(resources), this.getRnFromList(resources),
				this.getRnFromList(resources));
		challenge4.setTips("Divide the two numbers", "Use division sign");
		Challenge challenge5 = new Challenge("Simple calculator",
				"Design a simple calculator which performs the four basic operations, as addition, substraction, multiplication and division.",
				this.getRnFromList(skillLevels));
		challenge5.setTechnologies(this.getRnFromList(technologies), this.getRnFromList(technologies),
				this.getRnFromList(technologies), this.getRnFromList(technologies));
		challenge5.setResources(this.getRnFromList(resources));
		challenge5.setTips("Implement simple calculator with +, -, * and / operations");
		Challenge challenge6 = new Challenge("Complex calculator",
				"Design a complex calculator, which besides the addition, substraction, multiplication and division, has complex mathematical operations.",
				this.getRnFromList(skillLevels));
		challenge6.setTechnologies(this.getRnFromList(technologies), this.getRnFromList(technologies));
		challenge6.setResources(this.getRnFromList(resources), this.getRnFromList(resources),
				this.getRnFromList(resources));
		challenge6.setTips("Design complex calculator", "Allow user to perform factorial", "Allow user to insert fractions");
		Challenge challenge7 = new Challenge("Simply code",
				"Design a complex calculator, which besides the addition, substraction, multiplication and division, has complex mathematical operations.",
				this.getRnFromList(skillLevels));
		challenge7.setTechnologies(this.getRnFromList(technologies));
		challenge7.setResources(this.getRnFromList(resources), this.getRnFromList(resources));
		challenge7.setTips("Code factorial function", "Code simple calculator");

		Event event1 = new Event("Programming Marathon",
				"Competitive programming is a mind sport usually held over the Internet or a local network, involving participants trying to program according to provided specifications. Contestants are referred to as sport programmers.",
				toDate("10-01-2020"), toDate("20-02-2020"), this.getRnFromList(eventTypes));
		event1.setTags("Programming", "Begginer", "Math");
		event1.addChallenge(challenge1);
		event1.addChallenge(challenge2);
		Event event2 = new Event("Programmers Week",
				"This year, Programmers' Week will highlight the intersection of business and technology. Activities will include 4 days of technology discussions, the 256 Conference that brings together top national and international speakers,",
				toDate("15-10-2019"), toDate("15-12-2019"), this.getRnFromList(eventTypes));
		event2.setTags("Programming", "Intermediate", "Code");
		event2.addChallenge(challenge3);
		event2.addChallenge(challenge4);
		event2.addChallenge(challenge5);
		Event event3 = new Event("Just Code It",
				"EU Code Week is a grassroots initiative which aims to bring coding and digital literacy ... Just pick a topic and a target audience and add your activity to the map.",
				toDate("15-10-2019"), toDate("25-11-2019"), this.getRnFromList(eventTypes));
		event3.setTags("Programming", "Java", "Algorithms");
		event3.addChallenge(challenge6);
		Event event4 = new Event("Love IT or Leave IT",
				"Lorem ipsum dolor sit amet consectetur adipisicing elit. Quibusdam aperiam deserunt cumque delectus suscipit "
						+ "natus repellendus a mollitia. Quia sed dolorem dolorum. Ratione, magnam ut eius fugiat voluptate sed "
						+ "voluptatibus!",
				toDate("01-01-2020"), toDate("01-04-2020"), this.getRnFromList(eventTypes));
		event4.setTags("Programming", "Begginer", "Math");
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

		Solution admSol1 = new Solution("Sol1", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol2 = new Solution("Sol2", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol3 = new Solution("Sol3", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol4 = new Solution("Sol4", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol5 = new Solution("Sol5", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol6 = new Solution("Sol6", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol7 = new Solution("Sol7", this.getRnFromList(challenges), this.getRnFromList(admins));
		Solution admSol8 = new Solution("Sol1", this.getRnFromList(challenges), this.getRnFromList(admins));

		solutionService.insert(admSol1);
		solutionService.insert(admSol2);
		solutionService.insert(admSol3);
		solutionService.insert(admSol4);
		solutionService.insert(admSol5);
		solutionService.insert(admSol6);
		solutionService.insert(admSol7);
		solutionService.insert(admSol8);

		Solution usrSol1 = new Solution("UsrSol1", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol2 = new Solution("UsrSol2", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol3 = new Solution("UsrSol3", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol4 = new Solution("UsrSol4", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol5 = new Solution("UsrSol5", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol6 = new Solution("UsrSol6", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol7 = new Solution("UsrSol7", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol8 = new Solution("UsrSol8", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol9 = new Solution("UsrSol9", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol10 = new Solution("UsrSol10", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol11 = new Solution("UsrSol10", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol12 = new Solution("UsrSol11", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol13 = new Solution("UsrSol12", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol14 = new Solution("UsrSol13", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol15 = new Solution("UsrSol14", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol16 = new Solution("UsrSol15", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol17 = new Solution("UsrSol16", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol18 = new Solution("UsrSol17", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol19 = new Solution("UsrSol18", this.getRnFromList(challenges), this.getRnFromList(usrs));
		Solution usrSol20 = new Solution("UsrSol19", this.getRnFromList(challenges), this.getRnFromList(usrs));

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

	private Integer getRnInteger(Integer min, Integer max) {
		return new Random().nextInt(max - min + 1) + min;
	}

	private <T> T getRnFromList(List<T> elements) {
		return elements.get(getRnInteger(0, elements.size() - 1));
	}

}
