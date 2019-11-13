package com.project.comit.entities.event.challenge.technology;

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
@RequestMapping("/technology")
@CrossOrigin(origins = "http://localhost:4200")
public class TechnologyController implements IEntityController<Technology, Long> {

	@Autowired
	private ITechnologyService technologyService;

	@RequestMapping(value = "/{technologyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Technology> findById(@PathVariable("technologyId") Long technologyId) {
		return technologyService.findById(technologyId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Technology> getAll() {
		return technologyService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Technology technology) {
		technologyService.insert(technology);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Technology technology) {
		technologyService.update(technology);
	}

	@RequestMapping(value = "/delete/{technologyId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("technologyId") Long technologyId) {
		technologyService.deleteById(technologyId);
	}
}
