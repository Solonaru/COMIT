package com.project.comit.entities.event.eventstatus;

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
@RequestMapping("/eventStatus")
@CrossOrigin(origins = "http://localhost:4200")
public class EventStatusController implements IEntityController<EventStatus, Long> {

	@Autowired
	private IEventStatusService eventStatusService;

	@RequestMapping(value = "/{eventStatusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<EventStatus> findById(@PathVariable("eventStatusId") Long eventStatusId) {
		return eventStatusService.findById(eventStatusId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EventStatus> getAll() {
		return eventStatusService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody EventStatus eventStatus) {
		eventStatusService.insert(eventStatus);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody EventStatus eventStatus) {
		eventStatusService.update(eventStatus);
	}

	@RequestMapping(value = "/delete/{eventStatusId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("eventStatusId") Long eventStatusId) {
		eventStatusService.deleteById(eventStatusId);
	}
}
