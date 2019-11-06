package com.project.comit.security.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.comit.security.repository.IRoleRepository;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;

	public Optional<Role> findById(Long roleId) {
		return roleRepository.findById(roleId);
	}

	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}

	public void insert(Role role) {
		roleRepository.save(role);
	}

	public void update(Role role) {
		roleRepository.save(role);
	}

	public void deleteById(Long roleId) {
		roleRepository.deleteById(roleId);
	}

	public Optional<Role> findByName(RoleName roleName) {
		return this.roleRepository.findByName(roleName);
	}

}
