package com.project.comit.entities.event.challenge.solution;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.comit.entities.IEntityController;

@RestController
@RequestMapping("/solution")
@CrossOrigin(origins = "http://localhost:4200")
public class SolutionController implements IEntityController<Solution, Long> {

	@Autowired
	private ISolutionService solutionService;

	@RequestMapping(value = "/{solutionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Solution> findById(@PathVariable("solutionId") Long solutionId) {
		return solutionService.findById(solutionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Solution> getAll() {
		return solutionService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Solution solution) {
		solutionService.insert(solution);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Solution solution) {
		solutionService.update(solution);
	}

	@RequestMapping(value = "/delete/{solutionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("solutionId") Long solutionId) {
		solutionService.deleteById(solutionId);
	}
}
