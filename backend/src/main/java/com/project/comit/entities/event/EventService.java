package com.project.comit.entities.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

	@Autowired
	private IEventRepository eventRepository;

	public Optional<Event> findById(Long eventId) {
		return eventRepository.findById(eventId);
	}

	public List<Event> findAll() {
		return (List<Event>) eventRepository.findAll();
	}

	public void insert(Event event) {
		eventRepository.save(event);
	}

	public void update(Event event) {
		eventRepository.save(event);
	}

	public void deleteById(Long eventId) {
		eventRepository.deleteById(eventId);
	}
	
	public long getCount() {
		return this.eventRepository.count();
	}

}
