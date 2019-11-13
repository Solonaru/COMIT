package com.project.comit.entities.event.eventtype;

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

@RestController
@RequestMapping("/eventType")
@CrossOrigin(origins = "http://localhost:4200")
public class EventTypeController implements IEntityController<EventType, Long> {

	@Autowired
	private IEventTypeService eventTypeService;

	@RequestMapping(value = "/{eventTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<EventType> findById(@PathVariable("eventTypeId") Long eventTypeId) {
		return eventTypeService.findById(eventTypeId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EventType> getAll() {
		return eventTypeService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody EventType eventType) {
		eventTypeService.insert(eventType);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody EventType eventType) {
		eventTypeService.update(eventType);
	}

	@RequestMapping(value = "/delete/{eventTypeId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("eventTypeId") Long eventTypeId) {
		eventTypeService.deleteById(eventTypeId);
	}
}
