package com.project.comit.entities.event.challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.comit.entities.IEntityController;

@RestController
@RequestMapping("/challenge")
@CrossOrigin(origins = "http://localhost:4200")
public class ChallengeController implements IEntityController<Challenge, Long> {

	@Autowired
	private IChallengeService challengeService;

	@RequestMapping(value = "/{challengeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Challenge> findById(@PathVariable("challengeId") Long challengeId) {
		return challengeService.findById(challengeId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Challenge> getAll() {
		return challengeService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Challenge challenge) {
		challengeService.insert(challenge);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Challenge challenge) {
		challengeService.update(challenge);
	}

	@RequestMapping(value = "/delete/{challengeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("challengeId") Long challengeId) {
		challengeService.deleteById(challengeId);
	}
}
