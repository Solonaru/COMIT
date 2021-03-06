package com.project.comit.entities.event.challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.comit.entities.IEntityController;
import com.project.comit.entities.event.IEventService;

@RestController
@RequestMapping("/challenge")
@CrossOrigin(origins = "http://localhost:4200")
public class ChallengeController implements IEntityController<Challenge, Long> {

	@Autowired
	private IChallengeService challengeService;
	@Autowired
	private IEventService eventService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{challengeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Challenge> findById(@PathVariable("challengeId") Long challengeId) {
		return challengeService.findById(challengeId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Challenge> getAll() {
		return challengeService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Challenge> getAllByEventId(@PathVariable("eventId") Long eventId) {
		return challengeService.findAllByEvent(this.eventService.findById(eventId).get());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Challenge challenge) {
		challengeService.insert(challenge);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Challenge challenge) {
		challengeService.update(challenge);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{challengeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("challengeId") Long challengeId) {
		challengeService.deleteById(challengeId);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public long getCount() {
		return challengeService.getCount();
	}

}
