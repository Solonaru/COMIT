package com.project.comit.entities.event.challenge.skilllevel;

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
@RequestMapping("/skillLevel")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillLevelController implements IEntityController<SkillLevel, Long> {

	@Autowired
	private ISkillLevelService skillLevelService;

	@RequestMapping(value = "/{skillLevelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<SkillLevel> findById(@PathVariable("skillLevelId") Long skillLevelId) {
		return skillLevelService.findById(skillLevelId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SkillLevel> getAll() {
		return skillLevelService.findAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insert(@RequestBody SkillLevel skillLevel) {
		skillLevelService.insert(skillLevel);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody SkillLevel skillLevel) {
		skillLevelService.update(skillLevel);
	}

	@RequestMapping(value = "/delete/{skillLevelId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("skillLevelId") Long skillLevelId) {
		skillLevelService.deleteById(skillLevelId);
	}
}
