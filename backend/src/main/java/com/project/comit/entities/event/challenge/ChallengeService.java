package com.project.comit.entities.event.challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comit.entities.event.Event;

@Service
public class ChallengeService implements IChallengeService {

	@Autowired
	private IChallengeRepository challengeRepository;

	public Optional<Challenge> findById(Long challengeId) {
		return challengeRepository.findById(challengeId);
	}

	public List<Challenge> findAll() {
		return (List<Challenge>) challengeRepository.findAll();
	}
	
	public List<Challenge> findAllByEvent(Event event) {
		return challengeRepository.findAllByEvent(event);
	}

	public void insert(Challenge challenge) {
		challengeRepository.save(challenge);
	}

	public void update(Challenge challenge) {
		challengeRepository.save(challenge);
	}

	public void deleteById(Long challengeId) {
		challengeRepository.deleteById(challengeId);
	}

}
