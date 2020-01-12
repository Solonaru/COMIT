package com.project.comit.entities.event.challenge;

import java.util.List;

import com.project.comit.entities.IEntityService;
import com.project.comit.entities.event.Event;

public interface IChallengeService extends IEntityService<Challenge, Long> {
	
	public List<Challenge> findAllByEvent(Event event);
	
	public long getCount();
	
}
