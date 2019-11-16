package com.project.comit.entities.event.eventstatus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventStatusService implements IEventStatusService {

	@Autowired
	private IEventStatusRepository eventStatusRepository;

	public Optional<EventStatus> findById(Long eventStatusId) {
		return eventStatusRepository.findById(eventStatusId);
	}

	public List<EventStatus> findAll() {
		return (List<EventStatus>) eventStatusRepository.findAll();
	}

	public void insert(EventStatus eventStatus) {
		eventStatusRepository.save(eventStatus);
	}

	public void update(EventStatus eventStatus) {
		eventStatusRepository.save(eventStatus);
	}

	public void deleteById(Long eventStatusId) {
		eventStatusRepository.deleteById(eventStatusId);
	}

}
