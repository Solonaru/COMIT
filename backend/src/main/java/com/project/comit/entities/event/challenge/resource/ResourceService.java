package com.project.comit.entities.event.challenge.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService implements IResourceService {

	@Autowired
	private IResourceRepository resourceRepository;

	public Optional<Resource> findById(Long resourceId) {
		return resourceRepository.findById(resourceId);
	}

	public List<Resource> findAll() {
		return (List<Resource>) resourceRepository.findAll();
	}

	public void insert(Resource resource) {
		resourceRepository.save(resource);
	}

	public void update(Resource resource) {
		resourceRepository.save(resource);
	}

	public void deleteById(Long resourceId) {
		resourceRepository.deleteById(resourceId);
	}

}
