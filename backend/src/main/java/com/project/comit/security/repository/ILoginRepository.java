package com.project.comit.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.comit.security.model.Login;

public interface ILoginRepository extends CrudRepository<Login, Long> {

	Optional<Login> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
