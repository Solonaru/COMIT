package com.project.comit.entities.event;

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
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController implements IEntityController<Event, Long> {

	@Autowired
	private IEventService eventService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Event> findById(@PathVariable("eventId") Long eventId) {
		return eventService.findById(eventId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Event> getAll() {
		return eventService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Event event) {
		eventService.insert(event);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Event event) {
		eventService.update(event);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/delete/{eventId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("eventId") Long eventId) {
		eventService.deleteById(eventId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public long getCount() {
		return eventService.getCount();
	}

}
