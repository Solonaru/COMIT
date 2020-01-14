package com.project.comit.entities.event.challenge.solution;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.comit.entities.IEntityController;
import com.project.comit.entities.account.Account;
import com.project.comit.entities.account.IAccountService;

@RestController
@RequestMapping("/solution")
@CrossOrigin(origins = "http://localhost:4200")
public class SolutionController implements IEntityController<Solution, Long> {

	@Autowired
	private ISolutionService solutionService;
	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = "/{solutionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Solution> findById(@PathVariable("solutionId") Long solutionId) {
		return solutionService.findById(solutionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Solution> getAll() {
		return solutionService.findAll();
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody Solution solution) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account account = this.accountService.findByPersonalData_Name(auth.getName());
		Solution sol = new Solution(solution.getContent(), solution.getChallenge(), account);
		solutionService.insert(sol);
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
