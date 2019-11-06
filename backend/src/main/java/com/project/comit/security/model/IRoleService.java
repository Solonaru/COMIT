package com.project.comit.security.model;

import java.util.Optional;

import com.project.comit.entities.IEntityService;

public interface IRoleService extends IEntityService<Role, Long> {

	Optional<Role> findByName(RoleName roleName);

}
