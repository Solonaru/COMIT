package com.project.comit.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.comit.security.model.Role;
import com.project.comit.security.model.RoleName;

public interface IRoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleName);

}
