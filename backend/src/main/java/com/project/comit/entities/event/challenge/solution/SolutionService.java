package com.project.comit.entities.event.challenge.solution;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService implements ISolutionService {

	@Autowired
	private ISolutionRepository solutionRepository;

	public Optional<Solution> findById(Long solutionId) {
		return solutionRepository.findById(solutionId);
	}

	public List<Solution> findAll() {
		return (List<Solution>) solutionRepository.findAll();
	}

	public void insert(Solution solution) {
		solutionRepository.save(solution);
	}

	public void update(Solution solution) {
		solutionRepository.save(solution);
	}

	public void deleteById(Long solutionId) {
		solutionRepository.deleteById(solutionId);
	}

}
