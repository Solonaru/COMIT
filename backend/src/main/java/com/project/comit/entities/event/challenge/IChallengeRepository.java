package com.project.comit.entities.event.challenge;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.comit.entities.event.Event;

public interface IChallengeRepository extends CrudRepository<Challenge, Long> {
	public List<Challenge> findAllByEvent(Event event);
}
