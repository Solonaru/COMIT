package com.project.comit.entities.event.challenge.skilllevel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillLevelService implements ISkillLevelService {

	@Autowired
	private ISkillLevelRepository skillLevelRepository;

	public Optional<SkillLevel> findById(Long skillLevelId) {
		return skillLevelRepository.findById(skillLevelId);
	}

	public List<SkillLevel> findAll() {
		return (List<SkillLevel>) skillLevelRepository.findAll();
	}

	public void insert(SkillLevel skillLevel) {
		skillLevelRepository.save(skillLevel);
	}

	public void update(SkillLevel skillLevel) {
		skillLevelRepository.save(skillLevel);
	}

	public void deleteById(Long skillLevelId) {
		skillLevelRepository.deleteById(skillLevelId);
	}

}
