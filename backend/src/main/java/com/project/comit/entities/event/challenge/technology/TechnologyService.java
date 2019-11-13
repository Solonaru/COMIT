package com.project.comit.entities.event.challenge.technology;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService implements ITechnologyService {

	@Autowired
	private ITechnologyRepository technologyRepository;

	public Optional<Technology> findById(Long technologyId) {
		return technologyRepository.findById(technologyId);
	}

	public List<Technology> findAll() {
		return (List<Technology>) technologyRepository.findAll();
	}

	public void insert(Technology technology) {
		technologyRepository.save(technology);
	}

	public void update(Technology technology) {
		technologyRepository.save(technology);
	}

	public void deleteById(Long technologyId) {
		technologyRepository.deleteById(technologyId);
	}

}
