package com.project.comit.entities.event.eventtype;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService implements IEventTypeService {

	@Autowired
	private IEventTypeRepository eventTypeRepository;

	public Optional<EventType> findById(Long eventTypeId) {
		return eventTypeRepository.findById(eventTypeId);
	}

	public List<EventType> findAll() {
		return (List<EventType>) eventTypeRepository.findAll();
	}

	public void insert(EventType eventType) {
		eventTypeRepository.save(eventType);
	}

	public void update(EventType eventType) {
		eventTypeRepository.save(eventType);
	}

	public void deleteById(Long eventTypeId) {
		eventTypeRepository.deleteById(eventTypeId);
	}

}
